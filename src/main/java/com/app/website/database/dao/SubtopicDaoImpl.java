package com.app.website.database.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.website.database.interfaces.SubtopicDao;
import com.app.website.entity.Subtopic;
import com.app.website.entity.Topic;

@Repository
@Transactional
public class SubtopicDaoImpl implements SubtopicDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int createSubtopic(Subtopic subtopic) {
		Session session = sessionFactory.getCurrentSession();
		int id = -1;
		if (getSubtopicByName(subtopic.getSubtopic()) == null) {
			Topic topic = session.get(Topic.class, subtopic.getTopicId());
			topic.addSubtopic(subtopic);
			id = (int) session.save(subtopic);
			session.save(topic);
		}
		return id;
	}

	@Override
	public Subtopic getSubtopicByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		try {
			return (Subtopic) session.createQuery(String.format("from Subtopic where subtopic='%s'", name)).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Subtopic getSubtopicById(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			return (Subtopic) session.createQuery(String.format("from Subtopic where id='%d'", id)).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subtopic> getSubtopicsByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		try {
			return session.createQuery(String.format("from Subtopic where subtopic like '%s'", name)).getResultList();
		}catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subtopic> getAllSubtopics() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Subtopic").getResultList();
	}

	@Override
	public void updateSubtopic(int id, String name) {
		Session session = sessionFactory.getCurrentSession();
		Subtopic subtopic = session.get(Subtopic.class, id);
		subtopic.setSubtopic(name);
		session.save(subtopic);
	}

	@Override
	public void deleteSubtopic(int id) {
		Session session = sessionFactory.getCurrentSession();
		Subtopic subtopic = session.get(Subtopic.class, id);
		session.delete(subtopic);
	}

	@Override
	public Topic getTopic(int id) {
		try {
			Subtopic subtopic = getSubtopicById(id);
			return subtopic.getTopic();
		}catch (Exception e) {
			return null;
		}
	}

}
