package com.sourcey.theFixApp.items;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sourcey.theFixApp.R;

public class itemListActivity extends AppCompatActivity {

    public void goBack(View view){
        finish();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Bundle extras = getIntent().getExtras();
        String catName = extras.getString("cat");

    }


}
