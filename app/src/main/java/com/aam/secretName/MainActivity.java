package com.aam.secretName;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    boolean isConnected;
    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void onStart(View view) {
        nextActivity();
    }

    private void nextActivity() {
        dialog = new AlertDialog.Builder(this);
        isConnected = isNetworkConnected();

        if (isConnected){
            Intent intent = new Intent(this, LengthInput.class);
            startActivity(intent);
        }
        else{
            dialog.setMessage("Please check internet connection");
            dialog.setCancelable(true).show();
        }
    }

}