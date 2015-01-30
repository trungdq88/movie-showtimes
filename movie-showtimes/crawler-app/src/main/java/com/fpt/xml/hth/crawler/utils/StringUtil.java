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

    public static String makeCGVMovieUrl(String url) {
        String target = "https://www.cgv.vn/vn/";
        String replacement = "https://www.cgv.vn/vn/movies/now-showing-1/";
        return url.replace(target, replacement);
    }
    public static String getFirstMatch(String regex, String subject) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(subject);
        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }
}
