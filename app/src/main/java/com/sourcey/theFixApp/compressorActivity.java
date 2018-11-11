package com.sourcey.theFixApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class compressorActivity extends AppCompatActivity {


    public void goBack(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compressor);

    }
}
