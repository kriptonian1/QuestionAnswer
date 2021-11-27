package com.app.website.database.interfaces;

import java.util.List;

import com.app.website.entity.Question;
import com.app.website.entity.Tag;

public interface TagDao {
	
	public int createTag(Tag tag);
	public List<Tag> getTagsByName(String name);
	public Tag getTagByName(String name);
	public Tag getTagById(int id);
	public List<Tag> getAllTags();
	public List<Question> getQuestionOfTag(int tagId);
	public void updateTag(int id, String name);
	public void deleteTag(int id);

}
