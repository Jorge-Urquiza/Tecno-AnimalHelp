/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Email;

import correos.veterinaria.software.Email.TemplateMail;
import correos.veterinaria.protocolos.ClienteSMTP;
import correos.veterinaria.procesador.*;
import correos.veterinaria.software.Negocio.*;
import correos.veterinaria.utils.Cadenas;
import correos.veterinaria.utils.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class MailCliente extends TemplateMail {

    ClienteNegocio clienteNegocio = new ClienteNegocio();

    @Override
    public void registrar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
       
        analex.Avanzar();
        // Atributos
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String apellidos = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int ci = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String celular = Utils.quitarComillas(analex.Preanalisis().getToStr());
        clienteNegocio.registrar(nombre, apellidos, ci, celular);
        ClienteSMTP.sendMail(destinatario, "REGISTRAR CLIENTE", Cadenas.REGISTRO_SUCCESS);
    }

    @Override
    public void modificar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();
        
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        DefaultTableModel veterinario = clienteNegocio.getCliente(id);
        // verifica si existe al id
        if (veterinario.getRowCount() == 0) {
            ClienteSMTP.sendMail(destinatario, Cadenas.ERROR_ID, Cadenas.NO_EXISTS_ID);
            return;
        }
        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String nombre = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                // posicion de la fila del defaultTableModel
                : String.valueOf(veterinario.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String apellido = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(veterinario.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int ci = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                // posicion de la fila del defaultTableModel
                : (int) veterinario.getValueAt(0, 3);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String celular = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(veterinario.getValueAt(0, 4));
        clienteNegocio.modificar(id, nombre, apellido, ci, celular);
        ClienteSMTP.sendMail(destinatario, "MODIFICAR CLIENTE", Cadenas.MODIFICAR_SUCCESS);

    }

    @Override
    public void eliminar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        clienteNegocio.eliminar(id);
        ClienteSMTP.sendMail(destinatario, "ELIMINAR CLIENTE", Cadenas.ELIMINAR_SUCCESS);
    }

    @Override
    public void listar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();
        
        String Head[] = {"ID", "NOMBRE", "APELLIDO", "CI", "CELULAR"};
        String Cabecera = "ANIMALHELP - LISTA DE CLIENTES";
        Mensaje message = Utils.dibujarTablaHtml(clienteNegocio.getClientes(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println(Cadenas.SUCCESSFULL_MAIL);
        } else {
            System.out.println(Cadenas.FAILED_MAIL);
        }
    }

}
