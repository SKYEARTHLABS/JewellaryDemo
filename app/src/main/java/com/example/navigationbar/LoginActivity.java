package com.example.navigationbar;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationbar.sql.DatabaseHelper;
import com.example.navigationbar.helpers.InputValidation;
import com.example.navigationbar.R;
import com.example.navigationbar.sql.DatabaseHelper;

 public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
     private final AppCompatActivity activity = LoginActivity.this;

     private NestedScrollView nestedScrollView;

     private TextView textInputLayoutEmail;
     private TextView textInputLayoutPassword;
     private EditText textInputEditTextEmail;
     private EditText textInputEditTextPassword;

     private Button appCompatButtonLogin, appCompatButtonRegister;

     private TextView textViewLinkRegister;
     private ProgressBar progressBar;

     private InputValidation inputValidation;
     private DatabaseHelper databaseHelper;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_login);
//         getSupportActionBar().hide();

         initViews();
         initListeners();
         initObjects();
         progressBar=(ProgressBar)findViewById(R.id.prog);
     }

     /**
      * This method is to initialize views
      */


     private void initViews() {

         nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        /* textInputLayoutEmail = findViewById(R.id.username);
         textInputLayoutPassword = findViewById(R.id.password);*/

         textInputEditTextEmail = findViewById(R.id.username);
         textInputEditTextPassword = findViewById(R.id.password);

         appCompatButtonLogin = (Button) findViewById(R.id.button_login);

         appCompatButtonRegister = (Button) findViewById(R.id.RegisterTv);

     }

     /**
      * This method is to initialize listeners
      */
     private void initListeners() {
         appCompatButtonLogin.setOnClickListener(this);
         appCompatButtonRegister.setOnClickListener(this);
     }

     /**
      * This method is to initialize objects to be used
      */
     private void initObjects() {
         databaseHelper = new DatabaseHelper(activity);
         inputValidation = new InputValidation(activity);

     }

     /**
      * This implemented method is to listen the click on view
      * button_loginbutton_loginbutton_loginbutton_loginbutton_login
      *
      * @param v
      */
     @Override
     public void onClick(View v) {
         switch (v.getId()) {
             case R.id.button_login:
                 verifyFromSQLite();
                 break;

             case R.id.RegisterTv:
                 // Navigate to RegisterActivity
                 Log.d("loginactivity", "inside textview click");
                 Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                 startActivity(intentRegister);
                 break;
         }

     }

     /**
      * This method is to validate the input text fields and verify login credentials from SQLite
      */
     private void verifyFromSQLite() {
         progressBar.setVisibility(View.VISIBLE);
         Log.d("loginactivity", "Verify Called.");


         if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, "Error...")) {
             Log.d("loginactivity", "Verify Called.1");
             Toast.makeText(activity, "Enter Valid Email", Toast.LENGTH_SHORT).show();
             return;
         }
         if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, "Error..")) {
             Toast.makeText(activity, "Enter Valid Email", Toast.LENGTH_SHORT).show();
             Log.d("loginactivity", "Verify Called.2");
             return;
         }
         if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, "Error")) {
             Log.d("loginactivity", "Verify Called.3");
             Toast.makeText(activity, "Enter Valid Password", Toast.LENGTH_SHORT).show();
             return;
         }

         if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                 , textInputEditTextPassword.getText().toString().trim())) {

             Log.d("loginactivity", "Verify Called.4");
             Intent accountsIntent = new Intent(activity, MainActivity.class);
             accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
              emptyInputEditText();
             startActivity(accountsIntent);


         } else {
             Log.d("registeractivity", "Record is wrong");
             // Snack Bar to show success message that record is wrong
             //Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
             progressBar.setVisibility(View.GONE);
             Toast.makeText(activity, "Enter Valid Email or Password", Toast.LENGTH_SHORT).show();

         }


         /**
          * This method is to empty all input edit text
          */

     }

     private void emptyInputEditText() {
         textInputEditTextEmail.setText(null);
         textInputEditTextPassword.setText(null);
     }
 }