package com.example.varosok;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class InsertActivity extends AppCompatActivity {
    private Button backBtnInsrt;
    private Button newItemBtnInsrt;
    private EditText nev;
    private EditText orszag;
    private EditText lakossag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();

        backBtnInsrt.setOnClickListener(view -> {

            Intent intent = new Intent(InsertActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        newItemBtnInsrt.setOnClickListener(view -> {
            felv();
        });
    }

    private void init() {

        backBtnInsrt = findViewById(R.id.backBtnInsrt);
        newItemBtnInsrt = findViewById(R.id.newItemBtnInsrt);
        nev = findViewById(R.id.nev);
        orszag = findViewById(R.id.orszag);
        lakossag = findViewById(R.id.lakossag);
    }

    private void felv() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String name = nev.getText().toString().trim();
        String country = orszag.getText().toString().trim();
        String lakos = lakossag.getText().toString().trim();

        boolean err = false;
        if (name.isEmpty()) {
            Toast.makeText(this, "Kötelező megadni a nevet!", Toast.LENGTH_SHORT).show();
            err = true;
        }
        if (country.isEmpty()) {
            Toast.makeText(this, "Kötelező megadni az országot!",
            Toast.LENGTH_SHORT).show();
            err = true;
        }
        if (lakos.isEmpty()) {
            Toast.makeText(this, "Kötelező megadni a lakosságot!",
            Toast.LENGTH_SHORT).show();
            err = true;
        }

        if (err) {
            return;
        }

        int lakosSzam = Integer.parseInt(lakos);
        City city = new City(0, name, country, lakosSzam);
        GsonBuilder gsonbuilder = new GsonBuilder();
        Gson converter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = converter.toJson(city);
        try {
            Response response = RequestHandler.post(MainActivity.BASE_URL, json);
            if (response.getResponseCode() == 201) {
                Toast.makeText(this, "Sikeres hozzáadás!", Toast.LENGTH_SHORT).show();
                nev.setText("");
                orszag.setText("");
                lakossag.setText("");
            } else {
                String content = response.getContent();
                Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
