/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.DetalleVenta;
import correos.veterinaria.software.Datos.Venta;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class VentaNegocio {

    private Venta venta;
    private DetalleVenta detalle;

    public VentaNegocio() {
        this.venta = new Venta();
        this.detalle = new DetalleVenta();
    }

    public void registrar(String nit, Date fecha, int cliente_id, int veterinario_id, LinkedList<DetalleVenta> lista) {
        venta.setVenta(nit, fecha, cliente_id, veterinario_id);
        int total = 0;
        int venta_id = venta.registrar();

        if (venta_id != -1) {
            for (DetalleVenta detalle : lista) {
                detalle.setIDVenta(venta_id);
                detalle.registrar();
                // System.out.println("DETALLES: producto_id: " + detalle_venta.getProducto_id() + "venta_ id: " + detalle_venta.getVenta_id() + "cant :" + detalle_venta.getCantidad());
                total += detalle.getPrecioProductoxCantidad();
                System.out.println("Vueltas" + total++);
            }
            venta.actualizarTotal(total);
            System.out.println("COSTO TOTAL DE LA VENTA: " + total);
        } else {
            System.out.println("Error al crear la venta");
        }
    }

    public DefaultTableModel getVenta(int id) {
        return venta.getVenta(id);
    }

    public DefaultTableModel getVentas() {
        return venta.getVentas();
    }

    public void modificar(int id, String nit, Date fecha, int cliente_id, int veterinario_id) {
        //id de la categoria a modificar
        venta.setVenta(id, nit, fecha, cliente_id, veterinario_id);
        venta.modificar();
    }

    public void eliminar(int id) { 
        venta.setId(id); // elimina la venta
        venta.eliminar();

    }

}
