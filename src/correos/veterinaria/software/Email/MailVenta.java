/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Email;

import correos.veterinaria.software.Email.TemplateMail;
import correos.veterinaria.protocolos.ClienteSMTP;
import correos.veterinaria.procesador.*;
import correos.veterinaria.procesador.Token;
import correos.veterinaria.software.Datos.*;
import correos.veterinaria.software.Negocio.*;
import correos.veterinaria.utils.*;
import java.sql.Date;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class MailVenta extends TemplateMail {

    VentaNegocio ventaNegocio = new VentaNegocio();

    @Override
    public void registrar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        analex.Avanzar();
        // Atributos
        String nit = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int cliente_id = (int) analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int veterinario_id = (int) analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        token = analex.Preanalisis();
        LinkedList<DetalleVenta> lista = new LinkedList<>();
        while (!isEOF(token)) {
            analex.Avanzar();
            int producto_id = (int) analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            analex.Avanzar();
            int cantidad = (int) analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            token = analex.Preanalisis();
            lista.add(new DetalleVenta(producto_id, cantidad));
        }
        ventaNegocio.registrar(nit, fecha, cliente_id, veterinario_id, lista);
        ClienteSMTP.sendMail(destinatario, "REGISTRAR VENTA", Cadenas.REGISTRO_SUCCESS);

    }
    @Override
    public void modificar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        DefaultTableModel venta = ventaNegocio.getVenta(id);
        if (venta.getRowCount() == 0) {
            ClienteSMTP.sendMail(destinatario, Cadenas.ERROR_ID, Cadenas.NO_EXISTS_ID);
            return;
        }
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String nit = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(venta.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) venta.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int cliente_id = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : (int) (venta.getValueAt(0, 3));

        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int veterinario_id = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : (int) (venta.getValueAt(0, 4));
        ventaNegocio.modificar(id, nit, fecha, cliente_id, veterinario_id);
        ClienteSMTP.sendMail(destinatario, "MODIFICAR VENTA", Cadenas.MODIFICAR_SUCCESS);
    }

    @Override
    public void eliminar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        ventaNegocio.eliminar(id);
    }

    @Override
    public void listar(Analex analex, String destinatario) throws Exception {
        analex.Avanzar();
        Token token = analex.Preanalisis();
        String Head[] = {"ID", "NOMBRE", "PRECIO (BS.)", "CATEGORIA ID"};
        String Cabecera = "VETERINARIA ANIMALHELP - LISTA DE PRODUCTOS";
        Mensaje message = Utils.dibujarTablaHtml(ventaNegocio.getVentas(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println(Cadenas.SUCCESSFULL_MAIL);
        } else {
            System.out.println(Cadenas.FAILED_MAIL);
        }
    }
    private boolean isEOF(Token token) {
        return token.getNombre() == Token.FIN ? true : false; // pregunta si el token que veo es eof
    }
}
