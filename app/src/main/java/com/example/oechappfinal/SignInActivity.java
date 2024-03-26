package com.example.oechappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText pswField, emailField;
    private Button logInBtn;
    private TextView signup;
    private CheckBox remCheck;
    private ImageView visibilityBnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        visibilityBnt = findViewById(R.id.pswVisibilityToggle);
        pswField = findViewById(R.id.pswEdit);
        emailField = findViewById(R.id.emailEdit);
        remCheck = findViewById(R.id.rememberCheckbox);
        logInBtn = findViewById(R.id.logInBtn);
        signup = findViewById(R.id.signUpTxt);



        emailField.addTextChangedListener(textWatcher);
        pswField.addTextChangedListener(textWatcher);



        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startLogIn = new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(startLogIn);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startSignUp = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(startSignUp);
            }
        });

        visibilityBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pswField = findViewById(R.id.pswEdit);
                if (pswField != null) {
                    int inputType = pswField.getInputType();
                    if (inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        pswField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        visibilityBnt.setImageResource(R.drawable.eyeslash); // Change icon to show hidden password
                    } else {
                        pswField.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        visibilityBnt.setImageResource(R.drawable.eye); // Change icon to show visible password
                    }

                }
            }
        });

        TextView forgotTxt = findViewById(R.id.forgotTxt);
        forgotTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotActivity = new Intent(SignInActivity.this, ForgotActivity.class);
                startActivity(forgotActivity);
            }
        });
    }


    private void checkFieldsNotEmpty(){
        String email = emailField.getText().toString();
        String password = pswField.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()){
            logInBtn.setEnabled(true);
            logInBtn.setBackgroundResource(R.drawable.primary_button_back);
        } else {
            logInBtn.setEnabled(false);
            logInBtn.setBackgroundResource(R.drawable.unactive_btn);
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkFieldsNotEmpty();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}