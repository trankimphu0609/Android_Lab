package com.example.random;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtNumber;
    Button btnRandom;
    EditText txtnumber1, txtnumber2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chuoi1 = txtnumber1.getText().toString().trim();
                String chuoi2 = txtnumber2.getText().toString().trim();
                if(chuoi1.length() == 0 || chuoi2.length() == 0) {
                    Toast.makeText(MainActivity.this, "Nhập thiếu", Toast.LENGTH_SHORT).show();
                }
                else {
                    //ep kieu du lieu chuoi -> so
                    int min = Integer.parseInt(chuoi1); // "12" -> 12
                    int max = Integer.parseInt(chuoi2);
                    if(min < max) {
                        Random random = new Random();
                        int number = random.nextInt(max - min + 1) + min; //(10-5+1)+5
                        //so -> chuoi
                        txtNumber.setText(String.valueOf(number));
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Nhập lại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void AnhXa() {
        txtNumber = (TextView) findViewById(R.id.textViewNumber);
        btnRandom = (Button) findViewById(R.id.buttonRandom);
        txtnumber1 = (EditText) findViewById(R.id.txtnumber1);
        txtnumber2 = (EditText) findViewById(R.id.txtnumber2);
    }
}