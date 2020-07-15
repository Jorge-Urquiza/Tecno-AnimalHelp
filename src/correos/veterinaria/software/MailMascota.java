/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software;

import correos.veterinaria.correo.ClienteSMTP;
import correos.veterinaria.procesador.*;
import correos.veterinaria.software.Negocio.*;
import correos.veterinaria.utils.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class MailMascota extends TemplateMail {

    MascotaNegocio mascotaNegocio = new MascotaNegocio();

    @Override
    public void registrar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - AnimalHelp Mail", Helper.HELP_REGISTRARUSUARIO);
            return;
        }
        // Sino, ejecutar el comando
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
        int cliente_id = (int) analex.Preanalisis().getAtributo();
        mascotaNegocio.registrar(nombre, raza, color, cliente_id);
        ClienteSMTP.sendMail(destinatario, "REGISTRAR MASCOTA", Cadenas.REGISTRO_SUCCESS);
    }

    @Override
    public void modificar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, Cadenas.AYUDA, Helper.HELP_MODIFICARHORARIO);
            return;
        }
        // Sino, ejecutar el comando
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
        String color= (analex.Preanalisis().getNombre() != Token.GB)
                 ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                // posicion de la fila del defaultTableModel
                :  String.valueOf(veterinario.getValueAt(0, 3));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int cliente_id = (analex.Preanalisis().getNombre() != Token.GB)
                ?(int) analex.Preanalisis().getAtributo()
                :(int)(veterinario.getValueAt(0, 4));
        mascotaNegocio.modificar(id, nombre, raza, color, cliente_id);
        ClienteSMTP.sendMail(destinatario, "MODIFICAR MASCOTA", Cadenas.MODIFICAR_SUCCESS);

    }

    @Override
    public void eliminar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, Cadenas.AYUDA, Helper.HELP_ELIMINARPRODUCTO);
            return;
        }
        // Sino, ejecutar el comando
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        mascotaNegocio.eliminar(id);
        ClienteSMTP.sendMail(destinatario, "ELIMINAR MASCOTA", Cadenas.ELIMINAR_SUCCESS);
    }

    @Override
    public void listar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, Cadenas.AYUDA, Helper.HELP_OBTENERUSUARIOS);
            return;
        }
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
