package correos.veterinaria.software.Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:42 PM
 */
public class Asistencia {

    private int id;
    private Date fecha;
    private boolean presencia;
    private int id_usuario;
    private int id_horario;
    public Conexion m_Conexion;
    
    public Asistencia() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param fecha
     * @param presencia
     * @param id_usuario
     * @param id_horario
     */
    public void setAsistencia(Date fecha, boolean presencia, int id_usuario, int id_horario) {
        this.fecha = fecha;
        this.presencia = presencia;
        this.id_usuario = id_usuario;
        this.id_horario = id_horario;
    }

    /**
     *
     * @param id
     * @param fecha
     * @param presencia
     * @param id_usuario
     * @param id_horario
     */
    public void setAsistencia(int id, Date fecha, boolean presencia, int id_usuario, int id_horario) {
        this.id = id;
        this.fecha = fecha;
        this.presencia = presencia;
        this.id_usuario = id_usuario;
        this.id_horario = id_horario;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getAsistencia(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel asistencia = new DefaultTableModel();
        asistencia.setColumnIdentifiers(new Object[]{
            "id", "fecha", "presencia", "id_usuario", "id_horario"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "asistencia.id,\n"
                + "asistencia.fecha,\n"
                + "asistencia.presencia,\n"
                + "asistencia.id_usuario,\n"
                + "asistencia.id_horario\n"
                + "FROM asistencia\n"
                + "WHERE asistencia.id=?";
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
                asistencia.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getBoolean("presencia"),
                    rs.getInt("id_usuario"),
                    rs.getInt("id_horario"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return asistencia;
    }

    /**
     *
     * @return
     */
    public DefaultTableModel getAsistencias() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel asistencias = new DefaultTableModel();
        asistencias.setColumnIdentifiers(new Object[]{
            "id", "fecha", "presencia", "usuario", "horario"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
                String sql = "SELECT\n"
                + "asistencia.id,\n"
                + "asistencia.fecha,\n"
                + "asistencia.presencia,\n"
                + "(usuario.nombres||' '||usuario.apellidos||''||usuario.tipo)as usuario,\n"
                + "(horario.hora_inicio||'-'||horario.hora_fin) as horario\n"
                + "FROM asistencia,usuario,horario\n"
                + "WHERE asistencia.id_usuario=usuario.id and asistencia.id_horario=horario.id\n"
                + "order by id asc";
                //System.out.println(sql);
//        String sql = "SELECT\n"
//                + "asistencia.id,\n"
//                + "asistencia.fecha,\n"
//                + "asistencia.presencia,\n"
//                + "(usuario.nombres||' '||usuario.apellidos)as usuario,\n"
//                + "(horario.hora_inicio||'-'||horario.hora_fin) as horario\n"
//                + "FROM asistencia,usuario,horario order by id asc";
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
                asistencias.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getBoolean("presencia"),
                    rs.getString("usuario"),
                    rs.getString("horario")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return asistencias;
    }

    public int registrarAsistencia() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO asistencia(\n"
                + "fecha,presencia,id_usuario,id_horario)\n"
                + "VALUES(?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setDate(1, this.fecha);
            ps.setBoolean(2, this.presencia);
            ps.setInt(3, this.id_usuario);
            ps.setInt(4, this.id_horario);
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

    public void modificarAsistencia() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE asistencia SET\n"
                + "fecha = ?,\n"
                + "presencia = ?,\n"
                + "id_usuario = ?,\n"
                + "id_horario = ?\n"
                + "WHERE asistencia.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, this.fecha);
            ps.setBoolean(2, this.presencia);
            ps.setInt(3, this.id_usuario);
            ps.setInt(4, this.id_horario);
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
     * @param id_socio
     */
    public DefaultTableModel obtenerAsistenciaBySocio(int id_socio) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel x = new DefaultTableModel();
        x.setColumnIdentifiers(new Object[]{
            "id", "socio", "fecha", "presencia"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "select usuario.id,(usuario.nombres||' '||usuario.apellidos)as socio,asistencia.fecha,asistencia.presencia\n" +
                    "from usuario, asistencia\n" +
                    "where usuario.id=asistencia.id_usuario and usuario.id=?";
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
                    rs.getString("nombre"),
                    rs.getDate("fecha"),
                    rs.getBoolean("presencia"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;
    }

    /**
     *
     * @param id_horario
     */
    public DefaultTableModel estadisticaAsistenciaByHorario(int id_horario) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel x = new DefaultTableModel();
        x.setColumnIdentifiers(new Object[]{
            "id", "horario", "porcentaje"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "select horario.id,(horario.hora_inicio||' - '||horario.hora_fin) as horario,x.porcentaje\n" +
            "from horario\n" +
            "join (select distinct asistencia.id_horario,(td.cant_x_hor*100)/(select count(*)from asistencia)||' %' as porcentaje\n" +
            "from asistencia\n" +
            "join (select asistencia.id_horario,count(*) as cant_x_hor from asistencia group by asistencia.id_horario) as td\n" +
            "on asistencia.id_horario=td.id_horario\n" +
            "where asistencia.id_horario=?\n" +
            "order by asistencia.id_horario)as x\n" +
            "on horario.id=x.id_horario";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_horario);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                x.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("horario"),
                    rs.getString("porcentaje")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;
    }
}
