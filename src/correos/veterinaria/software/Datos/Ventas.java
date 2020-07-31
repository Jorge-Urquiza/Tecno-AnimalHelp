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
 * @author Jorge Luis Urquiza
 */
public class Ventas {

    private int id;
    private String nit;
    private Date fecha;
    private int total; // total de la venta 
    private int cliente_id;
    private int veterinario_id;

    private Conexion m_Conexion;

    public Ventas() {
        m_Conexion = Conexion.getInstancia();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setVenta(int id, String nit, Date fecha, int cliente_id, int veterinario_id) {
        this.id = id;
        this.nit = nit;
        this.fecha = fecha;
        this.cliente_id = cliente_id;
        this.veterinario_id = veterinario_id;
        total = 0;
    }

    public void setVenta(String nit, Date fecha, int cliente_id, int veterinario_id) {
        this.fecha = fecha;
        this.nit = nit;
        this.cliente_id = cliente_id;
        this.veterinario_id = veterinario_id;
        total = 0;
    }

    public int registrar() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO ventas (\n"
                + "nit,fecha,total,cliente_id,veterinario_id)\n"
                + "VALUES(?,?,?,?,?)";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nit);
            ps.setDate(2, this.fecha);
            ps.setInt(3, this.total);
            ps.setInt(4, this.cliente_id);
            ps.setInt(5, this.veterinario_id);
            int rows = ps.executeUpdate();
            // Cierro Conexion
            this.m_Conexion.cerrarConexion();
            // Obtengo el id generado pra devolverlo
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    id = generateKeys.getInt(1);
                    return id;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1; // NO REGISTRO

    }

    public DefaultTableModel getVenta(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel venta = new DefaultTableModel();
        venta.setColumnIdentifiers(new Object[]{
            "ID", "NIT", "FECHA", "CLIENTE_ID", "VETERINARIO_ID"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT * FROM ventas WHERE id=?";
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
                venta.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nit"),
                    rs.getDate("fecha"),
                    rs.getInt("cliente_id"),
                    rs.getInt("veterinario_id")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return venta;
    }

    public DefaultTableModel getVentas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void actualizarTotal(int total) {
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        PreparedStatement ps = null;
        String query = "UPDATE ventas SET \n"
                + "total = ? \n"
                + "WHERE id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, total);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Actualizado!!");
            con.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

    }

    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modificar() {
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        PreparedStatement ps = null;
        String query = "UPDATE ventas SET \n"
                + "nit = ?,\n"
                + "fecha = ?, \n"
                + "cliente_id = ?, \n"
                + "veterinario_id = ? \n"
                + "WHERE id = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, nit);
            ps.setDate(2, fecha);
            ps.setInt(3, cliente_id);
            ps.setInt(4, veterinario_id);
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("Modificado!!");
            con.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }
}
