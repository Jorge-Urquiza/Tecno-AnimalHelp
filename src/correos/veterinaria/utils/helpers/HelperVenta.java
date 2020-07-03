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
public class HelperVenta {
    public static final String HELP_OBTENERVENTAS = "Obtener Ventas!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es listar las ventas registradas en el sistema,\n"
            + "no es necesario enviar ningun parametro";
    public static final String HELP_REGISTRARVENTA = "Registrar Venta!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es registrar una venta en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- cantidad (Entero)\n"
            + "- fecha (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- precio (Real o Entero con Decimal)\n"
            + "- id_producto (Entero)\n"
	    + "REGISTRARVENTA[5][\"18-05-2019\"][40][1]";
    public static final String HELP_MODIFICARVENTA = "Modificar Venta!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es modificar una venta en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- id (Entero)\n"
            + "- cantidad (Entero)\n"
            + "- fecha (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- precio (Real o Entero con Decimal)\n"
            + "- id_producto (Entero)"
            + "\n"
            + "OPCIONALES: (Usar guion bajo \"_\" para decir no cambiar)\n"
            + "\n"
            + "id, cantidad, fecha, precio, id_producto\n"
	    + "MODIFICARVENTA[1][5][\"18-05-2019\"][30][2]";
}
