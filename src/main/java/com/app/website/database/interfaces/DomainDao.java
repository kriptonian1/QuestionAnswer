package com.app.website.database.interfaces;

import java.util.List;

import com.app.website.entity.Domain;
import com.app.website.entity.Topic;

public interface DomainDao {
	
	public int createDomain(Domain domain);
	public Domain getDomainById(int domainId);
	public Domain getDomainByName(String domainName);
	public List<Domain> getDomainsByName(String domainName);
	public List<Topic> getTopicsOfDomain(int domainId);
	public List<Domain> getAllDomains();
	public void updateDomain(int domainId, String domainName);
	public void deleteDomain(int domainId);

}
