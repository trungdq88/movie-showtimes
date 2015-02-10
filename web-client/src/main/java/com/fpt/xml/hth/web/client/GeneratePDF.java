/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.xml.hth.web.client;

import com.fpt.xml.hth.web.client.utils.EnvUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.fonts.apps.TTFReader;

/**
 *
 * @author Administrator
 */
public class GeneratePDF {

    public static byte[] generateMovieDetail(String xmlPath, String path, String movieId) {
        try {
            String xslPath = path + "/pdf/detail.xsl";
            String foPath = path + "/pdf/detailFo.fo";
            String config = path + "/pdf/fopUserConfig.xml";
            methodTrAX(xslPath, xmlPath, foPath, movieId);
            File file = new File(foPath);
            FileInputStream input = new FileInputStream(file);

            ByteArrayOutputStream outp = new ByteArrayOutputStream();

            FopFactory ff = FopFactory.newInstance();
            FOUserAgent fua = ff.newFOUserAgent();
            ff.setUserConfig(config);
            ff.getFontManager().setFontBaseURL(path+"/fonts/");
            Fop fop = ff.newFop(MimeConstants.MIME_PDF, fua, outp);

            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer trans = tff.newTransformer();
            File fo = new File(foPath);
            Source src = new StreamSource(fo);
            Result result = new SAXResult(fop.getDefaultHandler());
            trans.transform(src, result);

            return outp.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }

    private static void methodTrAX(String xslPath, String xmlPath, String output, String movieId) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xsltFile = new StreamSource(xslPath);
            Transformer trans = tf.newTransformer(xsltFile);

            StreamSource xmlFile = new StreamSource(xmlPath);
            StreamResult htmlFile = new StreamResult(new FileOutputStream(output));
            trans.setParameter("movieId", movieId);
            trans.transform(xmlFile, htmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
