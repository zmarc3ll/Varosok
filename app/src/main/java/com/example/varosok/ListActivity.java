package com.example.varosok;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ListActivity extends AppCompatActivity {
    private Button backBtn;
    private TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();

        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void init() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        backBtn = findViewById(R.id.backBtn);
        data = findViewById(R.id.data);
        Response response = null;

        try {
            response = RequestHandler.get(MainActivity.BASE_URL);
            String content = response.getContent();
            data.setText(content);

        } catch (IOException e) {
            Toast.makeText(this, "Hiba történt!", Toast.LENGTH_SHORT).show();
        }

    }
}
