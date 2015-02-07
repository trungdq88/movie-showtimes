/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.web.client;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author dinhquangtrung
 */
public class Validator {

    public static String validate(String xmlPath, String xsdPath) {
        String result = "true";
        try {
            JAXBContext jc = JAXBContext.newInstance("com.fpt.xml.hth.db.lib.resource");
            Unmarshaller u = jc.createUnmarshaller();

            SchemaFactory sf = SchemaFactory.newInstance(
                    XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(xsdPath));
            u.setSchema(schema);

            File f = new File(xmlPath);
            u.unmarshal(f);
        } catch (JAXBException ex) {
            result = getStackTrace(ex);
            ex.printStackTrace();
        } catch (SAXException ex) {
            result = getStackTrace(ex);
            ex.printStackTrace();
        }
        return result;
    }

    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
