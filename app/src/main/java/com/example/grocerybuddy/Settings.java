package com.example.grocerybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    Button close;
    TextView name;
    TextView email;
    TextView notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        close = findViewById(R.id.close_settings_button);
        name = findViewById(R.id.name_textView);
        email = findViewById(R.id.email_textView);
        notifications = findViewById(R.id.notifications_textView);
    }
}