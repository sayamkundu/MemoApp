package com.sayam.memo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sayam.memo.models.ItemPriceDataModel;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<ItemPriceDataModel> items;

    public ItemsAdapter(List<ItemPriceDataModel> items) {

        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(viewType, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {

        return R.layout.item_price_view_holder_layout;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        ItemPriceDataModel item = items.get(position);
        holder.update(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
