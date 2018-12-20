package com.sourcey.theFixApp.managment;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sourcey.theFixApp.R;
import com.sourcey.theFixApp.account.Technician;

public class addTechActivity extends AppCompatActivity {

    Button _add;
    Spinner spinnerCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tech);

        spinnerCat = findViewById(R.id.spinnerCatTech);

        String[] arraySpinner = new String[]{
                "Electric", "Cellphone", "Car"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerCat.setAdapter(adapter);
    }


    public void addTech(View view) {

        String _techName = ((TextView) findViewById(R.id.techName)).getText().toString();
        String _techArea = ((TextView) findViewById(R.id.techArea)).getText().toString();
        String _techPhone = ((TextView) findViewById(R.id.techPhone)).getText().toString();
        String _techMail = ((TextView) findViewById(R.id.techEmail)).getText().toString();
        String techType = spinnerCat.getSelectedItem().toString();

        createTech(_techName, _techArea, _techPhone, _techMail, techType);

    }

    private void createTech(final String _techName, final String _techArea, final String _techPhone,
                            final String _techMail, final String techType) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference().child("Technician");

        Technician technician = new Technician("",_techName,_techArea,_techMail,_techPhone,"");
        mRef.child(techType).child(_techName).setValue(technician).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(addTechActivity.this, "Done",
                        Toast.LENGTH_SHORT).show();
                ((TextView) findViewById(R.id.techName)).setText("");
                ((TextView) findViewById(R.id.techArea)).setText("");
                ((TextView) findViewById(R.id.techPhone)).setText("");
                ((TextView) findViewById(R.id.techEmail)).setText("");
            }
        });
    }
}
