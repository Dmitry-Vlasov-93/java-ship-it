package ru.yandex.practicum.delivery;

public abstract class Parcel {
    static protected final int PRICE_FOR_STANDARD = 2;
    static protected final int PRICE_FOR_FRAGILE = 4;
    static protected final int PRICE_FOR_PERISHABLE = 3;
    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка " + description + " упакована");
    }

    public void deliver() {
        System.out.println("Посылка " + description + " доставлена по адресу " + deliveryAddress);
    }

    public int getPrice() {
        return PRICE_FOR_STANDARD;
    }

    public int calculateDeliveryCost() {
        return weight * getPrice();
    }

    public int getWeight() {
        return weight;
    }

}
