package com.app.website.database.interfaces;

import java.util.List;

import com.app.website.entity.Company;
import com.app.website.entity.Question;

public interface CompanyDao {
	
	public int createCompany(Company company);
	public Company getCompanyByName(String name);
	public Company getCompanyById(int id);
	public List<Company> getAllCompanies();
	public List<Question> getAllQuestionsOfCompany(int companyId);
	public void updateCompany(int id, String name);
	public void deleteCompany(int id);

}
