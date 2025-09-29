package com.example.a412_assignment2_intro_app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondaryActivity extends AppCompatActivity {

    private View ScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secondary);


        LinearLayout challengesView = findViewById(R.id.ll_challenges);

        MobileChallenge challenge1 = new MobileChallenge("Device Fragmentation","There are a large varieties of different device configurations. (Screen size, refresh rates, etc.)");
        MobileChallenge challenge2 = new MobileChallenge("OS Fragmentation","App Development needs to consider the fact that there are many platforms and operating systems.");
        MobileChallenge challenge3 = new MobileChallenge("OS Fragmentation (cont.)","App Development also needs to focus on being compatible as OS changes, both forwards and backwards.");
        MobileChallenge challenge4 = new MobileChallenge("Dynamic Environments","Key features need to be accessible even in unfavorable environments, such as poor reception, low battery, and internal sensor inaccuracies");
        MobileChallenge challenge5 = new MobileChallenge("Rapid Changes","Operating systems are rapidly changing, it is difficult to keep up the pace. Significant maintenance and effort is required for this.");
        MobileChallenge challenge6 = new MobileChallenge("Low User Tolerance","Users are highly likely to uninstall apps that they won't tolerate. Unresponsiveness, Unstableness, Storage/Memory Waster, and Annoying to use are some qualms.");
        MobileChallenge challenge7 = new MobileChallenge("Low Security/Privacy","Users are not well aware of various ways to prevent security and privacy issues.");

        LinearLayout challenge1Layout = challenge1.toLinearLayout();
        LinearLayout challenge2Layout = challenge2.toLinearLayout();
        LinearLayout challenge3Layout = challenge3.toLinearLayout();
        LinearLayout challenge4Layout = challenge4.toLinearLayout();
        LinearLayout challenge5Layout = challenge5.toLinearLayout();
        LinearLayout challenge6Layout = challenge6.toLinearLayout();
        LinearLayout challenge7Layout = challenge7.toLinearLayout();

        challengesView.addView(challenge1Layout);
        challengesView.addView(challenge2Layout);
        challengesView.addView(challenge3Layout);
        challengesView.addView(challenge4Layout);
        challengesView.addView(challenge5Layout);
        challengesView.addView(challenge6Layout);
        challengesView.addView(challenge7Layout);


        Button backButton = findViewById(R.id.btn_activateMain);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent explicitIntent = new Intent( SecondaryActivity.this, MainActivity.class);
                startActivity(explicitIntent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Trying to do something new, rather than just make static content on the second activity
    private class MobileChallenge{
        private String name;
        private String description;

        public MobileChallenge(String name, String description){
            this.name = name;
            this.description = description;
        }

        public LinearLayout toLinearLayout(){
            TextView textView_name = new TextView(SecondaryActivity.this);
            textView_name.setText(name);
            textView_name.setTextSize(24);
            textView_name.setTextColor(Color.BLACK);
            textView_name.setTypeface(null, Typeface.BOLD);
            
            TextView textView_desc = new TextView(SecondaryActivity.this);
            textView_desc.setText(description);
            textView_desc.setTextColor(Color.GRAY);

            LinearLayout.LayoutParams name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            name_params.setMargins(8,8,8,8);
            LinearLayout.LayoutParams desc_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            desc_params.setMargins(24,8,8,8);


            textView_name.setLayoutParams(name_params);
            textView_desc.setLayoutParams(desc_params);
            textView_name.setGravity(Gravity.CENTER_VERTICAL);
            textView_desc.setGravity(Gravity.CENTER_VERTICAL);


            LinearLayout ll = new LinearLayout(SecondaryActivity.this);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setLayoutParams(name_params);

            ll.addView(textView_name);
            ll.addView(textView_desc);
            
            return ll;
        }

    }

}