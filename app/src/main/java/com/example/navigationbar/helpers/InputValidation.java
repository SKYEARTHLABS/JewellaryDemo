package com.example.navigationbar.helpers;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationbar.RegisterActivity;


 public class InputValidation {
    private Context context;


     /**
     * constructor
     *
     * @param context
     */
    public InputValidation(Context context) {
        this.context = context;
    }

    /**
     * method to check InputEditText filled .
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */
    public boolean isInputEditTextFilled(EditText textInputEditText, TextView textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            //textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
      //      textInputLayout.setError("Error");
            return true;
        }


    }


    /**
     * method to check InputEditText has valid email .
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */
    public boolean isInputEditTextEmail(EditText textInputEditText, TextView textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if ( value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
           // textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
        //    textInputLayout.setError("Error...");
            return true;
        }

    }

    public boolean isInputEditTextMatches(EditText textInputEditText1, EditText textInputEditText2, TextView textInputLayout, String message) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
       //     textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        } else {
          //  textInputLayout.setError("Error..");
            return true;
        }

    }

    /**
     * method to Hide keyboard
     *
     * @param view
     */
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
