package com.example.oechappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;

public class OTPActivity extends AppCompatActivity {

    private String OTPcode;
    TextView resendTimerBtn;
    EditText field1, field2, field3, field4, field5, field6;
    Button setPsw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        OTPcode = "222222";

        resendTimerBtn = findViewById(R.id.resendTimer);
        setPsw = findViewById(R.id.setPsw);
        field1 = findViewById(R.id.field1);
        field2 = findViewById(R.id.field2);
        field3 = findViewById(R.id.field3);
        field4 = findViewById(R.id.field4);
        field5 = findViewById(R.id.field5);
        field6 = findViewById(R.id.field6);

        setupTextListenerFields(field1, field2);
        setupTextListenerFields(field2, field3);
        setupTextListenerFields(field3, field4);
        setupTextListenerFields(field4, field5);
        setupTextListenerFields(field5, field6);
        setupTextListenerFields(field6, field6);
        checkActiveBtn();
        resendTimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //здесь отправка кода
                resendTimerBtn.setEnabled(false);
                resendTimerBtn.setTextColor(getColor(R.color.gray_dark));
                OTPcode = "555555";
                resendTimer.start();
            }
        });
        setPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getOTPcode()){
                    Intent startNewPsw = new Intent(OTPActivity.this, NewPasswordActivity.class);
                    startActivity(startNewPsw);
                }else {
                    field1.setBackgroundResource(R.drawable.otp_field_err);
                    field2.setBackgroundResource(R.drawable.otp_field_err);
                    field3.setBackgroundResource(R.drawable.otp_field_err);
                    field4.setBackgroundResource(R.drawable.otp_field_err);
                    field5.setBackgroundResource(R.drawable.otp_field_err);
                    field6.setBackgroundResource(R.drawable.otp_field_err);
                }
            }
        });
    }
    CountDownTimer resendTimer = new CountDownTimer(61000, 1000) {
        @Override
        public void onTick(long l) {
            long mm = l/1000/60;
            long ss = l/1000%60;
            String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", mm, ss);
            resendTimerBtn.setText(" resend after " + timeLeft);
        }
        @Override
        public void onFinish() {
            resendTimerBtn.setText("resend");
            resendTimerBtn.setEnabled(true);
            resendTimerBtn.setTextColor(getResources().getColor(R.color.primary));
        }
    }.start();
    private void setupTextListenerFields(final EditText current_field, final EditText next_field){
        current_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    current_field.setBackgroundResource(R.drawable.otp_field_enabled);
                    if (next_field != null){
                        next_field.requestFocus();
                    }
                }else {
                    current_field.setBackgroundResource(R.drawable.otp_field_disabled);
                }
                checkActiveBtn();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    private void checkActiveBtn(){
        String code = getFieldText(field1) + getFieldText(field2) + getFieldText(field3) + getFieldText(field4) + getFieldText(field5) + getFieldText(field6);
        if (code.length() == 6){
            setPsw.setBackgroundResource(R.drawable.primary_button_back);
            setPsw.setEnabled(true);
        }else {
            setPsw.setBackgroundResource(R.drawable.unactive_btn);
            setPsw.setEnabled(false);
        }
    }
    private String getFieldText(EditText field){
      return field.getText().toString().trim();
    }

    private boolean getOTPcode(){
        String code = getFieldText(field1) + getFieldText(field2) + getFieldText(field3) + getFieldText(field4) + getFieldText(field5) + getFieldText(field6);
        return code.equals(OTPcode);
    }
}