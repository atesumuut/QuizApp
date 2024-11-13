package com.example.bilgi_yarismasi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.xml.transform.Result;

public class KolayActivity extends AppCompatActivity {

    DataHelper dataHelper;
    TextView sorularr, puann, isim_oyun, gecsayi;
    ImageButton dogruq, yanlisq, anasayfag;

    RelativeLayout gec;
    int gecc;

    Random r = new Random();
    int n;
    int points;
    int point = 0;
    int SKIP_NUMBER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolay);

        dataHelper = new DataHelper(this);
        sorularr = (TextView) findViewById(R.id.sorular);
        gecsayi = (TextView) findViewById(R.id.gecsayi);
        isim_oyun = (TextView) findViewById(R.id.isim_oyun);
        puann = (TextView) findViewById(R.id.puan);
        dogruq = (ImageButton) findViewById(R.id.dogruq);
        yanlisq = (ImageButton) findViewById(R.id.yanlisq);
        anasayfag = (ImageButton) findViewById(R.id.anasayfayagit);
        gec = (RelativeLayout) findViewById(R.id.gec);

        gecsayi.setText(""+dataHelper.receiveDataInt("Geç",SKIP_NUMBER));
        anasayfag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KolayActivity.this,MainActivity.class));
                finish();
            }
        });
        isim_oyun.setText(dataHelper.receiveDataString("İsim","Kullanici"));

        //SORULAR
        final String[] arrayQ = {
                getString(R.string.k1),getString(R.string.k2),getString(R.string.k3),
                getString(R.string.k4),getString(R.string.k5),getString(R.string.k6),
                getString(R.string.k7),getString(R.string.k8),getString(R.string.k9),
                getString(R.string.k10)};
        final Boolean[] arrayA = {false,true,false,true,true,true,true,false,true,false};

        final ArrayList<String>sorular = new ArrayList<String>(Arrays.asList(arrayQ));
        final ArrayList<Boolean>cevaplar = new ArrayList<Boolean>(Arrays.asList(arrayA));
        //SORULAR

        n = r.nextInt(sorular.size());
        sorularr.setText(sorular.get(n));

        gec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gecsayi.setText(""+dataHelper.receiveDataInt("Geç",SKIP_NUMBER));
                gecc = dataHelper.receiveDataInt("Geç", SKIP_NUMBER);
                if(dataHelper.receiveDataInt("Geç", SKIP_NUMBER) == 0){
                    Toast.makeText(KolayActivity.this,"Geçme hakkınız kalmadı",Toast.LENGTH_SHORT).show();
                    SKIP_NUMBER++;
                } else{
                    sorular.remove(n);
                    cevaplar.remove(n);
                    if (sorular.size() == 0){
                        result();
                    } else {
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                        dataHelper.saveDataInt("Geç",gecc);
                    }
                }
            }
        });

        dogruq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cevaplar.get(n)){
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor: "+points);
                    if(sorular.size() == 0){
                        result();
                    } else{
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                    }
                } else{
                    result();
                }
            }
        });

        yanlisq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cevaplar.get(n)){
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor: "+points);
                    if(sorular.size() == 0){
                        result();
                    } else{
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                    }
                } else{
                    result();
                }
            }
        });
    }

    private void result() {
        dataHelper.saveDataInt("PUAN Kolay", points);
        startActivity(new Intent(KolayActivity.this, SonucActivity.class));
    }
}