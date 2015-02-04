/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpt.xml.hth.crawler.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class MapUtil {
    public static final Map<String, String> provine;
    static
    {
        provine = new HashMap<String, String>();
        provine.put("HCM", "");
        provine.put("TP HCM", "Hồ Chí Minh");
    }
    
}
