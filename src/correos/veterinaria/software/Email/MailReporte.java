/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Email;

import correos.veterinaria.procesador.Analex;
import correos.veterinaria.software.Negocio.*;
import correos.veterinaria.utils.Cadenas;
import correos.veterinaria.utils.Mensaje;
import correos.veterinaria.utils.Utils;
import javax.mail.MessagingException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class MailReporte {

    ReporteNegocio reporteNegocio = new ReporteNegocio();

    // reporte total de ventas por mes
    public void ventasMensuales(Analex analex, String destinatario) throws MessagingException {
        String Head[] = {"AÑO", "MES", "PRECIOTOTALVENTA", "CANTIDADVENTA", "PROMEDIOVENTA"};
        String Cabecera = "REPORTE - VENTAS TOTALES POR MES ";
        Mensaje message = Utils.dibujarTablaHtml(reporteNegocio.ventasMensuales(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println(Cadenas.SUCCESSFULL_MAIL);
        } else {
            System.out.println(Cadenas.FAILED_MAIL);
        }
    }

    // los clientes que mas compras han realizado
    public void top3ClientesCompras(Analex analex, String destinatario) throws MessagingException {
        String Head[] = {"NOMBRE", "APELLIDOS", "CI", "CELULAR", "CANTIDAD-COMPRAS"};
        String Cabecera = "REPORTE - TOP 3 CLIENTES QUE MAS COMPRAS REALIZARON";
        Mensaje message = Utils.dibujarTablaHtml(reporteNegocio.top3ClientesCompras(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println(Cadenas.SUCCESSFULL_MAIL);
        } else {
            System.out.println(Cadenas.FAILED_MAIL);
        }
    }

    // LAS 3 MASCOTAS QUE RECIBIERON ATENCION
    public void top3MascotasAtendidas(Analex analex, String destinatario) throws MessagingException {
        String Head[] = {"ID", "FECHA", "DESCRIPCION", "DIAGNOSTICO", "CIENTE ID", "VETERINARIO ID", "MASCOTA ID"};
        String Cabecera = "REPORTE - TOP 3 MASCOTAS MAS VECES ATENTIDAS";
        Mensaje message = Utils.dibujarTablaHtml(reporteNegocio.top3MascotasAtendidas(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println(Cadenas.SUCCESSFULL_MAIL);
        } else {
            System.out.println(Cadenas.FAILED_MAIL);
        }
    }

    //los productos que mas vendieron
    public void top3ProductosVendidos(Analex analex, String destinatario) throws MessagingException {
        String Head[] = {"ID", "NOMBRE", "PRECIO", "CATEGORIA", "CANTIDAD"};
        String Cabecera = "REPORTE - TOP 3 PRODUCTOS MAS VENDIDOS";
        Mensaje message = Utils.dibujarTablaHtml(reporteNegocio.top3ProductosVendidos(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println(Cadenas.SUCCESSFULL_MAIL);
        } else {
            System.out.println(Cadenas.FAILED_MAIL);
        }
    }

    //ventas total del dia de hoy
    public void ventasTotalDeHoy(Analex analex, String destinatario) throws MessagingException {
        String Head[] = { "ID", "NIT", "FECHA", "TOTAL", "PROMEDIOVENTA"};
        String Cabecera = "REPORTE - VENTAS DEL DIA DE HOY";
        Mensaje message = Utils.dibujarTablaHtml(reporteNegocio.top3ProductosVendidos(), Head, Cabecera);
        message.setCorreo(destinatario);
        if (message.enviarCorreo()) {
            System.out.println(Cadenas.SUCCESSFULL_MAIL);
        } else {
            System.out.println(Cadenas.FAILED_MAIL);
        }
    }
}
