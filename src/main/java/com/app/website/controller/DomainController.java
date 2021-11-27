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

import com.app.website.database.interfaces.DomainDao;
import com.app.website.entity.Domain;
import com.app.website.entity.Topic;

@RestController
@Configuration
public class DomainController {
	
	@Autowired
	private DomainDao domainDao;
	private final String STATUS_SUCCESS = "SUCCESS";
	private final String STATUS_ERROR = "ERROR";
	
	@PostMapping(value = "/domain", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int createDomain(@RequestBody Domain domain) {
		return domainDao.createDomain(domain);
	}
	
	@GetMapping(value = "/domain/id/{topicId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Domain getDomainById(@PathVariable int domainId) {
		return domainDao.getDomainById(domainId);
	}
	
	@GetMapping(value = "/domain/name/{topicName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Domain getDomainByName(@PathVariable String domainName) {
		return domainDao.getDomainByName(domainName);
	}
	
	@GetMapping(value = "/domains/{domainName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Domain> getDomainsByName(@PathVariable String domainName){
		return domainDao.getDomainsByName(domainName);
	}
	
	@GetMapping(value = "/domain/{domainId}/topics", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Topic> getTopicsOfDomain(@PathVariable int domainId){
		return domainDao.getTopicsOfDomain(domainId);
	}
	
	@GetMapping(value = "/domains", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Domain> getAllDomains(){
		return domainDao.getAllDomains();
	}
	
	@PutMapping(value = "/domain/{domainId}", consumes = MediaType.TEXT_PLAIN_VALUE)
	public String updateDomain(@PathVariable int domainId, @RequestBody String domainName) {
		try {
			domainDao.updateDomain(domainId, domainName);
			return STATUS_SUCCESS;
		}catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@DeleteMapping(value = "/domain/{domainId}")
	public String deleteDomain(@PathVariable int domainId) {
		try {
			domainDao.deleteDomain(domainId);
			return STATUS_SUCCESS;
		}catch (Exception e) {
			return STATUS_ERROR;
		}
	}

}
