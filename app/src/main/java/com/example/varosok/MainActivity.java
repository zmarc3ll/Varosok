package com.example.varosok;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button listBtn;
    private Button newItemBtn;

    protected final static String BASE_URL = "https://retoolapi.dev/u3Ci9z/varosok";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        listBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
            finish();
        });

        newItemBtn.setOnClickListener(view -> {
            Intent intent = new Intent( MainActivity.this, InsertActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void init() {
        listBtn = findViewById(R.id.listBtn);
        newItemBtn = findViewById(R.id.newItemBtn);
    }
}