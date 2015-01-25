/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpt.xml.hth.crawler.utils;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Administrator
 */
public class JsoupConnect {
    public static Document get(String url) throws IOException{
        Document doc;
        doc = Jsoup.connect(url).timeout(10000).get();
            doc.outputSettings().charset("UTF-8");
        return doc;
    }
}
