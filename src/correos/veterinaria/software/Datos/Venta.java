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
public class Venta {

    private int id;
    private int cantidad;
    private Date fecha;
    private float precio;
    private int id_producto;
    public Conexion m_Conexion;
    
    public Venta() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param cantidad
     * @param fecha
     * @param precio
     * @param id_producto
     */
    public void setVenta(int cantidad, Date fecha, float precio, int id_producto) {
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.precio = precio;
        this.id_producto = id_producto;
    }

    /**
     *
     * @param id
     * @param cantidad
     * @param fecha
     * @param precio
     * @param id_producto
     */
    public void setVenta(int id, int cantidad, Date fecha, float precio, int id_producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.precio = precio;
        this.id_producto = id_producto;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getVenta(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel venta = new DefaultTableModel();
        venta.setColumnIdentifiers(new Object[]{
            "id", "cantidad", "fecha", "precio", "id_producto"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "venta.id,\n"
                + "venta.cantidad,\n"
                + "venta.fecha,\n"
                + "venta.precio,\n"
                + "venta.id_producto\n"
                + "FROM venta\n"
                + "WHERE venta.id=?";
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
                venta.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("cantidad"),
                    rs.getDate("fecha"),
                    rs.getFloat("precio"),
                    rs.getInt("id_producto")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return venta;
    }

    /**
     *
     * @return
     */
    public DefaultTableModel getVentas() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel ventas = new DefaultTableModel();
        ventas.setColumnIdentifiers(new Object[]{
            "id", "cantidad", "fecha", "precio", "producto"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "venta.id,\n"
                + "venta.cantidad,\n"
                + "venta.fecha,\n"
                + "venta.precio,\n"
                + "producto.nombre as producto\n"
                + "FROM venta,producto\n"
                + "WHERE venta.id_producto=producto.id";
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
                ventas.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("cantidad"),
                    rs.getDate("fecha"),
                    rs.getFloat("precio"),
                    rs.getString("producto")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ventas;
    }

    public int registrarVenta() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO venta(\n"
                + "cantidad,fecha,precio,id_producto)\n"
                + "VALUES(?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, this.cantidad);
            ps.setDate(2, this.fecha);
            ps.setDouble(3, this.precio);
            ps.setInt(4, this.id_producto);
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

    public void modificarVenta() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE venta SET\n"
                + "cantidad = ?,\n"
                + "fecha = ?,\n"
                + "precio = ?,\n"
                + "id_producto = ?\n"
                + "WHERE venta.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.cantidad);
            ps.setDate(2, this.fecha);
            ps.setDouble(3, this.precio);
            ps.setInt(4, this.id_producto);
            ps.setInt(5, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
