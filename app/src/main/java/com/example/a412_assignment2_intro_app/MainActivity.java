package com.example.a412_assignment2_intro_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button button_implicit = findViewById(R.id.btn_implicit);
        Button button_explicit = findViewById(R.id.btn_explicit);

        button_explicit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent explicitIntent = new Intent(MainActivity.this, SecondaryActivity.class);
                startActivity(explicitIntent);
            }
        });

        button_implicit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent implicitIntent = new Intent("com.example.app.ACTIVATE_SECONDARY_ACTIVITY");
                startActivity(implicitIntent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



}