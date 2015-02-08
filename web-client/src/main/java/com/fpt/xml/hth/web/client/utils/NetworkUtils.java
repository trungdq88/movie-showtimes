package com.fpt.xml.hth.web.client.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by dinhquangtrung on 2/1/15.
 */
public class NetworkUtils {
    public String sendGetRequest(String url) {
        StringBuilder result = new StringBuilder();
        HttpURLConnection connection = null;
        BufferedReader serverResponse = null;
        try {
            //OPEN CONNECTION
            connection = (HttpURLConnection) new URL(url).openConnection();

            //SET REQUEST INFO
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            //RESPONSE STREAM
            serverResponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //READ THE RESPOSNE
            String line;
            while ((line = serverResponse.readLine()) != null) {
                result.append(line);
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();

            if (serverResponse != null) {
                try {
                    serverResponse.close();
                } catch (Exception ex) {
                }
            }
        }

        return result.toString();
    }
}
