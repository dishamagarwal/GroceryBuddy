package com.example.grocerybuddy;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import okhttp3.OkHttpClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogIn# newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogIn extends Fragment implements View.OnClickListener {
    private LogIn.Listener listener;
    private EditText text_email, text_password;
    private Button btn_login;
    private String email, password;
    FirebaseAuth auth;

    public LogIn() {
        // Required empty public constructor
    }

    private final OkHttpClient client = new OkHttpClient();

    public interface Listener {
        void goFromLogInToGroceryList(String userId);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LogIn.Listener) {
            listener = (LogIn.Listener) context;
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

        auth = FirebaseAuth.getInstance();
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_ica8_login) {
            email = text_email.getText().toString();
            password = text_password.getText().toString();
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser firebaseUser = auth.getCurrentUser();
                                    listener.goFromLogInToGroceryList(firebaseUser.getUid());
                                } else {
                                    Toast.makeText(getContext(), "Authentication failed:(", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }
}