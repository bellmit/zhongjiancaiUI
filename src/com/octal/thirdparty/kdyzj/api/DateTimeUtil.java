package com.octal.thirdparty.kdyzj.api;

import java.util.regex.*;
import java.text.*;
import java.util.*;

public class DateTimeUtil
{
    public static final long SECOND = 1000L;
    public static final long MINUTE = 60000L;
    public static final long HOUR = 3600000L;
    public static final long DAY = 86400000L;
    public static final long WEEK = 604800000L;
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT2 = "yyyyMMdd";
    public static final String DATETIME_FORMAT3 = "yyyyMMddHHmmss";
    public static final String DATETIME_LONG_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_CHINESE = "yyyy\ufffd\ufffdMM\ufffd\ufffddd\ufffd\ufffd";
    public static final String YEAR_MONTH_DAY_FORMAT = "yyyyMMdd";
    public static final String YEAR_MONTH_FORMAT = "yyyyMM";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String MONTH_DAY_FORMAT = "MMdd";
    public static final String MONTH_FORMAT = "MM";
    public static final String DAY_FORMAT = "dd";
    public static final int[] dayArray;
    
    static {
        dayArray = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    }
    
    public static Date date(final int y, final int m, final int d) {
        return date(y, m - 1, d, 0, 0, 0);
    }
    
    public static Date date(final int y, final int m, final int d, final int h, final int ms, final int s) {
        final Calendar cal = Calendar.getInstance();
        cal.set(y, m, d, h, ms, s);
        return cal.getTime();
    }
    
    public static Date removeTime(final Date d) {
        return setTime(d, 0, 0, 0);
    }
    
    public static Date setTime(final Date d, final int h, final int m, final int s) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(11, h);
        cal.set(12, m);
        cal.set(13, s);
        cal.set(14, 0);
        return cal.getTime();
    }
    
    public static String formatDate(final String format, final Date d) {
        if (d == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(d);
    }
    
    public static String formatDate(final Date d) {
        return formatDate("yyyy-MM-dd", d);
    }
    
    public static String formatDateTime(final Date d) {
        return formatDate("yyyy-MM-dd HH:mm:ss", d);
    }
    
    public static String formatPeriod(final long period) {
        final StringBuffer buf = new StringBuffer(20);
        long d = period / 86400000L;
        long r = period % 86400000L;
        if (d != 0L) {
            buf.append(d).append("d");
        }
        d = r / 3600000L;
        r %= 3600000L;
        if (d != 0L) {
            buf.append(d).append("h");
        }
        d = r / 60000L;
        r %= 60000L;
        if (d != 0L) {
            buf.append(d).append("m");
        }
        d = r / 1000L;
        r %= 1000L;
        if (d != 0L || r != 0L) {
            buf.append(d);
            if (r != 0L) {
                buf.append(".").append(r);
            }
            buf.append("s");
        }
        return buf.toString();
    }
    
    public static Date parse(final String v, final String fm, final Date def) {
        if (v == null || v.trim().length() == 0) {
            return def;
        }
        try {
            return new SimpleDateFormat(fm).parse(v);
        }
        catch (Exception e) {
            return def;
        }
    }
    
    public static Date parseDate(final String v, final Date def) {
        return parse(v, "yyyy-MM-dd", def);
    }
    
    public static Date parseLongDateTime(final String v, final Date def) {
        return parse(v, "yyyy-MM-dd HH:mm:ss.SSS", def);
    }
    
    public static Date parseLongDateTime(final String v, final long def) {
        return parse(v, "yyyy-MM-dd HH:mm:ss.SSS", new Date(def));
    }
    
    public static long parseDate(final String v, final long def) {
        final Date r = parse(v, "yyyy-MM-dd", new Date(def));
        return r.getTime();
    }
    
    public static Date parseDateTime(final String v, final Date def) {
        return parse(v, "yyyy-MM-dd HH:mm:ss", def);
    }
    
    public static long parseDateTime(final String v, final long def) {
        final Date r = parse(v, "yyyy-MM-dd HH:mm:ss", new Date(def));
        return r.getTime();
    }
    
    public static Date getFirstDayOfWeek(final Date date) {
        return getFirstDayOfWeek(date, 1);
    }
    
    public static Date getFirstDayOfWeek(final Date date, final int firstDayOfWeek) {
        final Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.setFirstDayOfWeek(firstDayOfWeek);
        cal.set(7, firstDayOfWeek);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }
    
    public static Date getLastDayOfWeek(final Date date) {
        return getFirstDayOfWeek(date, 1);
    }
    
    public static Date getLastDayOfWeek(final Date date, final int firstDayOfWeek) {
        final Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.setFirstDayOfWeek(firstDayOfWeek);
        cal.set(7, firstDayOfWeek);
        cal.add(7, 6);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }
    
    public static Date getFirstDayOfMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }
    
    public static Date getLastDayOfMonthThisYear(final Date date) {
        final Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.set(5, cal.getActualMaximum(5));
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }
    
    public static boolean isDateTimeFormat(final String date) {
        return date != null && date.trim().replaceAll("\\s+", " ").length() <= "yyyy-MM-dd HH:mm:ss".length() && Pattern.matches("[12]{1}[09]{1}\\d{2}-[01]?[0-9]?-[0-3]?[0-9]?\\s+[0-5]?[0-9]?:[0-5]?[0-9]?:[0-5]?[0-9]?", date);
    }
    
    public static boolean isRightDate(final String date, final String format) {
        if (date == null || format == null) {
            return false;
        }
        try {
            new SimpleDateFormat(format).parse(date);
        }
        catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    public static String formatDate(final Date date, final String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }
    
    public static String formatLongDateTime(final Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }
    
    public static String formatLongDateTime(final long time) {
        return formatDate(new Date(time), "yyyy-MM-dd HH:mm:ss.SSS");
    }
    
    public static boolean isLeapYear(final int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }
    
    public static Date getLastDayOfMonth(final Date date) {
        final Calendar gc = Calendar.getInstance();
        gc.setTime(date);
        gc.add(2, 1);
        gc.add(5, -1 * gc.get(5));
        gc.set(10, 23);
        gc.set(12, 59);
        gc.set(13, 59);
        return new Date(gc.getTimeInMillis());
    }
    
    public static Date getStartDateByDate(final Date date) {
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        gc.set(10, 0);
        gc.set(12, 0);
        gc.set(13, 0);
        return new Date(gc.getTimeInMillis());
    }
    
    public static Date getEndDateByDate(final Date date) {
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        gc.set(10, 23);
        gc.set(12, 59);
        gc.set(13, 59);
        return new Date(gc.getTimeInMillis());
    }
    
    public static Date getNowDateByServer() {
        return new Date(System.currentTimeMillis());
    }
    
    public static Integer getNowYearByServer() {
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(getNowDateByServer());
        return gc.get(1);
    }
    
    public static Integer getNowMonthByServer() {
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(getNowDateByServer());
        return gc.get(2);
    }
    
    public static Integer getNowDayByServer() {
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(getNowDateByServer());
        return gc.get(8);
    }
    
    public static String formatDateToString(final Date tm) {
        if (tm == null) {
            return "";
        }
        return formatDate(tm, "yyyy-MM-dd");
    }
    
    public static String formatDateToString(final String date_format) {
        return formatDate(getNowDateByServer(), date_format);
    }
    
    public static String formatDateToString(final Date tm, final String date_format) {
        if (tm == null) {
            return "";
        }
        return formatDate(tm, date_format);
    }
    
    public static String getLastMonth(final String sMonth) {
        int iYear = Integer.parseInt(sMonth.substring(0, 4));
        int iMonth = Integer.parseInt(sMonth.substring(4, 6));
        if (iMonth == 1) {
            --iYear;
            iMonth = 12;
        }
        else {
            --iMonth;
        }
        final String sTmpYear = "0000" + Integer.toString(iYear);
        final String sTmpMonth = "00" + Integer.toString(iMonth);
        return String.valueOf(sTmpYear.substring(sTmpYear.length() - 4, sTmpYear.length())) + sTmpMonth.substring(sTmpMonth.length() - 2, sTmpMonth.length());
    }
    
    public static String getNextMonth(final String sMonth) {
        int iYear = Integer.parseInt(sMonth.substring(0, 4));
        int iMonth = Integer.parseInt(sMonth.substring(4, 6));
        if (iMonth == 12) {
            ++iYear;
            iMonth = 1;
        }
        else {
            ++iMonth;
        }
        final String sTmpYear = "0000" + Integer.toString(iYear);
        final String sTmpMonth = "00" + Integer.toString(iMonth);
        return String.valueOf(sTmpYear.substring(sTmpYear.length() - 4)) + sTmpMonth.substring(sTmpMonth.length() - 2);
    }
    
    public static String getRelativeMonth(final String sMonth, final int shiftValue) {
        int tempMonth = Integer.parseInt(sMonth.substring(0, 4)) * 12 + Integer.parseInt(sMonth.substring(4, 6));
        tempMonth += shiftValue;
        if (tempMonth % 12 == 0) {
            return String.valueOf(tempMonth / 12 - 1) + "12";
        }
        final String sTmpMonth = "00" + tempMonth % 12;
        return String.valueOf(tempMonth / 12) + sTmpMonth.substring(sTmpMonth.length() - 2);
    }
    
    public static int calcuMonth(final String begMonth, final String endMonth) {
        int result = 0;
        if (begMonth == null || begMonth.length() != 6 || endMonth == null || endMonth.length() != 6) {
            return result;
        }
        result = 12 * (Integer.parseInt(endMonth.substring(0, 4)) - Integer.parseInt(begMonth.substring(0, 4))) + Integer.parseInt(endMonth.substring(4)) - Integer.parseInt(begMonth.substring(4));
        return result;
    }
    
    public static String getYearFirstAcctTermNoByDate(final Date dateTime) {
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(dateTime);
        return String.valueOf(gc.get(1)) + "00";
    }
    
    public static Date getFirstDateForYear(final Date dateTime) {
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(dateTime);
        return null;
    }
    
    public static int getYearByDate(final Date dateTime) {
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(dateTime);
        return gc.get(1);
    }
    
    public static int getMonthByDate(final Date dateTime) {
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(dateTime);
        return gc.get(2) + 1;
    }
    
    public static int getDayByDate(final Date dateTime) {
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(dateTime);
        return gc.get(5);
    }
    
    public static Date getNextDay(final Date dateTime) {
        final Calendar gc = Calendar.getInstance();
        gc.setTime(dateTime);
        gc.add(5, 1);
        return gc.getTime();
    }
    
    public static String createAcctTermNoByDate(final Date time) {
        String acctTermNo = "";
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(time);
        acctTermNo = String.valueOf(acctTermNo) + gc.get(1);
        acctTermNo = String.valueOf(acctTermNo) + ((gc.get(2) >= 9) ? (gc.get(2) + 1) : ("0" + (gc.get(2) + 1)));
        return acctTermNo;
    }
    
    public static String getYearMonth(final Date time) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        return String.valueOf(String.valueOf(cal.get(1))) + ((cal.get(2) >= 9) ? String.valueOf(cal.get(2) + 1) : ("0" + String.valueOf(cal.get(2) + 1)));
    }
    
    public static Date conversionDate(final int year, final int month) {
        final Calendar c = Calendar.getInstance();
        c.set(year, month, 0);
        return c.getTime();
    }
    
    public static Date getFirstDayOfMonthByAcctTermNo(final String AcctTermNo) {
        final int year = Integer.parseInt(AcctTermNo.substring(0, 4));
        final int month = Integer.parseInt(AcctTermNo.substring(4, 6));
        return date(year, month, 1);
    }
    
    public static boolean compareDate(final Date startDate, final Date endDate) {
        return endDate.after(startDate);
    }
    
    public static List<Date> getBetweenDates(final Date startDate, final Date endDate) {
        if (startDate == null) {
            if (endDate != null) {
                return Arrays.asList(endDate);
            }
        }
        else if (endDate == null) {
            return Arrays.asList(startDate);
        }
        final List<Date> dates = new ArrayList<Date>();
        if (startDate.getTime() > endDate.getTime()) {
            return dates;
        }
        final Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        final Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        int startYear = 0;
        int startMonth = 0;
        final int endYear = endCal.get(1);
        final int endMonth = endCal.get(2);
        while (true) {
            dates.add(new Date(startCal.getTime().getTime()));
            startYear = startCal.get(1);
            startMonth = startCal.get(2);
            if (startYear == endYear && startMonth == endMonth) {
                break;
            }
            startCal.add(2, 1);
        }
        return dates;
    }
    
    public static String getUpAcctTermNoForDate(final Date time, final int months) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(2, months);
        cal.set(10, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        return createAcctTermNoByDate(cal.getTime());
    }
    
    public static Date getFirstYearDay(final Date dateTime) {
        final GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(dateTime);
        gc.get(1);
        gc.set(gc.get(1), 0, 1);
        return gc.getTime();
    }
    
    public static Date genNewDateTime(final Date oriDateTime, final int type, final int rolls) {
        final GregorianCalendar c1 = new GregorianCalendar();
        c1.setTime(oriDateTime);
        c1.add(type, rolls);
        return c1.getTime();
    }
    
    public static Date addDays(final Date v, final int day) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(v);
        cal.add(5, day);
        return cal.getTime();
    }
    
    public static int getBetweenDays(final Date startDate, final Date endDate) {
    	Long l = (removeTime(startDate).getTime() - removeTime(endDate).getTime())/86400000L;
        return Math.abs(l.intValue());
    }
    
    public static Date getStartMonthDate(final int year, final int month) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getTime();
    }
    
    public static Date getEndMonthDate(final int year, final int month) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        final int day = calendar.getActualMaximum(5);
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }
    
    public static Date getEndMonthDate(final String yearmonth) {
        final int year = Integer.parseInt(yearmonth.substring(0, 4));
        final int month = Integer.parseInt(yearmonth.substring(4, 6));
        return getEndMonthDate(year, month);
    }
    
    public static long betweenMonths(final Date endDate, final Date startDate) {
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(endDate);
        final long endmonths = cal.get(1) * 12 + cal.get(2);
        cal.setTime(startDate);
        final long startmonths = cal.get(1) * 12 + cal.get(2);
        return endmonths - startmonths;
    }
    
    public static long betweenDate(final Date enddate, final Date startdate, final String flag) {
        if ("Y".equalsIgnoreCase(flag)) {
            return Math.abs(getYearByDate(enddate) - getYearByDate(startdate));
        }
        if ("M".equalsIgnoreCase(flag)) {
            return betweenMonths(enddate, startdate);
        }
        if ("D".equalsIgnoreCase(flag)) {
            return getBetweenDays(startdate, enddate);
        }
        return 0L;
    }
    
    public static Date addMonths(final Date source, final int months) {
        final Date newDate = (Date)source.clone();
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(newDate);
        cal.add(2, months);
        return cal.getTime();
    }
    
    public static Date addHours(final Date source, final int hours) {
        final Date newDate = (Date)source.clone();
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(newDate);
        cal.add(11, hours);
        return cal.getTime();
    }
    
    public static Date addMinutes(final Date source, final int minutes) {
        final Date newDate = (Date)source.clone();
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(newDate);
        cal.add(12, minutes);
        return cal.getTime();
    }
    
    public static Date addYears(final Date source, final int years) {
        final Date newDate = (Date)source.clone();
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(newDate);
        cal.add(1, years);
        return cal.getTime();
    }
    
    public static String getCurrentLastAcctTerm(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(String.valueOf(cal.get(1))) + "12";
    }
    
    public static String getPreviousLastAcctTerm(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(String.valueOf(cal.get(1) - 1)) + "12";
    }
    
    public static Date max(final Date... dates) {
        if (dates == null | dates.length == 0) {
            return null;
        }
        if (dates.length == 1) {
            return dates[0];
        }
        Date maxDate = dates[0];
        for (final Date d : dates) {
            if (d != null) {
                if (maxDate == null) {
                    maxDate = d;
                }
                else if (maxDate.compareTo(d) < 0) {
                    maxDate = d;
                }
            }
        }
        return maxDate;
    }
    
    public static Date getGMTDate() {
        final Date d = new Date();
        return new Date(d.getTime() - TimeZone.getDefault().getOffset(System.currentTimeMillis()));
    }
    
    public static Date getGMTDate(final long date) {
        final Date d = new Date(date);
        return new Date(d.getTime() - TimeZone.getDefault().getOffset(System.currentTimeMillis()));
    }
    
    public static Date getGMTPlus800Date(final long date) {
        final Date d = new Date(date);
        return new Date(d.getTime() - TimeZone.getDefault().getOffset(System.currentTimeMillis()) + 28800000L);
    }
    
    public static Date getGMTPlus800Date() {
        return getGMTPlus800Date(new Date().getTime());
    }
    
    public static boolean isSameDay(final Date d1, final Date d2) {
        return d1 != null && d2 != null && (d1.getYear() == d2.getYear() && d1.getMonth() == d2.getMonth() && d1.getDate() == d2.getDate());
    }
    
    public static boolean isSameMonth(final Date d1, final Date d2) {
        return d1 != null && d2 != null && (d1.getYear() == d2.getYear() && d1.getMonth() == d2.getMonth());
    }
}
