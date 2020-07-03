/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Datos;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class Categoria {

    public int id;
    public String nombre;
    public String descripcion;
    public Conexion m_Conexion;

    public void setCategoria(String nombre, String descripcion, Conexion m_Conexion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.m_Conexion = m_Conexion;
    }

    
    public Categoria(int id, String nombre, String descripcion, Conexion m_Conexion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.m_Conexion = m_Conexion;
    }

    public Categoria() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

}
