package com.sourcey.theFixApp.contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sourcey.theFixApp.R;

public class ContActivity extends AppCompatActivity {
    TextView nameText, emailText, messageText;
    Context context;
    ImageView backImage;

    public void goBack(View view){
        finish();
    }

    private void sendFeedback() {

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        messageText = findViewById(R.id.messageText);

        final Intent _Intent = new Intent(android.content.Intent.ACTION_SEND);
        _Intent.setType("text/html");
        _Intent.putExtra(android.content.Intent.EXTRA_EMAIL,  new String[] {"thefix-support@fix.com"});
        _Intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Feedback - The Fix");
        _Intent.putExtra(android.content.Intent.EXTRA_TEXT, messageText.getText());
        startActivity(Intent.createChooser(_Intent, "Feedback From " + nameText.getText()));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cont);

        Button sendButton = findViewById(R.id.sendButton);
        backImage = findViewById(R.id.backImage);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });
    }
}
