package com.aam.secretName;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Answer extends AppCompatActivity {
    private static final Uri CREATOR_URL = Uri.parse("https://m.facebook.com/abdullahalmasum2000");
    private static final Uri INSPIRE_URL = Uri.parse("https://m.facebook.com/ib.arif.73");
    String answer, DEVICE_NAME;
//    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        answer = getIntent().getStringExtra("answer");
        showAnswer();
//        DEVICE_NAME = Build.MANUFACTURER + " " + Build.MODEL + " " + getMac();
//
//        // Write a message to the database
//        myRef = FirebaseDatabase.getInstance().getReference("Names");
//        myRef.child(DEVICE_NAME).push().setValue(answer);
    }

    private void showAnswer() {
        TextView answerBox = (TextView) findViewById(R.id.textView10);
        answerBox.setText(answer);
    }

    public void onEnd(View view) {
        runAgain();
    }

    private void runAgain() {
        Intent intent = new Intent(this, com.aam.secretName.MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.finish();
    }

    public void onBack(View view) {
        onBackPressed();
    }

//    public void onCreatorFb(View view) {
//        myRef.child(DEVICE_NAME).push().setValue("User Clicked to see Creator");
//        Intent intent = new Intent(Intent.ACTION_VIEW, CREATOR_URL);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }
//
//    public void onInspireFb(View view) {
//        myRef.child(DEVICE_NAME).push().setValue("User clicked to see about Inspiration");
//        Intent intent = new Intent(Intent.ACTION_VIEW, INSPIRE_URL);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }
//
//    public String getMac(){
//        try {
//            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
//            for (NetworkInterface nif : all){
//                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
//                byte[] macBytes = nif.getHardwareAddress();
//                if (macBytes == null) return "";
//                StringBuilder res1 = new StringBuilder();
//                for (byte b : macBytes){
//                    res1.append(Integer.toHexString(b & 0xFF) + ":");
//                }
//                if (res1.length() > 0) res1.deleteCharAt(res1.length() - 1);
//                return res1.toString();
//            }
//
//        } catch (Exception e) {
//            return "";
//        }
//        return "";
//    }
}