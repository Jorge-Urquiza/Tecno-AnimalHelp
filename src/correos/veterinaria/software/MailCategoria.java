/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software;

import correos.veterinaria.correo.ClienteSMTP;
import correos.veterinaria.procesador.Analex;
import correos.veterinaria.procesador.Token;
import correos.veterinaria.software.Negocio.*;

import correos.veterinaria.utils.Helper;
import correos.veterinaria.utils.Mensaje;
import correos.veterinaria.utils.Utils;
import java.sql.Date;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class MailCategoria extends TemplateMail {

    private CategoriaNegocio categoriaNegocio;

    public MailCategoria() {
        this.categoriaNegocio = new CategoriaNegocio();
    }

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
        String descripcion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        System.out.println("descripcion :" + descripcion);
        categoriaNegocio.registrar(nombre, descripcion);
        ClienteSMTP.sendMail(destinatario, "Registrar Categoria", "Registro realizado Correctamente");

    }

    @Override
    public void modificar(Analex analex, String destinatario) throws Exception {

    }

    @Override
    public void eliminar(Analex analex, String destinatario) throws Exception {

    }

    @Override
    public void listar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - AnimalHelp Mail", Helper.HELP_OBTENERUSUARIOS);
            return;
        }

        String Head[] = {"ID", "NOMBRE", "DESCRIPCION"};
        String Cabecera = "VETERINARIA ANIMALHELP - LISTA DE CATEGORIAS";
        // Mensaje message = Utils.dibujarTabla2(usuarioNegocio.obtenerUsuarios(), Head, Cabecera);
        Mensaje message = Utils.dibujarTablaHtml(categoriaNegocio.getCategorias(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
    }

}
