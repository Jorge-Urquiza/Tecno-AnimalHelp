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
 * @author Percy Tomicha
 */
public class Ejercicio {

    private int id;
    private String nombre;
    public Conexion m_Conexion;

    public Ejercicio() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param nombre
     */
    public void setEjercicio(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @param id
     * @param nombre
     */
    public void setEjercicio(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     *
     * @param id
     */
    public DefaultTableModel getEjercicio(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel ejercicio = new DefaultTableModel();
        ejercicio.setColumnIdentifiers(new Object[]{
            "id", "nombre"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "ejercicio.id,\n"
                + "ejercicio.nombre\n"
                + "FROM ejercicio\n"
                + "WHERE ejercicio.id=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                ejercicio.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ejercicio;
    }

    public DefaultTableModel getEjercicios() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel ejercicios = new DefaultTableModel();
        ejercicios.setColumnIdentifiers(new Object[]{
            "id", "nombre"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "ejercicio.id,\n"
                + "ejercicio.nombre\n"
                + "FROM ejercicio";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                ejercicios.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ejercicios;
    }

    public int registrarEjercicio() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO ejercicio(\n"
                + "nombre)\n"
                + "VALUES(?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombre);
            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.m_Conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public void modificarEjercicio() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE ejercicio SET\n"
                + "nombre = ?\n"
                + "WHERE ejercicio.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setInt(2, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
