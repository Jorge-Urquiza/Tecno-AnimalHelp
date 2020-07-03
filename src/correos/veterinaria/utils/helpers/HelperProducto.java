/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.utils.helpers;

/**
 *
 * @author Percy Tomicha
 */
public class HelperProducto {
    public static final String HELP_OBTENERPRODUCTOS = "Obtener Productos!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es listar los productos registrados en el sistema,\n"
            + "no es necesario enviar ningun parametro";
    public static final String HELP_REGISTRARPRODUCTO = "Registrar Producto!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es registrar a un producto en el sistema.\n"
            + "\n"
            + "PARAMETRO:\n"
            + "\n"
            + "- nombre (String con Comillas Dobles)\n"
	    + "REGISTRARPRODUCTO[\"Red Bull\"]\n";
    public static final String HELP_MODIFICARPRODUCTO = "Modificar Producto!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es modificar a un producto en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- id (Entero)\n"
            + "- nombre (String con Comillas Dobles)\n"
            + "\n"
            /*+ "OPCIONALES: (Usar guion bajo \"_\" para decir no cambiar)\n"
            + "\n"*/
            + "id, nombre\n"
	    + "MODIFICARPRODUCTO[1][\"Black\"]\n";
    public static final String HELP_ELIMINARPRODUCTO = "Eliminar Producto!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es eliminar a un producto en el sistema.\n"
            + "\n"
            + "PARAMETRO:\n"
            + "\n"
            + "- id (Entero)\n"
	    + "ELIMINARPRODUCTO[1]";
}
