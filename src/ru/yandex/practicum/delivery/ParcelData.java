package ru.yandex.practicum.delivery;

class ParcelData {
    private String description;
    private int weight;
    private String deliveryAddress;
    private int sendDay;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public boolean setWeight(int weight) {
        if (weight > 0) {
            this.weight = weight;
            return true;
        } else {
            return false;
        }
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }

    public boolean setSendDay(int sendDay) {
        if (sendDay >= 1 && sendDay <= 30) {
            this.sendDay = sendDay;
            return true;
        } else {
            return false;
        }
    }
}