package com.example.cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnm1, btnm2, btnm3, btnm4;
    Intent intent;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnm1 = findViewById(R.id.btn_azkar);
        btnm2 = findViewById(R.id.btn_Adyaa);
        btnm3 = findViewById(R.id.btn_more);
        btnm4 = findViewById(R.id.btn_tasabeeh);

        btnm1.setOnClickListener(this);
        btnm2.setOnClickListener(this);
        btnm3.setOnClickListener(this);
        btnm4.setOnClickListener(this);


        /*btnm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id", btnm1.getId());
                startActivity(intent);
            }
        });
        btnm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id", btnm2.getId());
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        intent = new Intent(this, MainActivity.class);
        switch (v.getId()) {
            case R.id.btn_Adyaa: {
                intent.putExtra("id", btnm2.getId());
                startActivity(intent);
                break;
            }
            case R.id.btn_azkar: {
                intent.putExtra("id", btnm1.getId());
                startActivity(intent);
                break;
            }
            case R.id.btn_tasabeeh: {
                intent.putExtra("id", btnm4.getId());
                startActivity(intent);
                break;
            }

            default: //Toast.makeText(MainActivity.this, "ERROR ", Toast.LENGTH_SHORT).show();


        }
    }
}