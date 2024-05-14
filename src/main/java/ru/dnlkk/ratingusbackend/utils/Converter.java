package ru.dnlkk.ratingusbackend.utils;

import java.sql.Timestamp;
import java.util.Calendar;

public class Converter {

    public Timestamp getAcademicDateByWeek(int weekOfYear) {
        Timestamp date = getAcademicDate(new Timestamp(System.currentTimeMillis()));
        int difference = (weekOfYear - 1) * 7;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, difference);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public int getAcademicWeekOfYear(Timestamp date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        int startOfYearWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTimeInMillis(getAcademicDate(date).getTime());
        int academicStartWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        return startOfYearWeek - academicStartWeek + 1;
    }

    public Timestamp getAcademicDate(Timestamp date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(calendar.get(Calendar.YEAR), Calendar.SEPTEMBER, 1);

        if (calendar.getTimeInMillis() > date.getTime()) {
            calendar.add(Calendar.YEAR, -1);
        }

        return new Timestamp(calendar.getTimeInMillis());
    }
}
