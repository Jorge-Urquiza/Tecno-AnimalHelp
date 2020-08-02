/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.*;
import static java.awt.PageAttributes.MediaType.C;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
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

    public DefaultTableModel getCategoria(int x) {
        return categoria.getCategoria(x);

    }

    public DefaultTableModel getCategorias() {
        return categoria.getCategorias();
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

    public void registrar(String nombre, String descripcion) {
        categoria.setCategoria(nombre, descripcion);
        categoria.registrar();
    }

    public boolean existeCategoria(int id) {
        return categoria.getCategoria(id).getRowCount() == 0 ? false : true;
    }
}
