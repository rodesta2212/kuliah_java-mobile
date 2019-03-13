package com.gmail.rodesta2212.pertemuan12;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HandleJSON {
    private String country = "country";
    private Integer temperature = 0;
    private String humidity = "humidity";
    private String pressure = "pressure";
    private String urlString = "null";
    private String icon = "icon";
    private String description ="description";
    public volatile boolean parsingComplete = true;
    public HandleJSON(String url){
        this.urlString = url;
    }
    public String getCountry(){
        return country;
    }
    public Integer getTemperature(){
        temperature = temperature -273;
        return temperature;
    }
    public String getHumidity(){
        return humidity;
    }
    public String getPressure(){
        return pressure;
    }
    public String getIcon(){
        return icon;
    }
    public String getDescription(){
        return description;
    }
    public void readAndParseJSON(String in){
        try{
            JSONObject reader = new JSONObject(in);
            JSONObject sys = reader.getJSONObject("sys");
            country = sys.getString("country");
            JSONObject main = reader.getJSONObject("main");
            temperature = main.getInt("temp");
            pressure = main.getString("pressure");
            humidity = main.getString("humidity");
            JSONArray weather = reader.getJSONArray("weather");
            JSONObject Weather = weather.getJSONObject(0);
            icon = Weather.getString("icon");
            description = Weather.getString("description");
            parsingComplete = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void fetchJSON(){
        Thread thread = new Thread(new Runnable(){
            public void run(){
                try{
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection)
                            url.openConnection();
                    conn.setReadTimeout(10000/*miliseconds*/);
                    conn.setConnectTimeout(15000/*miliseconds*/);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    String data = convertStreamToString(stream);
                    readAndParseJSON(data);
                    stream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    private String convertStreamToString(java.io.InputStream is) {
// TODO Auto-generated method stub
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
