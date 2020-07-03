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
public class HelperHorario {
    public static final String HELP_OBTENERHORARIOS = "Obtener Horarios!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es listar los horarios registrados en el sistema,\n"
            + "no es necesario enviar ningun parametro";
    public static final String HELP_REGISTRARHORARIO = "Registrar Horario!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es registrar a un horario en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- hora_inicio (String con Comillas Dobles)\n"
            + "- hora_fin (String con Comillas Dobles)\n"
            + "- tipo (Entero)\n"
            + "- \t 1: Musculacion\n"
            + "- \t 2: Zumba\n"
            + "- \t 3: CrossFit\n"
	    + "REGISTRARHORARIO[\"10:30\"][\"11:30\"][2]";
    public static final String HELP_MODIFICARHORARIO = "Modificar Horario!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es modificar a un horario en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- id (Entero)\n"
            + "- hora_inicio (String con Comillas Dobles)\n"
            + "- hora_fin (String con Comillas Dobles)\n"
            + "- tipo (Entero)\n"
            + "- \t 1: Musculacion\n"
            + "- \t 2: Zumba\n"
            + "- \t 3: CrossFit\n"
            + "\n"
            + "OPCIONALES: (Usar guion bajo \"_\" para decir no cambiar)\n"
            + "\n"
            + "id, hora_inicio, hora_fin, tipo\n"
	    + "MODIFICARHORARIO[1][\"12:30\"][_][3]\n";
}
