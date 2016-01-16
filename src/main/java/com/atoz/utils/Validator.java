package com.atoz.utils;

import com.atoz.exceptions.DeadlineExceededException;
import com.atoz.exceptions.WrongGradeException;
import com.google.gwt.user.server.Util;

import java.util.Date;

/**
 * Created by Raul Breje on 01/16/2016.
 */
public class Validator {

    public static void validateGrade(String grade) throws WrongGradeException {
        int gradeInt;
        try {
            gradeInt = Integer.parseInt(grade);
        } catch (Exception e) {
            throw new WrongGradeException("Wrong grade.");
        }
        if (gradeInt < 0 || gradeInt > 10) {
            throw new WrongGradeException("Wrong grade.");
        }
    }

    public static void validateDate(Date deadline) throws DeadlineExceededException {
        Date now = new Date();
        if (deadline.after(now)) {
            throw new DeadlineExceededException("Deadline exceeded.");
        }
    }

}
