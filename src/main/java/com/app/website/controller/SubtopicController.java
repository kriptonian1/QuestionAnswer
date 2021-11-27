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

import com.app.website.database.interfaces.SubtopicDao;
import com.app.website.entity.Subtopic;
import com.app.website.entity.Topic;

@Configuration
@RestController
public class SubtopicController {
	
	@Autowired
	private SubtopicDao subtopicDao;
	private static final String STATUS_SUCCESS = "SUCCESS";
	private static final String STATUS_ERROR = "ERROR";
	
	@PostMapping(value = "/subtopic", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int createSubtopic(@RequestBody Subtopic subtopic) {
		return subtopicDao.createSubtopic(subtopic);
	}
	
	@GetMapping(value = "/subtopic/id/{subtopicId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Subtopic getSubtopicById(@PathVariable int subtopicId) {
		return subtopicDao.getSubtopicById(subtopicId);
	}

	@GetMapping(value = "/subtopic/name/{subtopicName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Subtopic getSubtopicById(@PathVariable String subtopicName) {
		return subtopicDao.getSubtopicByName(subtopicName);
	}
	
	@GetMapping(value = "/subtopics/{subtopicName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Subtopic> getSubtopicsByName(@PathVariable String subtopicName){
		return subtopicDao.getSubtopicsByName(subtopicName);
	}
	
	@GetMapping(value = "/subtopics", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Subtopic> getAllSubtopics(){
		return subtopicDao.getAllSubtopics();
	}
	
	@PutMapping(value = "/subtopic/{subtopicId}", consumes = MediaType.TEXT_PLAIN_VALUE)
	public String updateSubtopic(@PathVariable int subtopicId, @RequestBody String name) {
		try {
			subtopicDao.updateSubtopic(subtopicId, name);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@DeleteMapping(value = "/subtopic/{subtopicId}")
	public String deleteSubtopic(@PathVariable int subtopicId) {
		try {
			subtopicDao.deleteSubtopic(subtopicId);
			return STATUS_SUCCESS;
		} catch (Exception e) {
			return STATUS_ERROR;
		}
	}
	
	@GetMapping(value = "/subtopic/{subtopicId}/topic", produces = MediaType.APPLICATION_JSON_VALUE)
	public Topic getTopic(@PathVariable int subtopicId) {
		return subtopicDao.getTopic(subtopicId);
	}

}
