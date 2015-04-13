package org.sitenv.common.statistics.dao.impl;

import org.sitenv.common.statistics.dao.SmtpTransmissionDAO;
import org.sitenv.common.statistics.entity.SmtpReceiveEntity;
import org.sitenv.common.statistics.entity.SmtpSendSearchEntity;
import org.springframework.stereotype.Repository;

@Repository(value="smtpTransmissionDAO")
public class SmtpTransmissionDAOImpl extends BaseDAOImpl implements SmtpTransmissionDAO {

	public void createSMTPReceive(String domain, String from, String to, Boolean precanned, Boolean uploaded,
			Boolean hasErrors) {
		SmtpReceiveEntity entity = new SmtpReceiveEntity();
		entity.setErrors(hasErrors);
		entity.setFrom(from);
		entity.setPrecanned(precanned);
		entity.setTo(to);
		entity.setUploaded(uploaded);
		entityManager.persist(entity);
	}

	public void createSMTPSendSearch(String value, Boolean hasErrors) {
		SmtpSendSearchEntity entity = new SmtpSendSearchEntity();
		entity.setError(hasErrors);
		entity.setValue(value);
		entityManager.persist(entity);
	}

}
