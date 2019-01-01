package com.sourcey.theFixApp.account.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.text.DecimalFormat;

public class ProfileActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mRef;


    //When press back finish the activity
    public void goBack(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Progress dialog for loading the user data
        final ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading Information...");
        progressDialog.show();



        //Go to the user location in the database
        mRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Get the string that represent the user
                String name = dataSnapshot.getValue().toString();
                String cleanName = name.substring(1, name.length()-1)
                        .replace("Name=", "").replace("Address=", "")
                        .replace("Email=","").replace("Mobile=","")
                        .replace("Points=", "").replace("Role=","");
                String[] itemData = cleanName.split(",");

                //Set all the user data in the fields
                ((TextView) findViewById(R.id.profileEmail)).setText(itemData[2]);
                ((TextView) findViewById(R.id.profileName)).setText(itemData[5]);
                ((TextView) findViewById(R.id.profileMobile)).setText(itemData[4]);
                ((TextView) findViewById(R.id.profileAddress)).setText(itemData[3]);
                ((TextView) findViewById(R.id.profilePoints)).setText(itemData[1]);

                //After loading the data release the progress dialog
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //When press Back open the main activity
    public void openMain(View view){
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
