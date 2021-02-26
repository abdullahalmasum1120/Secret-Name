package com.aam.secretName;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class FinalInput extends AppCompatActivity {
    int length, count = 0;
    String lineNo1 = "", lineNo2 = "", lineNo3 = "", lineNo4 = "", lineNo5 = "";
    char[] arrayOfLineNumbers;
    char[][] formedTable;
    boolean isConnected;
    char[][] alphabet = {{'A','B','C','D','E'},
            {'F','G','H','I','J'},
            {'K','L','M','N','O'},
            {'P','Q','R','S','T'},
            {'U','V','W','X','Y'},
            {'Z','-','-','-','-'}};

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_input);
        length = getIntent().getIntExtra("lengthOfWord", length);
        arrayOfLineNumbers = getIntent().getCharArrayExtra("arrayOfLineNumbers");
        setLines();
    }

    private void setLines() {
        formedTable = new char[5][length];
        int[] arrayOfLineNumbers = new int[length];
        for (int i = 0; i < this.arrayOfLineNumbers.length; i++){
            arrayOfLineNumbers[i] = this.arrayOfLineNumbers[i] - '0';
        }

        Arrays.fill(this.arrayOfLineNumbers, '\0');

        for (int i = 0; i < length; i++){
            for (int j = 0; j < 5; j++){
                formedTable[j][i] = alphabet[arrayOfLineNumbers[i] - 1][j];
            }
        }

        Button line1 = (Button) findViewById(R.id.button11);
        Button line2 = (Button) findViewById(R.id.button12);
        Button line3 = (Button) findViewById(R.id.button13);
        Button line4 = (Button) findViewById(R.id.button14);
        Button line5 = (Button) findViewById(R.id.button15);

        for (int i = 0; i < length; i++){
            lineNo1 += (formedTable[0][i]);
            lineNo2 += (formedTable[1][i]);
            lineNo3 += (formedTable[2][i]);
            lineNo4 += (formedTable[3][i]);
            lineNo5 += (formedTable[4][i]);
        }

        line1.setText(lineNo1);
        line2.setText(lineNo2);
        line3.setText(lineNo3);
        line4.setText(lineNo4);
        line5.setText(lineNo5);
    }

    public void onFinish(View view) {
        nextActivity();
    }

    private void nextActivity() {
        isConnected = isNetworkConnected();
        if ((count >= length) && (isConnected)){
            String answer = getAnswer();
            Intent intent = new Intent(this, com.aam.secretName.Answer.class);
            intent.putExtra("answer", answer);
            startActivity(intent);
        }
        else if (!isConnected){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Please check internet connection to get answer");
            dialog.setCancelable(true).show();
        }
        else {
            Toast.makeText(this, "Please Give input first", Toast.LENGTH_SHORT).show();
        }
    }

    public void onReset(View view) {
        reset();
    }

    private void reset() {
        Arrays.fill(arrayOfLineNumbers, '\0');
        count = 0;
        TextView reset = (TextView) findViewById(R.id.textView9);
        reset.setText(new String(arrayOfLineNumbers));
        Toast.makeText(this, "Reseted", Toast.LENGTH_SHORT).show();
    }

    public void onBack(View view) {
        onBackPressed();
    }

    private void lineNumbers(char c) {
        if (count<length){
            arrayOfLineNumbers[count++] = c;
            TextView lineNumbers = (TextView) findViewById(R.id.textView9);
            lineNumbers.setText(new String(arrayOfLineNumbers));
        }
        else {
            Toast.makeText(this, "Input Completed", Toast.LENGTH_SHORT).show();
        }
    }

    private String getAnswer() {
        int[] arrayOfLineNumbers = new int[length];
        for (int i = 0; i < this.arrayOfLineNumbers.length; i++){
            arrayOfLineNumbers[i] = this.arrayOfLineNumbers[i] - '0';
        }
        String answer = "";
        for (int i = 0; i < length; i++){
            answer += formedTable[arrayOfLineNumbers[i] - 1][i];
        }
        return answer;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}