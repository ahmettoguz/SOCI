package com.soci.soci;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity {

    // UI widgets to handle
    Button bSubmit;
    EditText mEditText;
    TextView tvTextPreview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Register the UI widgets
        // with their appropriate IDs.
        bSubmit = findViewById(R.id.submit_button);
        mEditText = findViewById(R.id.edit_text);
        tvTextPreview = findViewById(R.id.text_preview);

        // handle submit button to preview the entered data
//        bSubmit.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onClick(View v) {
//                // set the entered data to text preview
//                tvTextPreview.setText("You Entered : " + mEditText.getText().toString());
//            }
//        });
    }
}