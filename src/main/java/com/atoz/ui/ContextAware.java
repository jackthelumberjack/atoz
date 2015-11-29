package com.atoz.ui;

import com.vaadin.ui.UI;
import org.springframework.context.ApplicationContext;

/**
 * Created by Sergiu on 29.11.2015.
 */
public class ContextAware {
  public static <T> T getBean(Class<T> expectedType) {
    AppUI ui = (AppUI) UI.getCurrent();
    ApplicationContext context = ui.getApplicationContext();
    return context.getBean(expectedType);
  }
}
