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
public class ProvineUtil {

    public static final Map<String, String> provines;

    static {
        provines = new HashMap<String, String>();
        provines.put("HO-CHI-MINH", "Hồ Chí Minh");
        provines.put("HA-NOI", "Hà Nội");
        provines.put("DA-NANG", "Đà Nẵng");
        provines.put("", "Hà Nội");
        provines.put("HA-NOI", "Hà Nội");
    }

    public static final Map<String, String> mapping;

    static {
        mapping = new HashMap<String, String>();
        mapping.put("HCM", provines.get("HO-CHI-MINH"));
        mapping.put("TP HCM", provines.get("HO-CHI-MINH"));
        mapping.put("Hồ Chí Minh", provines.get("HO-CHI-MINH"));
        mapping.put("Ho Chi Minh", provines.get("HO-CHI-MINH"));
        mapping.put("HN", provines.get("HA-NOI"));
        mapping.put("Hà Nội", provines.get("HA-NOI"));
        mapping.put("Ha Noi", provines.get("HA-NOI"));
    }

}
