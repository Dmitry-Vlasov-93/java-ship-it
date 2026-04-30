package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private final int timeToLive;
    private static final int PRICE_FOR_PERISHABLE = 3;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    protected int getPrice() {
        return PRICE_FOR_PERISHABLE;
    }

    public boolean isExpired(int currentDay) {
        return sendDay + timeToLive < currentDay;
    }

    @Override
    public String toString() {
        return "Скоропортящаяся. " +
                "Описание: " + description + ',' + " Вес посылки: " + weight + " Адрес доставки: " + deliveryAddress +
                " День доставки: " + sendDay + " Срок хранения: " + timeToLive;
    }

}
