package com.sayam.memo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.sayam.memo.models.ItemPriceDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private final List<ItemPriceDataModel> items = new ArrayList<>();

    private static final int REQUEST = 5142;

    private LinearLayoutManager  linearLayoutManager;
    private RecyclerView         recyclerView;
    private FloatingActionButton floating;
    private ItemsAdapter         adapter;
    private Button               button;

    private TextView totalItemCountOutlet, totalPriceOutlet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        getSupportActionBar().hide();

        floating             = findViewById(R.id.additem);
        totalPriceOutlet     = findViewById(R.id.totalPriceOutlet);
        totalItemCountOutlet = findViewById(R.id.totalItemCountOutlet);
        button               = findViewById(R.id.logout);

        //MARK: Recycler view STEP 1
        adapter             = new ItemsAdapter(items);
        recyclerView        = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);

        //MARK: Recycler view STEP 2
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
            }
        });

        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AddUp.class);
                startActivityForResult(intent, REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST) {
            if (resultCode == 1) {
                ItemPriceDataModel value = data.getParcelableExtra("item.data.parcelable.value");
                items.add(value);
                adapter.notifyItemInserted(items.size() - 1);
                update();
            }
        }
    }

    private void update() {

        int count = items.size();
        double totalPrice = 0;

        // For Each Loop
        for(ItemPriceDataModel value : items) {

            totalPrice += value.getPrice();
        }

        String countLabel = String.format(Locale.US,"(%d) Items added", count);
        String priceLabel = String.format(Locale.US, "₹ %f", totalPrice);

        totalItemCountOutlet.setText(countLabel);
        totalPriceOutlet.setText(priceLabel);
    }
}