package com.agungsantoso.popularmovies.utilities;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Agung Santoso
 * <p/>
 * NetworkUtils conversion on Android.
 */
public class DateUtils {

    /**
     * Date formatting based on user locale on android.
     * Based on code from https://stackoverflow.com/a/11093572/448050
     *
     * @param context Application context
     * @param date    Date to be formatted
     * @param format  Format of the date
     * @return String representation of date
     * @throws ParseException related to parsing error
     */
    public static String localizedBy(Context context, String date, String format)
            throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
            return dateFormat.format(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
