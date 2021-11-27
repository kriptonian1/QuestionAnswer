package com.app.website.database.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.website.database.interfaces.DomainDao;
import com.app.website.entity.Domain;
import com.app.website.entity.Topic;

@Repository
public class DomainDaoImpl implements DomainDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int createDomain(Domain domain) {
		Session session = sessionFactory.getCurrentSession();
		int id = -1;
		if (getDomainByName(domain.getName()) != null)
			id = (int) session.save(domain);
		return id;
	}

	@Override
	public Domain getDomainById(int domainId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Domain.class, domainId);
	}

	@Override
	public Domain getDomainByName(String domainName) {
		Session session = sessionFactory.getCurrentSession();
		try {
			return (Domain) session.createQuery(String.format("from Domain where name='%s'", domainName)).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Domain> getDomainsByName(String domainName) {
		Session session = sessionFactory.getCurrentSession();
		try {
			return session.createQuery("from Domain where name like %"+domainName+"%").getResultList();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Topic> getTopicsOfDomain(int domainId) {
		Session session = sessionFactory.getCurrentSession();
		Domain domain = session.get(Domain.class, domainId);
		return domain.getTopics();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Domain> getAllDomains() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Domain").getResultList();
	}

	@Override
	public void updateDomain(int domainId, String domainName) {
		Session session = sessionFactory.getCurrentSession();
		Domain domain = session.get(Domain.class, domainId);
		domain.setName(domainName);
		session.update(domain);
	}

	@Override
	public void deleteDomain(int domainId) {
		Session session = sessionFactory.getCurrentSession();
		Domain domain = session.get(Domain.class, domainId);
		session.delete(domain);
		
	}

}
