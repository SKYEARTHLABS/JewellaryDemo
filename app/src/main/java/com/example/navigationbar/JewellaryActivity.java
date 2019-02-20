package com.example.navigationbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


public class JewellaryActivity extends AppCompatActivity {
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jewellary);
        Log.d("jewellaryactivity", "on create call");
        Toolbar toolbar=findViewById(R.id.nav_action1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Gold Jewellary");
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onStart() {
        int spacing=20;
        int spanCount=2;
        boolean includeEdge=false;
        super.onStart();
        recyclerView = findViewById(R.id.recycler_jwel);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        JwelaryAdapter adapter = new JwelaryAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount,spacing,includeEdge));

    }
}
