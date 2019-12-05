package com.solar.apps.utility;

import android.util.Log;

import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

//https://github.com/android-async-http/android-async-http/tree/1.4.10/sample/src/main/java/com/loopj/android/http/sample

public class HttpUtils {

    private static final String LOG_TAG = "GetSample";

    public static void invokeWS(RequestParams params){
        // Show Progress Dialog
       // prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        Log.e("Start","calling ");
        SyncHttpClient client = new SyncHttpClient();
        client.get("http://172.20.10.5:8080/energy/getByMonth/10001/9/2019",params ,new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                Log.e("Start","calling111 ");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                Log.e("",""+statusCode);
                Log.e("",""+headers);
                Log.e("",""+response);
                debugHeaders("",headers);
                debugStatusCode(LOG_TAG, statusCode);
                debugResponse(LOG_TAG, new String(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                debugHeaders(LOG_TAG, headers);
                debugStatusCode(LOG_TAG, statusCode);
                debugThrowable(LOG_TAG, e);
                if (errorResponse != null) {
                    debugResponse(LOG_TAG, new String(errorResponse));
                }
                Log.e("Start","error ");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried

                Log.e("Start",String.format(Locale.US, "Request is retried, retry no. %d", retryNo));
            }



        });
    }


    protected static final void debugHeaders(String TAG, Header[] headers) {
        if (headers != null) {
            Log.d(TAG, "Return Headers:");
            StringBuilder builder = new StringBuilder();
            for (Header h : headers) {
                String _h = String.format(Locale.US, "%s : %s", h.getName(), h.getValue());
                Log.d(TAG, _h);
                builder.append(_h);
                builder.append("\n");
            }
            Log.e("Header",builder.toString());
        }
    }

    protected static final void debugResponse(String TAG, String response) {
        if (response != null) {
            Log.d(TAG, "Response data:");
            Log.d(TAG, response);
            Log.e("Response ",response);
        }
    }

    protected static final void debugStatusCode(String TAG, int statusCode) {
        String msg = String.format(Locale.US, "Return Status Code: %d", statusCode);
        Log.d(TAG, msg);
        Log.e("Status",msg);
    }

    protected static final void debugThrowable(String TAG, Throwable t) {
        if (t != null) {
            Log.e(TAG, "AsyncHttpClient returned error", t);
            Log.e("Status",throwableToString(t));

        }
    }

    protected static String throwableToString(Throwable t) {
        if (t == null)
            return null;

        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}



