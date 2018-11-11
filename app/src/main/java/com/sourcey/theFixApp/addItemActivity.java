package com.sourcey.theFixApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class addItemActivity extends AppCompatActivity {

    Spinner spinnerCat;

    public void goBack(View view){
       finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        spinnerCat = findViewById(R.id.spinnerCat);

        String[] arraySpinner = new String[] {
                "Electric", "Cellphone", "Car"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerCat.setAdapter(adapter);
    }
}
