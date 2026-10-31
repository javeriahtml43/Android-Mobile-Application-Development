package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvLength, tvUpper, tvLower, tvNumber, tvSpecial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editEmail = findViewById(R.id.editTextEmail);
        EditText editPassword = findViewById(R.id.editTextPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        tvLength = findViewById(R.id.tvLength);
        tvUpper = findViewById(R.id.tvUpper);
        tvLower = findViewById(R.id.tvLower);
        tvNumber = findViewById(R.id.tvNumber);
        tvSpecial = findViewById(R.id.tvSpecial);

        // Live checklist update while typing
        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        btnLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString();

            if (email.isEmpty()) {
                editEmail.setError("Please enter your email");
                return;
            }

            if (password.isEmpty()) {
                editPassword.setError("Please enter your password");
                return;
            }

            // Tell the user exactly what is missing
            String problem = getMissingMessage(password);
            if (problem != null) {
                editPassword.setError(problem);
                Toast.makeText(this, problem, Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(this, "Login Successfully Saved", Toast.LENGTH_LONG).show();
        });
    }

    // Returns the first missing rule as a message, or null if the password is strong
    private String getMissingMessage(String password) {
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        if (password.length() < 8) {
            return "Password must be at least 8 characters long";
        }
        if (!hasUpper) {
            return "Use at least one uppercase letter (A-Z)";
        }
        if (!hasLower) {
            return "Use at least one lowercase letter (a-z)";
        }
        if (!hasNumber) {
            return "Use at least one number (0-9)";
        }
        if (!hasSpecial) {
            return "Use at least one special character (@ # $ % ! _)";
        }
        return null;
    }

    // Updates the checklist ticks
    private void checkPassword(String password) {
        boolean hasLength = password.length() >= 8;
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        updateRule(tvLength, "Must be at least 8 characters", hasLength);
        updateRule(tvUpper, "One uppercase letter (A-Z)", hasUpper);
        updateRule(tvLower, "One lowercase letter (a-z)", hasLower);
        updateRule(tvNumber, "One number (0-9)", hasNumber);
        updateRule(tvSpecial, "One special character (@ # $ % !)", hasSpecial);
    }

    private void updateRule(TextView tv, String text, boolean ok) {
        if (ok) {
            tv.setText("✔  " + text);
            tv.setTextColor(Color.parseColor("#4CAF50"));
        } else {
            tv.setText("○  " + text);
            tv.setTextColor(Color.parseColor("#8FB0B5"));
        }
    }
}