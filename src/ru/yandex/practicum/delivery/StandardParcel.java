package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel {
    private static final int PRICE_FOR_STANDARD = 2;

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public String toString() {
        return "Стандартная посылка. " +
                "Описание: " + description + ',' + " Вес посылки: " + weight + " Адрес доставки: " + deliveryAddress +
                " День доставки: " + sendDay;
    }

    @Override
    protected int getPrice(){
        return PRICE_FOR_STANDARD;
    }
}
