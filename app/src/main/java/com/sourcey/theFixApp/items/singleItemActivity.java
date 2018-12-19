package com.sourcey.theFixApp.items;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sourcey.theFixApp.R;

public class singleItemActivity extends AppCompatActivity {

    private DatabaseReference mRef;


    public void goBack(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        Bundle extras = getIntent().getExtras();
        final String catName = extras.getString("cat");
        final String itemName = extras.getString("item");

        mRef = FirebaseDatabase.getInstance().getReference().child("Items").child(catName).child(itemName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();
                String cleanName = name.substring(1, name.length()-1)
                        .replace("itemName=", "").replace("itemType=", "")
                        .replace("itemPrice=","").replace("itemDesc=","")
                        .replace("itemWorkPrice=","");
                String[] itemData = cleanName.split(",");
                ((TextView) findViewById(R.id.titleItem)).setText(itemData[0]);
                ((TextView) findViewById(R.id.itemDesc)).setText(itemData[3]);
                ((TextView) findViewById(R.id.productPrice)).setText(itemData[2]);
                ((TextView) findViewById(R.id.workPrice)).setText(itemData[4]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
