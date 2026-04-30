package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private int maxWeight;
    private int remainingWeight;
    private List<T> box = new ArrayList<>();

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.remainingWeight = maxWeight;
    }

    public boolean addParcel(T parcel) {
        if (remainingWeight < parcel.getWeight()) {
            System.out.println("Эта коробка не может вместить эту посылку!");
            return false;
        } else {
            box.add(parcel);
            remainingWeight -= parcel.getWeight();
            return true;
        }
    }

    public List<T> getAllParcels() {
        return box;
    }
}
