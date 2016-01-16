package com.atoz.ui;

import com.atoz.model.CourseDTO;
import com.atoz.model.CourseEnrolementDTO;
import com.atoz.security.SecurityHelper;
import com.atoz.service.CourseService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Sergiu on 16.01.2016.
 */
public class StudentsView extends VerticalLayout {

  private CourseService courseService;

  public StudentsView() {
    courseService = ContextAware.getBean(CourseService.class);
    loadCourses();
  }

  private void loadCourses() {
    ComboBox courseCombo = new ComboBox();
    List<CourseDTO> courseDTOs = courseService.loadCoursesForInstructor(SecurityHelper.getUserName());
    BeanItemContainer<CourseDTO> beanItemContainer = new BeanItemContainer<>(CourseDTO.class);
    beanItemContainer.addAll(courseDTOs);
    courseCombo.setContainerDataSource(beanItemContainer);
    courseCombo.setItemCaptionPropertyId("name");
    courseCombo.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
    courseCombo.setNullSelectionAllowed(false);
    courseCombo.setValue(courseDTOs.get(0));
    courseCombo.setWidth("150px");

    HorizontalLayout selectCourseLayout = new HorizontalLayout();
    selectCourseLayout.setHeight("100px");
    selectCourseLayout.addComponent(courseCombo);
    selectCourseLayout.setComponentAlignment(courseCombo, Alignment.MIDDLE_CENTER);

    Button loadCourse = new Button("Load course");
    selectCourseLayout.addComponent(loadCourse);
    selectCourseLayout.setComponentAlignment(loadCourse, Alignment.MIDDLE_CENTER);

    loadCourse.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        List<CourseEnrolementDTO> studentsForCourse = courseService.getStudentsForCourse(((CourseDTO)courseCombo.getValue()).getId());
        BeanItemContainer<CourseEnrolementDTO> beanItemContainer = new BeanItemContainer<>(CourseEnrolementDTO.class, studentsForCourse);
        Grid grid = new Grid("Grades", beanItemContainer);
        grid.removeColumn("studentId");
        grid.removeColumn("courseId");
        grid.removeColumn("courseName");
        grid.setEditorEnabled(true);

        VerticalLayout editGradesLayout = new VerticalLayout();
        editGradesLayout.setSizeFull();
        editGradesLayout.addComponent(grid);
        editGradesLayout.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);

        Button saveChanges = new Button("Save");
        saveChanges.addClickListener(new Button.ClickListener() {
          @Override
          public void buttonClick(Button.ClickEvent clickEvent) {
            courseService.saveGrades(beanItemContainer.getItemIds());
          }
        });
        editGradesLayout.addComponent(saveChanges);
        editGradesLayout.setComponentAlignment(saveChanges, Alignment.MIDDLE_CENTER);

        removeAllComponents();
        addComponent(editGradesLayout);
        setComponentAlignment(editGradesLayout, Alignment.MIDDLE_CENTER);
      }
    });

    setSizeFull();
    addComponent(selectCourseLayout);
    setComponentAlignment(selectCourseLayout, Alignment.MIDDLE_CENTER);
  }
}
