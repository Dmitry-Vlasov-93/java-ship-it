package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public int getPrice() {
        return PRICE_FOR_PERISHABLE;
    }

    public boolean isExpired(int currentDay) {
        if (sendDay + timeToLive >= currentDay) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Скоропортящаяся. " +
                "Описание: " + description + ',' + " Вес посылки: " + weight + " Адрес доставки: " + deliveryAddress +
                " День доставки: " + sendDay + " Срок хранения: " + timeToLive;
    }
}
