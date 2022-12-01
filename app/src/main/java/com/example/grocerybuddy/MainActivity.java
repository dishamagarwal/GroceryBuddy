package com.example.grocerybuddy;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LogIn.Listener, Home.Listener, Register.Listener {

    ImageButton listView;
    ImageButton addItemView;
    ImageButton scanView;
    ImageButton settings;
    private FirebaseAnalytics mFirebaseAnalytics;

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
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        listView = findViewById(R.id.listView_imageButton);
        addItemView = findViewById(R.id.addItemView_imageButton);
        scanView = findViewById(R.id.scanView_imageButton);
        settings = findViewById(R.id.settings);

        getSupportFragmentManager().beginTransaction().add(R.id.containerFrag2,
                new Home(), "home").addToBackStack(null).commit();

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

    @Override
    public void goToLoginFrag() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrag2,
                new LogIn(), "login").addToBackStack(null).commit();
    }

//    @Override
//    public void goFromSettingsToHome() {
//        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrag2,
//                new Home(), "home").addToBackStack(null).commit();
//    }

    @Override
    public void goToRegisterFrag() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFrag2,
                new Register(), "register").addToBackStack(null).commit();
    }

    @Override
    public void goFromRegisterToGroceryList(String userId) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,
                new GroceryList(), "grocery list").commit();
    }

//    @Override
//    public void goToSettings() {
//        getSupportFragmentManager().beginTransaction().add(R.id.containerFrag2,
//                new Settings(), "settings").addToBackStack(null).commit();
//    }

    @Override
    public void goFromLogInToGroceryList(String userId) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,
                new GroceryList(), "grocery list").commit();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}