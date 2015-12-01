package com.atoz.ui;

import com.atoz.model.UserInformation;
import com.atoz.security.SecurityHelper;
import com.atoz.service.UserService;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Sergiu on 29.11.2015.
 */
public class UserDetailsLayout extends VerticalLayout {

  private UserService userService;

  TextField userId;
  TextField firstName;
  TextField lastName;
  TextField serialNumber;
  TextField email;

  public UserDetailsLayout() {
    initLayout();

    userService = ContextAware.getBean(UserService.class);

    loadDetails();
  }

  private void initLayout() {
    userId = new TextField("User ID:");
    firstName = new TextField("First name:");
    lastName = new TextField("Last name:");
    serialNumber = new TextField("Serial number:");
    email = new TextField("E-mail:");
    FormLayout formLayout = new FormLayout(userId, firstName, lastName, serialNumber, email);
    formLayout.setSizeFull();
    addComponent(formLayout);
  }

  private void loadDetails() {
    UserInformation userInformation = userService.loadUserInformation(SecurityHelper.getUserName());
    userId.setValue(userInformation.getUserID()+"");
    firstName.setValue(userInformation.getFirstName());
    lastName.setValue(userInformation.getLastName());
    serialNumber.setValue(userInformation.getSerialNumber());
    email.setValue(userInformation.getEmail());
  }
}
