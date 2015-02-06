/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.utils;

import com.fpt.xml.hth.crawler.crawlentities.CrawlCinema;
import com.fpt.xml.hth.crawler.services.UnmarshallingXML;
import com.fpt.xml.hth.crawler.validation.ValidCinemaTrack;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Administrator
 */
public class MarshalUtil {

    public static void marshalXML(Object obj, String target) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(obj, sw);
            String xmlString = sw.toString();
            
            PrintWriter writer = new PrintWriter("output_" + target + ".xml", "UTF-8");
            writer.println(xmlString);
            writer.close();
        } catch (JAXBException ex) {
            Logger.getLogger(MarshalUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MarshalUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MarshalUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Object unmarshalXML(Object obj, String file){
        try {
            JAXBContext jc = JAXBContext.newInstance(obj.getClass());
            Unmarshaller u = jc.createUnmarshaller();
            File f = new File(file);
            CrawlCinema item = (CrawlCinema) u.unmarshal(f);                        
            System.out.println(item.getName());
            
            ValidCinemaTrack track = new ValidCinemaTrack(item);
            track.start();
            track.log();
            if(track.isValid()){
                System.out.println("VALID CINEMA");
            }else{
                System.out.println("NOT VALID CINEMA");
            }
            return (Object) item;
        } catch (JAXBException ex) {
            Logger.getLogger(UnmarshallingXML.class.getName()).log(Level.SEVERE, null, ex);
            return new Object();
        }
    }
}
