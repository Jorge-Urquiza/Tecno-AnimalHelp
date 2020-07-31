/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Email;

import correos.veterinaria.software.Email.TemplateMail;
import correos.veterinaria.protocolos.ClienteSMTP;
import correos.veterinaria.procesador.*;
import correos.veterinaria.software.Negocio.VeterinarioNegocio;
import correos.veterinaria.utils.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class MailVeterinario extends TemplateMail {

    VeterinarioNegocio veterinarioNegocio = new VeterinarioNegocio();

    @Override
    public void registrar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
      
        // Sino, ejecutar el comando
        analex.Avanzar();
        // Atributos
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String apellido = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int ci = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String celular = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String direccion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        veterinarioNegocio.registrar(nombre, apellido, ci, celular, direccion);

        ClienteSMTP.sendMail(destinatario, "REGISTRAR VETERINARIO",Cadenas.REGISTRO_SUCCESS);
    }

    @Override
    public void modificar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Sino, ejecutar el comando
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        DefaultTableModel veterinario = veterinarioNegocio.getVeterinario(id);
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
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String direccion = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(veterinario.getValueAt(0, 5));
        veterinarioNegocio.modificar(id,nombre, apellido, ci, celular, direccion);
        ClienteSMTP.sendMail(destinatario, "MODIFICAR VETERINARIO", Cadenas.MODIFICAR_SUCCESS);

    }

    @Override
    public void eliminar(Analex analex, String destinatario) throws Exception {
       // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        veterinarioNegocio.eliminar(id);
        ClienteSMTP.sendMail(destinatario, "ELIMINAR VETERINARIO", Cadenas.ELIMINAR_SUCCESS);  
    }

    @Override
    public void listar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();
        String Head[] = {"ID","NOMBRE", "APELLIDO", "CI", "CELULAR", "DIRECCION"};
        String Cabecera = "ANIMALHELP - LISTA DE VETERINARIOS";
        Mensaje message = Utils.dibujarTablaHtml(veterinarioNegocio.getVeterinarios(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println(Cadenas.SUCCESSFULL_MAIL);
        } else {
            System.out.println(Cadenas.FAILED_MAIL);
        }
    }

}
