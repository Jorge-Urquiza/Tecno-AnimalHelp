/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software;

import correos.veterinaria.correo.*;
import correos.veterinaria.procesador.*;
import correos.veterinaria.software.Negocio.VeterinarioNegocio;
import correos.veterinaria.utils.*;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class MailVeterinario extends TemplateMail {

    VeterinarioNegocio veterinarioNegocio = new VeterinarioNegocio();

    @Override
    public void registrar(Analex analex, String destinatario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Analex analex, String destinatario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Analex analex, String destinatario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario,Cadenas.AYUDA, Helper.HELP_OBTENERUSUARIOS);
            return;
        }
        String Head[] = {"ID", "NOMBRE", "APELLIDO", "CI", "CELULAR", "DIRECCION"};
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
