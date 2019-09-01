package com.budgetapp;

import android.content.Context;
import android.net.SSLCertificateSocketFactory;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class APIServiceSingleton {
    private static APIServiceSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private APIServiceSingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized APIServiceSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new APIServiceSingleton(context);
        }

        return instance;
    }

    private HurlStack getInsecureHurlStack() {
        return new HurlStack() {
            @Override
            protected HttpsURLConnection createConnection(URL url) throws IOException {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.createConnection(url);
                try {
                    httpsURLConnection.setSSLSocketFactory(
                            SSLCertificateSocketFactory.getInsecure(
                                    0,
                                    null));

                    httpsURLConnection.setHostnameVerifier(new AllowAllHostnameVerifier());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return httpsURLConnection;
            }
        };
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext(), getInsecureHurlStack()); // TODO: Remove insecure stack parameter
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
