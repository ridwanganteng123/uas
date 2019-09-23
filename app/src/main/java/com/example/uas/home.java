package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class home extends AppCompatActivity implements RecyclerViewAdapter.OnUserClickListener {
    RecyclerView recyclerView;
    Context context;
    List<PersonBean>listPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = getApplicationContext();
        recyclerView = findViewById(R.id.rcyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupRecyclerView();
    }
    public void tambah(View view){
        Intent pindah = new Intent(this,tambah.class);
        startActivity(pindah);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupRecyclerView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        setupRecyclerView();
        }

    @Override
    protected void onStart() {
        super.onStart();
    }
    private void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(context);
        listPersonInfo = db.selectUserData();
        Toast.makeText(context, "Jumlah : "+listPersonInfo.size(), Toast.LENGTH_SHORT).show();

        RecyclerViewAdapter adapter;
        adapter = new RecyclerViewAdapter(context, listPersonInfo, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onUserClick(PersonBean currentPerson, String action) {

    }
}
