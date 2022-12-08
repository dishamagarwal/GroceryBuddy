package com.example.grocerybuddy;
import com.example.grocerybuddy.Model.Users;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class Settings extends Fragment {

    private Settings.Listener listener;
    ImageView profile_pic;
    TextView email;
    EditText name;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    Button logout;

    public Settings() {
        // Required empty public constructor
    }

    public interface Listener {
        void goFromSettingsToHome();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Settings.Listener) {
            listener = (Settings.Listener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement Listener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_settings, container, false);
        //profile_pic = rootView.findViewById(R.id.imageView_profile);
        email = rootView.findViewById(R.id.email_textView);
        name = rootView.findViewById(R.id.name_textView);
        logout = rootView.findViewById(R.id.button_logout);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users =snapshot.getValue(Users.class);
                name.setText(users.getName());
                if (users.getImageUrl().equals("default")) {
                    profile_pic.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(Settings.this).load(users.getImageUrl()).into(profile_pic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                listener.goFromSettingsToHome();
            }
        });
        return rootView;
    }
}