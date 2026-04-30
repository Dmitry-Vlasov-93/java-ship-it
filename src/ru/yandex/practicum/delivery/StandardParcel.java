package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel {

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public String toString() {
        return "Стандартная посылка. " +
                "Описание: " + description + ',' + " Вес посылки: " + weight + " Адрес доставки: " + deliveryAddress +
                " День доставки: " + sendDay;
    }
}
