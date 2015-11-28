package com.atoz.ui;

import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Date;

public class UserView extends VerticalLayout implements View {

  private HorizontalLayout upperSection;
  private HorizontalLayout lowerSection;
  private VerticalLayout sideMenu;
  private VerticalLayout content;
  private Calendar userCalendar;

  /**
   * Constructs an empty VerticalLayout.
   */
  public UserView() {
  }

  private void initLayouts(Authentication authentication) {
    String name = authentication.getName();

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

    upperSection = new HorizontalLayout();
//    upperSection.setStyleName("v-ddwrapper-over");
    upperSection.setSizeFull();

    Label labelLogin = new Label("Welcome, " + name);
    upperSection.addComponent(labelLogin);
    upperSection.setComponentAlignment(labelLogin, Alignment.MIDDLE_CENTER);
    upperSection.setMargin(true);

    MenuBar menuBar = new MenuBar();
    MenuBar.MenuItem userMenu = menuBar.addItem("User", null, null);
    userMenu.addItem("Show calendar", null, new MenuBar.Command() {
      @Override
      public void menuSelected(MenuBar.MenuItem menuItem) {
        userCalendar = new Calendar();
        userCalendar.setSizeFull();
        content.removeAllComponents();
        content.addComponent(userCalendar);
      }
    });
    userMenu.addItem("Logout", null, new MenuBar.Command() {
      @Override
      public void menuSelected(MenuBar.MenuItem menuItem) {
        SecurityContextHolder.clearContext();
        Navigator navigator = UI.getCurrent().getNavigator();
        navigator.navigateTo("login");
      }
    });
    upperSection.addComponent(menuBar);
    upperSection.setComponentAlignment(menuBar, Alignment.MIDDLE_RIGHT);

    sideMenu = new VerticalLayout();
//    sideMenu.setStyleName("v-ddwrapper-over");
    sideMenu.setSizeFull();

    content = new VerticalLayout();
//    content.setStyleName("v-ddwrapper-over");
    content.setSizeFull();

    lowerSection = new HorizontalLayout();
//    lowerSection.setStyleName("v-ddwrapper-over");
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
