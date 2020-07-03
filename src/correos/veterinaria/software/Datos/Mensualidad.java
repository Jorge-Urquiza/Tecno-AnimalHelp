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
public class Mensualidad {

    private int id;
    private Date fecha_inicio;
    private Date fecha_fin;
    private float monto;
    private int id_socio;
    public Conexion m_Conexion;
    
    public Mensualidad() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param fecha_inicio
     * @param fecha_fin
     * @param monto
     * @param id_socio
     */
    public void setMensualidad(Date fecha_inicio, Date fecha_fin, float monto, int id_socio) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.monto = monto;
        this.id_socio = id_socio;
    }

    /**
     *
     * @param id
     * @param fecha_inicio
     * @param fecha_fin
     * @param monto
     * @param id_socio
     */
    public void setMensualidad(int id, Date fecha_inicio, Date fecha_fin, float monto, int id_socio) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.monto = monto;
        this.id_socio = id_socio;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getMensualidad(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel mensualidad = new DefaultTableModel();
        mensualidad.setColumnIdentifiers(new Object[]{
            "id", "fecha_inicio", "fecha_fin", "monto", "id_usuario"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "mensualidad.id,\n"
                + "mensualidad.fecha_inicio,\n"
                + "mensualidad.fecha_fin,\n"
                + "mensualidad.monto,\n"
                + "mensualidad.id_usuario\n"
                + "FROM mensualidad\n"
                + "WHERE mensualidad.id=?";
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
                mensualidad.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getFloat("monto"),
                    rs.getInt("id_usuario")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mensualidad;
    }

    /**
     *
     * @return
     */
    public DefaultTableModel getMensualidades() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel mensualidades = new DefaultTableModel();
        mensualidades.setColumnIdentifiers(new Object[]{
            "id", "fecha_inicio", "fecha_fin", "monto", "socio"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "mensualidad.id,\n"
                + "mensualidad.fecha_inicio,\n"
                + "mensualidad.fecha_fin,\n"
                + "mensualidad.monto,\n"
                + "(usuario.nombres||' '||usuario.apellidos) as socio\n"
                + "FROM mensualidad,usuario\n"
                + "WHERE mensualidad.id_socio=usuario.id";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                mensualidades.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getFloat("monto"),
                    rs.getString("socio")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mensualidades;
    }

    public int registrarMensualidad() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO mensualidad(\n"
                + "fecha_inicio,fecha_fin,monto,id_socio)\n"
                + "VALUES(?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setDate(1, this.fecha_inicio);
            ps.setDate(2, this.fecha_fin);
            ps.setDouble(3, this.monto);
            ps.setInt(4, this.id_socio);
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

    public void modificarMensualidad() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE mensualidad SET\n"
                + "fecha_inicio = ?,\n"
                + "fecha_fin = ?,\n"
                + "monto = ?,\n"
                + "id_socio = ?\n"
                + "WHERE mensualidad.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, this.fecha_inicio);
            ps.setDate(2, this.fecha_fin);
            ps.setDouble(3, this.monto);
            ps.setInt(4, this.id_socio);
            ps.setInt(5, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     *
     */
    public DefaultTableModel estadisticaMensualidadActual() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel x = new DefaultTableModel();
        x.setColumnIdentifiers(new Object[]{
            "id", "socio", "fecha"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "select distinct usuario.id,(usuario.nombres||' '||usuario.apellidos)as socio,fecha_fin as fecha\n" +
                    "from mensualidad,usuario\n" +
                    "where ((date_part('year',fecha_fin)>date_part('year',now()))or\n" +
                    "	(date_part('year',fecha_fin)=date_part('year',now())and date_part('month',fecha_fin)>date_part('month',now()))or\n" +
                    "	(date_part('year',fecha_fin)=date_part('year',now())and date_part('month',fecha_fin)=date_part('month',now())and date_part('day',fecha_fin)>=date_part('day',now())))and\n" +
                    "	mensualidad.id_socio=usuario.id\n" +
                    "order by usuario.id asc";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                x.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("socio"),
                    rs.getDate("fecha")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;
    }
}
