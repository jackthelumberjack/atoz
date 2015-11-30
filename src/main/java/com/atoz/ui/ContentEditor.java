package com.atoz.ui;

import com.vaadin.ui.*;

/**
 * Created by Sergiu on 30.11.2015.
 */
public class ContentEditor extends HorizontalLayout {

  private RichTextArea textArea;
  private Button saveButton;

  public ContentEditor() {
    initLayout();
    addHandlers();
  }

  private void initLayout() {
    VerticalLayout leftMenu = new VerticalLayout();
    leftMenu.setSizeFull();
    saveButton = new Button("Save");
    saveButton.setWidth("100%");
    leftMenu.addComponent(saveButton);
    leftMenu.setComponentAlignment(saveButton, Alignment.MIDDLE_CENTER);

    textArea = new RichTextArea();
    textArea.setSizeFull();

    addComponent(leftMenu);
    addComponent(textArea);
    setExpandRatio(leftMenu, 1);
    setExpandRatio(textArea, 9);
  }

  private void addHandlers() {
    saveButton.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
      }
    });
  }
}
