package correos.veterinaria.software.Datos;

import correos.veterinaria.software.Datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jorge Luis Urquiza
 */
public class Productos {

    private int id;
    private String nombre;
    private String precio;
    private int categoria_id;
    private Conexion m_Conexion;

    public Productos() {
        m_Conexion = Conexion.getInstancia();
    }

    //Setters and Getters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    ///METHODS
    public void setProducto(String nombre, String precio, int categoria_id) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria_id = categoria_id;
    }

    public void setProducto(int id, String nombre, String precio, int categoria_id) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria_id = categoria_id;
    }

    public void registrar() {
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        PreparedStatement ps = null;
        String query = "INSERT INTO productos \n"
                + "(nombre,precio,categoria_id) \n"
                + " values (?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, precio);
            ps.setInt(3, categoria_id);
            ps.executeUpdate();
            System.out.println("Registrado!!");
            con.close();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    // JOIN 
    /*
    
     SELECT * FROM productos INNER JOIN categorias 
     ON productos.categoria_id = categorias.id;
    
     */

    public void modificar() {
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        PreparedStatement ps = null;
        String query = "UPDATE productos SET \n"
                + "nombre = ?,\n"
                + "precio = ?, \n"
                + "categoria_id = ? \n"
                + "WHERE id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, precio);
            ps.setInt(3, categoria_id);
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("Modificado!!");
            con.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

    }

    public void eliminar() {
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM productos WHERE id = ?");
            ps.setInt(1, this.id);
            System.out.println("ELIMINADO");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public DefaultTableModel getProducto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DefaultTableModel getProductos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
