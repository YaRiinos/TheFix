package com.sourcey.theFixApp.contact;

import android.content.Context;
import android.content.Intent;
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

    public Intent getSendEmailIntent(Context context, String email, String subject, String body) {

        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        try {
            // Explicitly only use Gmail to send
            emailIntent.setClassName("com.google.android.gm",
                    "com.google.android.gm.ComposeActivityGmail");
            emailIntent.setType("text/html");

            // Add the recipients
            if (email != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[] {email});

            if (subject != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        subject);

            if (body != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body));

            return emailIntent;
            //          myContext.startActivity(emailIntent);
        } catch (Exception e) {
            emailIntent.setType("text/html");

            // Add the recipients
            if (email != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[] { email });

            if (subject != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        subject);

            if (body != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body));

            //          myContext.startActivity(Intent.createChooser(emailIntent,
            //                  "Share Via"));
            return emailIntent;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cont);

        Button sendButton = findViewById(R.id.sendButton);
        backImage = findViewById(R.id.backImage);
        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        messageText = findViewById(R.id.messageText);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = "Name: " + nameText.getText().toString() + "\nEmail: " +
                        emailText.getText().toString() + "\nMessage: " + messageText.getText().toString();

                startActivity(getSendEmailIntent(context, "yarin665@gmail.com" ,"Contact Us Mail", message));

            }
        });
    }
}
