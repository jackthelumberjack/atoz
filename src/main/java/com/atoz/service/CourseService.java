package com.atoz.service;

import com.atoz.model.Course;
import com.atoz.model.CourseDTO;

import java.util.List;

/**
 * Created by Sergiu on 01.12.2015.
 */
public interface CourseService {

  int saveCourse(Course course, String userName);
  List<CourseDTO> loadCoursesForUser(String userName);
  Course loadCourse(String coursename);

}
