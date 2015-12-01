package com.atoz.dao;

import com.atoz.model.Course;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;

/**
 * Created by Sergiu on 01.12.2015.
 */
public class CourseDAOImpl implements CourseDAO {

  private Log log = LogFactory.getLog(CourseDAOImpl.class);


  private NamedParameterJdbcTemplate template;

  public void setDataSource(DataSource dataSource) {
    template = new NamedParameterJdbcTemplate(dataSource);
  }

  private String insertCourse = "insert into courses(name, code, department_id, start_date, end_date, content) " +
      " values(:name, :code, :department_id, :start_date, :end_date, :content)";

  @Override
  public void saveCourse(Course course, String userName) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    try {
      params.addValue("name", course.getName());
      params.addValue("code", course.getCode());
      params.addValue("department_id", course.getDepartmentId());
      params.addValue("start_date", course.getStartDate());
      params.addValue("end_date", course.getStopDate());
      params.addValue("content", course.getContent());
      template.update(insertCourse, params, keyHolder);
    } catch (DataAccessException ex) {
      log.error("Failed to save course: " + ex);
    }
  }
}
