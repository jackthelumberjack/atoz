package com.atoz.service;

import com.atoz.dao.CourseDAO;
import com.atoz.model.Course;

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
}
