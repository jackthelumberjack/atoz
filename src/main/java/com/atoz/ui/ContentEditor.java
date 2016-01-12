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

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Sergiu on 30.11.2015.
 */
public class ContentEditor extends HorizontalLayout {

  private Button saveButton;
  private Button loadButton;

  private RichTextArea textArea;
  private TextField name;
  private TextField code;
  private ComboBox department;
  private PopupDateField startDate;
  private PopupDateField stopDate;

  private UserService userService;
  private CourseService courseService;

  private Logger logger = Logger.getLogger(ContentEditor.class.getName());

  public ContentEditor() {
    userService = ContextAware.getBean(UserService.class);
    courseService = ContextAware.getBean(CourseService.class);

    initLayout();
    addHandlers();
    loadData();
  }

  private void initLayout() {
    VerticalLayout leftMenu = new VerticalLayout();
    leftMenu.setSizeFull();

    VerticalLayout buttonsLayout = new VerticalLayout();
//    buttonsLayout.setStyleName("v-ddwrapper-over");
    saveButton = new Button("Save");
    saveButton.setWidth("100%");
    loadButton = new Button("Load");
    loadButton.setWidth("100%");
    buttonsLayout.addComponent(saveButton);
    buttonsLayout.addComponent(loadButton);
    buttonsLayout.setComponentAlignment(saveButton, Alignment.MIDDLE_CENTER);
    buttonsLayout.setComponentAlignment(loadButton, Alignment.MIDDLE_CENTER);

    leftMenu.addComponent(buttonsLayout);
    leftMenu.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);

    HorizontalLayout topMenu = new HorizontalLayout();
    topMenu.setSizeFull();

    HorizontalLayout nameLayout = new HorizontalLayout();
    nameLayout.setSizeFull();
//    nameLayout.setStyleName("v-ddwrapper-over");
    Label nameLabel = new Label("Name: ");
    name = new TextField();
    name.setWidth("50%");
    nameLayout.addComponent(nameLabel);
    nameLayout.addComponent(name);
    nameLayout.setComponentAlignment(nameLabel, Alignment.MIDDLE_LEFT);
    nameLayout.setComponentAlignment(name, Alignment.MIDDLE_LEFT);
    nameLayout.setExpandRatio(nameLabel, 11);
    nameLayout.setExpandRatio(name, 50);

    HorizontalLayout codeLayout = new HorizontalLayout();
    codeLayout.setSizeFull();
//    codeLayout.setStyleName("v-ddwrapper-over");
    Label codeLabel = new Label("Code: ");
    code = new TextField();
    code.setWidth("50%");
    codeLayout.addComponent(codeLabel);
    codeLayout.addComponent(code);
    codeLayout.setComponentAlignment(codeLabel, Alignment.MIDDLE_LEFT);
    codeLayout.setComponentAlignment(code, Alignment.MIDDLE_LEFT);
    codeLayout.setExpandRatio(codeLabel, 11);
    codeLayout.setExpandRatio(code, 50);

    HorizontalLayout departmentLayout = new HorizontalLayout();
    departmentLayout.setSizeFull();
//    departmentLayout.setStyleName("v-ddwrapper-over");
    Label departmentLabel = new Label("Department: ");
    department = new ComboBox();
    department.setWidth("75%");
    departmentLayout.addComponent(departmentLabel);
    departmentLayout.addComponent(department);
    departmentLayout.setComponentAlignment(departmentLabel, Alignment.MIDDLE_LEFT);
    departmentLayout.setComponentAlignment(department, Alignment.MIDDLE_LEFT);
    departmentLayout.setExpandRatio(departmentLabel, 1);
    departmentLayout.setExpandRatio(department, 2);

    HorizontalLayout startDateLayout = new HorizontalLayout();
    startDateLayout.setSizeFull();
//    startDateLayout.setStyleName("v-ddwrapper-over");
    Label startDateLabel = new Label("Start date: ");
    startDate = new PopupDateField();
    startDate.setWidth("75%");
    startDateLayout.addComponent(startDateLabel);
    startDateLayout.addComponent(startDate);
    startDateLayout.setComponentAlignment(startDateLabel, Alignment.MIDDLE_LEFT);
    startDateLayout.setComponentAlignment(startDate, Alignment.MIDDLE_LEFT);
    startDateLayout.setExpandRatio(startDateLabel, 1);
    startDateLayout.setExpandRatio(startDate, 2);

    HorizontalLayout endDateLayout = new HorizontalLayout();
    endDateLayout.setSizeFull();
//    endDateLayout.setStyleName("v-ddwrapper-over");
    Label endDateLabel = new Label("End date: ");
    stopDate = new PopupDateField();
    stopDate.setWidth("75%");
    endDateLayout.addComponent(endDateLabel);
    endDateLayout.addComponent(stopDate);
    endDateLayout.setComponentAlignment(endDateLabel, Alignment.MIDDLE_LEFT);
    endDateLayout.setComponentAlignment(stopDate, Alignment.MIDDLE_LEFT);
    endDateLayout.setExpandRatio(endDateLabel, 1);
    endDateLayout.setExpandRatio(stopDate, 2);

    topMenu.addComponent(nameLayout);
    topMenu.addComponent(codeLayout);
    topMenu.addComponent(departmentLayout);
    topMenu.addComponent(startDateLayout);
    topMenu.addComponent(endDateLayout);
    topMenu.setExpandRatio(nameLayout, 1);
    topMenu.setExpandRatio(codeLayout, 1);
    topMenu.setExpandRatio(departmentLayout, 1);
    topMenu.setExpandRatio(startDateLayout, 1);
    topMenu.setExpandRatio(endDateLayout, 1);

    textArea = new RichTextArea();
    textArea.setSizeFull();

    VerticalLayout editorLayout = new VerticalLayout();
    editorLayout.setSizeFull();
    editorLayout.addComponent(topMenu);
    editorLayout.addComponent(textArea);
    editorLayout.setExpandRatio(topMenu, 0.5f);
    editorLayout.setExpandRatio(textArea, 9.5f);

    addComponent(leftMenu);
    addComponent(editorLayout);
    setExpandRatio(leftMenu, 1);
    setExpandRatio(editorLayout, 9);
  }

  private void addHandlers() {
    saveButton.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        saveCourse();
      }
    });
    loadButton.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        loadCourse();
      }
    });
  }

  private void loadData() {
    BeanItemContainer<Department> beanItemContainer = new BeanItemContainer<>(Department.class);
    List<Department> departments = userService.getUserDepartments(SecurityHelper.getUserName());
    beanItemContainer.addAll(departments);
    department.setContainerDataSource(beanItemContainer);
    department.setItemCaptionPropertyId("name");
    department.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
    department.setNullSelectionAllowed(false);
  }

  private void saveCourse() {
    String courseName = name.getValue();
    String courseCode = code.getValue();
    int departmentId=0;
    if(!department.isEmpty()){
      departmentId = ((Department)department.getValue()).getId();
    }
    Date courseStartDate = startDate.getValue();
    Date courseStopDate = stopDate.getValue();
    String courseContent = textArea.getValue();
    if(name.isEmpty() || code.isEmpty() || startDate.isEmpty() || stopDate.isEmpty()){
      new Notification("One or more of the fields are empty!",
              "<br/>Be more <i>carefull</i>",
              Notification.TYPE_HUMANIZED_MESSAGE, true)
              .show(Page.getCurrent());
    }else{
      Course course = new Course(0, courseName, courseCode, departmentId, courseStartDate, courseStopDate, courseContent);
      int value=courseService.saveCourse(course, SecurityHelper.getUserName());
      if (value==1)
      {
        new Notification("Course <font color=red>exists</font> already!",
                "<br/>",
                Notification.TYPE_HUMANIZED_MESSAGE, true)
                .show(Page.getCurrent());
      }else{
        new Notification("Course has been <font color=green>successfully</font> added!",
                "<br/>",
                Notification.TYPE_HUMANIZED_MESSAGE, true)
                .show(Page.getCurrent());
      }

    }

  }

  private void loadCourse() {
    Window window = new Window("Load course");
    window.setPosition(0, 0);
    window.setModal(true);
    window.setWidth("250px");
    window.setHeight("150px");

    Label courseLabel = new Label("Select course to load");
    courseLabel.setWidth("50%");

    ComboBox courseCombo = new ComboBox();
    List<CourseDTO> courseDTOs = courseService.loadCoursesForUser(SecurityHelper.getUserName());
    BeanItemContainer<CourseDTO> beanItemContainer = new BeanItemContainer<>(CourseDTO.class);
    beanItemContainer.addAll(courseDTOs);
    courseCombo.setContainerDataSource(beanItemContainer);
    courseCombo.setItemCaptionPropertyId("name");
    courseCombo.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
    courseCombo.setNullSelectionAllowed(false);

    HorizontalLayout buttonsLayout = new HorizontalLayout();
//    buttonsLayout.setStyleName("v-ddwrapper-over");
    buttonsLayout.setWidth("60%");
    Button okButton = new Button("OK");
    okButton.setWidth("100%");
    okButton.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        Course course = courseService.loadCourse(((CourseDTO)courseCombo.getValue()).getName());

        name.setValue(course.getName());
        code.setValue(course.getCode());
        startDate.setValue(course.getStartDate());
        stopDate.setValue(course.getStopDate());
        textArea.setValue(course.getContent());
        window.close();
      }
    });
    Button cancelButton = new Button("Cancel");
    cancelButton.setWidth("100%");
    buttonsLayout.addComponent(okButton);
    buttonsLayout.addComponent(cancelButton);
    buttonsLayout.setExpandRatio(okButton, 1);
    buttonsLayout.setExpandRatio(cancelButton, 1);

    VerticalLayout windowLayout = new VerticalLayout();
    windowLayout.setSizeFull();
    window.setContent(windowLayout);
    windowLayout.addComponent(courseLabel);
    windowLayout.addComponent(courseCombo);
    windowLayout.addComponent(buttonsLayout);
    windowLayout.setComponentAlignment(courseLabel, Alignment.MIDDLE_CENTER);
    windowLayout.setComponentAlignment(courseCombo, Alignment.MIDDLE_CENTER);
    windowLayout.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);

    UI.getCurrent().addWindow(window);
  }
}
