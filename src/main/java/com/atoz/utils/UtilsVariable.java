package com.atoz.utils;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.text.SimpleDateFormat;

/**
 * Created by Raul Breje on 01/16/2016.
 */
public class UtilsVariable {

    public static SimpleDateFormat SIMPLE_DATA_FORMAT_HOURS = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public static SimpleDateFormat SIMPLE_DATA_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
}
