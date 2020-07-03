package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.Producto;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:41 PM
 */
public class ProductoNegocio {

    public Producto m_Producto;

    public ProductoNegocio() {
        this.m_Producto = new Producto();
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel obtenerProducto(int id) {
        return this.m_Producto.getProducto(id);
    }

    public DefaultTableModel obtenerProductos() {
        return this.m_Producto.getProductos();
    }

    /**
     *
     * @param nombre
     * @return
     */
    public int registrarProducto(String nombre) {
        this.m_Producto.setProducto(nombre);
        return this.m_Producto.registrarProducto();
    }

    /**
     *
     * @param id
     * @param nombre
     */
    public void modificarProducto(int id, String nombre) {
        this.m_Producto.setProducto(id, nombre);
        this.m_Producto.modificarProducto();
    }

    /**
     *
     * @param id
     */
    public void eliminarProducto(int id) {
        this.m_Producto.eliminarProducto(id);
    }

}
