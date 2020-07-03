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
public class VeterinarioNegocio {

    //Datos
    private Veterinario veterinario;

    public VeterinarioNegocio() {
        this.veterinario = new Veterinario();
    }

    public DefaultTableModel getVeterinario(int id) {
        return veterinario.getVeterinario(id);
    }

    public DefaultTableModel getVeterinarios() {
        return veterinario.getVeterinarios();
    }

    public void registrar(String nombre, String apellidos, int ci, String celular, String direccion) {
        veterinario.setVeterinario(nombre, apellidos, ci, celular, direccion);
        veterinario.registrar();
    }

    public void modificar(int id, String nombre, String apellidos, int ci, String celular, String direccion) {

        veterinario.setVeterinario(id, nombre, apellidos, ci, celular, direccion);
        veterinario.modificar();
    }

    public void eliminar(int id) {
        veterinario.setId(id);
        veterinario.eliminar();
    }

}
