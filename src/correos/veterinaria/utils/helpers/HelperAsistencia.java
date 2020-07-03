/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.utils.helpers;

/**
 *
 * @author Percy Tomicha, jdaniel rojas
 */
public class HelperAsistencia {
    public static final String HELP_OBTENERASISTENCIAS = "Obtener Asistencias!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es listar las asistencias de los usuario registrados en el sistema,\n"
            + "no es necesario enviar ningun parametro";
    public static final String HELP_REGISTRARASISTENCIA = "Registrar Asistencias!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es registrar una asistencia en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- fecha (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- presencia (true, false)\n"
            + "- id_usuario (Entero)\n"
            + "- id_horario (Entero)\n"
	    + "REGISTRARASISTENCIA[\"20-07-2019\"][true][1][2]";
    public static final String HELP_MODIFICARASISTENCIA = "Modificar Asistencias!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es modificar una asistencia en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- id (Entero)\n"
            + "- fecha (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- presencia (true, false)\n"
            + "- id_usuario (Entero)\n"
            + "- id_horario (Entero)"
            + "\n"
            + "OPCIONALES: (Usar guion bajo \"_\" para decir no cambiar)\n"
            + "\n"
            + "id, fecha, presencia, id_usuario, id_horario\n"
	    + "MODIFICARASISTENCIA[1][_][_][2][3]";
}
