package com.sourcey.theFixApp.managment;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sourcey.theFixApp.R;
import com.sourcey.theFixApp.items.Item;

import butterknife.BindView;

public class addItemActivity extends AppCompatActivity {

    private static final String TAG = addItemActivity.class.getSimpleName();

    @BindView(R.id.itemName)
    EditText _itemName;
    @BindView(R.id.itemPrice)
    EditText _itemPrice;
    @BindView(R.id.itemWorkPrice)
    EditText _itemWorkPrice;
    @BindView(R.id.desc)
    EditText _desc;
    @BindView(R.id.addButton)
    Button _add;
    Spinner spinnerCat;

    public void goBack(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        spinnerCat = findViewById(R.id.spinnerCat);

        String[] arraySpinner = new String[]{
                "Electric", "Cellphone", "Car"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerCat.setAdapter(adapter);

    }

    public void addItem(View view) {

        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'items' node
        DatabaseReference mFirebaseDatabase = mFirebaseInstance.getReference("items");


        String itemName = ((TextView) findViewById(R.id.itemName)).getText().toString();
        String itemPrice = ((TextView) findViewById(R.id.itemPrice)).getText().toString();
        String itemWorkPrice = ((TextView) findViewById(R.id.itemWorkPrice)).getText().toString();
        String itemDesc = ((TextView) findViewById(R.id.desc)).getText().toString();
        String itemType = spinnerCat.getSelectedItem().toString();

        createItem(itemName, itemPrice, itemWorkPrice, itemDesc, itemType);

    }

    private void createItem(final String itemName, final String itemPrice, final String itemWorkPrice, final String itemDesc, final String itemType) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference().child("Items");

        Item item = new Item(itemName, itemPrice, itemWorkPrice, itemDesc, itemType);
        mRef.child(itemType).child(itemName).setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(addItemActivity.this, "Done",
                        Toast.LENGTH_SHORT).show();
                ((TextView) findViewById(R.id.itemName)).setText("");
                ((TextView) findViewById(R.id.itemPrice)).setText("");
                ((TextView) findViewById(R.id.itemWorkPrice)).setText("");
                ((TextView) findViewById(R.id.desc)).setText("");
            }
        });
    }

}
