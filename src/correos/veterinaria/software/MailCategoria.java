/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software;
import correos.veterinaria.correo.ClienteSMTP;
import correos.veterinaria.procesador.Analex;
import correos.veterinaria.procesador.Token;
import correos.veterinaria.software.Negocio.*;

import correos.veterinaria.utils.Helper;
import correos.veterinaria.utils.Utils;
import java.sql.Date;
/**
 *
 * @author Jorge Luis Urquiza
 */
public class MailCategoria extends TemplateMail{
    private CategoriaNegocio categoriaNegocio;

    public MailCategoria() {
        this.categoriaNegocio = new CategoriaNegocio();
    }
    
    @Override
    public void registrar(Analex analex, String destinatario) throws Exception {
          // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Musclemania Mail", Helper.HELP_REGISTRARUSUARIO);
            return;
        }

        // Sino, ejecutar el comando
       
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
            ClienteSMTP.sendMail(destinatario, "Registrar Usuario", "Tipo No Valido");
        } else {
           // categoriaNegocio.registrar(nombre,descripcion);
            ClienteSMTP.sendMail(destinatario, "Registrar Usuario", "Registro realizado Correctamente");
        }

    }

    @Override
    public void modificar(Analex analex, String destinatario) throws Exception {
 
    }

    @Override
    public void eliminar(Analex analex, String destinatario) throws Exception {

    }

    @Override
    public void listar(Analex analex, String destinatario) throws Exception {
        
    }

  
   
}
