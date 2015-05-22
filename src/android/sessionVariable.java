package com.cordova.sessionVariable;


import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;


public class sessionVariable extends CordovaPlugin {
    public static final String TAG = "session Variable";
    private SharedPreferences pref;
    SharedPreferences.Editor editor;

    public sessionVariable() {
        pref = cordova.getActivity().getApplicationContext().getSharedPreferences("com.example.sourabh.trackinguser", 0); // 0 - for private mode
        editor = pref.edit();
    }

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        webView.loadUrl("javascript:console.log('hello');");
        Log.v(TAG, "Init storage");
    }

    public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        final int duration = Toast.LENGTH_SHORT;

        Log.v(TAG, "CoolPlugin received:" + action);
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), action, duration);
                toast.show();
            }
        });

        if (action.equals("setKey")) {
            editor.putString(args.getString(0), args.getString(1));
            editor.commit();
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), "VARIABLE SAVED", duration);
                    toast.show();
                }
            });
            return true;
        }

        if (action.equals("getKey")) {
            final String value = pref.getString(args.getString(0), "not found");
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), value, duration);
                    toast.show();
                }
            });
//                return value;
            return true;
        }

        if (action.equals("removeKey")){
            editor.remove(args.getString(0));
            editor.commit();
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), "VARIABLE REMOVED", duration);
                    toast.show();
                }
            });
            return true;
        }
        return false;
    }
}

