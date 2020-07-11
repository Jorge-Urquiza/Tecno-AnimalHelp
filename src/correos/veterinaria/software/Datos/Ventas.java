/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Datos;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class Ventas {
    
    private int id;
    private String fecha;
    private int cliente_id;
    private int veterinario_id;
    private int total; // totala de la venta 
    
    private Conexion m_conexion;

    public Ventas() {
        m_conexion = Conexion.getInstancia();
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public void setVenta(int id, String fecha, int cliente_id, int veterinario_id) {
        this.id = id;
        this.fecha = fecha;
        this.cliente_id = cliente_id;
        this.veterinario_id = veterinario_id;
    }
    
    public void setVenta(String fecha, int cliente_id, int veterinario_id) {
        this.fecha = fecha;
        this.cliente_id = cliente_id;
        this.veterinario_id = veterinario_id;
    }

    public int registrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DefaultTableModel getVenta(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DefaultTableModel getVentas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void actualizarTotal(int subtotal_detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    
}
