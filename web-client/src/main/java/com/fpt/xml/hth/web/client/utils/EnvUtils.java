/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.web.client.utils;

import javax.servlet.ServletContext;

/**
 *
 * @author dinhquangtrung
 */
public class EnvUtils {
    public static String getDataPath(ServletContext servletContext) {
        String openshiftPath = System.getenv("OPENSHIFT_DATA_DIR");
        if (openshiftPath == null) {
            return servletContext.getClass().getResource("/").getPath();
        }
        return openshiftPath;
    }
}
