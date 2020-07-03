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
public class SocioRutina {
    

    private int id;
    private Date fecha;
    private int id_socio;
    private int id_rutina;
    public Conexion m_Conexion;
    
    public SocioRutina() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param fecha
     * @param id_socio
     * @param id_rutina
     */
    public void setSocioRutina(Date fecha, int id_socio, int id_rutina) {
        this.fecha = fecha;
        this.id_socio = id_socio;
        this.id_rutina = id_rutina;
    }
    /**
     *
     * @param id
     * @param fecha
     * @param id_socio
     * @param id_rutina
     */
    public void setSocioRutina(int id, Date fecha, int id_socio, int id_rutina) {
        this.id = id;
        this.fecha = fecha;
        this.id_socio = id_socio;
        this.id_rutina = id_rutina;
    }
    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getSocioRutina(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel socio_rutina = new DefaultTableModel();
        socio_rutina.setColumnIdentifiers(new Object[]{
            "id", "fecha", "id_socio", "id_rutina"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "socio_rutina.id,\n"
                + "socio_rutina.fecha,\n"
                + "socio_rutina.id_socio,\n"
                + "socio_rutina.id_rutina\n"
                + "FROM socio_rutina\n"
                + "WHERE socio_rutina.id=?";
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
                socio_rutina.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getInt("id_socio"),
                    rs.getInt("id_rutina"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return socio_rutina;
    }
    /**
     *
     * @return
     */
    public DefaultTableModel getSocioRutinas() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel socio_rutinas = new DefaultTableModel();
        socio_rutinas.setColumnIdentifiers(new Object[]{
            "id", "fecha", "socio", "rutina"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
                String sql = "SELECT\n"
                + "socio_rutina.id,\n"
                + "socio_rutina.fecha,\n"
                + "(usuario.nombres||' '||usuario.apellidos)as socio,\n"
                + "(rutina.nombre||'-'||rutina.musculo) as rutina\n"
                + "FROM socio_rutina,usuario,rutina\n"
                + "WHERE socio_rutina.id_socio=usuario.id and socio_rutina.id_rutina=rutina.id\n"
                + "order by id asc";
                //System.out.println(sql);
//        String sql = "SELECT\n"
//                + "socio_rutina.id,\n"
//                + "socio_rutina.fecha,\n"
//                + "socio_rutina.presencia,\n"
//                + "(usuario.nombres||' '||usuario.apellidos)as usuario,\n"
//                + "(horario.hora_inicio||'-'||horario.hora_fin) as horario\n"
//                + "FROM socio_rutina,usuario,horario order by id asc";
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
                socio_rutinas.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getString("socio"),
                    rs.getString("rutina")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return socio_rutinas;
    }
    public int registrarSocioRutina() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO socio_rutina(\n"
                + "fecha,id_socio,id_rutina)\n"
                + "VALUES(?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setDate(1, this.fecha);
            ps.setInt(2, this.id_socio);
            ps.setInt(3, this.id_rutina);
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
    public void modificarSocioRutina() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE socio_rutina SET\n"
                + "fecha = ?,\n"
                + "id_socio = ?,\n"
                + "id_rutina = ?\n"
                + "WHERE socio_rutina.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, this.fecha);
            ps.setInt(2, this.id_socio);
            ps.setInt(3, this.id_rutina);
            ps.setInt(4, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param id_socio
     */
    public DefaultTableModel obtenerHistorialSocioRutinaBySocio(int id_socio) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel x = new DefaultTableModel();
        x.setColumnIdentifiers(new Object[]{
            "id", "socio", "rutina", "fecha"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "select usuario.id,(usuario.nombres||' '||usuario.apellidos)as socio,rutina.nombre as rutina,socio_rutina.fecha\n" +
                "from usuario,socio_rutina,rutina\n" +
                "where usuario.id=socio_rutina.id_socio and socio_rutina.id_rutina=rutina.id and usuario.id=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_socio);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                x.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("socio"),
                    rs.getString("rutina"),
                    rs.getDate("fecha"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;
    }
}
