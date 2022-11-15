package com.example.grocerybuddy;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

// declare layouts here
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroceryList #newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroceryList extends Fragment {

//    public InClass08FragChat(String token) {
//        this.currentToken = token;
//        // Required empty public constructor
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: fetch any existing note for this user and init it
//        groceryList = new ArrayList<>();
//        groceryList.add("Example item here!");
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Grocery List");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grocery_list, container, false);
        // Inflate the layout for this fragment
        //initialize layouts here
        return rootView;
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.listView_imageButton:
//                break;
//            case R.id.addItemView_imageButton:
//                listener.goToFragAddItemView();
//                break;
//            case R.id.scanView_imageButton:
//                listener.goToFragScanView();
//                break;
//            case R.id.settings:
//                listener.goToSettings();
//                break;
//            default:
//                throw new IllegalStateException("Invalid View ID - This should be unreachable");
//        }
//    }
}