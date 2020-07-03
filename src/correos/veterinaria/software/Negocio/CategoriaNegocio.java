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
public class CategoriaNegocio {

    private Categoria categoria;

    public CategoriaNegocio() {
        this.categoria = new Categoria();
    }

    public DefaultTableModel getCategoria(int id) {
        return categoria.getCategoria(id);
    }

    public DefaultTableModel getCategorias() {
        return categoria.getCategorias();
    }

    public void registrar(String nombre, String descripcion) {
        categoria.setCategoria(nombre, descripcion);
        categoria.registrar();
    }

    public void modificar(int id, String nombre, String descripcion) {
        //id de la categoria a modificar
        categoria.setCategoria(id, nombre, descripcion);
        categoria.modificar();
    }
     public void eliminar(int id) {
        //id de la categoria a modificar
        categoria.setId(id);
        categoria.eliminar();
    }

}
