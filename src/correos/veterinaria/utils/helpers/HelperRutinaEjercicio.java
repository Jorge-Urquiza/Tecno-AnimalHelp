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
public class HelperRutinaEjercicio {
    public static final String HELP_OBTENERRUTINAS = "Obtener Rutinas!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es listar las rutinas registradas en el sistema,\n"
            + "no es necesario enviar ningun parametro";
    public static final String HELP_REGISTRARRUTINA = "Registrar Rutina!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es registrar una Rutina en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- nombre (String con Comillas Dobles)\n"
            + "- musculo (String con Comillas Dobles)\n"
	    + "REGISTRARRUTINA[\"Rutina 120 kg 1.70 m\"][\"brazos\"]";
    public static final String HELP_MODIFICARRUTINA = "Modificar Rutina!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es modificar a una Rutina registradas en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- id (Entero)\n"
            + "- nombre (String con Comillas Dobles)\n"
            + "- musculo (String con Comillas Dobles)\n"
            + "\n"
            + "OPCIONALES: (Usar guion bajo \"_\" para decir no cambiar)\n"
            + "\n"
            + "id, nombre, musculo\n"
	    + "MODIFICARRUTINA[1][\"Rutina 120 kg 1.70 m\"][_]";
    public static final String HELP_OBTENEREJERCICIOS = "Obtener Ejercicios!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es listar los ejercicios registrados en el sistema,\n"
            + "no es necesario enviar ningun parametro";
    public static final String HELP_REGISTRAREJERCICIO = "Registrar Ejercicio!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es registrar un Ejercicio en el sistema.\n"
            + "\n"
            + "PARAMETRO:\n"
            + "\n"
            + "- nombre (String con Comillas Dobles)\n"
            + "REGISTRAREJERCICIO[\"sentadilla\"]";
    public static final String HELP_MODIFICAREJERCICIO = "Modificar Ejercicio!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es modificar a un Ejercicio registrados en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- id (Entero)\n"
            + "- nombre (String con Comillas Dobles)\n"
            + "\n"
            /*+ "OPCIONALES: (Usar guion bajo \"_\" para decir no cambiar)\n"
            + "\n"*/
            + "id, nombre\n"
	    + "MODIFICAREJERCICIO[1][\"ranita\"]";
    public static final String HELP_REGISTRARRUTINAEJERCICIO = "Registrar Ejercicios a las Rutinas!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es registrar un ejercicio a una rutina en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- repeticiones (Entero)\n"
            + "- series (Entero)\n"
            + "- id_rutina (Entero)\n"
            + "- id_ejercicio (Entero)\n"
	    + "REGISTRARRUTINAEJERCICIO[20][3][2][3]";
    public static final String HELP_MODIFICARRUTINAEJERCICIO = "Modificar Ejercicios a las Rutinas!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es modificar un ejercicio a una rutina en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- id (Entero)\n"
            + "- repeticiones (Entero)\n"
            + "- series (Entero)\n"
            + "- id_rutina (Entero)\n"
            + "- id_ejercicio (Entero)\n"
            + "\n"
            + "OPCIONALES: (Usar guion bajo \"_\" para decir no cambiar)\n"
            + "\n"
            + "id, repeticiones, series, id_rutina, id_ejercicio\n"
	    + "MODIFICARRUTINAEJERCICIO[1][20][_][2][3]";
    public static final String HELP_REGISTRARSOCIORUTINA = "Registrar Rutinas de Socio!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es registrar una Rutina al Socio en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- fecha (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- id_socio (Entero)\n"
            + "- id_rutina (Entero)\n"
	    + "REGISTRARSOCIORUTINA[\"21-06-2019\"][3][1]";
    public static final String HELP_MODIFICARSOCIORUTINA = "Modificar Rutinas de Socio!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es modificar a una Rutina al Socio en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- id (Entero)\n"
            + "- fecha (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- id_socio (Entero)\n"
            + "- id_rutina (Entero)\n"
            + "\n"
            + "OPCIONALES: (Usar guion bajo \"_\" para decir no cambiar)\n"
            + "\n"
            + "id, fecha, id_socio, id_rutina\n"
	    + "MODIFICARSOCIORUTINA[1][\"21-06-2019\"][3][1]";
}
