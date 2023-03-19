package edu.ou.coreservice.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * Parse string to Date object
     *
     * @param date string represents for date
     * @return date object
     * @author Nguyen Trung Kien - OU
     */
    public static Date parse(String date) {
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date dateResult;
        try {
            dateResult = df.parse(date + "T00:00:00Z");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return dateResult;
    }

}
