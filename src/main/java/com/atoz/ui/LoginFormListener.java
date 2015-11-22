package com.atoz.ui;

import com.atoz.auth.AuthManager;
import com.atoz.auth.SecurityRole;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoginFormListener implements Button.ClickListener {

  @Autowired
  private AuthManager authManager;

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

      Navigator navigator = UI.getCurrent().getNavigator();

      for (GrantedAuthority grantedAuthority : result.getAuthorities()) {
        if (grantedAuthority.getAuthority().equalsIgnoreCase(SecurityRole.ADMIN.toString())) {
          navigator.navigateTo("user");
        } else if (grantedAuthority.getAuthority().equalsIgnoreCase(SecurityRole.USER.toString())){
          navigator.navigateTo("user");
        }
      }

    } catch (AuthenticationException e) {
      Notification.show("Authentication failed: " + e.getMessage());
    }

  }
}
