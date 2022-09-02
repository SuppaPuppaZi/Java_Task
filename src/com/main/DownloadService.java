package com.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadService {

    public static String NumberFact(String url){
        HttpURLConnection connection = null;

        try{
            connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);

            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }

                String readyQuery = sb.toString();
                System.out.println("GET " + url);
                System.out.println(readyQuery.substring(0, readyQuery.length() - 1));

                return readyQuery.substring(0, readyQuery.length() - 1);

            } else {
                return "fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage();
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return "";
    }
}
