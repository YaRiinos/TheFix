package com.sourcey.theFixApp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sourcey.theFixApp.account.Account;
import com.sourcey.theFixApp.contact.ContActivity;
import com.sourcey.theFixApp.items.Item;
import com.sourcey.theFixApp.items.categoryType;
import com.sourcey.theFixApp.managment.addItemActivity;
import com.sourcey.theFixApp.items.itemListActivity;
import com.sourcey.theFixApp.managment.managmentActivity;

import java.util.ArrayList;

import butterknife.BindView;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.elctCat)
    Button _elec;
    @BindView(R.id.carCat)
    Button _car;
    @BindView(R.id.cellCat)
    Button _cell;

    public void openItemListActivity(View view){

        String catName;

        switch(view.getId()) {
            case R.id.elctCat:
                catName = "Electric";
                break;

            case R.id.carCat:
                catName = "Car";
                break;

            case R.id.cellCat:
                catName = "Cellphone";
                break;

            default:
                throw new RuntimeException("Unknow button ID");

        }

        Intent intent = new Intent(MainActivity.this, itemListActivity.class);
        intent.putExtra("cat", catName);
        startActivity(intent);
    }

    public void openCont(View view){
        Intent intent = new Intent(MainActivity.this, ContActivity.class);
        startActivity(intent);

    }

    public void openManagerPage(View view){
        Intent intent = new Intent(MainActivity.this, managmentActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
