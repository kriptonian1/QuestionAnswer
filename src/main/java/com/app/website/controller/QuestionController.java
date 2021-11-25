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

import com.app.website.database.interfaces.QuestionDao;
import com.app.website.entity.Company;
import com.app.website.entity.Question;
import com.app.website.entity.Subtopic;
import com.app.website.entity.Tag;
import com.app.website.entity.User;
import com.app.website.entity.request.PostQuestion;

@RestController
@Configuration
public class QuestionController {
	
	@Autowired
	private QuestionDao questionDao;
	
	@PostMapping(value = "/question", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int postQuestion(@RequestBody PostQuestion requestQuestion) {
		return questionDao.postQuestion(requestQuestion);
	}
	
	@GetMapping(value = "/question/{questionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Question getQuestionById(@PathVariable int questionId) {
		return questionDao.getQuestionById(questionId); 
	}
	
	@GetMapping(value = "/questions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Question> getAllQuestions(){
		return questionDao.getAllQuestions();
	}
	
	@GetMapping(value = "/user/{userId}/questions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Question> getQuestionsFromUser(@PathVariable int userId){
		return questionDao.getQuestionsFromUser(userId);
	}
	
	@GetMapping(value = "/tag/{tagId}/questions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Question> getQuestionsByTag(@PathVariable int tagId){
		return questionDao.getQuestionsByTag(tagId);
	}
	
	@GetMapping(value = "/company/{companyId}/questions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Question> getQuestionsByCompany(@PathVariable int companyId){
		return questionDao.getQuestionsByCompany(companyId);
	}
	
	@GetMapping(value = "/subtopic/{subtopicId}/questions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Question> getQuestionsBySubtopic(@PathVariable int subtopicId){
		return questionDao.getQuestionsBySubtopic(subtopicId);
	}
	
	@GetMapping(value = "/topic/{topicId}/questions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Question> getQuestionsByTopic(@PathVariable int topicId){
		return questionDao.getQuestionsByTopic(topicId);
	}
	
	@GetMapping(value = "/question/{questionId}/tags", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tag> getTags(@PathVariable int questionId){
		return questionDao.getTags(questionId);
	}
	
	@GetMapping(value = "/question/{questionId}/companies", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Company> getCompanies(@PathVariable int questionId){
		return questionDao.getCompanies(questionId);
	}
	
	@GetMapping(value = "/question/{questionId}/subtopics", produces = MediaType.APPLICATION_JSON_VALUE)
	public Subtopic getSubtopic(@PathVariable int questionId) {
		return questionDao.getSubtopic(questionId);
	}
	
	@GetMapping(value = "/question/{questionId}/likes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getLikes(@PathVariable int questionId){
		return questionDao.getLikes(questionId);
	}
	
	@GetMapping(value = "/question/{questionId}/creator", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getCreator(@PathVariable int questionId) {
		return questionDao.getCreator(questionId);
	}
	
	@PutMapping(value = "/question/{questionId}/like/{userId}")
	public String addLike(@PathVariable int questionId, @PathVariable int userId) {
		return String.valueOf(questionDao.addLike(questionId, userId));
	}
	
	@DeleteMapping(value = "/question/{questionId}/like/{userId}")
	public String removeLike(@PathVariable int questionId, @PathVariable int userId) {
		return String.valueOf(questionDao.removeLike(questionId, userId));
	}
	
	@DeleteMapping(value = "/question/{questionId}")
	public String deleteQuestion(@PathVariable int questionId) {
		return String.valueOf(questionDao.deleteQuestion(questionId));
	}
	
	@DeleteMapping(value = "/question/{questionId}/tag/{tagId}")
	public String removeTag(@PathVariable int tagId, @PathVariable int questionId) {
		return String.valueOf(questionDao.removeTag(questionId, tagId));
	}
	
	@PutMapping(value = "/question/{questionId}/tag/{tagId}")
	public String addTag(@PathVariable int tagId, @PathVariable int questionId) {
		return String.valueOf(questionDao.addTag(questionId, tagId));
	}
	
	@DeleteMapping(value = "/question/{questionId}/company/{companyId}")
	public String removeCompany(@PathVariable int companyId, @PathVariable int questionId) {
		return String.valueOf(questionDao.removeCompany(questionId, companyId));
	}
	
	@PutMapping(value = "/question/{questionId}/company/{companyId}")
	public String addCompany(@PathVariable int companyId, @PathVariable int questionId) {
		return String.valueOf(questionDao.addCompany(questionId, companyId));
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
	public String editQuestion(@PathVariable int id, @RequestBody String question) {
		return String.valueOf(questionDao.editQuestion(id, question));
	}

}
