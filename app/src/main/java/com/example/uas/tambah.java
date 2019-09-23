package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tambah extends AppCompatActivity {
   private EditText edtjudul, edtdeskripsi;
   private Button btn_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);


        edtjudul = findViewById(R.id.edtjudul);
        edtdeskripsi = findViewById(R.id.edtdeskripsi);
        btn_tambah = findViewById(R.id.btnsubmit);

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper d = new DatabaseHelper(getApplicationContext());
                PersonBean p = new PersonBean();

                String judul = edtjudul.getText().toString();
                String deskripsi = edtdeskripsi.getText().toString();


                if (judul.isEmpty()|| deskripsi.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Isi" ,Toast.LENGTH_LONG).show();

                }else {

                    p.setJudul(judul);
                    p.setDeskripsi(deskripsi);

                    d.insert(p);
                    Toast.makeText(getApplicationContext()," Data "+p.getJudul() +" Masuk ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    }
