/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software;

import correos.veterinaria.correo.ClienteSMTP;
import correos.veterinaria.procesador.*;
import correos.veterinaria.software.Negocio.*;
import correos.veterinaria.utils.*;
import java.sql.Date;
import javax.mail.MessagingException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Luis Urquiza
 */
public class VeterinariaMail {

    MailCategoria mailCategoria = new MailCategoria();
    MailVeterinario mailVeterinario = new MailVeterinario();
    MailProducto mailProducto = new MailProducto();
    MailMascota mailMascota = new MailMascota();
    MailCliente mailCliente = new MailCliente();
    MailVenta mailVenta = new MailVenta();
    MailAtencion mailAtencion = new MailAtencion();

    public void processMessage(String Message) throws MessagingException, Exception {
        // Setteando Variables
        String destinatario = Utils.getDestinatario(Message);
        String content = Utils.getSubjectOrden(Message);
        System.out.println("Texto : \t" + content);
        System.out.println("Destinatario : \t" + destinatario);
        System.out.println("CONTENIDO FINAL DEL COMANDO: " + content);
        System.out.println("FIN DEL COMENTENIDO DEL COMANDO ");
        Cinta cinta = new Cinta(content);
        Analex analex = new Analex(cinta);
        Parser parser = new Parser(analex);

        // Verificar Orden
        parser.Expresion();
        if (parser.errorFlag) {
            // Enviar Correo de Error
            ClienteSMTP.sendMail(destinatario, "ERROR DE COMANDO ",
                    "El comando que usted ha introducido es incorrecto,"
                    + " para ver la lista de comandos use el comando HELP"
            );
            return;
        }

        // Si todo va bien, procesar el Comando
        analex.Init();
        Token token = analex.Preanalisis();
        if (token.getNombre() == Token.HELP) {
            // Mostrar Ayudas
            Mensaje message = Utils.dibujarMenuAyuda();
            message.setCorreo(destinatario);
            if (message.enviarCorreo()) {
                System.out.println("Envio Correo de Respuesta successful");
            } else {
                System.out.println("No envio Correo Failed ");
            }
            //ClienteSMTP.sendMail(destinatario, "Ayudas - Veterina Animal-Help Mail", Helper.HELP_GLOBAL);
            // System.out.println("Envio el Correo de Respuesta del Comando HELP Supuestamente ...!");
            return;
        }

        // Sino es HELP, es una funcionalidad
        switch ((int) token.getAtributo()) {
/*
            case Token.OBTENERUSUARIOS:
                obtenerUsuarios(analex, destinatario);
                break;
            case Token.REGISTRARUSUARIO:
                registrarUsuario(analex, destinatario);
                break;
            case Token.MODIFICARUSUARIO:
                modificarUsuario(analex, destinatario);
                break;
            ////////////////////////////////////////////////////////////////////
            case Token.OBTENERASISTENCIAS:
                obtenerAsistencias(analex, destinatario);
                break;
            case Token.REGISTRARASISTENCIA:
                registrarAsistencia(analex, destinatario);
                break;
            case Token.MODIFICARASISTENCIA:
                modificarAsistencia(analex, destinatario);
                break;
            ////////////////////////////////////////////////////////////////////
            case Token.OBTENERHORARIOS:
                obtenerHorarios(analex, destinatario);
                break;
            case Token.REGISTRARHORARIO:
                registrarHorario(analex, destinatario);
                break;
            case Token.MODIFICARHORARIO:
                modificarHorario(analex, destinatario);
                break;
            ////////////////////////////////////////////////////////////////////
           /* case Token.OBTENERPRODUCTOS:
             obtenerProductos(analex, destinatario);
             break;
             case Token.REGISTRARPRODUCTO:
             registrarProducto(analex, destinatario);
             break;
             case Token.MODIFICARPRODUCTO:
             modificarProducto(analex, destinatario);
             break;
             case Token.ELIMINARPRODUCTO:
             eliminarProducto(analex, destinatario);
             break;
             ////////////////////////////////////////////////////////////////////
             case Token.OBTENERRUTINAS:
             obtenerRutinas(analex, destinatario);
             break;
             case Token.REGISTRARRUTINA:
             registrarRutina(analex, destinatario);
             break;
             case Token.MODIFICARRUTINA:
             modificarRutina(analex, destinatario);
             break;
             case Token.OBTENEREJERCICIOS:
             obtenerEjercicios(analex, destinatario);
             break;
             case Token.REGISTRAREJERCICIO:
             registrarEjercicio(analex, destinatario);
             break;
             case Token.MODIFICAREJERCICIO:
             modificarEjercicio(analex, destinatario);
             break;
             case Token.REGISTRARRUTINAEJERCICIO:
             registrarRutinaEjercicio(analex, destinatario);
             break;
             case Token.MODIFICARRUTINAEJERCICIO:
             modificarRutinaEjercicio(analex, destinatario);
             break;
             case Token.REGISTRARSOCIORUTINA:
             registrarSocioRutina(analex, destinatario);
             break;
             case Token.MODIFICARSOCIORUTINA:
             modificarSocioRutina(analex, destinatario);
             break;
             ////////////////////////////////////////////////////////////////////
             case Token.OBTENERVENTAS:
             obtenerVentas(analex, destinatario);
             break;
             case Token.REGISTRARVENTA:
             registrarVenta(analex, destinatario);
             break;
             case Token.MODIFICARVENTA:
             modificarVenta(analex, destinatario);
             break;
             ////////////////////////////////////////////////////////////////////
             case Token.OBTENERMENSUALIDADES:
             obtenerMensualidades(analex, destinatario);
             break;
             case Token.REGISTRARMENSUALIDAD:
             registrarMensualidad(analex, destinatario);
             break;
             case Token.MODIFICARMENSUALIDAD:
             modificarMensualidad(analex, destinatario);
             break;
             ////////////////////////////////////////////////////////////////////
             case Token.OBTENERASISTENCIABYSOCIO:
             obtenerAsistenciaBySocio(analex, destinatario);
             break;
             case Token.ESTADISTICAASISTENCIABYHORARIO:
             estadisticaAsistenciaByHorario(analex, destinatario);
             break;
             case Token.OBTENERRUTINAEJERCICIOBYRUTINA:
             obtenerRutinaEjercicioByRutina(analex, destinatario);
             break;
             case Token.ESTADISTICASOCIORUTINABYRUTINA:
             estadisticaSocioRutinaByRutina(analex, destinatario);
             break;
             case Token.OBTENERHISTORIALSOCIORUTINABYSOCIO:
             obtenerHistorialSocioRutinaBySocio(analex, destinatario);
             break;
             case Token.ESTADISTICAMENSUALIDADACTUAL:
             estadisticaMensualidadActual(analex, destinatario);
             break;
             */
////////// VETERINARIA ANIMAL HELP 
            // CU1 
            case Token.REGISTRARCATEGORIA:
                mailCategoria.registrar(analex, destinatario);
                break;
            case Token.OBTENERCATEGORIAS:
                mailCategoria.listar(analex, destinatario);
                break;
            case Token.MODIFICARCATEGORIA:
                mailCategoria.modificar(analex, destinatario);
                break;
            case Token.ELIMINARCARTEGORIA:
                mailCategoria.eliminar(analex, destinatario);
                break;

            //CU2
            case Token.REGISTRARVETERINARIO:
                mailVeterinario.registrar(analex, destinatario);
                break;
            case Token.MODIFICARVETERINARIO:
                mailVeterinario.modificar(analex, destinatario);
                break;
            case Token.OBTENERVETERINARIOS:
                mailVeterinario.listar(analex, destinatario);
                break;
            case Token.ELIMINARVETERINARIO:
                mailVeterinario.eliminar(analex, destinatario);
                break;
            // CU3

            case Token.REGISTRARPRODUCTO:
                mailProducto.registrar(analex, destinatario);
                break;
            case Token.MODIFICARPRODUCTO:
                mailProducto.modificar(analex, destinatario);
                break;
            case Token.OBTENERPRODUCTOS:
                mailProducto.listar(analex, destinatario);
                break;
            case Token.ELIMINARPRODUCTO:
                mailProducto.eliminar(analex, destinatario);
                break;

            //CU4
            case Token.REGISTRARCLIENTE:
                mailCliente.registrar(analex, destinatario);
                break;
            case Token.MODIFICARCLIENTE:
                mailCliente.modificar(analex, destinatario);
                break;
            case Token.OBTENERCLIENTES:
                mailCliente.listar(analex, destinatario);
                break;
            case Token.ELIMINARCLIENTE:
                mailCliente.eliminar(analex, destinatario);
                break;

            //CU5
            case Token.REGISTRARMASCOTA:
                mailMascota.registrar(analex, destinatario);
                break;
            case Token.MODIFICARMASCOTA:
                mailMascota.modificar(analex, destinatario);
                break;
            case Token.OBTENERMASCOTAS:
                mailMascota.listar(analex, destinatario);
                break;
            case Token.ELIMINARMASCOTA:
                mailMascota.eliminar(analex, destinatario);
                break;

            //CU6
            case Token.REGISTRARVENTA:
                mailVenta.registrar(analex, destinatario);
                break;
            case Token.MODIFICARVENTA:
                mailVenta.modificar(analex, destinatario);
                break;
            case Token.OBTENERVENTAS:
                mailVenta.listar(analex, destinatario);
                break;
            case Token.ELIMINARVENTA:
                mailVenta.eliminar(analex, destinatario);
                break;
            //CU7

            case Token.REGISTRARATENCION:
                mailAtencion.registrar(analex, destinatario);
                break;
            case Token.MODIFICARATENCION:
                mailAtencion.modificar(analex, destinatario);
                break;
            case Token.OBTENERATENCIONES:
                mailAtencion.listar(analex, destinatario);
                break;
            case Token.ELIMINARATENCION:
                mailAtencion.eliminar(analex, destinatario);
                break;
        }
    }

    public void registrarUsuario(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_REGISTRARUSUARIO);
            return;
        }

        // Sino, ejecutar el comando
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
        analex.Avanzar();
        // Atributos
        String nombres = Utils.quitarComillas(analex.Preanalisis().getToStr());
        System.out.println("Nombres :" + nombres);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String apellidos = Utils.quitarComillas(analex.Preanalisis().getToStr());
        System.out.println("Apellidos :" + apellidos);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int telefono = (int) analex.Preanalisis().getAtributo();
        System.out.println("Telefono :" + telefono);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_nacimiento = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        System.out.println("Fecha :" + fecha_nacimiento);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int tipo = (int) analex.Preanalisis().getAtributo();
        System.out.println("Tipo :" + tipo);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        boolean estado = analex.Preanalisis().getNombre() == Token.TRUE;
        System.out.println("Estado :" + estado);
        if (tipo > 3) {
            ClienteSMTP.sendMail(correoDest, "Registrar CATEGORIA", "Tipo No Valido");
        } else {
            // usuarioNegocio.registrarUsuario(nombres, apellidos, telefono, fecha_nacimiento, tipo, estado);
            ClienteSMTP.sendMail(correoDest, "Registrar CATEGORIA", "Registro realizado Correctamente");
        }

    }

    public void obtenerUsuarios(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania", Helper.HELP_OBTENERUSUARIOS);
            return;
        }

        // Sino, ejecutar el comando
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        String Head[] = {"id", "Nombre(s)", "Apellido(s)", "Telefono", "Fecha de Nacimiento", "Tipo", "Estado"};
        String Cabecera = "LISTADO DE USUARIOS";
        Mensaje message = Utils.dibujarTabla2(usuarioNegocio.obtenerUsuarios(), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
//        String Head[]={"id : ","Nombre(s) : ","Apellido(s) : ","Telefono : ","Fecha de Nacimiento : ","Tipo : ", "Estado : "};
//        String Cabecera="LISTADO DE USUARIO";
//        String message=Utils.dibujarTabla1(usuarioNegocio.obtenerUsuarios(),Head);
//        ClienteSMTP.sendMail(correoDest,Cabecera,message);
    }

    public void modificarUsuario(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_MODIFICARUSUARIO);
            return;
        }

        // Sino, ejecutar el comando
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        System.out.println("id :" + id);
        DefaultTableModel usuario = usuarioNegocio.obtenerUsuario(id);
        System.out.println(usuario.getDataVector());

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String nombres = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(usuario.getValueAt(0, 1));
        System.out.println("Nombres :" + nombres);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String apellidos = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(usuario.getValueAt(0, 2));
        System.out.println("Apellidos :" + apellidos);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int telefono = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(usuario.getValueAt(0, 3)));
        System.out.println("Telefono :" + telefono);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_nacimiento = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) usuario.getValueAt(0, 4));
        System.out.println("Fecha Nacimiento :" + fecha_nacimiento);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int tipo = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(usuario.getValueAt(0, 5)));
        System.out.println("Tipo :" + tipo);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        boolean estado = (analex.Preanalisis().getNombre() != Token.GB)
                ? (analex.Preanalisis().getNombre() == Token.TRUE)
                : Boolean.valueOf(String.valueOf(usuario.getValueAt(0, 6)));
        System.out.println("Estado :" + estado);
        if (tipo > 3) {
            ClienteSMTP.sendMail(correoDest, "Modificar Usuario", "Tipo No Valido");
        } else {
            usuarioNegocio.modificarUsuario(id, nombres, apellidos, telefono, fecha_nacimiento, tipo, estado);
            ClienteSMTP.sendMail(correoDest, "Modificar Usuario", "Modificacion realizada Correctamente");
        }
    }

    public void obtenerAsistencias(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_OBTENERASISTENCIAS);
            return;
        }

        // Sino, ejecutar el comando
        AsistenciaNegocio asistenciaNegocio = new AsistenciaNegocio();
        String Head[] = {"id", "Fecha", "Presencia", "Usuario(Tipo)", "Horario"};
        String Cabecera = "LISTADO DE ASISTENCIAS";
        Mensaje message = Utils.dibujarTabla2(asistenciaNegocio.obtenerAsistencias(), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
//        String message = Utils.dibujarTabla(asistenciaNegocio.obtenerAsistencias());
//        ClienteSMTP.sendMail(correoDest, "Obtener Asistencias", message);
    }

    public void registrarAsistencia(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_REGISTRARASISTENCIA);
            return;
        }

        // Sino, ejecutar el comando
        AsistenciaNegocio asistenciaNegocio = new AsistenciaNegocio();
        analex.Avanzar();
        // Atributos
        Date fecha = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        System.out.println("Fecha : " + fecha);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        boolean presencia = analex.Preanalisis().getNombre() == Token.TRUE;
        System.out.println("Presencia : " + presencia);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_usuario = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_usuario : " + id_usuario);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_horario = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_horario : " + id_horario);
        asistenciaNegocio.registrarAsistencia(fecha, presencia, id_usuario, id_horario);
        ClienteSMTP.sendMail(correoDest, "Registrar Asistencia", "Registro realizado Correctamente");
    }

    public void modificarAsistencia(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_MODIFICARASISTENCIA);
            return;
        }

        // Sino, ejecutar el comando
        AsistenciaNegocio asistenciaNegocio = new AsistenciaNegocio();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        DefaultTableModel asistencia = asistenciaNegocio.obtenerAsistencia(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) asistencia.getValueAt(0, 1));
        System.out.println("Fecha : " + fecha);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        boolean presencia = (analex.Preanalisis().getNombre() != Token.GB)
                ? (analex.Preanalisis().getNombre() == Token.TRUE)
                : Boolean.valueOf(String.valueOf(asistencia.getValueAt(0, 2)));
        System.out.println("Presencia : " + presencia);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_usuario = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(asistencia.getValueAt(0, 3)));
        System.out.println("id_usuario : " + id_usuario);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_horario = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(asistencia.getValueAt(0, 4)));
        System.out.println("id_horario : " + id_horario);
        asistenciaNegocio.modificarAsistencia(id, fecha, presencia, id_usuario, id_horario);
        ClienteSMTP.sendMail(correoDest, "Modificar Asistencia", "Modificacion realizada Correctamente");
    }

    public void obtenerHorarios(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_OBTENERHORARIOS);
            return;
        }

        // Sino, ejecutar el comando
        HorarioNegocio horarioNegocio = new HorarioNegocio();
        String Head[] = {"id", "Hora de Inicio", "Hora Final", "Tipo"};
        String Cabecera = "LISTADO DE HORARIOS";
        Mensaje message = Utils.dibujarTabla2(horarioNegocio.obtenerHorarios(), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
//        String Head[]={"id : ","Nombre(s) : ","Apellido(s) : ","Telefono : ","Fecha de Nacimiento : ","Tipo : ", "Estado : "};
//        String Cabecera="LISTADO DE USUARIO";
//        String message=Utils.dibujarTabla1(usuarioNegocio.obtenerUsuarios(),Head);
//        ClienteSMTP.sendMail(correoDest,Cabecera,message);
    }

    public void registrarHorario(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_REGISTRARHORARIO);
            return;
        }

        // Sino, ejecutar el comando
        HorarioNegocio horarioNegocio = new HorarioNegocio();
        analex.Avanzar();
        // Atributos
        String hora_inicio = Utils.quitarComillas(analex.Preanalisis().getToStr());
        System.out.println("Hora de Inicio :" + hora_inicio);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String hora_fin = Utils.quitarComillas(analex.Preanalisis().getToStr());
        System.out.println("Hora Final :" + hora_fin);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int tipo = (int) analex.Preanalisis().getAtributo();
        System.out.println("Tipo :" + tipo);
        horarioNegocio.registrarHorario(hora_inicio, hora_fin, tipo);
        ClienteSMTP.sendMail(correoDest, "Registrar Horario", "Registro realizado Correctamente");

    }

    public void modificarHorario(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_MODIFICARHORARIO);
            return;
        }

        // Sino, ejecutar el comando
        HorarioNegocio horarioNegocio = new HorarioNegocio();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        System.out.println("id :" + id);
        DefaultTableModel horario = horarioNegocio.obtenerHorario(id);
        System.out.println(horario.getDataVector());

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String hora_inicio = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(horario.getValueAt(0, 1));
        System.out.println("Hora de Inicio :" + hora_inicio);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String hora_fin = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(horario.getValueAt(0, 2));
        System.out.println("Hora Final :" + hora_fin);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int tipo = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(horario.getValueAt(0, 3)));
        System.out.println("Tipo :" + tipo);
        horarioNegocio.modificarHorario(id, hora_inicio, hora_fin, tipo);
        ClienteSMTP.sendMail(correoDest, "Modificar Horario", "Modificacion realizada Correctamente");
    }

    public void obtenerProductos(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_OBTENERPRODUCTOS);
            return;
        }

        // Sino, ejecutar el comando
        ProductoNegocio productoNegocio = new ProductoNegocio();
        String Head[] = {"id", "Nombre del Producto"};
        String Cabecera = "LISTADO DE PRODUCTOS";
        Mensaje message = Utils.dibujarTabla2(productoNegocio.obtenerProductos(), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
//        String message = Utils.dibujarTabla(productoNegocio.obtenerProductos());
//        ClienteSMTP.sendMail(correoDest, "Obtener Productos", message);
    }

    public void registrarProducto(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_REGISTRARPRODUCTO);
            return;
        }

        // Sino, ejecutar el comando
        ProductoNegocio productoNegocio = new ProductoNegocio();
        analex.Avanzar();
        // Atributos
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        productoNegocio.registrarProducto(nombre);
        ClienteSMTP.sendMail(correoDest, "Registrar Producto", "Registro realizado Correctamente");
    }

    public void modificarProducto(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_MODIFICARPRODUCTO);
            return;
        }

        // Sino, ejecutar el comando
        ProductoNegocio productoNegocio = new ProductoNegocio();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        System.out.println("id : " + id);
        DefaultTableModel producto = productoNegocio.obtenerProducto(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String nombre = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(producto.getValueAt(0, 1));
        System.out.println("Nombre : " + nombre);
        productoNegocio.modificarProducto(id, nombre);
        ClienteSMTP.sendMail(correoDest, "Modificar Producto", "Modificacion realizada Correctamente");
    }

    public void eliminarProducto(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_ELIMINARPRODUCTO);
            return;
        }

        // Sino, ejecutar el comando
        ProductoNegocio productoNegocio = new ProductoNegocio();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        System.out.println("id : " + id);
        productoNegocio.eliminarProducto(id);
        ClienteSMTP.sendMail(correoDest, "Eliminar Producto", "Eliminacion realizada Correctamente");
        //**DefaultTableModel producto = productoNegocio.obtenerProducto(id);**//

        // Revisar los GuionBajo
        /**
         * analex.Avanzar(); analex.Avanzar(); analex.Avanzar(); String nombre =
         * (analex.Preanalisis().getNombre() != Token.GB) ?
         * Utils.quitarComillas(analex.Preanalisis().getToStr()) :
         * String.valueOf(producto.getValueAt(0, 1)); System.out.println("Nombre
         * : "+nombre); productoNegocio.modificarProducto(id, nombre);
         * ClienteSMTP.sendMail(correoDest, "Modificar Producto", "Eliminacion
         * realizada Correctamente");
         *
         */
    }

    public void obtenerRutinas(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_OBTENERRUTINAS);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio rutinaNegocio = new RutinaEjercicioNegocio();
        String Head[] = {"id", "Nombre de la Rutina", "Musculo a Trabajar"};
        String Cabecera = "LISTADO DE RUTINAS";
        Mensaje message = Utils.dibujarTabla2(rutinaNegocio.obtenerRutinas(), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
//        String message = Utils.dibujarTabla(rutinaNegocio.obtenerRutinas());
//        ClienteSMTP.sendMail(correoDest, "Obtener Rutinas", message);
    }

    public void registrarRutina(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_REGISTRARRUTINA);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio rutinaNegocio = new RutinaEjercicioNegocio();
        analex.Avanzar();
        // Atributos
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        System.out.println("Nombre :" + nombre);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String musculo = Utils.quitarComillas(analex.Preanalisis().getToStr());
        System.out.println("Musculo :" + musculo);
        rutinaNegocio.registrarRutina(nombre, musculo);
        ClienteSMTP.sendMail(correoDest, "Registrar Rutina", "Registro realizado Correctamente");
    }

    public void modificarRutina(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_MODIFICARRUTINA);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio rutinaNegocio = new RutinaEjercicioNegocio();

        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        System.out.println("id :" + id);
        DefaultTableModel rutina = rutinaNegocio.obtenerRutina(id);
        System.out.println(rutina.getDataVector());

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String nombre = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(rutina.getValueAt(0, 1));
        System.out.println("Nombre :" + nombre);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String musculo = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(rutina.getValueAt(0, 2));
        System.out.println("Musculo :" + musculo);
        rutinaNegocio.modificarRutina(id, nombre, musculo);
        ClienteSMTP.sendMail(correoDest, "Modificar Rutina", "Modificacion realizada Correctamente");
    }

    public void obtenerEjercicios(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_OBTENEREJERCICIOS);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio ejercicioNegocio = new RutinaEjercicioNegocio();
        String Head[] = {"id", "Nombre del Ejercicio"};
        String Cabecera = "LISTADO DE EJERCICIOS";
        Mensaje message = Utils.dibujarTabla2(ejercicioNegocio.obtenerEjercicios(), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
//        String message = Utils.dibujarTabla(ejercicioNegocio.obtenerEjercicios());
//        ClienteSMTP.sendMail(correoDest, "Obtener Ejercicios", message);
    }

    public void registrarEjercicio(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_REGISTRAREJERCICIO);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio ejercicioNegocio = new RutinaEjercicioNegocio();
        analex.Avanzar();
        // Atributos
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        System.out.println("Nombre :" + nombre);
        ejercicioNegocio.registrarEjercicio(nombre);
        ClienteSMTP.sendMail(correoDest, "Registrar Ejercicio", "Registro realizado Correctamente");
    }

    public void modificarEjercicio(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_MODIFICAREJERCICIO);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio ejercicioNegocio = new RutinaEjercicioNegocio();

        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        System.out.println("id :" + id);
        DefaultTableModel ejercicio = ejercicioNegocio.obtenerEjercicio(id);
        System.out.println(ejercicio.getDataVector());

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        String nombre = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(ejercicio.getValueAt(0, 1));
        System.out.println("Nombre :" + nombre);
        ejercicioNegocio.modificarEjercicio(id, nombre);
        ClienteSMTP.sendMail(correoDest, "Modificar Ejercicio", "Modificacion realizada Correctamente");
    }

    public void registrarRutinaEjercicio(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_REGISTRARRUTINAEJERCICIO);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio rutinaEjercicioNegocio = new RutinaEjercicioNegocio();
        analex.Avanzar();
        // Atributos
        int repeticiones = (int) analex.Preanalisis().getAtributo();
        System.out.println("Repeticiones : " + repeticiones);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int series = (int) analex.Preanalisis().getAtributo();
        System.out.println("Series : " + series);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_rutina = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_rutina : " + id_rutina);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_ejercicio = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_ejercicio : " + id_ejercicio);
        rutinaEjercicioNegocio.registrarRutinaEjercicio(repeticiones, series, id_rutina, id_ejercicio);
        ClienteSMTP.sendMail(correoDest, "Registrar Rutina Ejercicio", "Registro realizado Correctamente");
    }

    public void modificarRutinaEjercicio(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_MODIFICARRUTINAEJERCICIO);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio rutinaEjercicioNegocio = new RutinaEjercicioNegocio();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        System.out.println("id : " + id);
        DefaultTableModel rutinaEjercicio = rutinaEjercicioNegocio.obtenerRutinaEjercicio(id);
        System.out.println("datos : " + rutinaEjercicio.getDataVector());
        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int repeticiones = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(rutinaEjercicio.getValueAt(0, 1)));
        System.out.println("Repeticiones : " + repeticiones);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int series = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(rutinaEjercicio.getValueAt(0, 2)));
        System.out.println("Series : " + series);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_rutina = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(rutinaEjercicio.getValueAt(0, 3)));
        System.out.println("id_rutina : " + id_rutina);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_ejercicio = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(rutinaEjercicio.getValueAt(0, 4)));
        System.out.println("id_ejercicio : " + id_ejercicio);
        rutinaEjercicioNegocio.modificarRutinaEjercicio(id, repeticiones, series, id_rutina, id_ejercicio);
        ClienteSMTP.sendMail(correoDest, "Modificar Rutina Ejercicio", "Modificacion realizada Correctamente");
    }

    public void registrarSocioRutina(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_REGISTRARSOCIORUTINA);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio socioRutinaNegocio = new RutinaEjercicioNegocio();
        analex.Avanzar();
        // Atributos
        Date fecha = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        System.out.println("Fecha : " + fecha);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_socio = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_socio : " + id_socio);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_rutina = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_rutina : " + id_rutina);
        socioRutinaNegocio.registrarSocioRutina(fecha, id_socio, id_rutina);
        ClienteSMTP.sendMail(correoDest, "Registrar Rutina de Socio", "Registro realizado Correctamente");
    }

    public void modificarSocioRutina(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_MODIFICARSOCIORUTINA);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio socioRutinaNegocio = new RutinaEjercicioNegocio();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        DefaultTableModel asistencia = socioRutinaNegocio.obtenerSocioRutina(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) asistencia.getValueAt(0, 1));
        System.out.println("Fecha : " + fecha);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_socio = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(asistencia.getValueAt(0, 2)));
        System.out.println("id_socio : " + id_socio);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_rutina = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(asistencia.getValueAt(0, 3)));
        System.out.println("id_rutina : " + id_rutina);
        socioRutinaNegocio.modificarSocioRutina(id, fecha, id_socio, id_rutina);
        ClienteSMTP.sendMail(correoDest, "Modificar Socio Rutina", "Modificacion realizada Correctamente");
    }

    public void obtenerVentas(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_OBTENERVENTAS);
            return;
        }

        // Sino, ejecutar el comando
        VentaNegocio ventaNegocio = new VentaNegocio();
        String Head[] = {"id", "Cantidad", "Fecha", "Precio", "Producto"};
        String Cabecera = "LISTADO DE VENTAS";
        Mensaje message = Utils.dibujarTabla2(ventaNegocio.obtenerVentas(), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
//        String message = Utils.dibujarTabla(ventaNegocio.obtenerVentas());
//        ClienteSMTP.sendMail(correoDest, "Obtener Ventas", message);
    }

    public void registrarVenta(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_REGISTRARVENTA);
            return;
        }

        // Sino, ejecutar el comando
        VentaNegocio ventaNegocio = new VentaNegocio();
        analex.Avanzar();
        // Atributos
        int cantidad = (int) analex.Preanalisis().getAtributo();
        System.out.println("Cantidad : " + cantidad);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        System.out.println("Fecha : " + fecha);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        float precio = (float) analex.Preanalisis().getAtributo();
        System.out.println("Precio : " + precio);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_producto = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_producto : " + id_producto);
        ventaNegocio.registrarVenta(cantidad, fecha, precio, id_producto);
        ClienteSMTP.sendMail(correoDest, "Registrar Venta", "Registro realizado Correctamente");
    }

    public void modificarVenta(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_MODIFICARVENTA);
            return;
        }

        // Sino, ejecutar el comando
        VentaNegocio ventaNegocio = new VentaNegocio();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        System.out.println("id : " + id);
        DefaultTableModel venta = ventaNegocio.obtenerVenta(id);
        System.out.println("datos : " + venta.getDataVector());
        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int cantidad = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(venta.getValueAt(0, 1)));
        System.out.println("Cantidad : " + cantidad);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) venta.getValueAt(0, 2));
        System.out.println("Fecha : " + fecha);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        float precio = (analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : Float.parseFloat(String.valueOf(venta.getValueAt(0, 3)));
        System.out.println("Precio : " + precio);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_producto = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(venta.getValueAt(0, 4)));
        System.out.println("id_producto : " + id_producto);
        ventaNegocio.modificarVenta(id, cantidad, fecha, precio, id_producto);
        ClienteSMTP.sendMail(correoDest, "Modificar Venta", "Modificacion realizada Correctamente");
    }

    public void obtenerMensualidades(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_OBTENERMENSUALIDADES);
            return;
        }

        // Sino, ejecutar el comando
        MensualidadNegocio mensualidadNegocio = new MensualidadNegocio();
        String Head[] = {"id", "Fecha de Inicio", "Fecha Final", "Monto", "Socio"};
        String Cabecera = "LISTADO DE MENSUALIDADES";
        Mensaje message = Utils.dibujarTabla2(mensualidadNegocio.obtenerMensualidades(), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
//        String message = Utils.dibujarTabla(mensualidadNegocio.obtenerMensualidades());
//        ClienteSMTP.sendMail(correoDest, "Obtener Mensualidades", message);
    }

    public void registrarMensualidad(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_REGISTRARMENSUALIDAD);
            return;
        }

        // Sino, ejecutar el comando
        MensualidadNegocio mensualidadNegocio = new MensualidadNegocio();
        analex.Avanzar();
        // Atributos
        Date fecha_inicio = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        System.out.println("fecha_inicio : " + fecha_inicio);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_fin = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        System.out.println("fecha_fin : " + fecha_fin);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        float monto = (float) analex.Preanalisis().getAtributo();
        System.out.println("monto : " + monto);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_socio = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_socio : " + id_socio);
        mensualidadNegocio.registrarMensualidad(fecha_inicio, fecha_fin, monto, id_socio);
        ClienteSMTP.sendMail(correoDest, "Registrar Mensualidad", "Registro realizado Correctamente");
    }

    public void modificarMensualidad(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_MODIFICARMENSUALIDAD);
            return;
        }

        // Sino, ejecutar el comando
        MensualidadNegocio mensualidadNegocio = new MensualidadNegocio();
        analex.Avanzar();
        int id = (int) analex.Preanalisis().getAtributo();
        System.out.println("id : " + id);
        DefaultTableModel mensualidad = mensualidadNegocio.obtenerMensualidad(id);
        System.out.println("datos : " + mensualidad.getDataVector());
        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_inicio = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) mensualidad.getValueAt(0, 2));
        System.out.println("fecha_inicio: " + fecha_inicio);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_fin = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) mensualidad.getValueAt(0, 1));
        System.out.println("fecha_fin: " + fecha_fin);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        float monto = (analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : Float.parseFloat(String.valueOf(mensualidad.getValueAt(0, 3)));
        System.out.println("monto : " + monto);
        analex.Avanzar();
        analex.Avanzar();
        analex.Avanzar();
        int id_socio = (analex.Preanalisis().getNombre() != Token.GB)
                ? (int) analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(mensualidad.getValueAt(0, 5)));
        System.out.println("id_socio : " + id_socio);
        mensualidadNegocio.modificarMensualidad(id, fecha_inicio, fecha_fin, monto, id_socio);
        ClienteSMTP.sendMail(correoDest, "Modificar Mensualidad", "Modificacion realizada Correctamente");
    }

    public void obtenerAsistenciaBySocio(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_OBTENERASISTENCIABYSOCIO);
            return;
        }

        // Sino, ejecutar el comando
        AsistenciaNegocio asistenciaNegocio = new AsistenciaNegocio();
        analex.Avanzar();
        int id_socio = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_socio : " + id_socio);
        String Head[] = {"id", "Socio", "Fecha", "Presencia"};
        String Cabecera = "ASISTENCIAS POR SOCIO";
        Mensaje message = Utils.dibujarTabla2(asistenciaNegocio.obtenerAsistenciaBySocio(id_socio), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
    }

    public void estadisticaAsistenciaByHorario(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_ESTADISTICAASISTENCIABYHORARIO);
            return;
        }

        // Sino, ejecutar el comando
        AsistenciaNegocio asistenciaNegocio = new AsistenciaNegocio();
        analex.Avanzar();
        int id_horario = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_horario : " + id_horario);
        String Head[] = {"id", "Horario", "Porcentaje"};
        String Cabecera = "ESTADISTICA DE ASISTENCIAS POR HORARIO";
        Mensaje message = Utils.dibujarTabla2(asistenciaNegocio.estadisticaAsistenciaByHorario(id_horario), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
    }

    public void obtenerRutinaEjercicioByRutina(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_OBTENERRUTINAEJERCICIOBYRUTINA);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio rutinaEjercicioNegocio = new RutinaEjercicioNegocio();
        analex.Avanzar();
        int id_rutina = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_rutina : " + id_rutina);
        String Head[] = {"id", "Repeticiones", "Series", "Rutina -> Ejercicio"};
        String Cabecera = "RUTINA EJERCICIO POR RUTINA";
        Mensaje message = Utils.dibujarTabla2(rutinaEjercicioNegocio.obtenerRutinaEjercicioByRutina(id_rutina), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
    }

    public void estadisticaSocioRutinaByRutina(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_ESTADISTICASOCIORUTINABYRUTINA);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio rutinaEjercicioNegocio = new RutinaEjercicioNegocio();
        analex.Avanzar();
        int id_rutina = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_rutina : " + id_rutina);
        String Head[] = {"id", "Rutina", "Porcentaje"};
        String Cabecera = "ESTADISTICA DE SOCIO RUTINA POR RUTINA";
        Mensaje message = Utils.dibujarTabla2(rutinaEjercicioNegocio.estadisticaSocioRutinaByRutina(id_rutina), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
    }

    public void obtenerHistorialSocioRutinaBySocio(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_OBTENERHISTORIALSOCIORUTINABYSOCIO);
            return;
        }

        // Sino, ejecutar el comando
        RutinaEjercicioNegocio socioRutinaNegocio = new RutinaEjercicioNegocio();
        analex.Avanzar();
        int id_socio = (int) analex.Preanalisis().getAtributo();
        System.out.println("id_socio : " + id_socio);
        String Head[] = {"id", "Socio", "Rutina", "Fecha"};
        String Cabecera = "HISTORIAL SOCIO RUTINA POR SOCIO";
        Mensaje message = Utils.dibujarTabla2(socioRutinaNegocio.obtenerHistorialSocioRutinaBySocio(id_socio), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
    }

    public void estadisticaMensualidadActual(Analex analex, String correoDest) throws MessagingException {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Musclemania Mail", Helper.HELP_ESTADISTICAMENSUALIDADACTUAL);
            return;
        }

        // Sino, ejecutar el comando
        MensualidadNegocio mensualidadNegocio = new MensualidadNegocio();
        String Head[] = {"id", "Socio", "Mensualidades al Dia, pagadas Hasta..."};
        String Cabecera = "HISTORIAL SOCIO RUTINA POR SOCIO";
        Mensaje message = Utils.dibujarTabla2(mensualidadNegocio.estadisticaMensualidadActual(), Head, Cabecera);
        message.setCorreo(correoDest);
        if (message.enviarCorreo()) {
            System.out.println("Envio Correo");
        } else {
            System.out.println("No envio Correo");
        }
    }
}
