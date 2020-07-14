/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.utils;

import correos.veterinaria.presentador.Block;
import correos.veterinaria.presentador.Board;
import correos.veterinaria.presentador.Table;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class Utils {

    public static Date convertirFechas(String fecha) {
        // Formato de fecha a ingresar dd-MM-yyyy
        Date fechaNueva = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date fechaJava = formato.parse(fecha);
            fechaNueva = new Date(fechaJava.getTime());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return fechaNueva;
    }

    public static String getDestinatario(String contenido) {
        String destinatario = "";
        // Dividir en lineas
        String[] lines = contenido.split("\n");
        //System.out.println("Linea 2 : "+lines[2]);
        if (lines[2].contains("Return-Path:")) {
            int inicio = lines[2].indexOf("<");
            int fin = lines[2].indexOf(">");
            String s = lines[2].substring(inicio + 1, fin);
            //System.out.println("Linea 2 : "+s);
            if (s.contains(".com")) {
                return s;
            }
            return s;
        }
        int index = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > 5
                    && lines[i].substring(0, 5).toUpperCase().equals("FROM:")) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            // Quitar la palabra 'From: '
            destinatario = lines[index].substring(6);
            lines = destinatario.split(" ");
            if (lines.length == 1) { // Correo del Server
                destinatario = lines[0];
            } else { // Desde otro Servidor de Correo
                destinatario = lines[lines.length - 1];
                destinatario = destinatario.split("<")[1].split(">")[0];
            }
        }
        return destinatario;
    }

    public static String getSubjectOrden(String contenido) {
        String orden = "";
        // Dividir en lineas
        String[] lines = contenido.split("\n");
        int index = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > 9
                    && lines[i].substring(0, 9).toUpperCase().equals("SUBJECT: ")) {
                index = i;
                break;
            }
            if (lines[i].length() > 14
                    && lines[i].substring(0, 14).toUpperCase().equals("SUBJECT: FWD: ")) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            // Quitar la palabra 'Subject: '
            orden = lines[index].substring(9) + lines[index + 1].substring(0) + lines[index + 2].substring(0) + lines[index + 3].substring(0);
            //System.out.println("Mensaje : "+orden);
            int i = orden.indexOf("Thread-Topic");
            if (i == -1) {
                i = orden.indexOf("To");
            }
            if (i == -1) {
                i = orden.indexOf("MIME-Version:");
            }
            if (i != -1) {
                orden = orden.substring(0, i);
            }
        }
        return orden;
    }

    public static String dibujarTabla(DefaultTableModel tabla) {
        String tableString = "";
        ArrayList<String> headers = new ArrayList<>();
        ArrayList<List<String>> rowList = new ArrayList<>();

        // Agregando Los Headers
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            headers.add(tabla.getColumnName(i));
        }

        // Agregando Content
        for (int i = 0; i < tabla.getRowCount(); i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                row.add(String.valueOf(tabla.getValueAt(i, j)));
            }
            rowList.add(row.subList(0, row.size()));
        }

        if (rowList.size() < 1) {
            return "(Tabla Vacia)";
        }

        // Creando Tabla para mostrar
        Board board = new Board(125);
        Table table = new Table(board, 125, headers, rowList);
        Block tableBlock = table.tableToBlocks();
        board.setInitialBlock(tableBlock);
        board.build();
        tableString = board.getPreview();

        return tableString;
    }

    public static String Aumentar(String a) {
        int i = a.length();
        String b = a;
//        int c=i/8;
//        while(c<=4){
//            b=b+"\t";
//            c=c+1;
//        }
        while (i <= 50) {
            b = b + " ";
            i++;
        }
        return b;
    }

    public static String dibujarTabla1(DefaultTableModel tabla, String Head[]) {
        String tableString = "";
        ArrayList<String> headers = new ArrayList<>();
        ArrayList<List<String>> rowList = new ArrayList<>();
        // Agregando Los Headers
        String Resultados = "";
        //System.out.println("+-------------------------------------------------------------");
        Resultados = Resultados + "+-------------------------------------------------------------+\n";
        //System.out.println("|\tLISTADO DE REGISTROS : ");
        Resultados = Resultados + "\tLISTADO DE REGISTROS : \n";
        // Agregando Los Headers
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            headers.add(tabla.getColumnName(i));
        }
        //System.out.println("|-------------------------------------------------------------");
        Resultados = Resultados + "+-------------------------------------------------------------+\n";

        // Agregando Content
        for (int i = 0; i < tabla.getRowCount(); i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                row.add(String.valueOf(tabla.getValueAt(i, j)));
                //System.out.println("|\t"+Aumentar(Head[j])+row.get(j));
                if (Head[j].contains("Tipo")) {
                    if (row.get(j).contains("1")) {
                        row.set(j, "Administrador");
                    } else if (row.get(j).contains("2")) {
                        row.set(j, "Instructor");
                    } else if (row.get(j).contains("3")) {
                        row.set(j, "Socio");
                    }
                } else if (Head[j].contains("Estado")) {
                    if (row.get(j).contains("t")) {
                        row.set(j, "Activo");
                    } else if (row.get(j).contains("f")) {
                        row.set(j, "Inactivo");
                    }
                }
                Resultados = Resultados + "\t" + Head[j] + "  " + row.get(j) + '\n';
            }
            rowList.add(row.subList(0, row.size()));
            //System.out.println("+-------------------------------------------------------------");
            if (i != tabla.getRowCount() - 1) {
                Resultados = Resultados + "-------------------------------------------------------------\n";
            } else {
                Resultados = Resultados + "+-------------------------------------------------------------+\n";
            }
        }

        if (rowList.size() < 1) {
            return "(Tabla Vacia)";
        }

        // Creando Tabla para mostrar
//        Board board = new Board(250);
//        Table table = new Table(board, 250, headers, rowList);
//        Block tableBlock = table.tableToBlocks();
//        board.setInitialBlock(tableBlock);
//        board.build();
//        tableString = board.getPreview();
        System.out.println(Resultados);
        //return tableString;
        return Resultados;
    }

    public static Mensaje dibujarMenuAyuda() {
        //System.out.println("|-------------------------------------------------------------");
        String Cabecera = "MENÚ DE AYUDA- ANIMAL HELP";
        LinkedList<String> Head = new LinkedList<>(Arrays.asList(
                "CASO DE USO",
                "DETALLE",
                "EJEMPLO"
        ));
        LinkedList<String> CasosDeUso = new LinkedList<>(Arrays.asList(
                //Veterinaria
                //cu1
                "REGISTRAR CATEGORIA",
                "OBTENER CATEGORIA",
                "MODIFICAR CATEGORIA",
                "ELIMINAR CATEGORIA",
                //cu2
                "REGISTRAR VETERINARIO",
                "OBTENER VETERINARIO",
                "MODIFICAR VETERINARIO",
                "ELIMINAR VETERINARIO",
                //cu3
                "REGISTRAR PRODUCTO",
                "OBTENER PRODUCTOS",
                "MODIFICAR PRODUCTO",
                "ELIMINAR PRODUCTO",
                //////////////
                /*
                 "OBTENER USUARIOS",
                 "REGISTRAR USUARIO",
                 "MODIFICAR USUARIO",//////////////CU1/////////////////
                 "OBTENER ASISTENCIAS",
                 "REGISTRAR ASISTENCIA",
                 "MODIFICAR ASISTENCIA",////////////CU2///////////////////
                 "OBTENER HORARIOS",
                 "REGISTRAR HORARIO",
                 "MODIFICAR HORARIO",/////////////CU3/////////////////
                 "OBTENER PRODUCTOS",
                 "REGISTRAR PRODUCTO",
                 "MODIFICAR PRODUCTO",
                 "ELIMINAR PRODUCTO",/////////////CU4/////////////////
                 "OBTENER RUTINAS",
                 "REGISTRAR RUTINA",
                 "MODIFICAR RUTINA",
                 "OBTENER EJERCICIOS",
                 "REGISTRAR EJERCICIO",
                 "MODIFICAR EJERCICIO",
                 "REGISTRAR RUTINA EJERCICIO",
                 "MODIFICAR RUTINA EJERCICIO",
                 "REGISTRAR SOCIO RUTINA",
                 "MODIFICAR SOCIO RUTINA",////////////////CU5//////////////
                 "OBTENER VENTAS",
                 "REGISTRAR VENTA",
                 "MODIFICAR VENTA",//////////CU6////////////////////
                 "OBTENER MENSUALIDADES",
                 "REGISTRAR MENSUALIDAD",
                 "MODIFICAR MENSUALIDAD",////////////CU7//////////////////
                 "OBTENER ASISTENCIA BY SOCIO",
                 "ESTADISTICA ASISTENCIA BY HORARIO",
                 "OBTENER RUTINA EJERCICIO BY RUTINA",
                 "ESTADISTICA SOCIO RUTINA BY RUTINA",
                 "OBTENER HISTORIAL SOCIO RUTINA BY SOCIO",*/
                "ESTADISTICA MENSUALIDAD ACTUAL"////////////CU8//////////////////
        ));
        LinkedList<String> Detalles = new LinkedList<>(Arrays.asList(
                //veterinaria animal help

                //CU1
                "REGISTRARCATEGORIA[\"Nombre\"][\"Descripcion\"]",
                "OBTENERCATEGORIAS",
                "MODIFICARCATEGORIA[\"ID categoria\"][\"Nombre\"][\"Descripcion\"]",
                "ELIMINARCATEGORIA[\"ID categoria\"]",
                //CU2

                "REGISTRARVETERINARIO[\"Nombre\"][\"Apellidos\"][Cedula Identidad][\"Celular\"][\"Direcciopn\"]",
                "OBTENERVETERINARIOS",
                "MODIFICARVETERINARIO[id de veterinario][\"Nombre\"][\"Descripcion\"]",
                "ELIMINARVETERINARIO[id veterinario]",
                //cu3
                "REGISTRARPRODUCTO[\"Nombre\"][\"Precio\"][ID Categoria]",
                "OBTENERPRODUCTOS",
                "MODIFICARPRODUCTO[ID Producto][\"Nombre\"][\"Precio\"][ID Categoria]",
                "ELIMINARPRODUCTO[ID Producto]",
                /*
                 "OBTENERUSUARIOS",
                 "REGISTRARUSUARIO[\"Nombres\"][\"Apellidos\"][Telefono][\"Fecha de Nacimiento\"][Tipo de Usuario(1, 2, o 3)][Estado del Usuario(true o false)]",
                 "MODIFICARUSUARIO [id del Usuario][\"Nombres\"][\"Apellidos\"][Telefono][\"Fecha de Nacimiento\"][Tipo de Usuario(1, 2, o 3)][Estado del Usuario(true o false)]",//////////////CU1/////////////////
                 "OBTENERASISTENCIAS",
                 "REGISTRARASISTENCIA[\"Fecha de la Asistencia\"][Presencia (true o false)][Id del Usuario][Id del Horario]",
                 "MODIFICARASISTENCIA[Id de la Asistencia][\"Fecha de la Asistencia\"][Presencia (true o false)][Id del Usuario][Id del Horario]",////////////CU2///////////////////
                 "OBTENERHORARIOS",
                 "REGISTRARHORARIO[\"Hora de Inicio\"][\"Hora Final\"][Tipo de Horario(1, 2, o 3)]",
                 "MODIFICARHORARIO[Id del Horario][\"Hora de Inicio\"][\"Hora Final\"][Tipo de Horario(1, 2, o 3)]\n",/////////////CU3/////////////////
                 "OBTENERPRODUCTOS",
                 "REGISTRARPRODUCTO[\"Nombre del Producto\"]\n",
                 "MODIFICARPRODUCTO[Id del Producto][\"Nombre del Producto\"]\n",
                 "ELIMINARPRODUCTO[Id del Producto]",/////////////CU4/////////////////
                 "OBTENERRUTINAS",
                 "REGISTRARRUTINA[\"Nombre de la Rutina\"][\"Musculo que sera trabajado\"]",
                 "MODIFICARRUTINA[Id de la Rutina][\"Nombre de la Rutina\"][\"Musculo que sera trabajado\"]",
                 "OBTENEREJERCICIOS",
                 "REGISTRAREJERCICIO[\"Nombre del Ejercicio\"]",
                 "MODIFICAREJERCICIO[Id del Ejercicio][\"Nombre del Ejercicio\"]",
                 "REGISTRARRUTINAEJERCICIO[Repeticiones del Ejercicio][Series del Ejercicio][Id de la Rutina][Id del Ejercicio]",
                 "MODIFICARRUTINAEJERCICIO[Id de la Rutina Ejercicio][Repeticiones del Ejercicio][Series del Ejercicio][Id de la Rutina][Id del Ejercicio]",
                 "REGISTRARSOCIORUTINA[\"Fecha que realizara el Socio la Rutina\"][Id del Usuario][Id de la Rutina]",
                 "MODIFICARSOCIORUTINA[Id del socio Rutina][\"Fecha que realizara el Socio la Rutina\"][Id del Usuario][Id de la Rutina]",////////////////CU5//////////////
                 "OBTENERVENTAS",
                 "REGISTRARVENTA[Cantidad a vender][\"Fecha de la Venta\"][Precio de la Venta][Id del Produto]",
                 "MODIFICARVENTA[Id de la Venta][Cantidad a vender][\"Fecha de la Venta\"][Precio de la Venta][Id del Produto]",//////////CU6////////////////////
                 "OBTENERMENSUALIDADES",
                 "REGISTRARMENSUALIDAD[\"Fecha Inicio de la Mensualidad\"][\"Fecha Final de la Mensualidad\"][Monto de la Mensualidad][Id del Usuario]",
                 "MODIFICARMENSUALIDAD[Id de la Mensualidad][\"Fecha Inicio de la Mensualidad\"][\"Fecha Final de la Mensualidad\"][Monto de la Mensualidad][Id del Usuario]",////////////CU7//////////////////
                 "OBTENERASISTENCIABYSOCIO[Id del Socio]",
                 "ESTADISTICAASISTENCIABYHORARIO[Id del Horario]",
                 "OBTENERRUTINAEJERCICIOBYRUTINA[Id de la Rutina]",
                 "ESTADISTICASOCIORUTINABYRUTINA[Id de la Rutina]",
                 "OBTENERHISTORIALSOCIORUTINABYSOCIO[Id del Socio]",*/
                "ESTADISTICAMENSUALIDADACTUAL"////////////CU8//////////////////
        ));
        LinkedList<String> Ejemplos = new LinkedList<>(Arrays.asList(
                //Veterinaria
                //CU1
                "REGISTRARCATEGORIA[\"Balanceado\"][\"Comida saludable\"]",
                "OBTENERCATEGORIAS",
                "MODIFICARCATEGORIA[1][_][\"Comida\"] "+Cadenas.GUION_BAJO,
                "ELIMINARCATEGORIA[2]",
                //CU2

                "REGISTRARVETERINARIO[\"Luciano\"][\"Aguierre Peña\"][12530][\"+591700365436\"][\"Calle Guabira#03\"]",
                "OBTENERVETERINARIOS",
                "MODIFICARVETERINARIO[1][_][_][853651][\"+591 75575746\"][\"Calle Santa Cruz#1113\"]",
                "ELIMINARVETERINARIO[2]",
                
                //cu3
                "REGISTRARPRODUCTO[\"Doggy Cachorro\"][\"15\"][1]",
                "OBTENERPRODUCTOS",
                "MODIFICARPRODUCTO[1][\"Doggy\"][\"18\"][_]"+ Cadenas.GUION_BAJO,
                "ELIMINARPRODUCTO[2]",
                /*
                 //
                 "OBTENERUSUARIOS",
                 "REGISTRARUSUARIO[\"Juan Carlos Alberto\"][\"Contreras Montalvan\"][35435746][\"12-12-1997\"][1][true]",
                 "MODIFICARUSUARIO [1][\"Juan Miguel\"][_][2324524][_][2][_]",//////////////CU1/////////////////
                 "OBTENERASISTENCIAS",
                 "REGISTRARASISTENCIA[\"20-07-2019\"][true][1][2]",
                 "MODIFICARASISTENCIA[1][_][_][2][3]",////////////CU2///////////////////
                 "OBTENERHORARIOS",
                 "REGISTRARHORARIO[\"10:30\"][\"11:30\"][2]",
                 "MODIFICARHORARIO[1][\"12:30\"][_][3]\n",/////////////CU3/////////////////
                 "OBTENERPRODUCTOS",
                 "REGISTRARPRODUCTO[\"Red Bull\"]\n",
                 "MODIFICARPRODUCTO[1][\"Black\"]\n",
                 "ELIMINARPRODUCTO[1]",/////////////CU4/////////////////
                 "OBTENERRUTINAS",
                 "REGISTRARRUTINA[\"Rutina 120 kg 1.70 m\"][\"brazos\"]",
                 "MODIFICARRUTINA[1][\"Rutina 120 kg 1.70 m\"][_]",
                 "OBTENEREJERCICIOS",
                 "REGISTRAREJERCICIO[\"sentadilla\"]",
                 "MODIFICAREJERCICIO[1][\"ranita\"]",
                 "REGISTRARRUTINAEJERCICIO[20][3][2][3]",
                 "MODIFICARRUTINAEJERCICIO[1][20][_][2][3]",
                 "REGISTRARSOCIORUTINA[\"21-06-2019\"][3][1]",
                 "MODIFICARSOCIORUTINA[1][\"21-06-2019\"][3][1]",////////////////CU5//////////////
                 "OBTENERVENTAS",
                 "REGISTRARVENTA[5][\"18-05-2019\"][40][1]",
                 "MODIFICARVENTA[1][5][\"18-05-2019\"][30][1]",//////////CU6////////////////////
                 "OBTENERMENSUALIDADES",
                 "REGISTRARMENSUALIDAD[\"18-04-2019\"][\"18-05-2019\"][48][2]",
                 "MODIFICARMENSUALIDAD[1][_][\"18-05-2019\"][49][_]",////////////CU7//////////////////
                 "OBTENERASISTENCIABYSOCIO[1]",
                 "ESTADISTICAASISTENCIABYHORARIO[1]",
                 "OBTENERRUTINAEJERCICIOBYRUTINA[1]",
                 "ESTADISTICASOCIORUTINABYRUTINA[1]",
                 "OBTENERHISTORIALSOCIORUTINABYSOCIO[1]",
                 */
                "ESTADISTICAMENSUALIDADACTUAL"////////////CU8//////////////////
        ));
        String data = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + "h1 {\n"
                + "    color: black;\n"
                + "}\n"
                + "h2,img {\n"
                + "    display: inline-block;\n"
                + "}\n"
                + "img{"
                + "    float: right;\n"
                + "    vertical-align: top;\n"
                + "    margin-bottom: 0.45em;"
                + "}\n"
                + "table {\n"
                + "    border-collapse: collapse;\n"
                //+ "    border: green 5px solid;"
                + "    width: 100%;\n"
                + "}\n"
                + "\n"
                + "th, td {\n"
                + "    border: 2px solid #53ADA9;"
                + "    text-align: center;\n"
                + "    padding: 10px;\n"
                + "}\n"
                + "h1{\n"
                + "     text-align: center;"
                + "}\n"
                + "\n"
                + "tr:nth-child(even){background-color: #075C6D }\n"
                + "\n"
                + "th {\n"
                + "    background-color: #399393;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>" + Cabecera + "</h1> \n"
                + "<table class=\"w3-table-all\">\n";
        data = data + "<tr>\n";
        for (int i = 0; i < Head.size(); i++) {
            data = data + "<th>" + Head.get(i) + "</th>\n";
        }
        data = data + "</tr>\n";
        // Agregando Content
        for (int i = 0; i < CasosDeUso.size(); i++) {
            data = data + "<tr>";
            data = data + "<td><strong>" + CasosDeUso.get(i) + "</strong></td>";
            data = data + "<td><strong>" + Detalles.get(i) + "</strong></td>";
            data = data + "<td>" + Ejemplos.get(i) + "</td>";
            data = data + "</tr>\n";
        }
        data += "</table>\n"
                + "</body>\n"
                + "</html>\n";
        Mensaje mensaje = new Mensaje(Cabecera, data);
        return mensaje;
    }

    public static Mensaje dibujarTabla3(DefaultTableModel tabla, String Head[], String Cabecera) {
        String data = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <title>Bootstrap Example</title>\n"
                + "  <meta charset=\"utf-8\">\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n"
                + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js\"></script>\n"
                + "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n"
                + "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "<div class=\"container\">\n"
                + "  <h2>Table Head Colors</h2>\n"
                + "  <p>The .thead-dark class adds a black background to table headers, and the .thead-light class adds a grey background to table headers:</p>\n"
                + "  <table class=\"table\">\n"
                + "    <thead class=\"thead-dark\">\n"
                + "      <tr>\n"
                + "        <th>Firstname</th>\n"
                + "        <th>Lastname</th>\n"
                + "        <th>Email</th>\n"
                + "      </tr>\n"
                + "    </thead>\n"
                + "    <tbody>\n"
                + "      <tr>\n"
                + "        <td>John</td>\n"
                + "        <td>Doe</td>\n"
                + "        <td>john@example.com</td>\n"
                + "      </tr>\n"
                + "      <tr>\n"
                + "        <td>Mary</td>\n"
                + "        <td>Moe</td>\n"
                + "        <td>mary@example.com</td>\n"
                + "      </tr>\n"
                + "      <tr>\n"
                + "        <td>July</td>\n"
                + "        <td>Dooley</td>\n"
                + "        <td>july@example.com</td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table>\n"
                + "  <table class=\"table\">\n"
                + "    <thead class=\"thead-light\">\n"
                + "      <tr>\n"
                + "        <th>Firstname</th>\n"
                + "        <th>Lastname</th>\n"
                + "        <th>Email</th>\n"
                + "      </tr>\n"
                + "    </thead>\n"
                + "    <tbody>\n"
                + "      <tr>\n"
                + "        <td>John</td>\n"
                + "        <td>Doe</td>\n"
                + "        <td>john@example.com</td>\n"
                + "      </tr>\n"
                + "      <tr>\n"
                + "        <td>Mary</td>\n"
                + "        <td>Moe</td>\n"
                + "        <td>mary@example.com</td>\n"
                + "      </tr>\n"
                + "      <tr>\n"
                + "        <td>July</td>\n"
                + "        <td>Dooley</td>\n"
                + "        <td>july@example.com</td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table>\n"
                + "</div>\n"
                + "\n"
                + "</body>\n"
                + "</html>";
        Mensaje mensaje = new Mensaje(Cabecera, data);
        return mensaje;
    }

    public static String quitarComillas(String texto) {
        int len = texto.length() - 1;
        return texto.substring(1, len);
    }

    public static Mensaje dibujarTabla2(DefaultTableModel tabla, String Head[], String Cabecera) {
        String tableString = "";
        ArrayList<String> headers = new ArrayList<>();
        ArrayList<List<String>> rowList = new ArrayList<>();
        // Agregando Los Headers
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            headers.add(tabla.getColumnName(i));
        }
        //System.out.println("|-------------------------------------------------------------");       

        String data = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + "h1 {\n"
                + "    color: black;\n"
                + "}\n"
                + "h2,img {\n"
                + "    display: inline-block;\n"
                + "}\n"
                + "img{"
                + "    float: right;\n"
                + "    vertical-align: top;\n"
                + "    margin-bottom: 0.45em;"
                + "}\n"
                + "table {\n"
                + "    border-collapse: collapse;\n"
                //+ "    border: green 5px solid;"
                + "    width: 100%;\n"
                + "}\n"
                + "\n"
                + "th, td {\n"
                + "    border: 1px solid green;"
                + "    text-align: center;\n"
                + "    padding: 10px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even){background-color: #f2f2f2}\n"
                + "\n"
                + "th {\n"
                + "    background-color: #1C647A;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>" + Cabecera + "</h1> \n"
                + "<table class=\"w3-table-all\">\n"
                + "<tr>\n";
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            data = data + "<th>" + Head[i] + "</th>\n";
        }
        data = data + "</tr>\n";
        // Agregando Content
        for (int i = 0; i < tabla.getRowCount(); i++) {
            ArrayList<String> row = new ArrayList<>();
            data = data + "<tr>";
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                row.add(String.valueOf(tabla.getValueAt(i, j)));
                if (Head[j].contains("Tipo")) {
                    if (Cabecera.contains("USUARIO")) {
                        if (row.get(j).contains("1")) {
                            row.set(j, "Administrador");
                        } else if (row.get(j).contains("2")) {
                            row.set(j, "Instructor");
                        } else if (row.get(j).contains("3")) {
                            row.set(j, "Socio");
                        }
                    } else if (Cabecera.contains("HORARIO")) {
                        if (row.get(j).contains("1")) {
                            row.set(j, "Musculación");
                        } else if (row.get(j).contains("2")) {
                            row.set(j, "Zumba");
                        } else if (row.get(j).contains("3")) {
                            row.set(j, "CrossFit");
                        }
                    } else if (Cabecera.contains("ASISTENCIA")) {
                        String x = row.get(j);
                        String y = x;
                        x = x.substring(0, x.length() - 1);
                        if (y.contains("1")) {
                            row.set(j, x + " (Administrador)");
                        } else if (y.contains("2")) {
                            row.set(j, x + " (Instructor)");
                        } else {
                            row.set(j, x + " (Socio)");
                        }
                    }
                } else if (Head[j].contains("Estado")) {
                    if (row.get(j).contains("t")) {
                        row.set(j, "Activo");
                    } else if (row.get(j).contains("f")) {
                        row.set(j, "Inactivo");
                    }
                } else if (Head[j].contains("Presencia")) {
                    if (row.get(j).contains("t")) {
                        row.set(j, "Asistió");
                    } else if (row.get(j).contains("f")) {
                        row.set(j, "Faltó");
                    }
                }
                //System.out.println("|\t"+Aumentar(Head[j])+row.get(j));
                if (row.get(j).contains("Asistió") || row.get(j).contains("Activo")) {
                    data = data + "<td style=\"color:#0aa000\">" + row.get(j) + "</td>";
                } else if (row.get(j).contains("Faltó") || row.get(j).contains("Inactivo")) {
                    data = data + "<td style=\"color:#ff0000\">" + row.get(j) + "</td>";
                } else if (row.get(j).contains("Socio") || row.get(j).contains("Zumba")) {
                    data = data + "<td style=\"color:#0000ff\">" + row.get(j) + "</td>";
                } else if (row.get(j).contains("Instructor") || row.get(j).contains("CrossFit")) {
                    data = data + "<td style=\"color:#0b0b61\">" + row.get(j) + "</td>";
                } else {
                    data = data + "<td>" + row.get(j) + "</td>";
                }
            }
            data = data + "</tr>\n";
            rowList.add(row.subList(0, row.size()));
            //System.out.println("+-------------------------------------------------------------");
        }
        data += "</table>\n"
                + "</body>\n"
                + "</html>\n";

        if (rowList.size() < 1) {
            data = "(Tabla Vacia)";
        }
        Mensaje mensaje = new Mensaje(Cabecera, data);
        return mensaje;
    }

    public static Mensaje dibujarTablaHtml(DefaultTableModel tabla, String[] Head, String Cabecera) {
        ArrayList<String> headers = new ArrayList<>();
        ArrayList<List<String>> rowList = new ArrayList<>();
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            headers.add(tabla.getColumnName(i));
        }
        String data = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + "h1 {\n"
                + "    color: black;\n"
                + "}\n"
                + "h2,img {\n"
                + "    display: inline-block;\n"
                + "}\n"
                + "img{"
                + "    float: right;\n"
                + "    vertical-align: top;\n"
                + "    margin-bottom: 0.45em;"
                + "}\n"
                + "table {\n"
                + "    border-collapse: collapse;\n"
                + "    width: 100%;\n"
                + "}\n"
                + "\n"
                + "th, td {\n"
                + "    border: 1px solid #53ADA9;"
                + "    text-align: center;\n"
                + "    padding: 10px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even){background-color: #f2f2f2}\n"
                + "\n"
                + "th {\n"
                + "    background-color: #399393;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1 align=\"center\">" + Cabecera + "</h1> \n"
                + "<table class=\"w3-table-all\">\n"
                + "<tr>\n";
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            data = data + "<th>" + Head[i] + "</th>\n";
        }
        data = data + "</tr>\n";
        // Agregando Content
        for (int i = 0; i < tabla.getRowCount(); i++) {
            ArrayList<String> row = new ArrayList<>();
            data = data + "<tr>";
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                row.add(String.valueOf(tabla.getValueAt(i, j)));
                data = data + "<td><strong>" + row.get(j) + "</strong></td>";
            }
            data = data + "</tr>\n";
            rowList.add(row.subList(0, row.size()));
            //System.out.println("+-------------------------------------------------------------");
        }
        data += "</table>\n"
                + "</body>\n"
                + "</html>\n";

        if (rowList.size() < 1) {
            data = "Tabla Vacia";
        }
        Mensaje mensaje = new Mensaje(Cabecera, data);
        return mensaje;
    }

}
