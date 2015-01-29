/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.utils;

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
}
