/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.Asistencia;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Percy Tomicha
 */
public class AsistenciaNegocio {

    public Asistencia m_Asistencia;

    public AsistenciaNegocio() {
        this.m_Asistencia = new Asistencia();
    }

    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerAsistencia(int id) {
        return this.m_Asistencia.getAsistencia(id);
    }

    /**
     *
     * @return 
     */
    public DefaultTableModel obtenerAsistencias() {
        return this.m_Asistencia.getAsistencias();
    }

    /**
     *
     * @param fecha
     * @param presencia
     * @param id_usuario
     * @param id_horario
     * @return 
     */
    public int registrarAsistencia(Date fecha, boolean presencia, int id_usuario, int id_horario) {
        this.m_Asistencia.setAsistencia(fecha,presencia,id_usuario,id_horario);
        return this.m_Asistencia.registrarAsistencia();
    }

    /**
     *
     * @param id
     * @param fecha
     * @param presencia
     * @param id_usuario
     * @param id_horario
     */
    public void modificarAsistencia(int id, Date fecha, boolean presencia, int id_usuario, int id_horario) {
        this.m_Asistencia.setAsistencia(id,fecha,presencia,id_usuario,id_horario);
        this.m_Asistencia.modificarAsistencia();
    }

    /**
     *
     * @param id_socio
     * @return
     */
    public DefaultTableModel obtenerAsistenciaBySocio(int id_socio) {
        return this.m_Asistencia.obtenerAsistenciaBySocio(id_socio);
    }

    /**
     *
     * @param id_horario
     * @return
     */
    public DefaultTableModel estadisticaAsistenciaByHorario(int id_horario) {
        return this.m_Asistencia.estadisticaAsistenciaByHorario(id_horario);
    }
}
