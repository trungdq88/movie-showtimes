/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.crawler.validation;

/**
 *
 * @author Administrator
 * @param <G>
 */
public abstract class ValidTrack<G> {
    protected G element;
    protected int invalidNum;
    protected boolean valid;

    /**
     * Start validation crawled data
     *
     * @param element
     */
    public abstract void start();

    /**
     * Check validation after scrawler data Return 'true' mean that data can be
     * imported into database Return 'false' mean that data cannot be imported
     * into database
     *
     * @return
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Get number of invalid data
     *
     * @return
     */
    public int getInvalidNum() {
        return invalidNum;
    }

    /**
     * Print out invalid log
     */
    public abstract void log();    
    
    /**
     * Get element
     * @return 
     */
    public G getElement() {
        return element;
    }
       
}
