package com.example.bilgi_yarismasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SonucActivity extends AppCompatActivity {

    DataHelper dataHelper;
    TextView olanpuan, eniyi;
    Button anasay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        dataHelper = new DataHelper(this);

        olanpuan = (TextView) findViewById(R.id.yapilanpaun);
        eniyi = (TextView) findViewById(R.id.eniyiskor);
        anasay = (Button) findViewById(R.id.home);

        int puan = dataHelper.receiveDataInt("PUAN Kolay", 0);
        int eni = dataHelper.receiveDataInt("En İyi Kolay", 0);

        olanpuan  = (TextView) findViewById(R.id.yapilanpaun);
        eniyi = (TextView) findViewById(R.id.eniyiskor);
        olanpuan.setText(""+puan);

        if(puan> eni){
            eni = puan;
            dataHelper.saveDataInt("En iyi Kolay", eni);
        }
        eniyi.setText(""+eni);

        anasay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SonucActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}