/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class Categoria {

    public int id;
    public String nombre;
    public String descripcion;
    public Conexion m_Conexion;

    public void setCategoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void setCategoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Categoria() {
        this.m_Conexion = Conexion.getInstancia();
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

    public DefaultTableModel getCategoria(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DefaultTableModel getCategorias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void registrar() {
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        PreparedStatement ps = null;
        String query = "INSERT INTO categoriaS (nombre,descripcion) values (?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);

            ps.executeUpdate();
            System.out.println("INGRESANDO AL METODO REGISTRAR DEL MODELO CATEGORIA");
            con.close();
        } catch (SQLException e) {
            System.out.println("NO INGRESANDO AL METODO REGISTRAR DEL MODELO");
            System.out.println(e.getMessage());
        }

    }

    public void modificar() {
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        PreparedStatement ps = null;
        String query = "UPDATE categorias SET \n"
                + "nombre = ?,\n"
                + "descripcion = ? \n"
                + "WHERE id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, this.nombre);
            ps.setString(2, this.descripcion);
            ps.setInt(3, this.id);
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {

            System.out.println("ERROR:" + e.getMessage());
        }

    }

    public void eliminar() {
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM categorias WHERE id = ?");
            ps.setInt(1, this.id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
