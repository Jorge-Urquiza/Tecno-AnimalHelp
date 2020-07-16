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
public class MailAtencion extends TemplateMail {

    AtencionNegocio atencionNegocio = new AtencionNegocio();

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
        String fecha = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String descripcion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String diagnostico = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int cliente_id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int veterinario_id = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int mascota_id = Integer.parseInt(analex.Preanalisis().getToStr());
        atencionNegocio.registrar(fecha, descripcion, diagnostico, cliente_id, veterinario_id, mascota_id);
        ClienteSMTP.sendMail(destinatario, "REGISTRAR ATENCION", Cadenas.REGISTRO_SUCCESS);
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
        DefaultTableModel atencion = atencionNegocio.getAtencion(id);
        // verifica si existe al id
        if (atencion.getRowCount() == 0) {
            ClienteSMTP.sendMail(destinatario, Cadenas.ERROR_ID, Cadenas.NO_EXISTS_ID);
            return;
        }
        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String fecha = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                // posicion de la fila del defaultTableModel
                : String.valueOf(atencion.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String descripcion = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(atencion.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String diagnostio = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                // posicion de la fila del defaultTableModel
                : String.valueOf(atencion.getValueAt(0, 3));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int cliente_id = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(atencion.getValueAt(0, 4)));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int veterinario_id = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(atencion.getValueAt(0, 5)));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int mascota_id = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(atencion.getValueAt(0, 6)));
        atencionNegocio.modificar(id, fecha, descripcion, diagnostio, cliente_id, veterinario_id, mascota_id);
        ClienteSMTP.sendMail(destinatario, "MODIFICAR ATENCION", Cadenas.MODIFICAR_SUCCESS);

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
        atencionNegocio.eliminar(id);
        ClienteSMTP.sendMail(destinatario, "ELIMINAR ATENCION", Cadenas.ELIMINAR_SUCCESS);
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
        String Head[] = {"ID", "FECHA", "DESCRIPCION", "DIAGNOSTICO", "CIENTE ID", "VETERINARIO ID", "MASCOTA ID"};
        String Cabecera = "ANIMALHELP - LISTA DE ATENCIONES";
        Mensaje message = Utils.dibujarTablaHtml(atencionNegocio.getAtenciones(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println(Cadenas.SUCCESSFULL_MAIL);
        } else {
            System.out.println(Cadenas.FAILED_MAIL);
        }
    }

}
