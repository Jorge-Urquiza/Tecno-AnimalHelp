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
import correos.veterinaria.utils.Cadenas;

import correos.veterinaria.utils.Helper;
import correos.veterinaria.utils.Mensaje;
import correos.veterinaria.utils.Utils;
import javax.swing.table.DefaultTableModel;

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
        DefaultTableModel categoria = categoriaNegocio.getCategoria(id);
        // verifica si existe al id
        if (categoria.getRowCount() == 0) {
         ClienteSMTP.sendMail(destinatario, Cadenas.NO_EXISTS_ID, Cadenas.NO_EXISTS_ID);
            return;
        }
        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String nombre = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                // posicion de la fila del defaultTableModel
                : String.valueOf(categoria.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String descripcion = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(categoria.getValueAt(0, 2));
        categoriaNegocio.modificar(id, nombre, descripcion);
        ClienteSMTP.sendMail(destinatario, "MODIFICAR PRODUCTO",Cadenas.MODIFICAR_SUCCESS);
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
            ClienteSMTP.sendMail(destinatario,Cadenas.AYUDA, Helper.HELP_ELIMINARPRODUCTO);
            return;
        }
        // Sino, ejecutar el comando
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        categoriaNegocio.eliminar(id);
        ClienteSMTP.sendMail(destinatario, "ELIMINAR PRODUCTO", Cadenas.ELIMINAR_SUCCESS);
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
        String Head[] = {"ID", "NOMBRE", "PRECIO (BS.)", "CATEGORIA ID"};
        String Cabecera = "VETERINARIA ANIMALHELP - LISTA DE PRODUCTOS";
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
