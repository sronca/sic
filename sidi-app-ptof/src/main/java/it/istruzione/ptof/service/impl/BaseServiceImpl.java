package it.istruzione.ptof.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public abstract class BaseServiceImpl {
	@PersistenceContext
	protected EntityManager em;

	protected final Logger logger = Logger.getLogger(this.getClass());

	protected EntityManager em() {
		return em;
	}

	 
}
