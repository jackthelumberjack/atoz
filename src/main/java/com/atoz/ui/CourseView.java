package com.atoz.ui;

import com.atoz.model.Course;
import com.atoz.model.CourseDTO;
import com.atoz.model.Department;
import com.atoz.security.SecurityHelper;
import com.atoz.service.CourseService;
import com.atoz.service.UserService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Sergiu on 16.01.2016.
 */
public class CourseView extends HorizontalLayout {

  private Button viewUserCourses;

  private VerticalLayout centerLayout;

  private UserService userService;
  private CourseService courseService;

  private Logger logger = Logger.getLogger(ContentEditor.class.getName());

  public CourseView() {
    userService = ContextAware.getBean(UserService.class);
    courseService = ContextAware.getBean(CourseService.class);
    initLayout();
    addHandlers();
  }

  private void initLayout() {
    VerticalLayout leftMenu = new VerticalLayout();
    leftMenu.setSizeFull();

    VerticalLayout buttonsLayout = new VerticalLayout();
    viewUserCourses = new Button("View your courses");
    viewUserCourses.setWidth("100%");
    buttonsLayout.addComponent(viewUserCourses);
    buttonsLayout.setComponentAlignment(viewUserCourses, Alignment.MIDDLE_CENTER);

    leftMenu.addComponent(buttonsLayout);
    leftMenu.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);

    centerLayout = new VerticalLayout();
    centerLayout.setSizeFull();

    addComponent(leftMenu);
    addComponent(centerLayout);
    setExpandRatio(leftMenu, 1);
    setExpandRatio(centerLayout, 7);
  }

  private void addHandlers() {
    viewUserCourses.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        loadEnrolledCourses();
      }
    });
  }

  private void loadEnrolledCourses() {
    ComboBox courseCombo = new ComboBox();
    List<CourseDTO> courseDTOs = courseService.loadCoursesForStudent(SecurityHelper.getUserName());
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
        centerLayout.removeAllComponents();
        RichTextArea textArea = new RichTextArea();
        textArea.setSizeFull();
        textArea.setValue(courseService.loadCourse(((CourseDTO)courseCombo.getValue()).getName()).getContent());
        textArea.setReadOnly(true);
        centerLayout.addComponent(textArea);
      }
    });

    centerLayout.removeAllComponents();
    centerLayout.addComponent(selectCourseLayout);
    centerLayout.setComponentAlignment(selectCourseLayout, Alignment.MIDDLE_CENTER);
  }
}
