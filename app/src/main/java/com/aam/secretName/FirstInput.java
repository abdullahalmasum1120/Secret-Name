package com.aam.secretName;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class FirstInput extends AppCompatActivity {
    int length, count = 0;
    char[] arrayOfLineNumbers;

    public void line1(View v){
        lineNumbers('1');
    }
    public void line2(View v){
        lineNumbers('2');
    }
    public void line3(View v){
        lineNumbers('3');
    }
    public void line4(View v){
        lineNumbers('4');
    }
    public void line5(View v){
        lineNumbers('5');
    }
    public void line6(View v){
        lineNumbers('6');
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_input);
        length = getIntent().getIntExtra("lengthOfWord", length);
        arrayOfLineNumbers = new char[length];
    }

    public void onNext(View view) {
        nextActivity();
    }

    private void nextActivity() {
        Intent intent = new Intent(this, FinalInput.class);
        if (count >= length){
            intent.putExtra("arrayOfLineNumbers", arrayOfLineNumbers).putExtra("lengthOfWord", length);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Please Give input first", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBack(View view) {
        onBackPressed();
    }

    public void onReset(View view) {
        reset();
    }

    private void reset() {
        Arrays.fill(arrayOfLineNumbers, '\0');
        count = 0;
        TextView textView = (TextView) findViewById(R.id.textView7);
        textView.setText(new String(arrayOfLineNumbers));
        Toast.makeText(this, "Reseted", Toast.LENGTH_SHORT).show();
    }

    protected void lineNumbers(char lineNo) {
        if (count<length){
            arrayOfLineNumbers[count++] = lineNo;
            TextView lineNumbers = (TextView) findViewById(R.id.textView7);
            lineNumbers.setText(new String(arrayOfLineNumbers));
        }
        else {
            Toast.makeText(this, "Input Completed", Toast.LENGTH_SHORT).show();
        }
    }

    public void help(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Example").setMessage(R.string.example2 + length).setCancelable(true).show();
    }
}