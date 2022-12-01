package com.example.grocerybuddy;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogIn# newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogIn extends Fragment implements View.OnClickListener {

    private Listener listener;

    private EditText text_email, text_password;
    private Button btn_login;

    private String email, password;

    public LogIn() {
        // Required empty public constructor
    }

    private final OkHttpClient client = new OkHttpClient();

    public interface Listener {
        void goFromSignInToNotes();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            listener = (Listener) context;
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
        getActivity().setTitle("Sign In");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_log_in, container, false);

        text_email = rootView.findViewById(R.id.edit_txt_ica8_email_signin);
        text_password = rootView.findViewById(R.id.edit_txt_ica8_pwd_signin);

        btn_login = rootView.findViewById(R.id.btn_ica8_login);
        btn_login.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_ica8_login) {
            email = text_email.getText().toString();
            password = text_password.getText().toString();
            getCurrentUsers(email, password);
        }
    }

    private void getCurrentUsers(String email, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url("http://dev.sakibnm.space:3000/api/auth/login")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    listener.goFromSignInToNotes();
                }
            }
        });
    }
}