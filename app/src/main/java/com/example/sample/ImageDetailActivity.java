package com.example.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageDetailActivity extends AppCompatActivity {

    EditText fNameEdt;
    EditText lNameEdt;
    EditText emailEdt;
    EditText phoneEdt;
    Button buttonSubmit;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);


        image = findViewById(R.id.image_view);
        fNameEdt = findViewById(R.id.fname);
        lNameEdt = findViewById(R.id.lname);
        emailEdt = findViewById(R.id.email);
        phoneEdt = findViewById(R.id.phone);
        buttonSubmit = findViewById(R.id.submit);


        try {
            Picasso.with(this)
                    .load(getIntent().getStringExtra("image"))
                    .into(image);

        } catch (Exception e) {
            e.printStackTrace();
        }


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToServer();
            }
        });


    }

    private void sendDataToServer() {

    }
}
