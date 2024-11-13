package com.example.bilgi_yarismasi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText username;
    RelativeLayout kolay,orta,zor;
    TextView isim;
    ImageView isimduz;
    DataHelper dataHelper;

    private Veritabani v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataHelper = new DataHelper(this);

        kolay = (RelativeLayout) findViewById(R.id.kolay);
        orta = (RelativeLayout) findViewById(R.id.orta);
        zor = (RelativeLayout) findViewById(R.id.zor);
        isimduz = (ImageView) findViewById(R.id.kadi_duzenle);
        isim = (TextView) findViewById(R.id.kullaniciadi);

        isimduz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });


        kolay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KolayActivity.class));
            }
        });

        orta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OrtaActivity.class));
            }
        });

        zor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ZorActivity.class));
            }
        });
    }

    public void alertDialog(){
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.uyaripenceresi,null);
        final EditText name = (EditText) view.findViewById(R.id.name);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Kullanıcı Adınızı Giriniz")
            .setView(view)
            .setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String s = name.getText().toString();
                    dataHelper.saveDataString("İsim", s);
                    isim.setText(dataHelper.receiveDataString("İsim", "Kullanıcı"));
                }
            });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}