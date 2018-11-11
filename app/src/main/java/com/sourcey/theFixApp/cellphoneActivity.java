package com.sourcey.theFixApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class cellphoneActivity extends AppCompatActivity {


    public void goBack(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cellphone);

        ListView myListView = findViewById(R.id.mylistview);

        final ArrayList<String> myFriends = new ArrayList<String>();
        myFriends.add("Broken Screen"); myFriends.add("No Signal");
        myFriends.add("שם התקלה"); myFriends.add("שם התקלה");
        myFriends.add("שם התקלה"); myFriends.add("שם התקלה");
        myFriends.add("שם התקלה"); myFriends.add("שם התקלה");
        myFriends.add("שם התקלה"); myFriends.add("שם התקלה");myFriends.add("שם התקלה"); myFriends.add("שם התקלה");
        myFriends.add("שם התקלה"); myFriends.add("שם התקלה");
        myFriends.add("שם התקלה"); myFriends.add("שם התקלה");
        myFriends.add("שם התקלה"); myFriends.add("שם התקלה");
        myFriends.add("שם התקלה"); myFriends.add("שם התקלה");



        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, R.layout.mytextview, myFriends);

        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch( position )
                {
                    case 0:  Intent newActivity = new Intent(cellphoneActivity.this, compressorActivity.class);
                        startActivity(newActivity);
                        break;
//                    case 1:  Intent newActivity = new Intent(this, youtube.class);
//                        startActivity(newActivity);
//                        break;
//                    case 2:  Intent newActivity = new Intent(this, olympiakos.class);
//                        startActivity(newActivity);
//                        break;
//                    case 3:  Intent newActivity = new Intent(this, karaiskaki.class);
//                        startActivity(newActivity);
//                        break;
//                    case 4:  Intent newActivity = new Intent(this, reservetickets.class);
//                        startActivity(newActivity);
//                        break;
                }

            }
        });
    }
}
