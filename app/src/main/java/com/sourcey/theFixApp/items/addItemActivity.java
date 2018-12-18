package com.sourcey.theFixApp.items;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
    private FirebaseAuth auth;
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

    }

    public void addItem(View view) {

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'items' node
        mFirebaseDatabase = mFirebaseInstance.getReference("items");


        String itemName = ((TextView) findViewById(R.id.itemName)).getText().toString();
        String itemPrice = ((TextView) findViewById(R.id.itemPrice)).getText().toString();
        String itemWorkPrice = ((TextView) findViewById(R.id.itemWorkPrice)).getText().toString();
        String itemDesc = ((TextView) findViewById(R.id.desc)).getText().toString();
        String itemType = spinnerCat.getSelectedItem().toString();

        createItem(itemName, itemPrice, itemWorkPrice, itemDesc, itemType);

    }

    private void createItem(String itemName, String itemPrice, String itemWorkPrice, String itemDesc, String itemType) {
        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        DatabaseReference mRef =  database.getReference().child("Items");
        Item item = new Item(itemName, itemPrice, itemWorkPrice, itemDesc, itemType);

        mRef.setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(addItemActivity.this, "Done",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


//    private void createItem(String itemName, String itemPrice, String itemWorkPrice,
//                            String itemDesc, String itemType) {
//        itemId = mFirebaseDatabase.push().getKey();
//        if (TextUtils.isEmpty(itemId)) {
//            itemId = mFirebaseDatabase.push().getKey();
//        }
//
//        Item newItem = new Item(itemName, itemPrice, itemWorkPrice, itemDesc, itemType);
//
//        mFirebaseDatabase.child(itemId).setValue(newItem);
//
//        //addItemChangeListener();
//    }
//
//    /**
//     * Item data change listener
//     */
//    private void addItemChangeListener() {
//        // User data change listener
//        mFirebaseDatabase.child(itemId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Item item = dataSnapshot.getValue(Item.class);
//
//                // Check for null
//                if (item == null) {
//                    Log.e(TAG, "Item data is null!");
//                    return;
//                }
//
//                Log.e(TAG, "Item data is changed!");
//
//                // clear edit text
//                _desc.setText("");
//                _itemName.setText("");
//                _itemPrice.setText("");
//                _itemWorkPrice.setText("");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.e(TAG, "Failed to read item", error.toException());
//            }
//        });
//    }
//
//    private void updateItem(String itemName, String itemPrice,
//                            String itemWorkPrice, String itemDesc, String itemType) {
//    }


}
