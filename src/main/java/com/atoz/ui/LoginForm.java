package com.atoz.ui;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import org.springframework.context.ApplicationContext;

public class LoginForm extends VerticalLayout {

  private TextField txtLogin;
  private PasswordField txtPassword;
  private Button btnLogin;

  public LoginForm() {
    txtLogin = new TextField("Username");
    txtPassword = new PasswordField("Password");
    btnLogin = new Button("Login");

    FormLayout loginForm = new FormLayout(txtLogin, txtPassword);
    txtLogin.setSizeFull();
    txtPassword.setSizeFull();
    btnLogin.setSizeFull();

    ThemeResource logoThemeResource = new ThemeResource("img/atoz_logo.png");
    Image logo = new Image("", logoThemeResource);
    logo.setHeight("100%");
    logo.setWidth("100%");

    VerticalLayout loginLayout = new VerticalLayout();
//    loginLayout.setStyleName("v-ddwrapper-over");
    loginLayout.addComponent(logo);
    loginLayout.addComponent(loginForm);
    loginLayout.addComponent(btnLogin);
    loginLayout.setHeight("30%");
    loginLayout.setWidth("18%");

    addComponent(loginLayout);

    setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);

    LoginFormListener loginFormListener = getLoginFormListener();

    btnLogin.addClickListener(loginFormListener);
  }

  public LoginFormListener getLoginFormListener() {
    AppUI ui = (AppUI) UI.getCurrent();
    ApplicationContext context = ui.getApplicationContext();
    return context.getBean(LoginFormListener.class);
  }

  public TextField getTxtLogin() {
    return txtLogin;
  }

  public PasswordField getTxtPassword() {
    return txtPassword;
  }
}
