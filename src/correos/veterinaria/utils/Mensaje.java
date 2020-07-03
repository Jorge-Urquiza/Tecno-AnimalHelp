/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.veterinaria.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Jorge Luis Urquiza
 */
public class Mensaje {
    
    private String correo;
    private String subject;
    private String data;

    public Mensaje(String subject, String data) {
        this.subject = subject;
        this.data = data;
    }

    public Mensaje(String correo, String subject, String data) {
        this.correo = correo;
        this.subject = subject;
        this.data = data;
    }
    
    public Mensaje(String correo) {
        this.correo = correo;
    }

    public Mensaje() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    /*
    public boolean enviarCorreo() {
        SMTP smtp = new SMTP("grupo07sa@mail.ficct.uagrm.edu.bo", correo);
        return smtp.enviarMensaje(subject, data);
    }
    */

    @Override
    public String toString() {
        return "Mensaje{" + "subject=" + subject + ", data=" + data + '}';
    }
    
    public boolean enviarCorreo() throws AddressException,
            MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "mail.tecnoweb.org.bo");
        properties.put("mail.smtp.port", 25);
        properties.put("mail.smtp.auth", "false");
       // properties.put("mail.smtp.starttls.enable", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("grupo01sa", "grupo01grupo01");
            }
        };

        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        try {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("grupo01sa@tecnoweb.org.bo"));
            InternetAddress[] toAddresses = {new InternetAddress(this.correo)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            // set plain text message
            msg.setContent(this.data, "text/html; charset=UTF-8");
            // sends the e-mail
            Transport.send(msg);
            System.out.println("Envie MAIL: to=" + this.correo + " subject=" + subject + " data:" + this.data);
            return true;
        } catch (MessagingException mex) {
            System.out.println("ERROR AL ENVIAR SMTP");
            mex.printStackTrace();
        }
        return false;
    }
}
