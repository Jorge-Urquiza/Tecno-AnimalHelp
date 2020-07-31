/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Email;

import correos.veterinaria.software.Email.TemplateMail;
import correos.veterinaria.protocolos.ClienteSMTP;
import correos.veterinaria.procesador.Analex;
import correos.veterinaria.procesador.Token;
import correos.veterinaria.software.Negocio.*;
import correos.veterinaria.utils.Cadenas;


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
       
        String Head[] = {"ID","NOMBRE", "PRECIO", "CATEGORIA_ID"};
        String Cabecera = "ANIMALHELP - LISTA DE CATEGORIAS";
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
