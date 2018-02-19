package lolemon.logicaDeNegocio.Clases;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
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

import javafx.concurrent.Task;

public class Contacto {
	boolean enviado=false;
	public Contacto() {
		
	}
	
	/**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for SSL: 465
	 */
	public boolean enviar(String correo, String cabecera, String cuerpo) {
		
		final String fromEmail = "rilhi22@gmail.com"; //requires valid gmail id
		final String password = "gdhhrhxwujfmgjlq"; // correct password for gmail id
		final String toEmail = "richard_hanks@outlook.com"; // can be any email id 
		
		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
		
		try {
			  Message msg = new MimeMessage(session);
			  msg.setFrom(new InternetAddress(fromEmail, null));
			  msg.addRecipient(Message.RecipientType.TO,
			                   new InternetAddress(toEmail, null));
			  msg.setSubject(cabecera);
			  msg.setText(cuerpo+"\nEnviado por: "+correo);
			  
			Task<Boolean> tarea = new Task<Boolean>() {
				@Override
				protected Boolean call() throws Exception {
						Transport.send(msg);
					return true;
				}
			};
			tarea.setOnSucceeded(e->enviado=true);
			tarea.setOnFailed(e->enviado=false);
			new Thread(tarea).start();
			
			} catch (AddressException e) {
			  e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return enviado;
		
	}
	
		

	
}