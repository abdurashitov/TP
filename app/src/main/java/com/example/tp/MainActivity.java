package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick7(View view) {
        Intent intent = new Intent(MainActivity.this, LAB_7.class);
        startActivity(intent);
    }
    public void onClick8(View view) {
        Intent intent = new Intent(MainActivity.this, LAB_8.class);
        startActivity(intent);
    }
}
