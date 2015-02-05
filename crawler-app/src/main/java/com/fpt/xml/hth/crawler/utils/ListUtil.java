/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpt.xml.hth.crawler.utils;

import com.fpt.xml.hth.crawler.crawlentities.CrawlMovie;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ListUtil {
    
    public static int indexOfItem(ArrayList<CrawlMovie> list, String name){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }
    
    
}
