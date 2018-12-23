package com.sourcey.theFixApp.account.profile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sourcey.theFixApp.MainActivity;
import com.sourcey.theFixApp.R;
import com.sourcey.theFixApp.authentication.SignupActivity;

public class ProfileActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mRef;


    public void goBack(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        mRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();
                String cleanName = name.substring(1, name.length()-1)
                        .replace("Name=", "").replace("Address=", "")
                        .replace("Email=","").replace("Mobile=","");
                String[] itemData = cleanName.split(",");
                ((TextView) findViewById(R.id.profileEmail)).setText(itemData[0]);
                ((TextView) findViewById(R.id.profileName)).setText(itemData[3]);
                ((TextView) findViewById(R.id.profileMobile)).setText(itemData[2]);
                ((TextView) findViewById(R.id.profileAddress)).setText(itemData[1]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void openMain(View view){
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
