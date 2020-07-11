/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class VentasNegocio {
    
    private Ventas venta;
    private DetalleVenta detalle;
    
    public VentasNegocio() {
        this.venta = new Ventas();
    }
    
    public DefaultTableModel getVenta(int id) {
        return venta.getVenta(id);
    }
    
    public DefaultTableModel getVentas() {
        return venta.getVentas();
    }
    
    public void registrar(String fecha, int cliente_id, int veterinario_id,ArrayList<DetalleVenta> lista) {
        venta.setVenta(fecha, cliente_id, veterinario_id);
        int subtotal_detalle=0;
        int venta_id= venta.registrar();
        for (DetalleVenta detalle : lista){
            detalle.setIDVenta(venta_id);
             subtotal_detalle += detalle.registrar();
        }
      venta.actualizarTotal(subtotal_detalle);
    }
    
    public void modificar(int id,String fecha, int cliente_id, int total) {
      
    }
    
    public void eliminar(int id) {
        venta.setId(id);
        /// va eliminar todos los items del detalle de la venta que pertenezcan a esa venta.
        detalle.eliminarDetalleVenta(id);
        venta.eliminar();
    }
}
