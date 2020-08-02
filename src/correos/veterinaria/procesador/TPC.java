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
 * @author Jorge Luis Urquiza
 */
public class TPC {
//TPC se usa para reconocer las palabras reservadas y el token ID.
    private static final LinkedList<String> lexemas = new LinkedList<>(Arrays.asList(
            "HELP",
            "TRUE",
            "FALSE",
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
            "ELIMINARPRODUCTO",
            ///CU4
            "REGISTRARCLIENTE",
            "MODIFICARCLIENTE",
            "OBTENERCLIENTES",
            "ELIMINARCLIENTE",
            ///CU5
            "REGISTRARMASCOTA",
            "MODIFICARMASCOTA",
            "OBTENERMASCOTAS",
            "ELIMINARMASCOTA",
            //cu6
            "REGISTRARVENTA",
            "MODIFICARVENTA",
            "OBTENERVENTAS",
            "ELIMINARVENTA",
            //cu7
            "REGISTRARATENCION",
            "MODIFICARATENCION",
            "OBTENERATENCIONES",
            "ELIMINARATENCION",
            //CU8 REPORTES
            "VENTASMENSUALES",
            "TOP3CLIENTESCOMPRAS",
            "TOP3MASCOTASATENDIDAS",
            "TOP3PRODUCTOSVENDIDOS",
            "VENTASTOTALDEHOY"
    ));
//Token asociado
    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.HELP, -1, "HELP"),
            new Token(Token.TRUE, -1, "TRUE"),
            new Token(Token.FALSE, -1, "FALSE"),
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
            new Token(Token.FUNC, Token.ELIMINARPRODUCTO, "ELIMINARPRODUCTO"),
            //CU4
            new Token(Token.FUNC, Token.REGISTRARCLIENTE, "REGISTRARCLIENTE"),
            new Token(Token.FUNC, Token.MODIFICARCLIENTE, "MODIFICARCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTES, "OBTENERCLIENTES"),
            new Token(Token.FUNC, Token.ELIMINARCLIENTE, "ELIMINARCLIENTE"),
            //CU5
            new Token(Token.FUNC, Token.REGISTRARMASCOTA, "REGISTRARMASCOTA"),
            new Token(Token.FUNC, Token.MODIFICARMASCOTA, "MODIFICARMASCOTA"),
            new Token(Token.FUNC, Token.OBTENERMASCOTAS, "OBTENERMASCOTAS"),
            new Token(Token.FUNC, Token.ELIMINARMASCOTA, "ELIMINARMASCOTA"),
            //CU6
            new Token(Token.FUNC, Token.REGISTRARVENTA, "REGISTRARVENTA"),
            new Token(Token.FUNC, Token.MODIFICARVENTA, "MODIFICARVENTA"),
            new Token(Token.FUNC, Token.OBTENERVENTAS, "OBTENERVENTAS"),
            new Token(Token.FUNC, Token.ELIMINARVENTA, "ELIMINARVENTA"),
            //CU7
            new Token(Token.FUNC, Token.REGISTRARATENCION, "REGISTRARATENCION"),
            new Token(Token.FUNC, Token.MODIFICARATENCION, "MODIFICARATENCION"),
            new Token(Token.FUNC, Token.OBTENERATENCIONES, "OBTENERATENCIONES"),
            new Token(Token.FUNC, Token.ELIMINARATENCION, "ELIMINARATENCION"),
            //CU8 Reportes
            new Token(Token.FUNC, Token.VENTASMENSUALES, "VENTASMENSUALES"),
            new Token(Token.FUNC, Token.TOP3CLIENTESCOMPRAS, "TOP3CLIENTESCOMPRAS"),
            new Token(Token.FUNC, Token.TOP3MASCOTASATENDIDAS, "TOP3MASCOTASATENDIDAS"),
            new Token(Token.FUNC, Token.TOP3PRODUCTOSVENDIDOS, "TOP3PRODUCTOSVENDIDOS"),
            new Token(Token.FUNC, Token.VENTASTOTALDEHOY, "VENTASTOTALDEHOY")
    ));
//verifica si un lexema se encuentra en la tpc
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
