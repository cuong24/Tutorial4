package com.example.tutorial4;

import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {

    public static String getStudents(String urlStr){
        HttpURLConnection httpURLConnection = null;
        StringBuilder stringBuilder = new StringBuilder();

        try{
            URL url = new URL(urlStr);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static String postStudent(String name, String urlStr){
        URL url = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept","application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);

            Log.i("Json", jsonObject.toString());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return stringBuilder.toString();
    }

    public static String updateStudent(int id, String name, String urlStr){
        URL url = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            url = new URL(urlStr + "/" + Integer.toString(id));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept","application/json");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);

            Log.i("Json", jsonObject.toString());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static String deleteStudent(int id, String urlStr){
        URL url = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            url = new URL(urlStr + "/" + Integer.toString(id));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("DELETE");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
