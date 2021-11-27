package com.app.website.database.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.website.database.interfaces.CompanyDao;
import com.app.website.entity.Company;
import com.app.website.entity.Question;

@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int createCompany(Company company) {
		Session session = sessionFactory.getCurrentSession();
		int id = -1;
		if (getCompanyByName(company.getName()) == null)
			id = (int) session.save(company);
		return id;
	}

	@Override
	public Company getCompanyByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Company company = null;
		try {
			company = (Company) session.createQuery(String.format("select c from Company c where c.name = '%s'", name)).getSingleResult();
		}catch (NoResultException e) {
		}
		return company;
	}

	@Override
	public Company getCompanyById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Company company = null;
		try {
			company = (Company) session.createQuery(String.format("select c from Company c where c.id = '%d'", id)).getSingleResult();
		}catch (NoResultException e) {
		}
		return company;
	}

	@Override
	public void updateCompany(int id, String name) {
		Session session = sessionFactory.getCurrentSession();
		Company company = session.get(Company.class, id);
		company.setName(name);
		session.update(company);
	}

	@Override
	public void deleteCompany(int id) {
		Session session = sessionFactory.getCurrentSession();
		Company company = session.get(Company.class, id);
		session.delete(company);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getAllCompanies() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Company").getResultList();
	}

	@Override
	public List<Question> getAllQuestionsOfCompany(int companyId) {
		Session session = sessionFactory.getCurrentSession();
		Company company = session.get(Company.class, companyId);
		return company.getQuestions();
	}
	
}
