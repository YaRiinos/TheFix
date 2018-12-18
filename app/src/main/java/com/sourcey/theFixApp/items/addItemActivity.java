package com.sourcey.theFixApp.items;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sourcey.theFixApp.R;

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
    private String itemId;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

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

        _add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

    }

    public void addItem() {

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'items' node
        mFirebaseDatabase = mFirebaseInstance.getReference("items");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("Items Database");

        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });


        String itemName = _itemName.getText().toString();
        String itemPrice = _itemPrice.getText().toString();
        String itemWorkPrice = _itemWorkPrice.getText().toString();
        String itemDesc = _desc.getText().toString();
        String itemType = spinnerCat.getSelectedItem().toString();


        // Check for already existed itemId
        if (TextUtils.isEmpty(itemId)) {
            createItem(itemName, itemPrice, itemWorkPrice, itemDesc, itemType);
        }
//        else {
//            updateItem(itemName, itemPrice, itemWorkPrice, itemDesc, itemType);
//        }


        toggleButton();
    }

    private void createItem(String itemName, String itemPrice, String itemWorkPrice,
                            String itemDesc, String itemType) {
        // TODO
        // In real apps this itemId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(itemId)) {
            itemId = mFirebaseDatabase.push().getKey();
        }

        Item newItem = new Item(itemName, itemPrice, itemWorkPrice, itemDesc, itemType);

        mFirebaseDatabase.child(itemId).setValue(newItem);

        addItemChangeListener();
    }

    /**
     * Item data change listener
     */
    private void addItemChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(itemId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Item item = dataSnapshot.getValue(Item.class);

                // Check for null
                if (item == null) {
                    Log.e(TAG, "Item data is null!");
                    return;
                }

                Log.e(TAG, "Item data is changed!");

                // clear edit text
                _desc.setText("");
                _itemName.setText("");
                _itemPrice.setText("");
                _itemWorkPrice.setText("");

                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read item", error.toException());
            }
        });
    }

//    private void updateItem(String itemName, String itemPrice,
//                            String itemWorkPrice, String itemDesc, String itemType) {
//    }

    private void toggleButton() {
        if (TextUtils.isEmpty(itemId)) {
            _add.setText("Save");
        } else {
            _add.setText("Update");
        }
    }

}
