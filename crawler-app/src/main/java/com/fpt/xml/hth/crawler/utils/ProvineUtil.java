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
        provines.put("HAI-PHONG", "Hải Phòng");
        provines.put("DONG-NAI", "Đồng Nai");
        provines.put("QUANG-NINH", "Quảng Ninh");
        provines.put("CAN-THO", "Cần Thơ");
        provines.put("VUNG-TAU", "Vũng Tàu");
        provines.put("BINH-DINH", "Bình Định");
        provines.put("BINH-DUONG", "Bình Dương");
    }

    public static final Map<String, String> mapping;

    static {
        mapping = new HashMap<String, String>();
        mapping.put("hcm", provines.get("HO-CHI-MINH"));
        mapping.put("tphcm", provines.get("HO-CHI-MINH"));
        mapping.put("hochiminh", provines.get("HO-CHI-MINH"));
        mapping.put("tphochiminh", provines.get("HO-CHI-MINH"));
        mapping.put("hn", provines.get("HA-NOI"));
        mapping.put("tphn", provines.get("HA-NOI"));
        mapping.put("hanoi", provines.get("HA-NOI"));
        mapping.put("tphanoi", provines.get("HA-NOI"));
        mapping.put("tdhanoi", provines.get("HA-NOI"));
        mapping.put("danang", provines.get("DA-NANG"));
        mapping.put("hp", provines.get("HAI-PHONG"));
        mapping.put("haiphong", provines.get("HAI-PHONG"));
        mapping.put("tphaiphong", provines.get("HAI-PHONG"));
        mapping.put("dongnai", provines.get("DONG-NAI"));
        mapping.put("quangninh", provines.get("QUANG-NINH"));
        mapping.put("cantho", provines.get("CAN-THO"));
        mapping.put("tpcantho", provines.get("CAN-THO"));
        mapping.put("vungtau", provines.get("VUNG-TAU"));
        mapping.put("binhdinh", provines.get("BINH-DINH"));
        mapping.put("binhduong", provines.get("BINH-DUONG"));
    }

}
