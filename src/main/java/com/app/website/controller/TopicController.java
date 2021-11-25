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

import com.app.website.database.interfaces.TopicDao;
import com.app.website.entity.Subtopic;
import com.app.website.entity.Topic;

@Configuration
@RestController
public class TopicController {
	
	@Autowired
	private TopicDao topicDao;
	
	@PostMapping(value = "/topic", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int createTopic(@RequestBody Topic topic) {
		return topicDao.createTopic(topic);
	}
	
	@GetMapping(value = "/topic/id/{topicId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Topic getTopicById(@PathVariable int topicId) {
		return topicDao.getTopicById(topicId);
	}
	
	@GetMapping(value = "/topic/name/{topicName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Topic getTopicByName(@PathVariable String topicName) {
		return topicDao.getTopicByName(topicName);
	}
	
	@GetMapping(value = "/topics/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Topic> getTopicsByName(@PathVariable String name){
		return topicDao.getTopicsByName(name);
	}
	
	@GetMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Topic> getAllTopics(){
		return topicDao.getAllTopics();
	}
	
	@GetMapping(value = "/topic/{topicId}/subtopics", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Subtopic> getSubtopics(@PathVariable int topicId){
		return topicDao.getSubtopics(topicId);
	}
	
	@PutMapping(value = "/topic/{topicsId}", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String updateCompany(@PathVariable int topicId, @RequestBody String name) {
		return String.valueOf(topicDao.updateTopic(topicId, name));
	}
	
	@DeleteMapping(value = "/topic/{topicsId}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteCompany(@PathVariable int topicId) {
		return String.valueOf(topicDao.deleteTopic(topicId));
	}
	
}
