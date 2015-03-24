package org.sitenv.portlets.smtpdirectedgetransport.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.sitenv.portlets.smtpdirectedgetransport.models.SimpleEmailMessageAttributes;
import org.springframework.stereotype.Service;

@Service
public class DirectEdgeSmtpService extends SmtpService{

	public void sendEmail(Properties props, SimpleEmailMessageAttributes emailAttributes) throws AddressException, MessagingException {
		Message message = createMessage(props, emailAttributes);
		sendMessage(message);
	}

	public void getMessagesByFromAddress(Properties props, String fromAddress) {	
	}


}
