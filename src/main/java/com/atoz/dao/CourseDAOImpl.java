package com.atoz.dao;

import com.atoz.model.Course;
import com.atoz.model.CourseDTO;
import com.atoz.model.CourseEnrolementDTO;
import com.atoz.model.UserInformation;
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

  private String saveContent = "update courses c " +
      "set c.content=:content " +
      "where c.id=:course_id";

  @Override
  public int saveCourse(Course course, String userName) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params;
    try {
      Course cu;
      cu=loadCourse(course.getName());
      if(cu!=null)
      {
        params = new MapSqlParameterSource();
        params.addValue("content", course.getContent());
        params.addValue("course_id", cu.getId());
        template.update(saveContent, params);
        return 1; //course already exists
      }
      params = new MapSqlParameterSource();
      params.addValue("name", course.getName());
      params.addValue("code", course.getCode());
      params.addValue("department_id", course.getDepartmentId());
      params.addValue("start_date", course.getStartDate());
      params.addValue("end_date", course.getStopDate());
      params.addValue("content", course.getContent());
      template.update(insertCourse, params, keyHolder);

      UserInformation userInformation = userDAO.getUserInformation(userName);
      params = new MapSqlParameterSource();
      params.addValue("uid",userInformation.getUserID());
      params.addValue("cid",keyHolder.getKey().intValue());
      template.update(insertCourseUser, params, keyHolder);
      log.info("Course " +course.getName() +" was added");
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
  public List<CourseDTO> loadCoursesForInstructor(String userName) {
    List<CourseDTO> courseDTOs = null;
    try {
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue("login", userName);
      courseDTOs = template.query(selectCoursesForUser, params, new CourseDTORowMapper());
      log.info("Courses were loaded for user: "+userName);
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

  String selectCoursesForStudent = "select c.id, c.name from courses c " +
      "inner join course_enrolement ce on ce.course_id=c.id " +
      "inner join users u on u.id=ce.student_id " +
      "where u.login=:login";

  @Override
  public List<CourseDTO> loadCoursesForStudent(String userName) {
    List<CourseDTO> courseDTOs = null;
    try {
      MapSqlParameterSource parameterSource = new MapSqlParameterSource();
      parameterSource.addValue("login", userName);
      courseDTOs = template.query(selectCoursesForStudent, parameterSource, new CourseDTORowMapper());
      log.info("Courses were loaded for student: "+userName);
    } catch (DataAccessException ex) {
      log.error("Failed to load courses for student: " + ex);
    }
    return courseDTOs;
  }

  String selectGrades = "select c.name, c.id, ud.first_name, ud.last_name, u.id, ce.grade from courses c " +
      "inner join course_enrolement ce on ce.course_id=c.id " +
      "inner join users u on u.id=ce.student_id " +
      "inner join user_details ud on ud.user_id=u.id " +
      "where u.login=:login";

  @Override
  public List<CourseEnrolementDTO> getGrades(String userName) {
    List<CourseEnrolementDTO> enrolementDTOs = null;
    try {
      MapSqlParameterSource parameterSource = new MapSqlParameterSource();
      parameterSource.addValue("login", userName);
      enrolementDTOs = template.query(selectGrades, parameterSource, new CourseEnrolementDTOMapper());
      log.info("Courses were loaded for student: "+userName);
    } catch (DataAccessException ex) {
      log.error("Failed to load courses for student: " + ex);
    }
    return enrolementDTOs;
  }

  String selectStudentsForCourse = "select c.name, c.id, ud.first_name, ud.last_name, u.id, ce.grade from courses c " +
      "inner join course_enrolement ce on ce.course_id=c.id " +
      "inner join users u on u.id=ce.student_id " +
      "inner join user_details ud on ud.user_id=u.id " +
      "where c.id=:course_id";

  @Override
  public List<CourseEnrolementDTO> getStudentsForCourse(int courseId) {
    List<CourseEnrolementDTO> enrolementDTOs = null;
    try {
      MapSqlParameterSource parameterSource = new MapSqlParameterSource();
      parameterSource.addValue("course_id", courseId);
      enrolementDTOs = template.query(selectStudentsForCourse, parameterSource, new CourseEnrolementDTOMapper());
      log.info("Students were loaded for course: "+courseId);
    } catch (DataAccessException ex) {
      log.error("Failed to load students for course: " + ex);
    }
    return enrolementDTOs;
  }

  String updateGrades = "update course_enrolement ce " +
      "set grade=:grade " +
      "where ce.student_id=:student_id and ce.course_id=:course_id";

  @Override
  public void saveGrades(List<CourseEnrolementDTO> courseEnrolementDTOs) {
    try {
      for (CourseEnrolementDTO courseEnrolementDTO : courseEnrolementDTOs) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("grade", courseEnrolementDTO.getGrade());
        parameterSource.addValue("student_id", courseEnrolementDTO.getStudentId());
        parameterSource.addValue("course_id", courseEnrolementDTO.getCourseId());
        template.update(updateGrades, parameterSource);
      }
      log.info("Successfully updated grades");
    } catch (DataAccessException ex) {
      log.error("Failed to update grades: " + ex);
    }
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

  private final class CourseEnrolementDTOMapper implements RowMapper<CourseEnrolementDTO> {
    @Override
    public CourseEnrolementDTO mapRow(ResultSet resultSet, int i) throws SQLException {
      return new CourseEnrolementDTO(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3)+" "+resultSet.getString(4),
          resultSet.getInt(5), resultSet.getInt(6));
    }
  }
}
