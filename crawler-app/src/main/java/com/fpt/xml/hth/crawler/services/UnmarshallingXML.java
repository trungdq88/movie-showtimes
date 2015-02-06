/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.services;

import com.fpt.xml.hth.crawler.crawlentities.CrawlCinema;
import com.fpt.xml.hth.crawler.validation.ValidCinemaTrack;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Administrator
 */
public class UnmarshallingXML {

    public static void main(String[] args) {
        try {
            JAXBContext jc = JAXBContext.newInstance(CrawlCinema.class);
            Unmarshaller u = jc.createUnmarshaller();
            File f = new File("output_cgv.xml");
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
        } catch (JAXBException ex) {
            Logger.getLogger(UnmarshallingXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
