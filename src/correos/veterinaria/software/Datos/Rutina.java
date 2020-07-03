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
public class Rutina {

    private int id;
    private String nombre;
    private String musculo;
    public Conexion m_Conexion;

    public Rutina() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param nombre
     * @param musculo
     */
    public void setRutina(String nombre, String musculo) {
        this.nombre = nombre;
        this.musculo = musculo;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param musculo
     */
    public void setRutina(int id, String nombre, String musculo) {
        this.id = id;
        this.nombre = nombre;
        this.musculo = musculo;
    }

    /**
     *
     * @param id
     */
    public DefaultTableModel getRutina(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel rutina = new DefaultTableModel();
        rutina.setColumnIdentifiers(new Object[]{
            "id", "nombre", "musculo"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "rutina.id,\n"
                + "rutina.nombre,\n"
                + "rutina.musculo\n"
                + "FROM rutina\n"
                + "WHERE rutina.id=?";
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
                rutina.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("musculo"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rutina;
    }

    public DefaultTableModel getRutinas() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel rutinas = new DefaultTableModel();
        rutinas.setColumnIdentifiers(new Object[]{
            "id", "nombre", "musculo"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "rutina.id,\n"
                + "rutina.nombre,\n"
                + "rutina.musculo\n"
                + "FROM rutina";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                rutinas.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("musculo"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rutinas;
    }

    public int registrarRutina() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO rutina(\n"
                + "nombre,musculo)\n"
                + "VALUES(?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombre);
            ps.setString(2, this.musculo);
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

    public void modificarRutina() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE rutina SET\n"
                + "nombre = ?,\n"
                + "musculo = ?\n"
                + "WHERE rutina.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setString(2, this.musculo);
            ps.setInt(3, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
