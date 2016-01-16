package com.atoz.service;

import com.atoz.dao.CourseDAO;
import com.atoz.model.Course;
import com.atoz.model.CourseDTO;
import com.atoz.model.CourseEnrolementDTO;

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
  public int saveCourse(Course course, String userName) {
    return courseDAO.saveCourse(course, userName);
  }

  @Override
  public List<CourseDTO> loadCoursesForInstructor(String userName) {
    return courseDAO.loadCoursesForInstructor(userName);
  }

  @Override
  public Course loadCourse(String coursname) {
    return courseDAO.loadCourse(coursname);
  }

  @Override
  public List<CourseDTO> loadCoursesForStudent(String userName) {
    return courseDAO.loadCoursesForStudent(userName);
  }

  @Override
  public List<CourseEnrolementDTO> getGrades(String userName) {
    return courseDAO.getGrades(userName);
  }

  @Override
  public List<CourseEnrolementDTO> getStudentsForCourse(int courseId) {
    return courseDAO.getStudentsForCourse(courseId);
  }

  @Override
  public void saveGrades(List<CourseEnrolementDTO> courseEnrolementDTOs) {
    courseDAO.saveGrades(courseEnrolementDTOs);
  }
}
