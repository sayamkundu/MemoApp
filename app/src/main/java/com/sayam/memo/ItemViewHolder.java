package com.sayam.memo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sayam.memo.models.ItemPriceDataModel;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView nameOutlet;
    private TextView priceOutlet;

    public ItemViewHolder(@NonNull View itemView) {

        super(itemView);

        nameOutlet  = itemView.findViewById(R.id.itemNameOutlet);
        priceOutlet = itemView.findViewById(R.id.itemPriceOutlet);
    }

    public void update(@NonNull ItemPriceDataModel value) {

        nameOutlet.setText(value.getName());
        priceOutlet.setText(Double.toString(value.getPrice()));
    }
}
