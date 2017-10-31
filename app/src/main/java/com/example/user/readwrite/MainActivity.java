package com.example.user.readwrite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String getText(){
        EditText text = (EditText) findViewById(R.id.editText);
        return  text.getText().toString();
    }

    public void addText(View view) {
        String writeText = getText();

        try {
            FileOutputStream outputStream = openFileOutput("myfile", MODE_PRIVATE);
            outputStream.write(writeText.getBytes());
            outputStream.close();

            Toast.makeText(this, "Text added", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showText(View view){
        try {

            FileInputStream inputStream = openFileInput("myfile");
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputReader);
            String message = "The text is: ";
            String enteredText;

            while((enteredText = br.readLine())!= null){
                message += enteredText + "\n";
            }

            TextView textView = (TextView) findViewById(R.id.txtMessage);
            textView.setText(message);

            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         catch (IOException e) {
            e.printStackTrace();
        }

    }
}
