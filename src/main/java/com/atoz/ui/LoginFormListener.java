package com.atoz.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by Sergiu on 07.11.2015.
 */
@Component
public class LoginFormListener implements Button.ClickListener {
  @Autowired
  @Qualifier("authenticationManager")
  private AuthenticationManager authManager;

  @Override
  public void buttonClick(Button.ClickEvent event) {
    try {
      Button source = event.getButton();
      LoginForm parent = (LoginForm) source.getParent();
      String username = parent.getTxtLogin().getValue();
      String password = parent.getTxtPassword().getValue();
      UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken(username, password);
      Authentication result = authManager.authenticate(request);
      SecurityContextHolder.getContext().setAuthentication(result);
      AppUI current = (AppUI)UI.getCurrent();
      Navigator navigator = current.getNavigator();
      navigator.navigateTo("user");
    } catch (AuthenticationException e) {
      Notification.show("Authentication failed: " + e.getMessage());
    }
  }
}
