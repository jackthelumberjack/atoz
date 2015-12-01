package com.atoz.service;

import com.atoz.dao.CourseDAO;
import com.atoz.model.Course;
import com.atoz.model.CourseDTO;

import java.util.List;

/**
 * Created by Sergiu on 01.12.2015.
 */
public class CourseServiceImpl implements CourseService {

  private CourseDAO courseDAO;

  public void setCourseDAO(CourseDAO courseDAO) {
    this.courseDAO = courseDAO;
  }

  @Override
  public void saveCourse(Course course, String userName) {
    courseDAO.saveCourse(course, userName);
  }

  @Override
  public List<CourseDTO> loadCoursesForUser(String userName) {
    return courseDAO.loadCoursesForUser(userName);
  }

  @Override
  public Course loadCourse(int courseId) {
    return courseDAO.loadCourse(courseId);
  }
}
