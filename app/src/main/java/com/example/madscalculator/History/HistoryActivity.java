package com.example.madscalculator.History;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madscalculator.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView rcvHistoryList;
    AppCompatTextView textNoData;
    ArrayList<HistoryData> historyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Type type = new TypeToken<ArrayList<HistoryData>>() {
        }.getType();
        historyData = new Gson().fromJson(getIntent().getStringExtra("history_data_list"), type);
        textNoData = findViewById(R.id.tv_no_history_data);
        textNoData.setVisibility(View.GONE);
        rcvHistoryList = findViewById(R.id.rcv_history_list);
        if (historyData != null && historyData.size() != 0) {
            HistoryAdapter historyAdapter = new HistoryAdapter(HistoryActivity.this, historyData);
            rcvHistoryList.setHasFixedSize(true);
            rcvHistoryList.setLayoutManager(new LinearLayoutManager(this));
            rcvHistoryList.setAdapter(historyAdapter);
        } else {
            textNoData.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
