/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.utils;

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
        System.out.println(convertUTF8ToASCII("nguyễn ngọc thanh hải"));
        System.out.println(convertUTF8ToASCII("TP HCM"));
        System.out.println(convertUTF8ToASCII("nguyen ngoc thanh hai"));
        System.out.println(convertUTF8ToASCII("a á â ư w i ụ ạ ý ù ì ơ ợ ứ ữ"));
        System.out.println(convertUTF8ToASCII("     "));
        System.out.println(convertUTF8ToASCII("  ;'';';';;/.,.,[[]]{{}}{()(_=-=_+_+```~~~\\\\////||||   "));
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
    
    
    public static String convertUTF8ToASCII(String s) {
        return java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

}
