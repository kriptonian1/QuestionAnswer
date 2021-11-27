package com.app.website.database.interfaces;

import java.util.List;

import com.app.website.entity.Company;
import com.app.website.entity.Question;
import com.app.website.entity.Subtopic;
import com.app.website.entity.Tag;
import com.app.website.entity.User;
import com.app.website.entity.request.PostQuestion;

public interface QuestionDao {
	
	public int postQuestion(PostQuestion postQuestion);
	public Question getQuestionById(int id);
	public List<Question> getAllQuestions();
	public List<Question> getQuestionsFromUser(int userId);
	public List<Question> getQuestionsByTag(int tagId);
	public List<Question> getQuestionsByCompany(int companyId);
	public List<Question> getQuestionsBySubtopic(int subtopicId);
	public List<Question> getQuestionsByTopic(int topicId);
	public List<Company> getCompanies(int questionId);
	public List<Tag> getTags(int questionId);
	public Subtopic getSubtopic(int questionId);
	public User getCreator(int questionId);
	public List<User> getLikes(int questionId);
	public void deleteQuestion(int id);
	public void removeTag(int questionId, int tagId);
	public void addTag(int questionId, int tagId);
	public void removeCompany(int questionId, int companyId);
	public void addCompany(int questionId, int companyId);
	public void editQuestion(int questionId, String question);
	public void addLike(int questionId, int userId);
	public void removeLike(int questionId, int userId);

}
