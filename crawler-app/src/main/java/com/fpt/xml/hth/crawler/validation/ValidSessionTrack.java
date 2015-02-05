/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.validation;

import com.fpt.xml.hth.crawler.crawlentities.CrawlDate;
import com.fpt.xml.hth.crawler.crawlentities.CrawlTime;

/**
 *
 * @author Administrator
 */
public class ValidSessionTrack extends ValidTrack<CrawlDate> {

    public ValidSessionTrack(CrawlDate date) {
        this.invalidNum = 0;
        this.valid = false;
        this.element = date;
    }

    @Override
    public void start() {
        for (CrawlTime time : element.getTimes()) {
            invalidNum += time.isValid() ? 0 : 1;
        }
        this.valid = isValidData();
    }

    @Override
    public void log() {
        String message = element.getDate();
        message += this.valid ? " is valid \n" : " is not valid \n";
        message += invalidNum + " times are not valid \n";
        System.out.println(message);
    }

    private boolean isValidData() {
        if (invalidNum != 0) {
            return false;
        }
        return element.isValid();
    }

}
