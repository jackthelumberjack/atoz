package com.atoz.ui;

import com.atoz.model.Department;
import com.atoz.service.UserService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Sergiu on 30.11.2015.
 */
public class ContentEditor extends HorizontalLayout {

  private RichTextArea textArea;
  private Button saveButton;
  private TextField name;
  private TextField code;
  private ComboBox department;
  private PopupDateField startDate;
  private PopupDateField endDate;

  private UserService userService;

  public ContentEditor(String userName) {
    initLayout();
    addHandlers();

    userService = ContextAware.getBean(UserService.class);

    loadData(userName);
  }

  private void initLayout() {
    VerticalLayout leftMenu = new VerticalLayout();
    leftMenu.setSizeFull();
    saveButton = new Button("Save");
    saveButton.setWidth("100%");
    leftMenu.addComponent(saveButton);
    leftMenu.setComponentAlignment(saveButton, Alignment.MIDDLE_CENTER);

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
    endDate = new PopupDateField();
    endDate.setWidth("75%");
    endDateLayout.addComponent(endDateLabel);
    endDateLayout.addComponent(endDate);
    endDateLayout.setComponentAlignment(endDateLabel, Alignment.MIDDLE_LEFT);
    endDateLayout.setComponentAlignment(endDate, Alignment.MIDDLE_LEFT);
    endDateLayout.setExpandRatio(endDateLabel, 1);
    endDateLayout.setExpandRatio(endDate, 2);

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
      }
    });
  }

  private void loadData(String userName) {
    BeanItemContainer<Department> beanItemContainer = new BeanItemContainer<Department>(Department.class);
    List<Department> departments = userService.getUserDepartments(userName);
    beanItemContainer.addAll(departments);
    department.setContainerDataSource(beanItemContainer);
    department.setItemCaptionPropertyId("name");
    department.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
    department.setNullSelectionAllowed(false);
  }
}
