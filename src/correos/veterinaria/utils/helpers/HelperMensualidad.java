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
public class HelperMensualidad {
    public static final String HELP_OBTENERMENSUALIDADES = "Obtener Mensualidades!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es listar las mensualidades registradas en el sistema,\n"
            + "no es necesario enviar ningun parametro";
    public static final String HELP_REGISTRARMENSUALIDAD = "Registrar Mensualidad!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es registrar a una mensualidad en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- fecha_inicio (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- fecha_fin (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- monto (Real o Entero con Decimal)\n"
            + "- id_usuario (Entero)\n"
	    + "REGISTRARMENSUALIDAD[\"18-04-2019\"][\"18-05-2019\"][48][2]";
    public static final String HELP_MODIFICARMENSUALIDAD = "Modificar Mensualidad!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es modificar a una mensualidad en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- id (Entero)\n"
            + "- fecha_inicio (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- fecha_fin (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- monto (Real o Entero con Decimal)\n"
            + "- id_usuario (Entero)\n"
            + "\n"
            + "OPCIONALES: (Usar guion bajo \"_\" para decir no cambiar)\n"
            + "\n"
            + "fecha_inicio, fecha_fin, monto, id_usuario\n"
            //+ "\n"
	    + "MODIFICARMENSUALIDAD[1][_][\"18-05-2019\"][49][_]";
}
