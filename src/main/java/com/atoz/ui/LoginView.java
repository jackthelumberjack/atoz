package com.atoz.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {

  public LoginView() {
    setSizeFull();
    LoginForm loginForm = new LoginForm();
    addComponent(loginForm);
    setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {
  }
}
