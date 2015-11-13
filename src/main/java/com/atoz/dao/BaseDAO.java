package com.atoz.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Sergiu on 12.11.2015.
 */
public class BaseDAO {

  protected NamedParameterJdbcTemplate template;

  public void setDataSource(DataSource dataSource) {
    template = new NamedParameterJdbcTemplate(dataSource);
  }
}
