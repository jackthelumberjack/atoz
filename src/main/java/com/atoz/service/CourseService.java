package com.atoz.service;

import com.atoz.model.Course;
import com.atoz.model.CourseDTO;
import com.atoz.model.CourseEnrolementDTO;

import java.util.List;

/**
 * Created by Sergiu on 01.12.2015.
 */
public interface CourseService {

  int saveCourse(Course course, String userName);
  List<CourseDTO> loadCoursesForInstructor(String userName);
  Course loadCourse(String coursename);
  List<CourseDTO> loadCoursesForStudent(String userName);
  List<CourseEnrolementDTO> getGrades(String userName);
  List<CourseEnrolementDTO> getStudentsForCourse(int courseId);
  void saveGrades(List<CourseEnrolementDTO> courseEnrolementDTOs);
}
