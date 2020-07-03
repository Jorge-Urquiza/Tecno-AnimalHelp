/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Percy Tomicha
 */
public class Horario {
    
    private int id;
    private String hora_inicio;
    private String hora_fin;
    private int tipo;
    public Conexion m_Conexion;

    public Horario() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param hora_inicio
     * @param hora_fin
     * @param tipo
     */
    public void setHorario(String hora_inicio, String hora_fin, int tipo) {
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.tipo = tipo;
    }

    /**
     *
     * @param id
     * @param hora_inicio
     * @param hora_fin
     * @param tipo
     */
    public void setHorario(int id, String hora_inicio, String hora_fin, int tipo) {
        this.id = id;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.tipo = tipo;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getHorario(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel horario = new DefaultTableModel();
        horario.setColumnIdentifiers(new Object[]{
            "id", "hora_inicio", "hora_fin", "tipo"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "horario.id,\n"
                + "horario.hora_inicio,\n"
                + "horario.hora_fin,\n"
                + "horario.tipo\n"
                + "FROM horario\n"
                + "WHERE horario.id=?";
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
                horario.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("hora_inicio"),
                    rs.getString("hora_fin"),
                    rs.getInt("tipo")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return horario;
    }

    public DefaultTableModel getHorarios() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel horarios = new DefaultTableModel();
        horarios.setColumnIdentifiers(new Object[]{
            "id", "hora_inicio", "hora_fin", "tipo"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "horario.id,\n"
                + "horario.hora_inicio,\n"
                + "horario.hora_fin,\n"
                + "horario.tipo\n"
                + "FROM horario order by id asc";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                horarios.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("hora_inicio"),
                    rs.getString("hora_fin"),
                    rs.getInt("tipo")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return horarios;
    }

    public int registrarHorario() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO horario(\n"
                + "hora_inicio,hora_fin,tipo)\n"
                + "VALUES(?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.hora_inicio);
            ps.setString(2, this.hora_fin);
            ps.setInt(3, this.tipo);
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

    public void modificarHorario() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE horario SET\n"
                + "hora_inicio = ?,\n"
                + "hora_fin = ?,\n"
                + "tipo = ?\n"
                + "WHERE horario.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.hora_inicio);
            ps.setString(2, this.hora_fin);
            ps.setInt(3, this.tipo);
            ps.setInt(4, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
