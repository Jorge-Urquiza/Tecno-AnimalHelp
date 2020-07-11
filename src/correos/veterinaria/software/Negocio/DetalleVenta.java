/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.Conexion;

/**
 *
 * @author Jorge Luis Urquiza
 */
class DetalleVenta {
    private int producto_id;
    private int venta_id;
    private int cantidad;
    
    private Conexion m_conexion;
    
    public void setDetalleVenta(int producto_id, int venta_id, int cantidad) {
        this.producto_id = producto_id;
        this.venta_id = venta_id;
        this.cantidad = cantidad;
    }

    public DetalleVenta() {
        m_conexion= Conexion.getInstancia();
    }

    void setIDVenta(int venta_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int registrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void eliminarDetalleVenta(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
