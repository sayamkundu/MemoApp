package com.sayam.memo.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemPriceDataModel implements Parcelable {

    private final String name;
    private final Double price;

    public ItemPriceDataModel(String name, Double price) {

        this.name  = name;
        this.price = price;
    }

    protected ItemPriceDataModel(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemPriceDataModel> CREATOR = new Creator<ItemPriceDataModel>() {
        @Override
        public ItemPriceDataModel createFromParcel(Parcel in) {
            return new ItemPriceDataModel(in);
        }

        @Override
        public ItemPriceDataModel[] newArray(int size) {
            return new ItemPriceDataModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
