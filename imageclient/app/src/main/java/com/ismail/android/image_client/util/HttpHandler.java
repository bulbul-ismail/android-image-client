package com.ismail.android.image_client.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
/**
 * Created by ismail on 14.09.2018.
 */

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();
    private final int timeoutValue = 1000;
    private Context context;

    public HttpHandler(Context context) {
        this.context = context;
    }

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            //   HttpsURLConnection  conn = (HttpsURLConnection ) url.openConnection();
            HttpURLConnection  conn = (HttpURLConnection ) url.openConnection();
            conn.setRequestMethod("GET");
            //Log.d("qwqw",""+timeoutValue);
            conn.setConnectTimeout(timeoutValue);
            conn.setReadTimeout(timeoutValue);
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    public String makeServiceCallWithHeader(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);


            HttpURLConnection  conn = (HttpURLConnection ) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(timeoutValue);
            conn.setReadTimeout(timeoutValue);

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    // HTTP POST request
    public void sendPost(final String reqUrl, final JSONArray jsonArray){
        //    Log.d("serverResponse","sendpost start "+ Instant.now());
        try {



            Handler mainHandler = new Handler(Looper.getMainLooper());
            Runnable myRunnable = new Runnable() {
                @Override
                public void run() {


                    String input = jsonArray.toString();
                    StringEntity entity = null;
                    try {
                        entity = new StringEntity(input);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    AsyncHttpClient client = new AsyncHttpClient();
                    //RequestParams params = new RequestParams();
                    client.addHeader("Content-Type", "application/json");

                    client.post(context,reqUrl,entity, "application/json", new TextHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String res) {
                                    // called when response HTTP status is "200 OK"
                                    //        Log.d("serverResponse",res);
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                                    //            Log.d("serverResponse",res);
                                }
                            }
                    );

                }
            };
            mainHandler.post(myRunnable);




        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void sendPost(String reqUrl,JSONObject jsonObject){

        try {

            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = jsonObject.toString();

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    private String convertStreamToString(InputStream is) {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return result.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
