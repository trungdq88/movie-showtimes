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

    public static void main(String[] args) {
        String date = formatDate("2015-02-13", "yyyy-mm-dd", "mm/yyyy");
        System.out.println(date);
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
        //Pattern p = Pattern.compile("[0-9]{4}");
        char[] charFrom = from.toLowerCase().toCharArray();
        char[] charTo = to.toLowerCase().toCharArray();
        char[] charDate = date.toCharArray();
        String result = to.toLowerCase();

        for (int i = 0; i < charFrom.length; i++) {
            String year = "";
            String month = "";
            String day = "";
            String token = "";
            char f = charFrom[i];
            char d = charDate[i];

            for (int j = 0; j < charTo.length; j++) {
                if (charTo[j] == charFrom[i]) {
                    charTo[j] = charDate[i];
                    break;
                }
            }
        }
        return String.valueOf(charTo);
    }
}
