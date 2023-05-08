package com.soci.soci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button bttn;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();

        bttn = findViewById(R.id.registActBttn);

        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                intent=new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }





}