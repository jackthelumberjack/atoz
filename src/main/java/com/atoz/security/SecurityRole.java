package com.atoz.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Sergiu on 22.11.2015.
 */
public enum SecurityRole implements GrantedAuthority {
  ADMIN("admin"), USER("user"), INSTRUCTOR("instructor");

  String authority;

  SecurityRole(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return authority;
  }
}
