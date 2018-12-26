package com.sourcey.theFixApp.items;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sourcey.theFixApp.R;
import com.sourcey.theFixApp.maps.findTecMapsActivity;

public class singleItemActivity extends AppCompatActivity {

    private DatabaseReference mRef, mRefUser;
    Dialog updatePriceDialog;
    String [] itemData;
    private String catName, itemName;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public void goBack(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        Bundle extras = getIntent().getExtras();
        catName = extras.getString("cat");
        itemName = extras.getString("item");

        mRef = FirebaseDatabase.getInstance().getReference().child("Items").child(catName).child(itemName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();
                String cleanName = name.substring(1, name.length()-1)
                        .replace("itemName=", "").replace("itemType=", "")
                        .replace("itemPrice=","").replace("itemDesc=","")
                        .replace("itemWorkPrice=","");
                itemData = cleanName.split(",");
                ((TextView) findViewById(R.id.titleItem)).setText(itemData[0]);
                ((TextView) findViewById(R.id.itemDesc)).setText(itemData[3]);
                ((TextView) findViewById(R.id.productPrice)).setText(itemData[2]);
                ((TextView) findViewById(R.id.workPrice)).setText(itemData[4]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void updatePriceCustomPopup(View view){

        updatePriceDialog = new Dialog(singleItemActivity.this);
        updatePriceDialog.setContentView(R.layout.updatepricecustompop);
        updatePriceDialog.setTitle("Update Price");

        updatePriceDialog.show();

    }

    public void closeUpdatePopup(View view){
        updatePriceDialog.cancel();
    }

    public void updateItemPrice(View view){

        final EditText updatedItemCost = (EditText)updatePriceDialog.findViewById(R.id.itemNewPrice);
        final EditText updatedItemWorkCost = (EditText)updatePriceDialog.findViewById(R.id.workNewPrice);

        String updatedItemCostString = updatedItemCost.getText().toString();
        String updatedItemWorkCostString = updatedItemWorkCost.getText().toString();

        if (TextUtils.isEmpty(updatedItemCostString) || TextUtils.isEmpty(updatedItemWorkCostString)) {
            Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mRef.child("itemPrice").setValue(updatedItemCost.getText().toString());
        mRef.child("itemWorkPrice").setValue(updatedItemWorkCost.getText().toString());

        mRefUser = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("Points");
        mRefUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userPoints = dataSnapshot.getValue().toString();
                mRefUser.setValue(Double.parseDouble(userPoints) + 3.2);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        updatePriceDialog.cancel();
    }


    public void replaceItemCustomPopup(View view){

        updatePriceDialog = new Dialog(singleItemActivity.this);
        updatePriceDialog.setContentView(R.layout.replaceproductcustompop);
        updatePriceDialog.setTitle("Replace Item");

        updatePriceDialog.show();

    }

    public void closeReplaceItemPopup(View view){
        updatePriceDialog.cancel();
    }

    public void searchNewItem(View view){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        String keyword= itemName + " zap";
        intent.putExtra(SearchManager.QUERY, keyword);
        startActivity(intent);
    }

    public void findTechAround(View view){
        startActivity(new Intent(getApplicationContext(), findTecMapsActivity.class));
    }
}
