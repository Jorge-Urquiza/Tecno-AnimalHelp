/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.procesador;

/**
 *
 * @author mauriballes
 */
public class Token {

    // Constantes
    public static final int NUM = 0; // Numero Valor
    public static final int STRING = 1; // String Constante
    public static final int FUNC = 2; // Alguna Funcion
    public static final int GB = 3; //Guion Bajo
    public static final int CA = 4; // Corchete Abierto
    public static final int CC = 5; // Corchete Cerrado
    public static final int COMA = 6; // Coma ,
    public static final int FIN = 7;
    public static final int ERROR = 8;
    public static final int TRUE = 9;
    public static final int FALSE = 10;
    public static final int HELP = 11;

    // Funciones
    public static final int OBTENERUSUARIOS = 100;
    public static final int REGISTRARUSUARIO = 101;
    public static final int MODIFICARUSUARIO = 102;
    //////////////////////////////////////////////
    public static final int OBTENERASISTENCIAS = 103;
    public static final int REGISTRARASISTENCIA = 104;
    public static final int MODIFICARASISTENCIA = 105;
    //////////////////////////////////////////////
    public static final int OBTENERHORARIOS = 106;
    public static final int REGISTRARHORARIO = 107;
    public static final int MODIFICARHORARIO = 108;
    //////////////////////////////////////////////
   /* public static final int OBTENERPRODUCTOS = 109;
    public static final int REGISTRARPRODUCTO = 110;
    public static final int MODIFICARPRODUCTO = 111;
    public static final int ELIMINARPRODUCTO = 112;
    */
    //////////////////////////////////////////////
    public static final int OBTENERRUTINAS = 113;
    public static final int REGISTRARRUTINA = 114;
    public static final int MODIFICARRUTINA = 115;
    public static final int OBTENEREJERCICIOS = 116;
    public static final int REGISTRAREJERCICIO = 117;
    public static final int MODIFICAREJERCICIO = 118;
    public static final int REGISTRARRUTINAEJERCICIO = 119;
    public static final int MODIFICARRUTINAEJERCICIO = 120;
    public static final int REGISTRARSOCIORUTINA = 121;
    public static final int MODIFICARSOCIORUTINA = 122;
    //////////////////////////////////////////////
    public static final int OBTENERVENTAS = 123;
    public static final int REGISTRARVENTA = 124;
    public static final int MODIFICARVENTA = 125;
    //////////////////////////////////////////////
    public static final int OBTENERMENSUALIDADES = 126;
    public static final int REGISTRARMENSUALIDAD = 127;
    public static final int MODIFICARMENSUALIDAD = 128;
    //////////////////////////////////////////////
    public static final int OBTENERASISTENCIABYSOCIO = 129;
    public static final int ESTADISTICAASISTENCIABYHORARIO = 130;
    public static final int OBTENERRUTINAEJERCICIOBYRUTINA = 131;
    public static final int ESTADISTICASOCIORUTINABYRUTINA = 132;
    public static final int OBTENERHISTORIALSOCIORUTINABYSOCIO = 133;
    public static final int ESTADISTICAMENSUALIDADACTUAL = 134;

    //VETERINARIA
    
    //VETERINARIA CU #1
     
    public static final int REGISTRARCATEGORIA = 150;
    public static final int MODIFICARCATEGORIA = 151;
    public static final int OBTENERCATEGORIAS = 152;
    public static final int ELIMINARCARTEGORIA = 153;
     //VETERINARIA CU #2
 
    public static final int REGISTRARVETERINARIO = 154;
    public static final int MODIFICARVETERINARIO = 155;
    public static final int OBTENERVETERINARIOS = 156;
    public static final int ELIMINARVETERINARIO = 157;
    
     //VETERINARIA CU #3
    public static final int REGISTRARPRODUCTO = 158;
    public static final int MODIFICARPRODUCTO = 159;
    public static final int OBTENERPRODUCTOS = 160;
    public static final int ELIMINARPRODUCTO = 161;

    private int nombre;
    private float atributo;
    private String toStr;

    public Token() {
    }

    public Token(int nombre, float atributo, String toStr) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.toStr = toStr;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public float getAtributo() {
        return atributo;
    }

    public void setAtributo(float atributo) {
        this.atributo = atributo;
    }

    public String getToStr() {
        return toStr;
    }

    public void setToStr(String toStr) {
        this.toStr = toStr;
    }

}
