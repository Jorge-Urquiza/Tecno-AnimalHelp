/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.Mensualidad;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Percy Tomicha
 */
public class MensualidadNegocio {

    public Mensualidad m_Mensualidad;

    public MensualidadNegocio() {
        this.m_Mensualidad = new Mensualidad();
    }

    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerMensualidad(int id) {
        return this.m_Mensualidad.getMensualidad(id);
    }

    /**
     *
     * @return 
     */
    public DefaultTableModel obtenerMensualidades() {
        return this.m_Mensualidad.getMensualidades();
    }

    /**
     *
     * @param fecha_inicio
     * @param fecha_fin
     * @param monto
     * @param id_socio
     * @return 
     */
    public int registrarMensualidad(Date fecha_inicio, Date fecha_fin, float monto, int id_socio) {
        this.m_Mensualidad.setMensualidad(fecha_inicio,fecha_fin,monto,id_socio);
        return this.m_Mensualidad.registrarMensualidad();
    }

    /**
     *
     * @param id
     * @param fecha_inicio
     * @param fecha_fin
     * @param monto
     * @param id_socio
     */
    public void modificarMensualidad(int id, Date fecha_inicio, Date fecha_fin, float monto, int id_socio) {
        this.m_Mensualidad.setMensualidad(id,fecha_inicio,fecha_fin,monto,id_socio);
        this.m_Mensualidad.modificarMensualidad();
    }

    /**
     *
     * @return
     */
    public DefaultTableModel estadisticaMensualidadActual() {
        return this.m_Mensualidad.estadisticaMensualidadActual();
    }
    
}
