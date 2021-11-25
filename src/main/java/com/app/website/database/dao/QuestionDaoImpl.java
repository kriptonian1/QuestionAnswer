package com.app.website.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.website.database.interfaces.QuestionDao;
import com.app.website.entity.Company;
import com.app.website.entity.Question;
import com.app.website.entity.Subtopic;
import com.app.website.entity.Tag;
import com.app.website.entity.Topic;
import com.app.website.entity.User;
import com.app.website.entity.request.PostQuestion;

@Repository
@Transactional
public class QuestionDaoImpl implements QuestionDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int postQuestion(PostQuestion requestQuestion) {
		//Assuming that all the entities are already created
		Session session = sessionFactory.getCurrentSession();
		Question question = new Question(requestQuestion.getQuestion());
		Company company = session.get(Company.class, requestQuestion.getCompanyId());
		Subtopic subtopic = session.get(Subtopic.class, requestQuestion.getSubtopicId());
		User user = session.get(User.class, requestQuestion.getUserId());
		List<Tag> tags = new ArrayList<>();
		if (requestQuestion.getTagId() != null) {
			for (int tagId : requestQuestion.getTagId()) {
				tags.add(session.get(Tag.class, tagId));
			}
		}
		if (company != null)
			question.addCompany(company);
		if (tags.size() != 0)
			question.addTags(tags);
		if (subtopic != null)
			question.setSubtopic(subtopic);
		int id = (int) session.save(question);
		user.addQuestion(question);
		session.update(user);
		return id;
	}
	
	@Override
	public Question getQuestionById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, id);
		return question;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Question> getAllQuestions(){
		Session session = sessionFactory.getCurrentSession();		
		List<Question> questions = session.createQuery("from Question").getResultList();
		return questions;
	}

	@Override
	public List<Question> getQuestionsFromUser(int userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		user.forceLoadQuestions();
		return user.getQuestions();
	}

	@Override
	public boolean deleteQuestion(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Question question = session.get(Question.class, id);
			session.delete(question);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeTag(int questionId, int tagId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Question question = session.get(Question.class, questionId);
			Tag tag = session.get(Tag.class, tagId);
			question.removeTag(tag);
			session.update(question);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addTag(int questionId, int tagId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Question question = session.get(Question.class, questionId);
			Tag tag = session.get(Tag.class, tagId);
			question.addTag(tag);
			session.update(question);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeCompany(int questionId, int companyId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Question question = session.get(Question.class, questionId);
			Company company = session.get(Company.class, companyId);
			question.removeCompany(company);
			session.update(question);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addCompany(int questionId, int companyId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Question question = session.get(Question.class, questionId);
			Company company = session.get(Company.class, companyId);
			question.addCompany(company);
			session.update(question);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean editQuestion(int questionId, String question) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Question q = session.get(Question.class, questionId);
			q.setQuestion(question);
			session.update(q);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Question> getQuestionsByTag(int tagId) {
		Session session = sessionFactory.getCurrentSession();
		Tag tag = session.get(Tag.class, tagId);
		List<Question> questions = tag.getQuestions();
		return questions;
	}

	@Override
	public List<Question> getQuestionsByCompany(int companyId) {
		Session session = sessionFactory.getCurrentSession();
		Company company = session.get(Company.class, companyId);
		List<Question> questions = company.getQuestions();
		return questions;
	}

	@Override
	public List<Question> getQuestionsBySubtopic(int subtopicId) {
		Session session = sessionFactory.getCurrentSession();
		Subtopic subtopic = session.get(Subtopic.class, subtopicId);
		List<Question> questions = subtopic.getQuestions();
		return questions;
	}

	@Override
	public List<Question> getQuestionsByTopic(int topicId) {
		Session session = sessionFactory.getCurrentSession();
		Topic topic = session.get(Topic.class, topicId);
		topic.forceLoadSubtopics();
		List<Question> questions = new ArrayList<>();
		for (Subtopic subtopic : topic.getSubtopics()) {
			for (Question question : subtopic.getQuestions()) {
				questions.add(question);
			}
		}
		return questions;
	}

	@Override
	public List<Company> getCompanies(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		question.forceLoadCompanies();
		return question.getCompanies();
	}

	@Override
	public List<Tag> getTags(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		question.forceLoadTags();
		return question.getTags();
	}

	@Override
	public Subtopic getSubtopic(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		return question.getSubtopic();
	}

	@Override
	public boolean addLike(int questionId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Question question = session.get(Question.class, questionId);
			User user = session.get(User.class, userId);
			question.addLike(user);
			session.update(user);
			session.update(question);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<User> getLikes(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		question.forceLoadLikes();
		return question.getLikes();
	}

	@Override
	public boolean removeLike(int questionId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Question question = session.get(Question.class, questionId);
			User user = session.get(User.class, questionId);
			question.removeLike(user);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public User getCreator(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		return question.getCreatorUser();
	}

}
