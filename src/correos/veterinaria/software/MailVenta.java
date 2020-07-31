/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software;

import correos.veterinaria.correo.ClienteSMTP;
import correos.veterinaria.procesador.Analex;
import correos.veterinaria.procesador.Token;
import correos.veterinaria.software.Datos.DetalleVenta;
import correos.veterinaria.software.Datos.Ventas;
import correos.veterinaria.software.Negocio.AsistenciaNegocio;
import correos.veterinaria.software.Negocio.VentasNegocio;
import correos.veterinaria.utils.Cadenas;
import correos.veterinaria.utils.Helper;
import correos.veterinaria.utils.Utils;
import java.sql.Date;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class MailVenta extends TemplateMail {

    VentasNegocio ventaNegocio = new VentasNegocio();

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
        if (lista.size() != 0) {
            ventaNegocio.registrar(nit, fecha, cliente_id, veterinario_id, lista);
            ClienteSMTP.sendMail(destinatario, "REGISTRAR VENTA", Cadenas.REGISTRO_SUCCESS);
            return;
        }
    }

    private boolean isEOF(Token token) {
   
        return token.getNombre() == Token.FIN ? true : false; // pregunta si el token que veo es eof
    }

    @Override
    public void modificar(Analex analex, String destinatario) throws Exception {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "HELP", Helper.HELP_MODIFICARASISTENCIA);
            return;
        }

        // Sino, ejecutar el comando
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        DefaultTableModel venta = ventaNegocio.getVenta(id);
        if (venta.getRowCount() == 0) {
            ClienteSMTP.sendMail(destinatario, Cadenas.ERROR_ID, Cadenas.NO_EXISTS_ID);
            return;
        }
        System.out.println("VECTOR " + venta.getDataVector());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listar(Analex analex, String destinatario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
