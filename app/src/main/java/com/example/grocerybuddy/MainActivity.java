package com.example.grocerybuddy;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton listView;
    ImageButton addItemView;
    ImageButton scanView;
    ImageButton settings;
    ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
//                img_avatar = findViewById(R.id.img_avatar);
//                rid = result.getData().getIntExtra(InClass02Avatar.clicked_img, -1);
//                img_avatar.setImageResource(rid);
            }
        }
    });
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView_imageButton);
        addItemView = findViewById(R.id.addItemView_imageButton);
        scanView = findViewById(R.id.scanView_imageButton);
        settings = findViewById(R.id.settings);

        getSupportFragmentManager().beginTransaction().add(R.id.containerFragment,
                new GroceryList(), "groceryList").addToBackStack(null).commit();

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,
                        new GroceryList(), "groceryList").commit();
            }
        });

        addItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,
                        new AddItem(), "addItem").commit();
            }
        });

        scanView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,
                        new ScanBill(), "scanBill").commit();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivityForResult.launch(intent);
            }
        });
    }

    public MainActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: add logic to go back to the notes screen if we already logged in
    }
}