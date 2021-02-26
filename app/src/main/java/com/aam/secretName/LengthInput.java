package com.aam.secretName;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LengthInput extends AppCompatActivity {

    int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_input);
    }

    public void onNext(View view) {
        nextActivity();
    }

    private int getLength() {
        EditText etLength = findViewById(R.id.etLength);
        try {
            int x = Integer.parseInt(etLength.getText().toString());
            if (x == (int) x) return x;
        } catch (Exception e) {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//            dialog.setTitle("Warning").setMessage("Please type valid Input").setCancelable(true).show();
        }
        return 0;
    }

    private void nextActivity() {
        Intent intent = new Intent(this, com.aam.secretName.FirstInput.class);
        length = getLength();
        if (length != 0) {
            intent.putExtra("lengthOfWord", length);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please Give input first", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBack(View view) {
        onBackPressed();
    }

    public void help(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Example").setMessage(R.string.example).setCancelable(true).show();
    }
}