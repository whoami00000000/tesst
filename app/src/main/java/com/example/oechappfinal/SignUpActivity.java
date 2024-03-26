package com.example.oechappfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText fullNameEditText, phoneNumberEditText, emailAddressEditText, passwordEditText, confirmPasswordEditText;
    private CheckBox termsCheckBox;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullNameEditText = findViewById(R.id.edit_text_full_name);
        phoneNumberEditText = findViewById(R.id.edit_text_phone_number);
        emailAddressEditText = findViewById(R.id.edit_text_email_address);
        passwordEditText = findViewById(R.id.edit_text_password);
        confirmPasswordEditText = findViewById(R.id.edit_text_confirm_password);
        termsCheckBox = findViewById(R.id.check_terms);
        signUpButton = findViewById(R.id.btn_signup);
        TextView signin = findViewById(R.id.signIn);





        fullNameEditText.addTextChangedListener(textWatcher);
        phoneNumberEditText.addTextChangedListener(textWatcher);
        emailAddressEditText.addTextChangedListener(textWatcher);
        passwordEditText.addTextChangedListener(textWatcher);
        confirmPasswordEditText.addTextChangedListener(textWatcher);
        termsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkFieldsForEmptyValues();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startSignIn = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(startSignIn);
            }
        });




        // Находим иконку видимости пароля и устанавливаем для нее слушатель щелчка

        TextView termsView = findViewById(R.id.termsView);
        // Используем HTML для форматирования текста
        String htmlText = "<font color=" + getResources().getColor(R.color.gray_dark) + ">By ticking this box, you agree to our</font> <font color=" + getResources().getColor(R.color.warning_color) + "><a href=\"https://www.example.com\" style=\"text-decoration:none\">Terms and conditions and private policy</a></font>";
        termsView.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));

        // Делаем ссылку кликабельной
        termsView.setMovementMethod(LinkMovementMethod.getInstance());
        termsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"));
                 startActivity(intent);
            }
        });

        ImageView passwordVisibilityToggle = findViewById(R.id.password_visibility_toggle);
        ImageView confirmPasswordVisibilityToggle = findViewById(R.id.confirm_password_visibility_toggle);



        passwordVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility((ImageView) v, R.id.edit_text_password);
            }
        });

        confirmPasswordVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility((ImageView) v, R.id.edit_text_confirm_password);
            }
        });
    }

    private void togglePasswordVisibility(ImageView imageView, int editTextId) {
        EditText editText = findViewById(editTextId);
        if (editText != null) {
            int inputType = editText.getInputType();
            if (inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageView.setImageResource(R.drawable.eyeslash); // Change icon to show hidden password
            } else {
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageView.setImageResource(R.drawable.eye); // Change icon to show visible password
            }
            editText.setSelection(editText.getText().length()); // Keep cursor at the end of the text
        }
    }


    // Метод для проверки полей на пустоту и активации кнопки
    private void checkFieldsForEmptyValues() {
        String fullName = fullNameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String emailAddress = emailAddressEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        boolean termsChecked = termsCheckBox.isChecked();

        if (!fullName.isEmpty() && !phoneNumber.isEmpty() && !emailAddress.isEmpty() &&
                !password.isEmpty() && !confirmPassword.isEmpty() && termsChecked) {
            // Если все поля заполнены и чекбокс выбран, активируем кнопку
            signUpButton.setEnabled(true);
            signUpButton.setBackgroundResource(R.drawable.primary_button_back); // Замените на вашу картинку синей кнопки
        } else {
            // Иначе делаем кнопку неактивной
            signUpButton.setEnabled(false);
            signUpButton.setBackgroundResource(R.drawable.unactive_btn); // Замените на вашу картинку неактивной кнопки
        }
    }

    // Метод для изменения видимости пароля

    // Слушатель для отслеживания изменений в полях ввода
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkFieldsForEmptyValues(); // Вызываем метод проверки полей на пустоту
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}