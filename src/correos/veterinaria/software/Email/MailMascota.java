/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Email;


import correos.veterinaria.protocolos.ClienteSMTP;
import correos.veterinaria.procesador.*;
import correos.veterinaria.software.Negocio.*;
import correos.veterinaria.utils.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class MailMascota {

    MascotaNegocio mascotaNegocio = new MascotaNegocio();

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
        String raza = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String color = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int tipo = (int) analex.Preanalisis().getAtributo();
        System.out.println("TIPO");
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int cliente_id = (int) analex.Preanalisis().getAtributo();
        mascotaNegocio.registrar(nombre, raza, color, tipo, cliente_id);
        ClienteSMTP.sendMail(destinatario, "REGISTRAR MASCOTA", Cadenas.REGISTRO_SUCCESS);
    }

  
    public void modificar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        DefaultTableModel veterinario = mascotaNegocio.getMascota(id);
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
        String raza = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(veterinario.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String color = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                // posicion de la fila del defaultTableModel
                : String.valueOf(veterinario.getValueAt(0, 3));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int tipo = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : (int) (veterinario.getValueAt(0, 4));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int cliente_id = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : (int) (veterinario.getValueAt(0, 5));
        mascotaNegocio.modificar(id, nombre, raza, color, tipo, cliente_id);
        ClienteSMTP.sendMail(destinatario, "MODIFICAR MASCOTA", Cadenas.MODIFICAR_SUCCESS);

    }

 
    public void eliminar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        mascotaNegocio.eliminar(id);
        ClienteSMTP.sendMail(destinatario, "ELIMINAR MASCOTA", Cadenas.ELIMINAR_SUCCESS);
    }

   
    public void listar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        String Head[] = {"ID", "NOMBRE", "RAZA", "COLOR", "CLIENTE ID"};
        String Cabecera = "ANIMALHELP - LISTA DE MASCOTAS";
        Mensaje message = Utils.dibujarTablaHtml(mascotaNegocio.getMascotas(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println(Cadenas.SUCCESSFULL_MAIL);
        } else {
            System.out.println(Cadenas.FAILED_MAIL);
        }
    }
}
