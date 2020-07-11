/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class ProductosNegocio {

    private Productos producto;

    public ProductosNegocio() {
        this.producto = new Productos();
    }

    public DefaultTableModel getProducto(int id) {
        return producto.getProducto(id);
    }

    public DefaultTableModel getProductos() {
        return producto.getProductos();
    }

    public void registrar(String nombre, int precio, int categoria_id) {
        producto.setProducto(nombre, Integer.toString(precio), categoria_id);
        producto.registrar();
    }

    public void modificar(int id, String nombre, int precio, int categoria_id) {

        producto.setProducto(id, nombre, Integer.toString(precio), categoria_id);
        producto.modificar();
    }

    public void eliminar(int id) {
        producto.setId(id);
        producto.eliminar();
    }
}