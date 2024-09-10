package com.utils.tests;

import java.io.IOException; 
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendEmail extends Utilities{

	
	private static Logger logger = LogManager.getLogger();
	

	String sender, recipient, subject, body, host;
	int port;

	/**This method will send an outlook email along with the TestNG emailable-report.html
	 * @author Deshik - MX Techies
	 * @throws IOException
	 */
	public void sendEmail() throws IOException {
		
		logger.info("Entering sendEmail() method");

		boolean emailTrigger=Boolean.parseBoolean(properties.getProperty("EMAIL_TRIGGER"));
		
		if(emailTrigger){
		// Set the username and password for the Outlook account
		final String username = properties.getProperty("USERNAME");
		logger.info("Username " + username + " fetched from properties.");
		final String password = properties.getProperty("EMAIL_PASSWORD");
		logger.info("Password fetched from properties file.");

		recipient = properties.getProperty("EMAIL_RECIPIENT");
		logger.info("Email recipient " + recipient + " fetched from properties.");
		subject = properties.getProperty("PROJECT_NAME") + " - " + properties.getProperty("EMAIL_SUBJECT") + " ["
				+ getCurrentDate("dd/MM/yyyy") + "]";
		logger.info("Email subject fetched from properties");

		// Extracting firstname from recipient email

		int dotIndex = recipient.indexOf(".");
		int atIndex = recipient.indexOf("@");
		String recipientFirstname = (dotIndex != -1 && dotIndex < atIndex) ? recipient.substring(0, dotIndex)
				: recipient.substring(0, atIndex);

		recipientFirstname = recipientFirstname.substring(0, 1).toUpperCase() + recipientFirstname.substring(1);
		logger.info("Extracted recipient firstname from recipient email.");

		// Extracting firstname from sender email

		sender = properties.getProperty("EMAIL_SENDER");
		logger.info("Email sender " + sender + "fetched from properties.");
		dotIndex = sender.indexOf(".");
		atIndex = sender.indexOf("@");
		String senderFirstname = (dotIndex != -1 && dotIndex < atIndex) ? sender.substring(0, dotIndex)
				: sender.substring(0, atIndex);

		senderFirstname = senderFirstname.substring(0, 1).toUpperCase() + senderFirstname.substring(1);
		logger.info("Extracted sender firstname from sender email");
		

		// Load the HTML content of the emailable report
		String emailableReportContent = readFileAsString("test-output/emailable-report.html");
		logger.info("Reading the TestNG test-output/emailable-report.html as string.");

		// Build the HTML email body
		body = "<html><style>" + "body {font-family: Calibri; font-size: 14px;}" + "</style><body>" + "<p>Dear "
				+ recipientFirstname + ",</p>" + "<p>\tPlease find below the automation test report for "
				+ properties.getProperty("PROJECT_NAME") + " as of " + getCurrentDate("dd/MM/yyyy") + ".</p>"
				+ "<p>The report provides details on the test cases executed, their results, and any issues encountered.</p>"
				+ "<br>" + "<br>" + emailableReportContent // Insert the emailable report content
				+ "<br>" + "<p>This email has been sent automatically by automation testing framework from Eclipse.</p>"
				+ "<br>" + "<br>" + "<div>" + "<h3><b>Kind Regards,</b></h3>" + "<h4>" + senderFirstname + "</h4>"
				+ "<p>Automation Tester</p>" + "<p>MX Techies</p>" + "</div>" + "</body></html>";
		logger.info("Composed body of the email.");

		// Set the host and port for the Outlook SMTP server
		host = "smtp-mail.outlook.com";
		port = 587;

		// Set the properties for the Outlook SMTP server

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		logger.info("E-mail configuration completed.");

		// Create a session with the Outlook SMTP server
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		logger.info("Created a session with the Outlook SMTP server");

		try {
			// Create a new message
			Message message = new MimeMessage(session);

			// Set the sender and recipient addresses
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

			// Set the email subject and body
			message.setSubject(subject);
			message.setContent(body, "text/html");
			logger.info("Created a message.");

			// Send the message
			Transport.send(message);
			logger.info("Email sent successfully.");

		} catch (MessagingException e) {
			logger.error("Error sending email: " + e.getMessage());
		}
		
		}else
			logger.info("Automated Email feature is turned off.");
		
		logger.info("sendEmail() method ended.");

	}

	public String readFileAsString(String filePath) throws IOException {
		logger.info("Method call to readFileAsString() started.");
		byte[] encoded = Files.readAllBytes(Paths.get(filePath));
		logger.info("Method call to readFileAsString() ended.");
		return new String(encoded, Charset.defaultCharset());

	}

}
