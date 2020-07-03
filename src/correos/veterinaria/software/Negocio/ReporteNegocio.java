package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.Usuario;
import correos.veterinaria.software.Datos.Asistencia;
import correos.veterinaria.software.Datos.RutinaEjercicio;
import correos.veterinaria.software.Datos.Rutina;
import correos.veterinaria.software.Datos.Mensualidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:42 PM
 */
public class ReporteNegocio {

    public Usuario m_Usuario;
    public Asistencia m_Asistencia;
    public RutinaEjercicio m_RutinaEjercicio;
    public Rutina m_Rutina;
    public Mensualidad m_Mensualidad;

    public ReporteNegocio() {
        this.m_Usuario = new Usuario();
        this.m_Asistencia = new Asistencia();
        this.m_RutinaEjercicio = new RutinaEjercicio();
        this.m_Rutina = new Rutina();
        this.m_Mensualidad = new Mensualidad();
    }

    /**
     *
     * @return 
     */
    /*public DefaultTableModel obtenerAsistenciaBySocio(int id_socio) {
        return this.m_Asistencia.obtenerAsistenciaBySocio(id_socio);
    }*/

    /**
     *
     * @return 
     */
    /*public DefaultTableModel estadisticaAsistenciaByHorario(int id_horario) {
        return this.m_Asistencia.estadisticaAsistenciaByHorario(id_horario);
    }*/
    
    /**
     *
     * @return 
     */
    /*public DefaultTableModel obtenerRutinaEjercicioByRutina(int id_rutina) {
        return this.m_RutinaEjercicio.obtenerRutinaEjercicioByRutina(id_rutina);
    }*/
    
    /**
     *
     * @return 
     */
    /*public DefaultTableModel estadisticaSocioRutinaByRutina(int id_rutina) {
        return this.m_RutinaEjercicio.estadisticaSocioRutinaByRutina(id_rutina);
    }*/
    
    /**
     *
     * @return 
     */
    /*public DefaultTableModel obtenerHistorialSocioRutinaBySocio(int id_socio) {
        return this.m_Rutina.obtenerHistorialSocioRutinaBySocio(id_socio);
    }*/
    
    /**
     *
     * @return 
     */
    /*public DefaultTableModel estadisticaMensualidadActual() {
        return this.m_Mensualidad.estadisticaMensualidadActual();
    }*/

    //---------------------------------------------------------------------------------------------
    /**
     *
     * @param id_alumno
     * @return
     */
    /*public DefaultTableModel reporteHistorico(int id_alumno) {
        DefaultTableModel historico = new DefaultTableModel();
        historico.setColumnIdentifiers(new Object[]{
            "curso", "grupo", "nota"
        });

        // Abrir Conexion
        Conexion.getInstancia().abrirConexion();
        Connection con = Conexion.getInstancia().getConexion();

        String sql = "SELECT curso.nombre AS curso ,grupo.nombre AS grupo, nota \n"
                + "FROM kardex, grupo, curso \n"
                + "WHERE kardex.id_alumno = ? \n"
                + "and kardex.id_grupo = grupo.id\n"
                + "AND grupo.id_curso = curso.id";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            // Cerrar Conexion
            Conexion.getInstancia().cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                historico.addRow(new Object[]{
                    rs.getString("curso"),
                    rs.getString("grupo"),
                    rs.getString("nota")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return historico;
    }*/

    /**
     *
     * @param id_grupo
     * @param mes
     * @param gestion
     * @return
     */
    /*public DefaultTableModel reporteAlumnosInscritos(int id_grupo, int mes, int gestion) {
        DefaultTableModel listaAlumnos = new DefaultTableModel();
        listaAlumnos.setColumnIdentifiers(new Object[]{
            "id", "apellidos", "nombres"
        });

        // Abrir Conexion
        Conexion.getInstancia().abrirConexion();
        Connection con = Conexion.getInstancia().getConexion();

        String sql = "SELECT alumno.id, alumno.apellidos, alumno.nombres\n"
                + "FROM alumno, kardex, grupo\n"
                + "WHERE kardex.id_grupo = ?\n"
                + "AND alumno.id = kardex.id_alumno\n"
                + "AND kardex.mes = ?\n"
                + "AND kardex.gestion = ?\n"
                + "GROUP BY alumno.id";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_grupo);
            ps.setInt(2, mes);
            ps.setInt(3, gestion);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            // Cerrar Conexion
            Conexion.getInstancia().cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                listaAlumnos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("apellidos"),
                    rs.getString("nombres")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listaAlumnos;
    }*/

    /*public DefaultTableModel reporteOfertaCursos() {
        DefaultTableModel oferta = new DefaultTableModel();
        oferta.setColumnIdentifiers(new Object[]{
            "curso", "grupo"
        });

        // Abrir Conexion
        Conexion.getInstancia().abrirConexion();
        Connection con = Conexion.getInstancia().getConexion();

        String sql = "SELECT curso.nombre AS curso, grupo.nombre AS grupo\n"
                + "FROM curso, grupo\n"
                + "WHERE grupo.id_curso = curso.id\n"
                + "AND curso.estado = TRUE";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            // Cerrar Conexion
            Conexion.getInstancia().cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                oferta.addRow(new Object[]{
                    rs.getString("curso"),
                    rs.getString("grupo"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return oferta;
    }*/
}
