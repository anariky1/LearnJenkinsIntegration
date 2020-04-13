package com.qtpselenium.facebook.pom.mail;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.qtpselenium.facebook.pom.util.FBConstants;
 
public class SendMailSSLWithAttachment {
 
	public static void main(String[] args) {
 		
		sendEMail();
 
	}
	
	public static void sendEMail(){

		// Create object of Property file
		Properties props = new Properties();
 
		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");
 
		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");
 
		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
 
		// set the authentication to true
		props.put("mail.smtp.auth", "true");
 
		// set the port of SMTP server
		props.put("mail.smtp.port", "465");
 
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,
 
				new javax.mail.Authenticator() {
 
					protected PasswordAuthentication getPasswordAuthentication() {
 
					return new PasswordAuthentication("friendlyananth15@gmail.com", "");
 
					}
 
				});
 
		try {
 
			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
 
			// Set the from address
			message.setFrom(new InternetAddress("friendlyananth15@gmail.com"));
 
			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("ananthgithub@gmail.com"));
            
                        // Add the subject link
			message.setSubject("Automation Test Reports - Extent");
 
			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
 
			// Set the body of email
			messageBodyPart1.setText("Please find the attached Extent reports");
 
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			
			
			String reportFolder=FBConstants.REPORTS_PATH;
	    	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        /* FileFilterDateIntervalUtils filter =
	             new FileFilterDateIntervalUtils("2018-01-01", "2020-12-30");
	         File folder =  new File(reportFolder);
	         File files[] = folder.listFiles(filter);*/ //this will automatically list the files in ascending order
	        //date
	         
	         //String fileName=files[files.length-1].getName(); //getting the last generated file
	         //String extentFilePath=reportFolder+fileName; // most recently created file
			
			
	         File fileName=getLatestFilefromDir(FBConstants.REPORTS_PATH);
	         String extentFilePath=fileName.toString();
			
	
			// Mention the file which you want to send
			//String filename = extentFilePath;
 
			// Create data source and pass the filename
			DataSource source = new FileDataSource(extentFilePath);
 
			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
 
			// set the file
			messageBodyPart2.setFileName(extentFilePath);
 
			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();
 
			// add body part 1
			multipart.addBodyPart(messageBodyPart2);
 
			// add body part 2
			multipart.addBodyPart(messageBodyPart1);
 
			// set the content
			message.setContent(multipart);
 
			// finally send the email
			Transport.send(message);
 
			System.out.println("=====Email Sent=====");
 
		} catch (MessagingException e) {
 
			throw new RuntimeException(e);
 
		}
		

	}
	
	private static File getLatestFilefromDir(String dirPath){
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }

	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
	}
 
}