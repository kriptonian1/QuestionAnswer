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

import com.app.website.database.interfaces.TagDao;
import com.app.website.entity.Tag;

@Configuration
@RestController
public class TagController {
	
	@Autowired
	private TagDao tagDao;
	private static final String STATUS_SUCCESS = "SUCCESS";
	private static final String STATUS_ERROR = "ERROR";
	
	@PostMapping(value = "/tag", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int createTag(@RequestBody Tag tag) {
		return tagDao.createTag(tag);
	}
	
	@GetMapping(value = "/tag/id/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Tag getTagById(@PathVariable int tagId) {
		return tagDao.getTagById(tagId);
	}
	
	@GetMapping(value = "/tag/name/{tagName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Tag getTagByName(@PathVariable String tagName) {
		return tagDao.getTagByName(tagName);
	}
	
	@GetMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tag> getAllTags(){
		return tagDao.getAllTags();
	}
	
	@GetMapping(value = "/tags/{tagName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tag> getTagsByName(@PathVariable String tagName){
		return tagDao.getTagsByName(tagName);
	}
	
	@PutMapping(value = "/tag/{tagId}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String updateCompany(@PathVariable int tagId, @RequestBody String name) {
		try {
			tagDao.updateTag(tagId, name);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@DeleteMapping(value = "/tag/{tagId}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteCompany(@PathVariable int tagId) {
		try {
			tagDao.deleteTag(tagId);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
}
