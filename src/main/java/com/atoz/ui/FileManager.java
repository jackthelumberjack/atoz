package com.atoz.ui;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Sergiu on 30.11.2015.
 */
public class FileManager extends VerticalLayout {

  final Image image = new Image("Uploaded Image");

  public FileManager() {
    initLayout();
  }

  private void initLayout() {
    image.setVisible(false);

    ImageReceiver receiver = new ImageReceiver();

    final Upload upload = new Upload("Upload it here", receiver);
    upload.setButtonCaption("Start Upload");
    upload.addSucceededListener(receiver);

    final long UPLOAD_LIMIT = 1000000l;
    upload.addStartedListener(new Upload.StartedListener() {
      @Override
      public void uploadStarted(Upload.StartedEvent event) {
        if (event.getContentLength() > UPLOAD_LIMIT) {
          Notification.show("Too big file", Notification.Type.ERROR_MESSAGE);
          upload.interruptUpload();
        }
      }
    });

    upload.addProgressListener(new Upload.ProgressListener() {
      @Override
      public void updateProgress(long readBytes, long contentLength) {
        if (readBytes > UPLOAD_LIMIT) {
          Notification.show("Too big file", Notification.Type.ERROR_MESSAGE);
          upload.interruptUpload();
        }
      }
    });

    addComponents(upload, image);
  }

  class ImageReceiver implements Upload.Receiver, Upload.SucceededListener {
    public File file;

    public OutputStream receiveUpload(String filename,
                                      String mimeType) {
      FileOutputStream fos;
      try {
        file = new File(filename);
        fos = new FileOutputStream(file);
      } catch (final java.io.FileNotFoundException e) {
        new Notification("Could not open file<br/>", e.getMessage(),
            Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
        return null;
      }
      return fos;
    }

    public void uploadSucceeded(Upload.SucceededEvent event) {
      image.setVisible(true);
      image.setSource(new FileResource(file));
    }
  };
}
