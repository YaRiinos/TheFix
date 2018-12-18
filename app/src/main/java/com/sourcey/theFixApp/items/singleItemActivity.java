package com.sourcey.theFixApp.items;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.sourcey.theFixApp.R;

public class singleItemActivity extends AppCompatActivity {


    public void goBack(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        TextView title = findViewById(R.id.textView);
        //title.setText(NameArray.get(i));

    }
}
