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
public class MailProducto extends TemplateMail {
    
    ProductosNegocio productoNegocio = new ProductosNegocio();

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
        int precio = Integer.parseInt(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int categoria_id =Integer.parseInt(analex.Preanalisis().getToStr());
        productoNegocio.registrar(nombre, precio, categoria_id);
        ClienteSMTP.sendMail(destinatario, "REGISTRAR PRODUCTO",Cadenas.REGISTRO_SUCCESS);
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
        DefaultTableModel producto = productoNegocio.getProducto(id);
        // verifica si existe al id
        if (producto.getRowCount() == 0) {
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
                : String.valueOf(producto.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int precio = (analex.Preanalisis().getNombre() != Token.GB)
                ? Integer.parseInt(analex.Preanalisis().getToStr())
                : (int)(producto.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int categoria_id = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                // posicion de la fila del defaultTableModel
                : (int) producto.getValueAt(0, 3);
        productoNegocio.modificar(id, nombre, precio, categoria_id);
        
        ClienteSMTP.sendMail(destinatario, "MODIFICAR PRODUCTO", Cadenas.MODIFICAR_SUCCESS);

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
        productoNegocio.eliminar(id);
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
            ClienteSMTP.sendMail(destinatario, Cadenas.AYUDA, Helper.HELP_OBTENERUSUARIOS);
            return;
        }
         String Head[] = {"ID", "NOMBRE", "PRECIO (BS.)", "CATEGORIA ID"};
        String Cabecera = "VETERINARIA ANIMALHELP - LISTA DE PRODUCTOS";
        Mensaje message = Utils.dibujarTablaHtml(productoNegocio.getProductos(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println(Cadenas.SUCCESSFULL_MAIL);
        } else {
            System.out.println(Cadenas.FAILED_MAIL);
        }
    }

    
}
