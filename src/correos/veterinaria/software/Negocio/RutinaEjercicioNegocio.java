/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.Ejercicio;
import correos.veterinaria.software.Datos.Rutina;
import correos.veterinaria.software.Datos.RutinaEjercicio;
import correos.veterinaria.software.Datos.SocioRutina;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Percy Tomicha
 */
public class RutinaEjercicioNegocio {

    public Ejercicio m_Ejercicio;
    public Rutina m_Rutina;
    public RutinaEjercicio m_RutinaEjercicio;
    public SocioRutina m_SocioRutina;

    public RutinaEjercicioNegocio() {
        this.m_Ejercicio = new Ejercicio();
        this.m_Rutina = new Rutina();
        this.m_RutinaEjercicio = new RutinaEjercicio();
        this.m_SocioRutina = new SocioRutina();
    }

    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerRutina(int id) {
        return this.m_Rutina.getRutina(id);
    }
    /**
     *
     * @return 
     */
    public DefaultTableModel obtenerRutinas() {
        return this.m_Rutina.getRutinas();
    }
    /**
     *
     * @param nombre
     * @param musculo
     * @return 
     */
    public int registrarRutina(String nombre, String musculo) {
        this.m_Rutina.setRutina(nombre,musculo);
        return this.m_Rutina.registrarRutina();
    }
    /**
     *
     * @param id
     * @param nombre
     * @param musculo
     */
    public void modificarRutina(int id, String nombre, String musculo) {
        this.m_Rutina.setRutina(id,nombre,musculo);
        this.m_Rutina.modificarRutina();
    }
    

    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerEjercicio(int id) {
        return this.m_Ejercicio.getEjercicio(id);
    }
    /**
     *
     * @return 
     */
    public DefaultTableModel obtenerEjercicios() {
        return this.m_Ejercicio.getEjercicios();
    }
    /**
     *
     * @param nombre
     * @return 
     */
    public int registrarEjercicio(String nombre) {
        this.m_Ejercicio.setEjercicio(nombre);
        return this.m_Ejercicio.registrarEjercicio();
    }
    /**
     *
     * @param id
     * @param nombre
     */
    public void modificarEjercicio(int id, String nombre) {
        this.m_Ejercicio.setEjercicio(id,nombre);
        this.m_Ejercicio.modificarEjercicio();
    }
    
    
    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerRutinaEjercicio(int id) {
        return this.m_RutinaEjercicio.getRutinaEjercicio(id);
    }
    /**
     *
     * @param repeticiones
     * @param series
     * @param id_rutina
     * @param id_ejercicio
     * @return 
     */
    public int registrarRutinaEjercicio(int repeticiones, int series, int id_rutina, int id_ejercicio) {
        this.m_RutinaEjercicio.setRutinaEjercicio(repeticiones,series,id_rutina,id_ejercicio);
        return this.m_RutinaEjercicio.registrarRutinaEjercicio();
    }
    /**
     *
     * @param id
     * @param repeticiones
     * @param series
     * @param id_rutina
     * @param id_ejercicio
     */
    public void modificarRutinaEjercicio(int id, int repeticiones, int series, int id_rutina, int id_ejercicio) {
        this.m_RutinaEjercicio.setRutinaEjercicio(id,repeticiones,series,id_rutina,id_ejercicio);
        this.m_RutinaEjercicio.modificarRutinaEjercicio();
    }
    
    
    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerSocioRutina(int id) {
        return this.m_SocioRutina.getSocioRutina(id);
    }
    /**
     *
     * @param fecha
     * @param id_socio
     * @param id_rutina
     * @return 
     */
    public int registrarSocioRutina(Date fecha, int id_socio, int id_rutina) {
        this.m_SocioRutina.setSocioRutina(fecha,id_socio,id_rutina);
        return this.m_SocioRutina.registrarSocioRutina();
    }
    /**
     *
     * @param id
     * @param fecha
     * @param id_socio
     * @param id_rutina
     */
    public void modificarSocioRutina(int id, Date fecha, int id_socio, int id_rutina) {
        this.m_SocioRutina.setSocioRutina(id,fecha,id_socio,id_rutina);
        this.m_SocioRutina.modificarSocioRutina();
    }

    /**
     *
     * @param id_rutina
     * @return
     */
    public DefaultTableModel obtenerRutinaEjercicioByRutina(int id_rutina) {
        return this.m_RutinaEjercicio.obtenerRutinaEjercicioByRutina(id_rutina);
    }

    /**
     *
     * @param id_rutina
     * @return
     */
    public DefaultTableModel estadisticaSocioRutinaByRutina(int id_rutina) {
        return this.m_RutinaEjercicio.estadisticaSocioRutinaByRutina(id_rutina);
    }
    
    /**
     *
     * @param id_socio
     * @return
     */
    public DefaultTableModel obtenerHistorialSocioRutinaBySocio(int id_socio) {
        return this.m_SocioRutina.obtenerHistorialSocioRutinaBySocio(id_socio);
    }
}
