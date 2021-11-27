package com.app.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.website.database.interfaces.CompanyDao;
import com.app.website.entity.Company;

@Configuration
@RestController
public class CompanyController {
	
	@Autowired
	private CompanyDao companyDao;
	private static final String STATUS_SUCCESS = "SUCCESS";
	private static final String STATUS_ERROR = "ERROR";
	
	@PostMapping(value = "/company", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int createCompany(@RequestBody Company company) {
		return companyDao.createCompany(company);
	}
	
	@GetMapping(value = "/company/name/{companyName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Company getCompanyByName(@PathVariable String companyName) {
		return companyDao.getCompanyByName(companyName);
	}
	
	@GetMapping(value = "/company/id/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Company getCompanyById(@PathVariable int companyId) {
		return companyDao.getCompanyById(companyId);
	}
	
	@GetMapping(value = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Company> getAllCompanies(){
		return companyDao.getAllCompanies();
	}
	
	@PutMapping(value = "/company/{companyId}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String updateCompany(@PathVariable int companyId, @RequestBody String name) {
		try {
			companyDao.updateCompany(companyId, name);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@DeleteMapping(value = "/company/{companyId}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteCompany(@PathVariable int companyId) {
		try {
			companyDao.deleteCompany(companyId);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
}
