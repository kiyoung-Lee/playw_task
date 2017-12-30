package com.example.playwingstask.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.playwingstask.Main.Data.MainRepositoryImpl;
import com.example.playwingstask.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements MainContract.activity {

    private MainContract.presenter presenter;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MainAdapter();


        presenter = new MainPresenterImpl(new MainRepositoryImpl());
        presenter.start();
    }
}
