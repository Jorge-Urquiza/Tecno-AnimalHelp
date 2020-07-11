/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.utils;

import correos.veterinaria.utils.helpers.HelperAsistencia;
import correos.veterinaria.utils.helpers.HelperHorario;
import correos.veterinaria.utils.helpers.HelperMensualidad;
import correos.veterinaria.utils.helpers.HelperReporte;
import correos.veterinaria.utils.helpers.HelperProducto;
import correos.veterinaria.utils.helpers.HelperVenta;
import correos.veterinaria.utils.helpers.HelperRutinaEjercicio;
import correos.veterinaria.utils.helpers.HelperUsuario;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class Helper {

    public static final String HELP_GLOBAL = "Bienvenido!!!\n\n"
            + "A continuacion se listaran los comandos disponibles para interactuar con el sistema\n"
            + "Para acceder a la documentacion de cada uno, enviar el nombre del comando seguido de la palabra HELP\n\n"
            ////////////////////////
            + "OBTENERUSUARIOS\n"
            + "REGISTRARUSUARIO\n"
            + "MODIFICARUSUARIO\n"
            ////////////////////////
            + "OBTENERASISTENCIAS\n"
            + "REGISTRARASISTENCIA\n"
            + "MODIFICARASISTENCIA\n"
            ////////////////////////
            + "OBTENERHORARIOS\n"
            + "REGISTRARHORARIO\n"
            + "MODIFICARHORARIO\n"
            ////////////////////////
            + "OBTENERPRODUCTOS\n"
            + "REGISTRARPRODUCTO\n"
            + "MODIFICARPRODUCTO\n"
            + "ELIMINARPRODUCTO\n"
            ////////////////////////
            + "OBTENERRUTINAS\n"
            + "REGISTRARRUTINA\n"
            + "MODIFICARRUTINA\n"
            + "OBTENEREJERCICIOS\n"
            + "REGISTRAREJERCICIO\n"
            + "MODIFICAREJERCICIO\n"
            + "REGISTRARRUTINAEJERCICIO\n"
            + "MODIFICARRUTINAEJERCICIO\n"
            + "REGISTRARSOCIORUTINA\n"
            + "MODIFICARSOCIORUTINA\n"
            ////////////////////////
            + "OBTENERVENTAS\n"
            + "REGISTRARVENTA\n"
            + "MODIFICARVENTA\n"
            ////////////////////////
            + "OBTENERMENSUALIDADES\n"
            + "REGISTRARMENSUALIDAD\n"
            + "MODIFICARMENSUALIDAD\n"
            ////////////////////////
            + "OBTENERASISTENCIABYSOCIO\n"
            + "ESTADISTICAASISTENCIABYHORARIO\n"
            + "OBTENERRUTINAEJERCICIOBYRUTINA\n"
            + "ESTADISTICASOCIORUTINABYRUTINA\n"
            + "OBTENERHISTORIALSOCIORUTINA\n"
            + "ESTADISTICAMENSUALIDADACTUAL\n";
    /*
     VETERINARIA ANIMAL HELPS CONSTANTES HELPERS
    
    
     */

    public static final String HELP_OBTENERCATEGORIA = HelperUsuario.HELP_OBTENERUSUARIOS;

    /*
        
     FIN VETENERINA ANIAL HELP
     */
    public static final String HELP_OBTENERUSUARIOS = HelperUsuario.HELP_OBTENERUSUARIOS;
    public static final String HELP_REGISTRARUSUARIO = HelperUsuario.HELP_REGISTRARUSUARIO;
    public static final String HELP_MODIFICARUSUARIO = HelperUsuario.HELP_MODIFICARUSUARIO;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String HELP_OBTENERASISTENCIAS = HelperAsistencia.HELP_OBTENERASISTENCIAS;
    public static final String HELP_REGISTRARASISTENCIA = HelperAsistencia.HELP_REGISTRARASISTENCIA;
    public static final String HELP_MODIFICARASISTENCIA = HelperAsistencia.HELP_MODIFICARASISTENCIA;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String HELP_OBTENERHORARIOS = HelperHorario.HELP_OBTENERHORARIOS;
    public static final String HELP_REGISTRARHORARIO = HelperHorario.HELP_REGISTRARHORARIO;
    public static final String HELP_MODIFICARHORARIO = HelperHorario.HELP_MODIFICARHORARIO;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String HELP_OBTENERPRODUCTOS = HelperProducto.HELP_OBTENERPRODUCTOS;
    public static final String HELP_REGISTRARPRODUCTO = HelperProducto.HELP_REGISTRARPRODUCTO;
    public static final String HELP_MODIFICARPRODUCTO = HelperProducto.HELP_MODIFICARPRODUCTO;
    public static final String HELP_ELIMINARPRODUCTO = HelperProducto.HELP_ELIMINARPRODUCTO;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String HELP_OBTENERRUTINAS = HelperRutinaEjercicio.HELP_OBTENERRUTINAS;
    public static final String HELP_REGISTRARRUTINA = HelperRutinaEjercicio.HELP_REGISTRARRUTINA;
    public static final String HELP_MODIFICARRUTINA = HelperRutinaEjercicio.HELP_MODIFICARRUTINA;
    public static final String HELP_OBTENEREJERCICIOS = HelperRutinaEjercicio.HELP_OBTENEREJERCICIOS;
    public static final String HELP_REGISTRAREJERCICIO = HelperRutinaEjercicio.HELP_REGISTRAREJERCICIO;
    public static final String HELP_MODIFICAREJERCICIO = HelperRutinaEjercicio.HELP_MODIFICAREJERCICIO;
    public static final String HELP_REGISTRARRUTINAEJERCICIO = HelperRutinaEjercicio.HELP_REGISTRARRUTINAEJERCICIO;
    public static final String HELP_MODIFICARRUTINAEJERCICIO = HelperRutinaEjercicio.HELP_MODIFICARRUTINAEJERCICIO;
    public static final String HELP_REGISTRARSOCIORUTINA = HelperRutinaEjercicio.HELP_REGISTRARSOCIORUTINA;
    public static final String HELP_MODIFICARSOCIORUTINA = HelperRutinaEjercicio.HELP_MODIFICARSOCIORUTINA;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String HELP_OBTENERVENTAS = HelperVenta.HELP_OBTENERVENTAS;
    public static final String HELP_REGISTRARVENTA = HelperVenta.HELP_REGISTRARVENTA;
    public static final String HELP_MODIFICARVENTA = HelperVenta.HELP_MODIFICARVENTA;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String HELP_OBTENERMENSUALIDADES = HelperMensualidad.HELP_OBTENERMENSUALIDADES;
    public static final String HELP_REGISTRARMENSUALIDAD = HelperMensualidad.HELP_REGISTRARMENSUALIDAD;
    public static final String HELP_MODIFICARMENSUALIDAD = HelperMensualidad.HELP_MODIFICARMENSUALIDAD;
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String HELP_OBTENERASISTENCIABYSOCIO = HelperReporte.HELP_OBTENERASISTENCIABYSOCIO;
    public static final String HELP_ESTADISTICAASISTENCIABYHORARIO = HelperReporte.HELP_ESTADISTICAASISTENCIABYHORARIO;
    public static final String HELP_OBTENERRUTINAEJERCICIOBYRUTINA = HelperReporte.HELP_OBTENERRUTINAEJERCICIOBYRUTINA;
    public static final String HELP_ESTADISTICASOCIORUTINABYRUTINA = HelperReporte.HELP_ESTADISTICASOCIORUTINABYRUTINA;
    public static final String HELP_OBTENERHISTORIALSOCIORUTINABYSOCIO = HelperReporte.HELP_OBTENERHISTORIALSOCIORUTINABYSOCIO;
    public static final String HELP_ESTADISTICAMENSUALIDADACTUAL = HelperReporte.HELP_ESTADISTICAMENSUALIDADACTUAL;
}
