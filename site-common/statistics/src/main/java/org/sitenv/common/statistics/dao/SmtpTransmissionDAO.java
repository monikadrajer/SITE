package org.sitenv.common.statistics.dao;

public interface SmtpTransmissionDAO {

	public void createSMTPReceive(String domain, String from, String to, Boolean precanned, Boolean uploaded, Boolean hasErrors);
	
	public void createSMTPSendSearch(String value, Boolean hasErrors);
	
}
