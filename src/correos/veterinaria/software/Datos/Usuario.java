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
 * @created 15-Jun-2017 9:04:43 PM
 */
public class Usuario {

    private int id;
    private String nombres;
    private String apellidos;
    private int telefono;
    private Date fecha_nacimiento;
    private int tipo;
    private boolean estado;
    public Conexion m_Conexion;

    public Usuario() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_nacimiento
     * @param tipo
     * @param estado
     */
    public void setUsuario(String nombres, String apellidos, int telefono, Date fecha_nacimiento, int tipo, boolean estado) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.tipo = tipo;
        this.estado = estado;
    }

    /**
     *
     * @param id
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_nacimiento
     * @param tipo
     * @param estado
     */
    public void setUsuario(int id, String nombres, String apellidos, int telefono, Date fecha_nacimiento, int tipo, boolean estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.tipo = tipo;
        this.estado = estado;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getUsuario(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel usuario = new DefaultTableModel();
        usuario.setColumnIdentifiers(new Object[]{
            "id", "nombres", "apellidos", "telefono", "fecha_nacimiento", "tipo", "estado"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "usuario.id,\n"
                + "usuario.nombres,\n"
                + "usuario.apellidos,\n"
                + "usuario.telefono,\n"
                + "usuario.fecha_nacimiento,\n"
                + "usuario.tipo,\n"
                + "usuario.estado\n"
                + "FROM usuario\n"
                + "WHERE usuario.id=?";
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
                usuario.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getInt("telefono"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getInt("tipo"),
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usuario;
    }

    public DefaultTableModel getUsuarios() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel usuarios = new DefaultTableModel();
        usuarios.setColumnIdentifiers(new Object[]{
            "id", "nombres", "apellidos", "telefono", "fecha_nacimiento", "tipo", "estado"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "usuario.id,\n"
                + "usuario.nombres,\n"
                + "usuario.apellidos,\n"
                + "usuario.telefono,\n"
                + "usuario.fecha_nacimiento,\n"
                + "usuario.tipo,\n"
                + "usuario.estado\n"
                + "FROM usuario order by id asc";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                usuarios.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getInt("telefono"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getInt("tipo"),
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usuarios;
    }

    public int registrarUsuario() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO usuario(\n"
                + "nombres,apellidos,telefono,fecha_nacimiento,tipo,estado)\n"
                + "VALUES(?,?,?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombres);
            ps.setString(2, this.apellidos);
            ps.setInt(3, this.telefono);
            ps.setDate(4, this.fecha_nacimiento);
            ps.setInt(5, this.tipo);
            ps.setBoolean(6, this.estado);
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

    public void modificarUsuario() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE usuario SET\n"
                + "nombres = ?,\n"
                + "apellidos = ?,\n"
                + "telefono = ?,\n"
                + "fecha_nacimiento = ?,\n"
                + "tipo = ?,\n"
                + "estado = ?\n"
                + "WHERE usuario.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombres);
            ps.setString(2, this.apellidos);
            ps.setInt(3, this.telefono);
            ps.setDate(4, this.fecha_nacimiento);
            ps.setInt(5, this.tipo);
            ps.setBoolean(6, this.estado);
            ps.setInt(7, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
