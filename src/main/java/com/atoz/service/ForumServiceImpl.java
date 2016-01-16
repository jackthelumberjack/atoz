package com.atoz.service;

import com.atoz.dao.ForumDAO;
import com.atoz.model.ForumPost;
import com.atoz.model.ForumSubject;

import java.util.List;

/**
 * Created by Sergiu on 15.01.2016.
 */
public class ForumServiceImpl implements ForumService {

  private ForumDAO forumDAO;

  public void setForumDAO(ForumDAO forumDAO) {
    this.forumDAO = forumDAO;
  }

  @Override
  public boolean saveTopic(ForumSubject forumSubject) {
    return forumDAO.saveTopic(forumSubject);
  }

  @Override
  public List<ForumSubject> getAllTopics() {
    return forumDAO.getAllTopics();
  }

  @Override
  public boolean savePost(ForumPost forumPost) {
    return forumDAO.savePost(forumPost);
  }

  @Override
  public List<ForumPost> getPostsForTopic(int topicId) {
    return forumDAO.getPostsForTopic(topicId);
  }
}
