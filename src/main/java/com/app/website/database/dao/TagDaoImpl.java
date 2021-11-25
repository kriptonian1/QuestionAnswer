package com.app.website.database.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.website.database.interfaces.TagDao;
import com.app.website.entity.Tag;

@Repository
@Transactional
public class TagDaoImpl implements TagDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int createTag(Tag tag) {
		Session session = sessionFactory.getCurrentSession();
		int id = -1;
		if (getTagByName(tag.getName()) == null)
			id = (int) session.save(tag);
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> getTagsByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(String.format("from Tag where name like '%s'", name)).getResultList();
	}

	@Override
	public Tag getTagById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Tag tag = null;
		try {
			tag = session.get(Tag.class, id);
		}catch (Exception e) {
		}
		return tag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> getAllTags() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Tag").getResultList();
	}

	@Override
	public boolean updateTag(int id, String name) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Tag tag = getTagById(id);
			tag.setName(name);
			session.update(tag);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteTag(int id) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Tag tag = getTagById(id);
			session.delete(tag);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Tag getTagByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		try {
			return (Tag) session.createQuery(String.format("from Tag where name='%s'", name)).getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

}
