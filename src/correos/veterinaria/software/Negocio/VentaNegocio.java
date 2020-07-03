/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.Venta;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Percy Tomicha
 */
public class VentaNegocio {

    public Venta m_Venta;

    public VentaNegocio() {
        this.m_Venta = new Venta();
    }

    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerVenta(int id) {
        return this.m_Venta.getVenta(id);
    }

    /**
     *
     * @return 
     */
    public DefaultTableModel obtenerVentas() {
        return this.m_Venta.getVentas();
    }

    /**
     *
     * @param cantidad
     * @param fecha
     * @param precio
     * @param id_producto
     * @return 
     */
    public int registrarVenta(int cantidad, Date fecha, float precio, int id_producto) {
        this.m_Venta.setVenta(cantidad,fecha,precio,id_producto);
        return this.m_Venta.registrarVenta();
    }

    /**
     *
     * @param id
     * @param cantidad
     * @param fecha
     * @param precio
     * @param id_producto
     */
    public void modificarVenta(int id, int cantidad, Date fecha, float precio, int id_producto) {
        this.m_Venta.setVenta(id, cantidad,fecha,precio,id_producto);
        this.m_Venta.modificarVenta();
    }
}
