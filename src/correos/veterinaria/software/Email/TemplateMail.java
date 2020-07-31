/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.software.Email;

/**
 *
 * @author Jorge Luis Urquiza
 */
import correos.veterinaria.procesador.Analex;

public abstract class TemplateMail {

    public abstract void registrar(Analex analex, String destinatario) throws Exception;

    public abstract void modificar(Analex analex, String destinatario) throws Exception;

    public abstract void eliminar(Analex analex, String destinatario) throws Exception;

    public abstract void listar(Analex analex, String destinatario) throws Exception;
}
