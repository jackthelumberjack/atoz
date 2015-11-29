package com.atoz.service;

import com.atoz.model.UserInformation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Sergiu on 22.11.2015.
 */
public interface UserService extends UserDetailsService {
  @Override
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
  UserInformation loadUserInformation(String userName);
}
