package com.sourcey.theFixApp.managment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sourcey.theFixApp.MainActivity;
import com.sourcey.theFixApp.R;

public class managmentActivity extends AppCompatActivity {

    public void goBack(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment);
    }

    public void openAddItem(View view){
        Intent intent = new Intent(managmentActivity.this, addItemActivity.class);
        startActivity(intent);

    }

    public void openAddTech(View view){
        Intent intent = new Intent(managmentActivity.this, addTechActivity.class);
        startActivity(intent);

    }
}
