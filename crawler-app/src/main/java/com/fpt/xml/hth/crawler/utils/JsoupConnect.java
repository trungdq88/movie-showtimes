/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.utils;

import java.io.IOException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Administrator
 */
public class JsoupConnect {
    
    /**
     * Jsoup connect to an url to get html
     * @param url
     * @return
     * @throws IOException 
     */
    public static Document getHTML(String url) throws IOException {
        Document doc;
        doc = Jsoup.connect(url).timeout(10000).get();
        doc.outputSettings().charset("UTF-8");
        return doc;
    }

    /**
     * Jsoup connect to an url to get json
     * @param url
     * @return
     * @throws IOException 
     */
    public static JSONObject getJSON(String url) throws IOException {
        String strJSON = Jsoup.connect(url).timeout(10000).ignoreContentType(true).execute().body();

        JSONObject objJSON = new JSONObject(strJSON);
        return objJSON;
    }
}
