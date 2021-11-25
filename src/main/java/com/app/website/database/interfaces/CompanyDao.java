package com.app.website.database.interfaces;

import java.util.List;

import com.app.website.entity.Company;

public interface CompanyDao {
	
	public int createCompany(Company company);
	public Company getCompanyByName(String name);
	public Company getCompanyById(int id);
	public List<Company> getAllCompanies();
	public boolean updateCompany(int id, String name);
	public boolean deleteCompany(int id);

}
