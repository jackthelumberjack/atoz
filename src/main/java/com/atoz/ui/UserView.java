package com.atoz.ui;

import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import sun.util.resources.cldr.ar.CalendarData_ar_YE;

import java.util.Collection;

public class UserView extends VerticalLayout implements View {

  private HorizontalLayout upperSection;
  private HorizontalLayout lowerSection;
  private VerticalLayout sideMenu;
  private VerticalLayout content;

  /**
   * Constructs an empty VerticalLayout.
   */
  public UserView() {
  }

  private void initLayouts(Authentication authentication) {
    String name = authentication.getName();

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

    upperSection = new HorizontalLayout();
    upperSection.setSizeFull();

    Label labelLogin = new Label("Welcome, " + name);
    upperSection.addComponent(labelLogin);
    upperSection.setComponentAlignment(labelLogin, Alignment.MIDDLE_CENTER);
    upperSection.setMargin(true);

    MenuBar menuBar = new MenuBar();
    MenuBar.MenuItem userMenu = menuBar.addItem("User", null, null);
    userMenu.addItem("Logout", null, new MenuBar.Command() {
      @Override
      public void menuSelected(MenuBar.MenuItem menuItem) {
        SecurityContextHolder.clearContext();
//        UI.getCurrent().close();
        Navigator navigator = UI.getCurrent().getNavigator();
        navigator.navigateTo("login");
      }
    });
    upperSection.addComponent(menuBar);
    upperSection.setComponentAlignment(menuBar, Alignment.MIDDLE_RIGHT);

    sideMenu = new VerticalLayout();
    sideMenu.setSizeFull();
    sideMenu.addComponent(new Label("label2"));
    sideMenu.addComponent(new Calendar());

    content = new VerticalLayout();
    content.setSizeFull();
    content.addComponent(new Label("label3"));

    lowerSection = new HorizontalLayout();
    lowerSection.setSizeFull();
    lowerSection.addComponent(content);
    lowerSection.addComponent(sideMenu);
    lowerSection.setExpandRatio(content, 90);
    lowerSection.setExpandRatio(sideMenu, 10);

    addComponent(upperSection);
    addComponent(lowerSection);
    setExpandRatio(lowerSection, 95);
    setExpandRatio(upperSection, 5);
  }

  public void enter(ViewChangeListener.ViewChangeEvent event) {
    removeAllComponents();
    setSizeFull();

    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
      initLayouts(authentication);
    } else {
      Navigator navigator = UI.getCurrent().getNavigator();
      navigator.navigateTo("login");
    }
  }
}
