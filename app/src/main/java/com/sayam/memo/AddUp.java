package com.sayam.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sayam.memo.models.ItemPriceDataModel;

public class AddUp extends AppCompatActivity implements View.OnClickListener {

    private Button   save;
    private EditText name;
    private EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_up);
        save  = findViewById(R.id.save);
        name  = findViewById(R.id.nameitem);
        price = findViewById(R.id.priceitem);

        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == save) {

            String item  = name.getText().toString();
            Double price = Double.parseDouble(this.price.getText().toString());

            ItemPriceDataModel value = new ItemPriceDataModel(item, price);

            Intent intent = new Intent();
            intent.putExtra("item.data.parcelable.value", value);
            setResult(1, intent);
            finish();
        }
    }
}