package com.example.oechappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewPasswordActivity extends AppCompatActivity {

    Button logInBtn;
    EditText pswField, confirmPswField;
    ImageView pswVisibilityToggle, confirmPswVisibilityToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);


        logInBtn = findViewById(R.id.newPswlogInBtn);

        pswField = findViewById(R.id.edit_text_new_password);
        confirmPswField = findViewById(R.id.edit_text_confirm_new_password);

        pswVisibilityToggle = findViewById(R.id.new_password_visibility_toggle);
        confirmPswVisibilityToggle = findViewById(R.id.confirm_new_password_visibility_toggle);


        pswField.addTextChangedListener(textWatcher);
        confirmPswField.addTextChangedListener(textWatcher);


        pswVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityToggle((ImageView) v, R.id.edit_text_new_password);
            }
        });

        confirmPswVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityToggle((ImageView) v, R.id.edit_text_confirm_new_password);
            }
        });


        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startLogIn = new Intent(NewPasswordActivity.this, SignInActivity.class);
                startActivity(startLogIn);
            }
        });
    }

    private void setVisibilityToggle(ImageView toggle, int fieldId){
        EditText field = findViewById(fieldId);
        if (field != null){
            int inputType = field.getInputType();
            if(inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                field.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                toggle.setImageResource(R.drawable.eyeslash);
            }else{
                field.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                toggle.setImageResource(R.drawable.eye);
            }
            field.setSelection(field.getText().length());
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkFields();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void checkFields(){
        String pswFieldText = pswField.getText().toString();
        String confirmPswFieldText = confirmPswField.getText().toString();

        if (!pswFieldText.isEmpty() && !confirmPswFieldText.isEmpty()){
            logInBtn.setBackgroundResource(R.drawable.primary_button_back);
            logInBtn.setEnabled(true);
        }else{
            logInBtn.setBackgroundResource(R.drawable.unactive_btn);
            logInBtn.setEnabled(false);
        }
    }

}