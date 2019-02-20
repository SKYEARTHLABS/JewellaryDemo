package com.example.navigationbar;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.navigationbar.helpers.InputValidation;
import com.example.navigationbar.sql.DatabaseHelper;
import com.example.navigationbar.R;
import com.example.navigationbar.model.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextView textInputLayoutName;
    private TextView textInputLayoutEmail;
    private TextView textInputLayoutPassword;
    private TextView textInputLayoutConfirmPassword;

    private EditText textInputEditTextName;
    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;
    private EditText textInputEditTextConfirmPassword;

    private Button appCompatButtonRegister;

    //  private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getSupportActionBar().hide();
        Log.d("registeractivity", "inside on create");

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

      /*  textInputLayoutName = (TextView) findViewById(R.id.fullnametv);
        textInputLayoutEmail = (TextView) findViewById(R.id.usernametv);
        textInputLayoutPassword = (TextView) findViewById(R.id.passwordtv);
        textInputLayoutConfirmPassword = (TextView) findViewById(R.id.Confirmpasswordtv);*/

        textInputEditTextName = (EditText) findViewById(R.id.FullNameEtt);
        textInputEditTextEmail = (EditText) findViewById(R.id.usernameEtt);
        textInputEditTextPassword = (EditText) findViewById(R.id.passwordEt);
        textInputEditTextConfirmPassword = (EditText) findViewById(R.id.confirm_password);

        appCompatButtonRegister = (Button) findViewById(R.id.button_register);

        //       appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        //     appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_register:

                Log.d("registeractivity", "inside on create");
                postDataToSQLite();
               /* Intent intentRegister = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentRegister);*/
                break;

            case R.id.appCompatButtonRegister:
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_email)))
        {
            Toast.makeText(activity, "Enter Your Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail,getString(R.string.error_message_email)))
        {
            Toast.makeText(activity, "Enter Valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            Toast.makeText(activity, "Enter Valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            Toast.makeText(activity, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            Toast.makeText(activity, "Password Does Not Matches", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            Log.d("registeractivity", "Data: " + user.toString());
            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            //Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            Toast.makeText(activity, getString(R.string.success_message), Toast.LENGTH_SHORT).show();
            Intent intentRegister = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intentRegister);
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            // Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
            Toast.makeText(activity, "Error...", Toast.LENGTH_SHORT).show();
        }


        //}

        /**
         * This method is to empty all input edit text
         */

    }

    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }

}
