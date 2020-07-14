/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.procesador;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author mauriballes
 */
public class TPC {
//TPC se usa para reconocer las palabras reservadas y el token ID.

    private static final LinkedList<String> lexemas = new LinkedList<>(Arrays.asList(
            "HELP",
            "TRUE",
            "FALSE",
            "OBTENERUSUARIOS",
            "REGISTRARUSUARIO",
            "MODIFICARUSUARIO",//////////////CU1/////////////////
            "OBTENERASISTENCIAS",
            "REGISTRARASISTENCIA",
            "MODIFICARASISTENCIA",////////////CU2///////////////////
            "OBTENERHORARIOS",
            "REGISTRARHORARIO",
            "MODIFICARHORARIO",/////////////CU3/////////////////
        //    "OBTENERPRODUCTOS",
         //   "REGISTRARPRODUCTO",
           // "MODIFICARPRODUCTO",
            //"ELIMINARPRODUCTO",/////////////CU4/////////////////
            "OBTENERRUTINAS",
            "REGISTRARRUTINA",
            "MODIFICARRUTINA",
            "OBTENEREJERCICIOS",
            "REGISTRAREJERCICIO",
            "MODIFICAREJERCICIO",
            "REGISTRARRUTINAEJERCICIO",
            "MODIFICARRUTINAEJERCICIO",
            "REGISTRARSOCIORUTINA",
            "MODIFICARSOCIORUTINA",////////////////CU5//////////////
            "OBTENERVENTAS",
            "REGISTRARVENTA",
            "MODIFICARVENTA",//////////CU6////////////////////
            "OBTENERMENSUALIDADES",
            "REGISTRARMENSUALIDAD",
            "MODIFICARMENSUALIDAD",////////////CU7//////////////////
            "OBTENERASISTENCIABYSOCIO",
            "ESTADISTICAASISTENCIABYHORARIO",
            "OBTENERRUTINAEJERCICIOBYRUTINA",
            "ESTADISTICASOCIORUTINABYRUTINA",
            "OBTENERHISTORIALSOCIORUTINABYSOCIO",
            "ESTADISTICAMENSUALIDADACTUAL",////////////CU8//////////////////

            //CASOS DE USO VETERINARIA 
            //CU1
            "REGISTRARCATEGORIA",
            "MODIFICARCATEGORIA",
            "OBTENERCATEGORIAS",
            "ELIMINARCARTEGORIA",
            ///CU2
            "REGISTRARVETERINARIO",
            "MODIFICARVETERINARIO",
            "OBTENERVETERINARIOS",
            "ELIMINARVETERINARIO",
            
///CU3
            "REGISTRARPRODUCTO",
            "MODIFICARPRODUCTO",
            "OBTENERPRODUCTOS",
            "ELIMINARPRODUCTO"
    ));
//Token asociado
    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.HELP, -1, "HELP"),
            new Token(Token.TRUE, -1, "TRUE"),
            new Token(Token.FALSE, -1, "FALSE"),
            new Token(Token.FUNC, Token.OBTENERUSUARIOS, "OBTENERUSUARIOS"),
            new Token(Token.FUNC, Token.REGISTRARUSUARIO, "REGISTRARUSUARIO"),
            new Token(Token.FUNC, Token.MODIFICARUSUARIO, "MODIFICARUSUARIO"),//////////////CU1/////////////////
            new Token(Token.FUNC, Token.OBTENERASISTENCIAS, "OBTENERASISTENCIAS"),
            new Token(Token.FUNC, Token.REGISTRARASISTENCIA, "REGISTRARASISTENCIA"),
            new Token(Token.FUNC, Token.MODIFICARASISTENCIA, "MODIFICARASISTENCIA"),////////////CU2///////////////////
            new Token(Token.FUNC, Token.OBTENERHORARIOS, "OBTENERHORARIOS"),
            new Token(Token.FUNC, Token.REGISTRARHORARIO, "REGISTRARHORARIO"),
            new Token(Token.FUNC, Token.MODIFICARHORARIO, "MODIFICARHORARIO"),////////////CU3///////////////////
           // new Token(Token.FUNC, Token.OBTENERPRODUCTOS, "OBTENERPRODUCTOS"),
            //new Token(Token.FUNC, Token.REGISTRARPRODUCTO, "REGISTRARPRODUCTO"),
            //new Token(Token.FUNC, Token.MODIFICARPRODUCTO, "MODIFICARPRODUCTO"),
            //new Token(Token.FUNC, Token.ELIMINARPRODUCTO, "ELIMINARPRODUCTO"),////////////CU4///////////////////
            new Token(Token.FUNC, Token.OBTENERRUTINAS, "OBTENERRUTINAS"),
            new Token(Token.FUNC, Token.REGISTRARRUTINA, "REGISTRARRUTINA"),
            new Token(Token.FUNC, Token.MODIFICARRUTINA, "MODIFICARRUTINA"),
            new Token(Token.FUNC, Token.OBTENEREJERCICIOS, "OBTENEREJERCICIOS"),
            new Token(Token.FUNC, Token.REGISTRAREJERCICIO, "REGISTRAREJERCICIO"),
            new Token(Token.FUNC, Token.MODIFICAREJERCICIO, "MODIFICAREJERCICIO"),
            new Token(Token.FUNC, Token.REGISTRARRUTINAEJERCICIO, "REGISTRARRUTINAEJERCICIO"),
            new Token(Token.FUNC, Token.MODIFICARRUTINAEJERCICIO, "MODIFICARRUTINAEJERCICIO"),
            new Token(Token.FUNC, Token.REGISTRARSOCIORUTINA, "REGISTRARSOCIORUTINA"),
            new Token(Token.FUNC, Token.MODIFICARSOCIORUTINA, "MODIFICARSOCIORUTINA"),////////////CU5///////////////////
            new Token(Token.FUNC, Token.OBTENERVENTAS, "OBTENERVENTAS"),
            new Token(Token.FUNC, Token.REGISTRARVENTA, "REGISTRARVENTA"),
            new Token(Token.FUNC, Token.MODIFICARVENTA, "MODIFICARVENTA"),////////////CU6///////////////////
            new Token(Token.FUNC, Token.OBTENERMENSUALIDADES, "OBTENERMENSUALIDADES"),
            new Token(Token.FUNC, Token.REGISTRARMENSUALIDAD, "REGISTRARMENSUALIDAD"),
            new Token(Token.FUNC, Token.MODIFICARMENSUALIDAD, "MODIFICARMENSUALIDAD"),////////////CU7///////////////////
            new Token(Token.FUNC, Token.OBTENERASISTENCIABYSOCIO, "OBTENERASISTENCIABYSOCIO"),
            new Token(Token.FUNC, Token.ESTADISTICAASISTENCIABYHORARIO, "ESTADISTICAASISTENCIABYHORARIO"),
            new Token(Token.FUNC, Token.OBTENERRUTINAEJERCICIOBYRUTINA, "OBTENERRUTINAEJERCICIOBYRUTINA"),
            new Token(Token.FUNC, Token.ESTADISTICASOCIORUTINABYRUTINA, "ESTADISTICASOCIORUTINABYRUTINA"),
            new Token(Token.FUNC, Token.OBTENERHISTORIALSOCIORUTINABYSOCIO, "OBTENERHISTORIALSOCIORUTINABYSOCIO"),
            new Token(Token.FUNC, Token.ESTADISTICAMENSUALIDADACTUAL, "ESTADISTICAMENSUALIDADACTUAL"),////////////CU7///////////////////

            ////VETERINARIA ANIMALHELP
            //C1
          
            new Token(Token.FUNC, Token.REGISTRARCATEGORIA, "REGISTRARCATEGORIA"),
            new Token(Token.FUNC, Token.MODIFICARCATEGORIA, "MODIFICARCATEGORIA"),
            new Token(Token.FUNC, Token.OBTENERCATEGORIAS, "OBTENERCATEGORIAS"),
            new Token(Token.FUNC, Token.ELIMINARCARTEGORIA, "ELIMINARCARTEGORIA"),
            //CU2
            new Token(Token.FUNC, Token.REGISTRARVETERINARIO, "REGISTRARVETERINARIO"),
            new Token(Token.FUNC, Token.MODIFICARVETERINARIO, "MODIFICARVETERINARIO"),
            new Token(Token.FUNC, Token.OBTENERVETERINARIOS, "OBTENERVETERINARIOS"),
            new Token(Token.FUNC, Token.ELIMINARVETERINARIO, "ELIMINARVETERINARIO"),
            
            //CU3
            new Token(Token.FUNC, Token.REGISTRARPRODUCTO, "REGISTRARPRODUCTO"),
            new Token(Token.FUNC, Token.MODIFICARPRODUCTO, "MODIFICARPRODUCTO"),
            new Token(Token.FUNC, Token.OBTENERPRODUCTOS, "OBTENERPRODUCTOS"),
            new Token(Token.FUNC, Token.ELIMINARPRODUCTO, "ELIMINARPRODUCTO")
    ));

    public static Token estaEnTPC(String lexema) {
        lexema = lexema.toUpperCase();
        for (int i = 0; i < lexemas.size(); i++) {
            if (lexemas.get(i).toUpperCase().equals(lexema)) {
                Token token = new Token();
                token.setNombre(tokens.get(i).getNombre());
                token.setAtributo(tokens.get(i).getAtributo());
                token.setToStr(tokens.get(i).getToStr());
                return token;
            }
        }
        return null;
    }
}
