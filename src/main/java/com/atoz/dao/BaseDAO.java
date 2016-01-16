package com.atoz.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Sergiu on 15.01.2016.
 */
public class BaseDAO {

  NamedParameterJdbcTemplate template;

  public void setDataSource(DataSource dataSource) {
    template = new NamedParameterJdbcTemplate(dataSource);
  }

}
