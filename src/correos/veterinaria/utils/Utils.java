/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.utils;

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

    public static Mensaje dibujarMenuAyuda() {
        //System.out.println("|-------------------------------------------------------------");
        String Cabecera = "MENÚ DE AYUDA- ANIMAL HELP";
        LinkedList<String> Head = new LinkedList<>(Arrays.asList(
                "CASO DE USO",
                "DETALLE",
                "EJEMPLO"
        ));
        LinkedList<String> CasosDeUso = new LinkedList<>(Arrays.asList(
                //VETERINARIA ANIMAL HELP

                //CU1 Gestionar Cliente
                "REGISTRAR CLIENTE",
                "MODIFICAR CLIENTE",
                "OBTENERCLIENTES",
                "ELIMINAR CLIENTE",
                //CU2 Gestionar Mascota
                "REGISTRAR MASCOTA",
                "MODIFICAR MASCOTA",
                "OBTENER MASCOTAS",
                "ELIMINAR MASCOTA",
                ///CU3 Gestionar Veterinario
                "REGISTRAR VETERINARIO",
                "OBTENER VETERINARIO",
                "MODIFICAR VETERINARIO",
                "ELIMINAR VETERINARIO",
                ///CU4 Gestionar Categoria
                "REGISTRAR CATEGORIA",
                "OBTENER CATEGORIA",
                "MODIFICAR CATEGORIA",
                "ELIMINAR CATEGORIA",
                ///CU5 Gestionar Producto
                "REGISTRAR PRODUCTO",
                "MODIFICAR PRODUCTO",
                "OBTENER PRODUCTOS",
                "ELIMINAR PRODUCTO",
                //CU6 Gestionar Venta
                "REGISTRAR VENTA",
                "MODIFICAR VENTA",
                "OBTENERVENTAS",
                "ELIMINAR VENTA",
                //CU7 Gestionar atencion
                "REGISTRAR ATENCION",
                "MODIFICAR ATENCION",
                "OBTENERATENCIONES",
                "ELIMINAR ATENCION",
                //CU8 REPORTES
                "VENTASMENSUALES",
                "TOP3 CLIENTES COMPRAS",
                "TOP3 MASCOTAS MAS ATENDIDAS",
                "TOP3 PRODUCTOS MAS VENDIDOS",
                "VENTAS TOTAL DE HOY",
                "TORTA PORCENTAJE POR ANIMAL"
        ));
        LinkedList<String> Detalles = new LinkedList<>(Arrays.asList(
                //veterinaria animal help

                //CU1 Gestionar Cliente
                "REGISTRARCLIENTE[\"Nombre\"][\"Apellidos\"][Cedula Identidad][\"Celular\"][\"Direcciopn\"]",
                "MODIFICARCLIENTE[ID Cliente][\"Nombre\"][\"Apellidos\"][Cedula Identidad][\"Celular\"][\"Direcciopn\"]",
                "OBTENERCLIENTES",
                "ELIMINARCLIENTE[ID Cliente]",
                //CU2 Gestionar Mascota
                "REGISTRARMASCOTA[\"Nombre\"][\"Raza\"][\"Color\"][id del amo (cliente)][Tipo de mascota (1, 2 | 1=Perro 2 = Gato)]",
                "MODIFICARMASCOTA[id de mascota][\"Nombre\"][\"Raza\"][\"Color\"][id del amo (cliente)][Tipo de mascota (1, 2 | 1=Perro 2 = Gato)]",
                "OBTENERMASCOTAS",
                "ELIMINARMASCOTA[id de la mascota]",
                ///CU3 Gestionar Veterinario
                "REGISTRARVETERINARIO[\"Nombre\"][\"Apellidos\"][Cedula Identidad][\"Celular\"][\"Direcciopn\"]",
                "OBTENERVETERINARIOS",
                "MODIFICARVETERINARIO[id de veterinario][\"Nombre\"][\"Descripcion\"]",
                "ELIMINARVETERINARIO[id veterinario]",
                ///CU4 Gestionar Categoria
                "REGISTRARCATEGORIA[\"Nombre\"][\"Descripcion\"]",
                "OBTENERCATEGORIAS",
                "MODIFICARCATEGORIA[\"ID categoria\"][\"Nombre Categoria\"][\"Descripcion\"]",
                "ELIMINARCATEGORIA[\"ID categoria\"]",
                ///CU5 Gestionar Producto
                "REGISTRARPRODUCTO[\"Nombre\"][Precio][ID  de la Categoria]",
                "OBTENERPRODUCTOS",
                "MODIFICARPRODUCTO[ID Producto][\"Nombre\"][Precio][ID Categoria]",
                "ELIMINARPRODUCTO[ID Producto] ",
                //CU6 Gestionar Venta
                "REGISTRARVENTAREGISTRARVENTA[\"21461\"][\"02-08-2020\"][2][1][1][5][3][4]",
                "MODIFICARVENTAMODIFICARVENTA[8][_][\"10-02-2019\"][_][_]",
                "OBTENERVENTAS",
                "ELIMINARVENTAELIMINARVENTA[10]",
                //CU7 Gestionar Atencion
                "REGISTRARATENCION[\"Fecha\"][\"Descripcion\"][\"Diagnostico\"][id cliente][id veterinario][id mascota]",
                "MODIFICARATENCION[ID atencion[[\"03-08-2020\"][\"Descripcion\"][\"Diagnostico medico\"][id cliente][id veterinario][id mascota]",
                "OBTENERATENCIONES",
                "ELIMINARATENCION[ID de la atención])",
                //C8 REPORTES
                "VENTASMENSUALES(Ventas totales por mes)",
                "TOP3CLIENTESCOMPRAS (Los 3 clientes que mas compras realizaron)",
                "TOP3MASCOTASATENDIDAS(Las mascotas que mas veces fueron atendidas)",
                "TOP3PRODUCTOSVENDIDOS(Los 3 productos mas vendidos)",
                "VENTASTOTALDEHOY (Todas las ventas realizadas hoy)",
                "TORTAPORCENTAJEANIMAL(TORTA GRAFICA CON % DE ANIMALES ATENDIDOS POR TIPO (PERROS Y GATOS))"
        ));
        LinkedList<String> Ejemplos = new LinkedList<>(Arrays.asList(
                //Veterinaria
                //CU1 Gestionar Cliente
                "REGISTRARCLIENTE[\"Jhasmany\"][\"Campos Aguilar\"][1555][\"+59175575745\"]",
                "MODIFICARCLIENTE[7][_][_][_][\"+59178036436\"]<br>" + Cadenas.GUION_BAJO,
                "OBTENERCLIENTES",
                "ELIMINARCLIENTE[7]",
                ///CU2 Gestionar Mascota
                "REGISTRARMASCOTA[\"Sony\"][\"chihuahua\"][\"Negro\"][1][2]",
                "MODIFICARMASCOTA[4][\"Sonic\"][_][_][_][_]<br>"+ Cadenas.GUION_BAJO,
                "OBTENERMASCOTAS",
                "ELIMINARMASCOTA[4]",
                //CU3 Gestionar Veterinario
                "REGISTRARVETERINARIO[\"Luciano\"][\"Aguierre Peña\"][12530][\"+591700365436\"][\"Calle Guabira#03\"]",
                "OBTENERVETERINARIOS",
                "MODIFICARVETERINARIO[1][_][_][853651][\"+591 75575746\"][\"Calle Santa Cruz#1113\"]"+ Cadenas.GUION_BAJO,
                "ELIMINARVETERINARIO[2]",
                ///CU4 Gestionar Categoria
                "REGISTRARCATEGORIA[\"Limpieza\"][\"Productos de aseo para las mascotas\"]",
                "OBTENERCATEGORIAS",
                "MODIFICARCATEGORIA[11][_][\" Limpieza para mascotas\"]<br>" + Cadenas.GUION_BAJO,
                "ELIMINARCATEGORIA[10]",
                ///CU5 Gestionar Porducto
                "REGISTRARPRODUCTO[\"Golfo\"][15][11]",
                "OBTENERPRODUCTOS",
                "MODIFICARPRODUCTO[5][\"Golfo ++\"][_][_]<br>" + Cadenas.GUION_BAJO,
                "ELIMINARPRODUCTO[5]",
                //CU6 Gestionar Venta
                "REGISTRARVENTAREGISTRARVENTA[\"21461\"][\"02-08-2020\"][2][1][1][5][3][4]",
                "MODIFICARVENTAMODIFICARVENTA[8][_][\"10-02-2019\"][_][_]<br>" + Cadenas.GUION_BAJO,
                "OBTENERVENTAS",
                "ELIMINARVENTAELIMINARVENTA[10]",
                //CU7 Gestionar Atencion
                "REGISTRARATENCION[\"02-08-2020\"][\"el animal tenia moquillo\"][\"darle vitaminas C\"][1][1][3]",
                "MODIFICARATENCION[\"03-08-2020\"][_][\"darle vitaminas c y b\"][_][_][_]<br>" + Cadenas.GUION_BAJO,
                "OBTENERATENCIONES",
                "ELIMINARATENCION[4]",
                //CU8 REPORTES
                "VENTASMENSUALES",
                "TOPTRESCLIENTESCOMPRAS",
                "TOPTRESMASCOTASATENDIDAS",
                "TOPTRESPRODUCTOSVENDIDOS",
                "VENTASTOTALDEHOY",
                "TORTAPORCENTAJEANIMAL"
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

    //table example
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
