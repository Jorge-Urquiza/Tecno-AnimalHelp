/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Negocio;

import correos.veterinaria.software.Datos.Horario;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Percy Tomicha
 */
public class HorarioNegocio {
    public Horario m_Horario;

    public HorarioNegocio() {
        this.m_Horario = new Horario();
    }

    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerHorario(int id) {
        return this.m_Horario.getHorario(id);
    }

    public DefaultTableModel obtenerHorarios() {
        return this.m_Horario.getHorarios();
    }

    /**
     *
     * @param hora_inicio
     * @param hora_fin
     * @param tipo
     * @return 
     */
    public int registrarHorario(String hora_inicio, String hora_fin, int tipo) {
        // No olvidar primero settear los datos
        this.m_Horario.setHorario(hora_inicio, hora_fin, tipo);
        return this.m_Horario.registrarHorario();
    }

    /**
     *
     * @param id
     * @param hora_inicio
     * @param hora_fin
     * @param tipo
     */
    public void modificarHorario(int id, String hora_inicio, String hora_fin, int tipo) {
        // No olvidar primero settear los datos
        this.m_Horario.setHorario(id, hora_inicio, hora_fin, tipo);
        this.m_Horario.modificarHorario();
    }
}
