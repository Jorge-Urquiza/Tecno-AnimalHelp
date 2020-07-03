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
public class RutinaEjercicio {

    private int id;
    private int repeticiones;
    private int series;
    private int id_rutina;
    private int id_ejercicio;
    public Conexion m_Conexion;
    
    public RutinaEjercicio() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param repeticiones
     * @param series
     * @param id_rutina
     * @param id_ejercicio
     */
    public void setRutinaEjercicio(int repeticiones, int series, int id_rutina, int id_ejercicio) {
        this.repeticiones = repeticiones;
        this.series = series;
        this.id_rutina = id_rutina;
        this.id_ejercicio = id_ejercicio;
    }

    /**
     *
     * @param id
     * @param repeticiones
     * @param series
     * @param id_rutina
     * @param id_ejercicio
     */
    public void setRutinaEjercicio(int id, int repeticiones, int series, int id_rutina, int id_ejercicio) {
        this.id = id;
        this.repeticiones = repeticiones;
        this.series = series;
        this.id_rutina = id_rutina;
        this.id_ejercicio = id_ejercicio;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getRutinaEjercicio(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel rutina_ejercicio = new DefaultTableModel();
        rutina_ejercicio.setColumnIdentifiers(new Object[]{
            "id", "repeticiones", "series", "id_rutina", "id_ejercicio"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "rutina_ejercicio.id,\n"
                + "rutina_ejercicio.repeticiones,\n"
                + "rutina_ejercicio.series,\n"
                + "rutina_ejercicio.id_rutina,\n"
                + "rutina_ejercicio.id_ejercicio\n"
                + "FROM rutina_ejercicio\n"
                + "WHERE rutina_ejercicio.id=?";
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
                rutina_ejercicio.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("repeticiones"),
                    rs.getInt("series"),
                    rs.getInt("id_rutina"),
                    rs.getInt("id_ejercicio"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rutina_ejercicio;
    }

    /**
     *
     * @return
     */
    public DefaultTableModel getRutinaEjercicios() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel rutina_ejercicios = new DefaultTableModel();
        rutina_ejercicios.setColumnIdentifiers(new Object[]{
            "id", "repeticiones", "series", "rutina", "ejercicio"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
                String sql = "SELECT\n"
                + "rutina_ejercicio.id,\n"
                + "rutina_ejercicio.repeticiones,\n"
                + "rutina_ejercicio.series,\n"
                + "(rutina.nombre||'-'||rutina.musculo)as rutina,\n"
                + "(ejercicio.nombre) as ejercicio\n"
                + "FROM rutina_ejercicio,rutina,ejercicio\n"
                + "WHERE rutina_ejercicio.id_rutina=rutina.id and rutina_ejercicio.id_ejercicio=ejercicio.id\n"
                + "order by id asc";
                System.out.println(sql);
//        String sql = "SELECT\n"
//                + "rutina_ejercicio.id,\n"
//                + "rutina_ejercicio.repeticiones,\n"
//                + "rutina_ejercicio.series,\n"
//                + "(rutina.nombres||' '||rutina.apellidos)as rutina,\n"
//                + "(ejercicio.hora_inicio||'-'||ejercicio.hora_fin) as ejercicio\n"
//                + "FROM rutina_ejercicio,rutina,ejercicio order by id asc";
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
                rutina_ejercicios.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("repeticiones"),
                    rs.getInt("series"),
                    rs.getString("rutina"),
                    rs.getString("ejercicio")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rutina_ejercicios;
    }

    public int registrarRutinaEjercicio() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO rutina_ejercicio(\n"
                + "repeticiones,series,id_rutina,id_ejercicio)\n"
                + "VALUES(?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, this.repeticiones);
            ps.setInt(2, this.series);
            ps.setInt(3, this.id_rutina);
            ps.setInt(4, this.id_ejercicio);
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

    public void modificarRutinaEjercicio() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE rutina_ejercicio SET\n"
                + "repeticiones = ?,\n"
                + "series = ?,\n"
                + "id_rutina = ?,\n"
                + "id_ejercicio = ?\n"
                + "WHERE rutina_ejercicio.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.repeticiones);
            ps.setInt(2, this.series);
            ps.setInt(3, this.id_rutina);
            ps.setInt(4, this.id_ejercicio);
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
     * @param id_rutina
     */
    public DefaultTableModel obtenerRutinaEjercicioByRutina(int id_rutina) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel x = new DefaultTableModel();
        x.setColumnIdentifiers(new Object[]{
            "id", "repeticiones", "series", "rutina_ejercicio"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "select rutina_ejercicio.id,rutina_ejercicio.repeticiones,rutina_ejercicio.series,(rutina.nombre||' -> '||ejercicio.nombre)as rutina_ejercicio\n" +
                    "from rutina_ejercicio,rutina,ejercicio\n" +
                    "where rutina_ejercicio.id_rutina=rutina.id and rutina_ejercicio.id_ejercicio=ejercicio.id and rutina.id=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_rutina);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                x.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("repeticiones"),
                    rs.getInt("series"),
                    rs.getString("rutina_ejercicio"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;
    }

    /**
     *
     * @param id_rutina
     */
    public DefaultTableModel estadisticaSocioRutinaByRutina(int id_rutina) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel x = new DefaultTableModel();
        x.setColumnIdentifiers(new Object[]{
            "id", "rutina", "porcentaje"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "select rutina.id,(rutina.nombre||' - '||rutina.musculo) as rutina,x.porcentaje\n" +
                    "from rutina\n" +
                    "join (select distinct socio_rutina.id_rutina,(td.cant_x_hor*100)/(select count(*)from socio_rutina)||' %' as porcentaje\n" +
                    "from socio_rutina\n" +
                    "join (select socio_rutina.id_rutina,count(*) as cant_x_hor from socio_rutina group by socio_rutina.id_rutina) as td\n" +
                    "on socio_rutina.id_rutina=td.id_rutina\n" +
                    "where socio_rutina.id_rutina=?\n" +
                    "order by socio_rutina.id_rutina)as x\n" +
                    "on rutina.id=x.id_rutina\n" +
                    "order by porcentaje desc";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_rutina);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                x.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("rutina"),
                    rs.getString("porcentaje"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;
    }
}
