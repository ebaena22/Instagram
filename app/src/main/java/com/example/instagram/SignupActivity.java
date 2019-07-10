package com.example.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    private Button signupButton;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText handleInput;
    private EditText emailInput;
    private EditText phoneInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupButton = findViewById(R.id.signup_btn);
        usernameInput = findViewById(R.id.et_username);
        passwordInput = findViewById(R.id.et_password);
        handleInput = findViewById(R.id.et_handle);
        emailInput = findViewById(R.id.et_email);
        phoneInput = findViewById(R.id.et_phone);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();
                final String handle = handleInput.getText().toString();
                final String email = emailInput.getText().toString();
                final String phone = phoneInput.getText().toString();
                // Create the ParseUser
                ParseUser user = new ParseUser();
                // Set core properties
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.put("handle", handle);
                user.put("phoneNumber", phone);
                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.d("SignupActivity", "Sign up successful");
                            final Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.e("LoginActivity", "Sign up failure.");
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
