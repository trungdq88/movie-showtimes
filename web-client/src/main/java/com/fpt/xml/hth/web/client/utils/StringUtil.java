/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.web.client.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class StringUtil {

    /**
     * Regex to check valid movie length
     */
    public static final String REGEX_LENGTH = "^\\d+.*?$|^$";
    /**
     * Regex to check valid youtube url
     */
    public static final String REGEX_YOUTUBE_URL = "(https?://)?(www\\.)?youtube\\.com.*";
    /**
     * Regex to check valid date
     */
    public static final String REGEX_DATE
            = "((\\d{2}|\\d)(-|/|\\\\)(\\d{2}|\\d)(-|/|\\\\)(\\d{2}|\\d{4}))|"
            + "((\\d{2}|\\d{4})(-|/|\\\\)(\\d{2}|\\d)(-|/|\\\\)(\\d{2}|\\d))";
    /**
     * Regex to check valid time
     */
    public static final String REGEX_TIME = "(\\d|\\d{2}):(\\d|\\d{2})";

    public static void main(String[] args) {
//        String date = formatDate("2015-02-13", "yyyy-mm-dd", "mm/dd/yyyy");        
//        System.out.println(convertUTF8ToASCII("nguyễn ngọc thanh hải"));
//        System.out.println(convertUTF8ToASCII("TP HCM"));
//        System.out.println(convertUTF8ToASCII("nguyen ngoc thanh hai"));
//        System.out.println(convertUTF8ToASCII("a á â ư w i ụ ạ ý ù ì ơ ợ ứ ữ"));
//        System.out.println(convertUTF8ToASCII("     "));
//        System.out.println(convertUTF8ToASCII("  ;'';';';;/.,.,[[]]{{}}{()(_=-=_+_+```~~~\\\\////||||   "));
//        System.out.println(Integer.parseInt(subDate("2015-02-7", "yyyy-mm-dd", 'd')));
        Calendar _date = new GregorianCalendar(2015, 1, 7, 9, 0);
        System.out.println(_date.getTimeInMillis());
//        Date date = _date.getTime();
//        TimeZone zone = new SimpleTimeZone(7 * 60 * 60 * 1000, "GMT+7");
//        _date.setTimeZone(zone);
        System.out.println(_date.getTime());
    }

    /**
     * Change CGV movie url
     *
     * @param url
     * @return
     */
    public static String makeCGVMovieUrl(String url) {
        String target = "https://www.cgv.vn/vn/";
        String replacement = "https://www.cgv.vn/vn/movies/now-showing-1/";
        return url.replace(target, replacement);
    }

    /**
     *
     * @param regex
     * @param subject
     * @return
     */
    public static String getFirstMatch(String regex, String subject) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(subject);
        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }

    /**
     * Change date format from a format to another format Ex1: yyyy/mm/dd to
     * dd/mm/yyyy Ex2: yyyy-mm-dd to dd/mm/yyyy
     *
     * @param date
     * @param from
     * @param to
     * @return
     */
    public static String formatDate(String date, String from, String to) {
        char[] charFrom = from.toLowerCase().toCharArray();
        char[] charTo = to.toLowerCase().toCharArray();
        char[] charDate = date.toCharArray();

        for (int i = 0; i < charFrom.length; i++) {
            for (int j = 0; j < charTo.length; j++) {
                if (charTo[j] == charFrom[i]) {
                    charTo[j] = charDate[i];
                    break;
                }
            }
        }
        return String.valueOf(charTo);
    }

    /**
     * Check if a string is not empty (null or empty string)
     *
     * @param str
     * @return
     */
    public static boolean notEmpty(String str) {
        if (str != null) {
            return !str.equals("");
        }
        return false;
    }

    /**
     * Check valid string with a regex
     *
     * @param str
     * @param regex
     * @return
     */
    public static boolean validStringFormat(String str, String regex) {
        return str.matches(regex);
    }

    /**
     * Convert utf8 string to ascii string Ex: Nguyễn Ngọc Thanh Hải to Nguyen
     * Ngoc Thanh Hai
     *
     * @param s
     * @return
     */
    public static String convertUTF8ToASCII(String s) {
        return java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    /**
     * Get day, month, year, hour, minute or second from a date or time string
     * with a particular format.
     *
     * @param date
     * @param dateFormat
     * @param target d | m | y | h | m
     * @return
     */
    public static String subDate(String date, String dateFormat, char target) {
        try {

            String result = "";
            for (int i = 0; i < dateFormat.length(); i++) {
                if (dateFormat.toCharArray()[i] == target) {
                    result += date.toCharArray()[i];
                }
            }
            return result;
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * Compare 2 string ignore case and utf8
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compareString(String str1, String str2) {
        str1 = convertUTF8ToASCII(str1).toLowerCase().replace(" ", "");
        str2 = convertUTF8ToASCII(str2).toLowerCase().replace(" ", "");
        return str1.equals(str2);
    }

    /**
     * Create map string key
     *
     * @param str
     * @return
     */
    public static String createKey(String str) {
        return convertUTF8ToASCII(str).toLowerCase().replace(" ", "");
    }

}
