package com.example.oechappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForgotActivity extends AppCompatActivity {
    private Button sendOTPbtn;
    private EditText forgotEmailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        forgotEmailField = findViewById(R.id.forgotEmailEdit);
        sendOTPbtn = findViewById(R.id.sendOTPbtn);
        TextView signInTxt = findViewById(R.id.signInTxt);

        forgotEmailField.addTextChangedListener(textWatcher);





        sendOTPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startOTP = new Intent(ForgotActivity.this, OTPActivity.class);
                startActivity(startOTP);
            }
        });

        signInTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startSignIn = new Intent(ForgotActivity.this, SignInActivity.class);
                startActivity(startSignIn);
            }
        });
    }


    private void checkEmailField(){
        if (forgotEmailField != null){
            sendOTPbtn.setEnabled(true);
            sendOTPbtn.setBackgroundResource(R.drawable.primary_button_back);
        }else{
            sendOTPbtn.setEnabled(false);
            sendOTPbtn.setBackgroundResource(R.drawable.unactive_btn);
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkEmailField();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}