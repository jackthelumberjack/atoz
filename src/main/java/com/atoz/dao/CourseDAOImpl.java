package com.atoz.dao;

import com.atoz.model.Course;
import com.atoz.model.CourseDTO;
import com.atoz.model.UserInformation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sergiu on 01.12.2015.
 */
public class CourseDAOImpl implements CourseDAO {

 // private Log log = LogFactory.getLog(CourseDAOImpl.class);
  static Logger log = Logger.getLogger(CourseDAOImpl.class.getName());

  private NamedParameterJdbcTemplate template;
  private UserDAO userDAO;

  public void setDataSource(DataSource dataSource) {

    template = new NamedParameterJdbcTemplate(dataSource);
  }

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO=userDAO;
  }

  private String insertCourse = "insert into courses(name, code, department_id, start_date, end_date, content) " +
      " values(:name, :code, :department_id, :start_date, :end_date, :content)";

  private String insertCourseUser="insert into user_course(user_id, course_id) " +
      " values(:uid, :cid)";

  @Override
  public int saveCourse(Course course, String userName) {
      PropertyConfigurator.configure("D:\\AtoZ\\src\\main\\log4j.properties");
    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    try {
      params.addValue("name", course.getName());
      Course cu=null;
      cu=loadCourse(course.getName());
      if(cu!=null)
      {
          return 1; //course already exists
      }
      params.addValue("code", course.getCode());
      params.addValue("department_id", course.getDepartmentId());
      params.addValue("start_date", course.getStartDate());
      params.addValue("end_date", course.getStopDate());
      params.addValue("content", course.getContent());


      template.update(insertCourse, params, keyHolder);
      log.info("Course " +course.getName() +" was added");
      UserInformation userInformation=userDAO.getUserInformation("sbreban");
      Course c=loadCourse(course.getName());
      MapSqlParameterSource params2 = new MapSqlParameterSource();
   //   params = new MapSqlParameterSource();
     // System.out.println(""+userInformation.getUserID());
      params2.addValue("uid",userInformation.getUserID());
      params2.addValue("cid",c.getId());
      template.update(insertCourseUser, params2, keyHolder);
    } catch (DataAccessException ex) {
      log.error("Failed to save course: " + ex);
    }
    return 0;//course does not exist
  }

  private String selectCoursesForUser = "select c.id, c.name from courses c " +
      " inner join user_course uc on uc.course_id=c.id " +
      " inner join users u on u.id=uc.user_id " +
      " where u.login=:login";

  @Override
  public List<CourseDTO> loadCoursesForUser(String userName) {
    List<CourseDTO> courseDTOs = null;
    try {
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue("login", userName);
      courseDTOs = template.query(selectCoursesForUser, params, new CourseDTORowMapper());
    } catch (DataAccessException ex) {
      log.error("Failed to load courses for user: " + ex);
    }
    return courseDTOs;
  }

  private String selectCourse = "select * from courses c where c.name=:course_name";

  @Override
  public Course loadCourse(String name) {
    Course course = null;
    try {
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue("course_name", name);
      course = template.queryForObject(selectCourse, params, new CourseRowMapper());
    } catch (DataAccessException ex) {
      log.error("Failed to load course: " + ex);
    }
    return course;
  }

  private final class CourseDTORowMapper implements RowMapper<CourseDTO> {
    @Override
    public CourseDTO mapRow(ResultSet resultSet, int i) throws SQLException {
      return new CourseDTO(resultSet.getInt(1), resultSet.getString(2));
    }
  }

  private final class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
      return new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getDate(5), resultSet.getDate(6), resultSet.getString(7));
    }
  }
}
