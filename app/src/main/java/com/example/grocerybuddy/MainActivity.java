package com.example.grocerybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,
                new GroceryList(), "groceryList").addToBackStack(null).commit();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.containerFragment, new GroceryList())
//                .commit();
    }
}