package com.example.navigationbar;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigationbar.R;

public class DetailActivity extends AppCompatActivity {
    private ImageView image;
    private TextView name, price;
    private Button buttonapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        buttonapp = findViewById(R.id.buttonapp);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);

        name.setText(getIntent().getExtras().getString("key_name"));
        price.setText(getIntent().getExtras().getString("key_price"));
        image.setImageResource(getIntent().getExtras().getInt("key_image"));

                buttonapp.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                String toNumber = " 9036171120"; // contains spaces.
                String message="Completed";
                toNumber = toNumber.replace("+", "").replace(" ", "");


                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.putExtra("jid", toNumber );
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
               sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("https//web.whatsapp.com/send?phone="+ toNumber);
               sendIntent.setType("text/plain");
               startActivity(sendIntent);
                 //String webUrl="https://www.google.com";
               // Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
               // startActivity(sendIntent);
            }
       });
        }


    }

