package com.example.networkstatus;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.telephony.TelephonyManager.NETWORK_TYPE_1xRTT;
import static android.telephony.TelephonyManager.NETWORK_TYPE_CDMA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EDGE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_0;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_A;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_B;
import static android.telephony.TelephonyManager.NETWORK_TYPE_GPRS;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSDPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPAP;
import static android.telephony.TelephonyManager.NETWORK_TYPE_IDEN;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_NR;
import static android.telephony.TelephonyManager.NETWORK_TYPE_UMTS;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button network_btn;
    private TelephonyManager telephonyManager;
    Handler handler = new Handler();
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        network_btn = findViewById(R.id.btn_network);
//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},PackageManager.PERMISSION_GRANTED);
        telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        network_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkNetworkConnectionStatus();
            }


        });
    }

    @SuppressLint("MissingPermission")
    private void checkNetworkConnectionStatus() {
        boolean wifiConnected;
        boolean mobileDataConnected;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            wifiConnected = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileDataConnected = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
//                Toast.makeText(this, "wifi connected", Toast.LENGTH_SHORT).show();
                informUser("wifi connected");
            } else if (mobileDataConnected) {
               Toast.makeText(this, "mobile network connected", Toast.LENGTH_SHORT).show();
//                informUser("mobile network connected");
                if (telephonyManager.getDataNetworkType() == (NETWORK_TYPE_EDGE)) {
//                    Toast.makeText(this, " current network bandwidth is 2G", Toast.LENGTH_SHORT).show();
                    informUser("current network bandwidth is 2G");
                }
                if (telephonyManager.getDataNetworkType() == (NETWORK_TYPE_HSPA)) {
//                    Toast.makeText(this, " current network bandwidth is 3G", Toast.LENGTH_SHORT).show();
                    informUser("current network bandwidth is 3G");
                }
                if (telephonyManager.getDataNetworkType() == (NETWORK_TYPE_LTE)) {
//                    Toast.makeText(this, " current network bandwidth is 4G", Toast.LENGTH_SHORT).show();
                    informUser("current network bandwidth is 4G");
                }
//                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                    return;
//                }
//                switch (telephonyManager.getDataNetworkType()) {
//                    case NETWORK_TYPE_EDGE:
//                    case NETWORK_TYPE_GPRS:
//                    case NETWORK_TYPE_CDMA:
//                    case NETWORK_TYPE_IDEN:
//                    case NETWORK_TYPE_1xRTT:
//                        Toast.makeText(this, " current network bandwidth is 2G", Toast.LENGTH_SHORT).show();
//                        break;
//                    case NETWORK_TYPE_UMTS:
//                    case NETWORK_TYPE_HSDPA:
//                    case NETWORK_TYPE_HSPA:
//                    case NETWORK_TYPE_HSPAP:
//                    case NETWORK_TYPE_EVDO_0:
//                    case NETWORK_TYPE_EVDO_A:
//                    case NETWORK_TYPE_EVDO_B:
//                        Toast.makeText(this, " current network bandwidth is 3G", Toast.LENGTH_SHORT).show();
//                        break;
//                    case NETWORK_TYPE_LTE:
//                        Toast.makeText(this, " current network bandwidth is 4G", Toast.LENGTH_SHORT).show();
//                        break;
//                    case NETWORK_TYPE_NR:
//                        Toast.makeText(this, " current network bandwidth is 5G", Toast.LENGTH_SHORT).show();
//                        break;
//
//                }
            }
        }else {
//            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
            informUser("No internet");
        }
    }

    private void informUser(String message) {
        Snackbar.make(findViewById(R.id.root_view), message,Snackbar.LENGTH_SHORT).show();
    }

}