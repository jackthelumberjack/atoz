package com.atoz.ui;

import com.atoz.model.Forum;
import com.atoz.model.ForumPost;
import com.atoz.model.ForumSubject;
import com.atoz.security.SecurityHelper;
import com.atoz.security.SecurityRole;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;

public class UserView extends VerticalLayout implements View {

  private HorizontalLayout upperSection;
  private HorizontalLayout lowerSection;
  private VerticalLayout sideMenu;
  private VerticalLayout content;
  private Calendar userCalendar;
  private ForumView forumView;

  /**
   * Constructs an empty VerticalLayout.
   */
  public UserView() {
  }

  private void initLayouts(Authentication authentication) {
    String name = authentication.getName();

    Collection<SecurityRole> authorities = (Collection<SecurityRole>)authentication.getAuthorities();

    upperSection = new HorizontalLayout();
//    upperSection.setStyleName("v-ddwrapper-over");
    upperSection.setSizeFull();

    Label labelLogin = new Label("Welcome, " + name);
    upperSection.addComponent(labelLogin);
    upperSection.setComponentAlignment(labelLogin, Alignment.MIDDLE_CENTER);
    upperSection.setMargin(true);

    MenuBar menuBar = new MenuBar();

    if (authorities.contains(SecurityRole.INSTRUCTOR)) {
      MenuBar.MenuItem instructorMenu = menuBar.addItem("Instructor", null, null);
      instructorMenu.addItem("Create course", new MenuBar.Command() {
        @Override
        public void menuSelected(MenuBar.MenuItem menuItem) {
          ContentEditor contentEditor = new ContentEditor();
          contentEditor.setSizeFull();
          content.removeAllComponents();
          content.addComponent(contentEditor);
        }
      });

      instructorMenu.addItem("Manage files", new MenuBar.Command() {
        @Override
        public void menuSelected(MenuBar.MenuItem menuItem) {
          FileManager fileManager = new FileManager();
          content.removeAllComponents();
          content.addComponent(fileManager);
        }
      });
    }

    if (authorities.contains(SecurityRole.ADMIN)) {
      MenuBar.MenuItem adminMenu = menuBar.addItem("Admin", null, null);
    }

    MenuBar.MenuItem userMenu = menuBar.addItem("User", null, null);
    userMenu.addItem("View details", null, new MenuBar.Command() {
      @Override
      public void menuSelected(MenuBar.MenuItem menuItem) {
        UserDetailsLayout userDetailsLayout = new UserDetailsLayout();
        userDetailsLayout.setHeight("30%");
        userDetailsLayout.setWidth("20%");

        content.removeAllComponents();
        content.addComponent(userDetailsLayout);
        content.setComponentAlignment(userDetailsLayout, Alignment.MIDDLE_CENTER);
      }
    });
    userMenu.addItem("Show calendar", null, new MenuBar.Command() {
      @Override
      public void menuSelected(MenuBar.MenuItem menuItem) {
        userCalendar = new Calendar();
        userCalendar.setSizeFull();
        content.removeAllComponents();
        content.addComponent(userCalendar);
      }
    });

    userMenu.addItem("Forum", null, new MenuBar.Command() {
      @Override
      public void menuSelected(MenuBar.MenuItem menuItem) {
        forumView = new ForumView(name);

        forumView.constructForum();
        content.removeAllComponents();
        content.addComponent(forumView);
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
    content.setMargin(new MarginInfo(true, false, false, false));

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
