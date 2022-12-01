package com.example.grocerybuddy;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home# newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment implements View.OnClickListener {

    private Button btn_login, btn_register;
    private Home.Listener listener;

    public Home() {
        // Required empty public constructor
    }

    public interface Listener {
        void goToLoginFrag();
        void goToRegisterFrag();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Home.Listener) {
            listener = (Home.Listener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement Listener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Home");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container,
                false);
        btn_login = rootView.findViewById(R.id.button_login);
        btn_register = rootView.findViewById(R.id.button_register);

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                listener.goToLoginFrag();
                break;
            case R.id.button_register:
                listener.goToRegisterFrag();
                break;
            default:
                throw new IllegalStateException("Invalid View ID - This should be unreachable");
        }
    }
}