/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
/**
 *
 * @author bruno
 */
public class enviar_email {
    
    public void emailsimples(String mensagem,String emails,String assunto,String supervisor) {
        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("bacvvr@gmail.com", "bacvvr91356166"));
            email.setSSLOnConnect(true);
            email.setFrom("bacvvr@gmail.com"); // email de envio
            email.setSubject(assunto); // assunto
            email.setMsg(mensagem);
            email.addTo(emails);
            email.addCc(supervisor);
            email.send();
            System.out.println("enviado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("erro ao enviar email" + e.getMessage());
        }
    }
}
