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
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Register #newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register extends Fragment implements View.OnClickListener {
    public static final String NAME = "name";
    private Register.Listener listener;
    FirebaseAuth auth;
    DatabaseReference reference;
    private TextView text_view_error_msg;
    private EditText text_name, text_email, text_password;
    private Button btn_register;
    private final OkHttpClient client = new OkHttpClient();
    private static String INVALID_NAME = "**Name cannot be empty**";
    private static String INVALID_EMAIL = "**Email is invalid**";
    private static String INVALID_PASSWORD = "**Password cannot be empty or less than length of 6**";

    public Register() {
        // Required empty public constructor
    }

    public interface Listener {
        void goFromRegisterToGroceryList(String userId);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Register.Listener) {
            listener = (Register.Listener) context;
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
        getActivity().setTitle("Register");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_register, container, false);

        text_view_error_msg = rootView.findViewById(R.id.error_msg_register);
        text_name = rootView.findViewById(R.id.edit_txt_ica8_firstname_register);
        text_email = rootView.findViewById(R.id.edit_txt_ica8_email_register);
        text_password = rootView.findViewById(R.id.edit_txt_ica8_pwd_register);
        btn_register = rootView.findViewById(R.id.btn_ica8_register2);
        auth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_ica8_register2) {
            String name = text_name.getText().toString();
            String email = text_email.getText().toString();
            String password = text_password.getText().toString();

            boolean is_valid_name = is_valid_name(name);
            boolean is_valid_email = is_valid_email(email);
            boolean is_valid_password = is_valid_password(password);

            if (!is_valid_name) {
                text_view_error_msg.setText(INVALID_NAME);
                text_view_error_msg.setVisibility(View.VISIBLE);
                return;
            }

            if (!is_valid_email) {
                text_view_error_msg.setText(INVALID_EMAIL);
                text_view_error_msg.setVisibility(View.VISIBLE);
                return;
            }

            if (!is_valid_password) {
                text_view_error_msg.setText(INVALID_PASSWORD);
                text_view_error_msg.setVisibility(View.VISIBLE);
                return;
            }

            // Register and sign in the user
            if (is_valid_name && is_valid_email && is_valid_password) {
                register(name, email, password);
            }
        }
    }

    private boolean is_valid_name(String name) {
        return !name.equals("");
    }

    private boolean is_valid_email(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(\\w+).(\\w+)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    private boolean is_valid_password(String password) {
        return !password.equals("") && password.length() >= 6;
    }

    private void register(String email, String username, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userId = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance()
                                    .getReference("Users").child(userId);

                            HashMap<String, String> hashmap = new HashMap<>();
                            hashmap.put("id", userId);
                            hashmap.put("username", username);
                            hashmap.put("imageUrl", "default");

                            reference.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        listener.goFromRegisterToGroceryList(userId);
                                    }
                                }
                            });
                        }
                    }
                });
    }
}