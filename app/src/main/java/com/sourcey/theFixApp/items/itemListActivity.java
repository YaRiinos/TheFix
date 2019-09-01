package com.sourcey.theFixApp.items;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sourcey.theFixApp.R;
import com.sourcey.theFixApp.authentication.LoginActivity;

import java.util.ArrayList;


public class itemListActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    private ArrayList<String> items = new ArrayList<>();

    private ListView _itemListView;

    public void goBack(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        _itemListView = findViewById(R.id.itemlist);

        final ProgressDialog progressDialog = new ProgressDialog(itemListActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Retrieving Data...");
        progressDialog.show();

        //Get the category from the last activity
        Bundle extras = getIntent().getExtras();
        final String catName = extras.getString("cat");

        //Go to that category in the database and get all the data
        mRef = FirebaseDatabase.getInstance().getReference().child("Items").child(catName);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);

        _itemListView.setAdapter(arrayAdapter);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item value = dataSnapshot.getValue(Item.class);
                assert value != null;
                items.add(value.getItemName());
                arrayAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        _itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemName = items.get(position);
                Intent intent = new Intent(itemListActivity.this, singleItemActivity.class);
                intent.putExtra("cat", catName);
                intent.putExtra("item",itemName);
                startActivity(intent);
            }
        });
    }

}
