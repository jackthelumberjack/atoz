package com.atoz.model;

/**
 * Created by Sergiu on 16.01.2016.
 */
public class CourseEnrolementDTO {
  private String courseName;
  private int courseId;
  private String studentName;
  private int studentId;
  private int grade;

  public CourseEnrolementDTO(String courseName, int grade) {
    this.courseName = courseName;
    this.grade = grade;
  }

  public CourseEnrolementDTO(String courseName, int courseId, String studentName, int studentId, int grade) {
    this.courseName = courseName;
    this.courseId = courseId;
    this.studentName = studentName;
    this.studentId = studentId;
    this.grade = grade;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }
}
