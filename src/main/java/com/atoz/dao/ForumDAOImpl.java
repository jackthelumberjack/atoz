package com.atoz.dao;

import com.atoz.model.ForumPost;
import com.atoz.model.ForumSubject;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Sergiu on 15.01.2016.
 */
public class ForumDAOImpl extends BaseDAO implements ForumDAO {

  private Logger log = Logger.getLogger(ForumDAOImpl.class.getName());

  String insertSubject = "insert into forum_topics(title) " +
      "values (:subject_title)";

  @Override
  public boolean saveTopic(ForumSubject forumSubject) {
    boolean result = false;
    try {
      MapSqlParameterSource parameterSource  = new MapSqlParameterSource();
//      parameterSource.addValue("subject_id", forumSubject.getId());
      parameterSource.addValue("subject_title", forumSubject.getTitle());
      template.update(insertSubject, parameterSource);
      result = true;
    } catch (DataAccessException ex) {
      log.error("Failed to save forum subject: "+ex);
    }
    return result;
  }

  String selectTopics = "select * from forum_topics";

  @Override
  public List<ForumSubject> getAllTopics() {
    List<ForumSubject> result = null;
    try {
      result = template.query(selectTopics, new ForumSubjectMapper());
    } catch (DataAccessException ex) {
      log.error("Failed to load forum subjects: "+ex);
    }
    return result;
  }

  String insertPost = "insert into forum_posts(topic_id, post_date, message, author) " +
      "values(:topic_id, :post_date, :message, :author)";

  @Override
  public boolean savePost(ForumPost forumPost) {
    boolean result = false;
    try {
      MapSqlParameterSource parameterSource  = new MapSqlParameterSource();
      parameterSource.addValue("topic_id", forumPost.getTopicId());
      parameterSource.addValue("post_date", forumPost.getPostDate());
      parameterSource.addValue("message", forumPost.getMessage());
      parameterSource.addValue("author", forumPost.getAuthor());

      template.update(insertPost, parameterSource);
      result = true;
    } catch (DataAccessException ex) {
      log.error("Failed to save forum post: "+ex);
    }
    return result;
  }

  String selectPostsForTopic = "select * from forum_posts " +
      "where topic_id=:topic_id";

  @Override
  public List<ForumPost> getPostsForTopic(int topicId) {
    List<ForumPost> result = null;
    try {
      MapSqlParameterSource parameterSource  = new MapSqlParameterSource();
      parameterSource.addValue("topic_id", topicId);
      result = template.query(selectPostsForTopic, parameterSource, new ForumPostMapper());
      Collections.sort(result, new PostComparator());
    } catch (DataAccessException ex) {
      log.error("Failed to load forum subjects: "+ex);
    }
    return result;
  }

  private class PostComparator implements Comparator<ForumPost> {
    @Override
    public int compare(ForumPost o1, ForumPost o2) {
      return o1.getPostDate().compareTo(o2.getPostDate());
    }
  }

  private class ForumSubjectMapper implements RowMapper<ForumSubject> {
    @Override
    public ForumSubject mapRow(ResultSet resultSet, int i) throws SQLException {
      return new ForumSubject(resultSet.getInt(1), resultSet.getString(2));
    }
  }

  private class ForumPostMapper implements RowMapper<ForumPost> {
    @Override
    public ForumPost mapRow(ResultSet resultSet, int i) throws SQLException {
      return new ForumPost(resultSet.getInt(2), resultSet.getString(5), resultSet.getString(4), resultSet.getDate(3));
    }
  }
}
