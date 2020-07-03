/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class Cliente {

    //atributos
    private int id;
    private String nombre;
    private String apellidos;
    private String ci;
    private String celular;

    //db connection 
    public Conexion m_Conexion;

    public Cliente() {
        this.m_Conexion = Conexion.getInstancia();
    }

    public void setCliente(String nombre, String apellidos, String ci, String celular) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ci = ci;
        this.celular = celular;
    }

    public void setCliente(int id, String nombre, String apellidos, String ci, String celular) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ci = ci;
        this.celular = celular;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DefaultTableModel getCliente(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DefaultTableModel getClientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void registrar() {
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        PreparedStatement ps = null;
        String query = "INSERT INTO clientes \n"
                + "(nombre,apellido,ci,celular) \n"
                + " values (?,?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, this.nombre);
            ps.setString(2, this.apellidos);
            ps.setString(3, this.ci);
            ps.setString(4, celular);
            ps.executeUpdate();
            System.out.println("Registrado!!");
            con.close();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    public void modificar() {
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        PreparedStatement ps = null;
        String query = "UPDATE clientes SET \n"
                + "nombre = ?,\n"
                + "apellido = ?, \n"
                + "ci = ?, \n"
                + "celular = ?\n"
                + "WHERE id = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, ci);
            ps.setString(4, celular);
            ps.setInt(5, id);

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
            ps = con.prepareStatement("DELETE FROM clientes WHERE id = ?");
            ps.setInt(1, this.id);
            System.out.println("ELIMINADO!!");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
