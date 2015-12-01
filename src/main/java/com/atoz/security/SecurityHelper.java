package com.atoz.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Sergiu on 01.12.2015.
 */
public class SecurityHelper {

  public static String getUserName() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }

  public static Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

}
