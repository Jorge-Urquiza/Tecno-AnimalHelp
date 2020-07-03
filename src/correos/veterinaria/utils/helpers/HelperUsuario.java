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
public class HelperUsuario {
    public static final String HELP_OBTENERUSUARIOS = "Obtener Usuarios!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es listar los usuarios registrados en el sistema,\n"
            + "no es necesario enviar ningun parametro";
    public static final String HELP_REGISTRARUSUARIO = "Registrar Usuario!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es registrar a un usuario en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- nombres (String con Comillas Dobles)\n"
            + "- apellidos (String con Comillas Dobles)\n"
            + "- telefono (Entero)\n"
            + "- fecha_nacimiento (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- tipo (Entero)\n"
            + "- \t 1: Administrador\n"
            + "- \t 2: Instructor\n"
            + "- \t 3: Socio\n"
            + "- estado (true, false)\n"
            + "REGISTRARUSUARIO[\"Juan Carlos Alberto\"][\"Contreras Montalvan\"][35435746][\"12-12-1997\"][1][true]"; 
    public static final String HELP_MODIFICARUSUARIO = "Modificar Usuario!!!\n"
            + "\n"
            + "Lo que hace el siguiente comando es modificar a un usuario en el sistema.\n"
            + "\n"
            + "PARAMETROS:\n"
            + "\n"
            + "- id (Entero)\n"
            + "- nombres (String con Comillas Dobles)\n"
            + "- apellidos (String con Comillas Dobles)\n"
            + "- telefono (Entero)\n"
            + "- fecha_nacimiento (String con Comillas Dobles) [\"dd-mm-aaaa\"]\n"
            + "- tipo (Entero)\n"
            + "- \t 1: Administrador\n"
            + "- \t 2: Instructor\n"
            + "- \t 3: Socio\n"
            + "- estado (true, false)\n"
            + "\n"
            + "OPCIONALES: (Usar guion bajo \"_\" para decir no cambiar)\n"
            + "Ejemplos:\n"
            + "id, nombres, apellidos, telefono, fecha_nacimiento, tipo, estado\n"
            + "MODIFICARUSUARIO [1][\"Juan Miguel\"][_][2324524][_][2][_]";
}
