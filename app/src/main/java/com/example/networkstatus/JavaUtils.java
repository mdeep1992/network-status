package com.example.networkstatus;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JavaUtils extends AppCompatActivity {
    public void checkNetworkConnectionStatus() {
        boolean wifiConnected;
        boolean mobileDataConnected;
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
            wifiConnected = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileDataConnected = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected){
                Toast.makeText(this, "wifi connected", Toast.LENGTH_SHORT).show();
            } else if (mobileDataConnected){
                Toast.makeText(this, "mobile network connected", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
        }
    }
}
