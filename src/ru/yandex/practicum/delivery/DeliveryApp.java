package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> allTrackable = new ArrayList<>();
    private static ParcelBox<StandardParcel> boxForStandard = new ParcelBox<>(300);
    private static ParcelBox<FragileParcel> boxForFragile = new ParcelBox<>(300);
    private static ParcelBox<PerishableParcel> boxForPerishable = new ParcelBox<>(300);
    private static HashMap<String, ArrayList<ParcelBox<? extends Parcel>>> allBox = new HashMap<>();

    static {
        allBox.put("Standard", new ArrayList<>());
        allBox.put("Fragile", new ArrayList<>());
        allBox.put("Perishable", new ArrayList<>());
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatus();
                    break;
                case 5:
                    showContentsOfBox();
                    break;
                case 0:
                    running = false;
                    System.out.println("Программа завершена, хорошего дня!");
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Посмотреть статус посылок");
        System.out.println("5 - Показать содержимое коробок");
        System.out.println("0 — Завершить");
    }

    private static ParcelData showMenuForAddParcel() {
        ParcelData parcelData = new ParcelData();

        System.out.println("Введите описание посылки:");
        String description = scanner.nextLine();
        parcelData.setDescription(description);

        System.out.println("Введите вес посылки:");
        while (true) {
            int weight = Integer.parseInt(scanner.nextLine());
            if (parcelData.setWeight(weight)) {
                break;
            } else {
                System.out.println("Введите корректный вес посылки!");
            }
        }

        System.out.println("Введите адрес доставки:");
        String deliveryAddress = scanner.nextLine();
        parcelData.setDeliveryAddress(deliveryAddress);

        System.out.println("Введите день отправки:");
        while (true) {
            int sendDay = Integer.parseInt(scanner.nextLine());
            if (parcelData.setSendDay(sendDay)) {
                break;
            } else {
                System.out.println("Введите число от 1 до 30");
            }
        }
        return parcelData;
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        System.out.println("Выберите вид посылки: 1 - Обычная 2 - Хрупкая 3 - Скоропортящаяся");
        int typeParcel = Integer.parseInt(scanner.nextLine());
        if (typeParcel == 1) {
            ParcelData parcelData = showMenuForAddParcel();
            StandardParcel parcel = new StandardParcel(parcelData.getDescription(), parcelData.getWeight(),
                    parcelData.getDeliveryAddress(), parcelData.getSendDay());

            allParcels.add(parcel);

            if (!boxForStandard.addParcel(parcel)) {
                boxForStandard = new ParcelBox<>(parcel.getWeight() + 300);
                boxForStandard.addParcel(parcel);
            }

            if (boxForStandard.getAllParcels().size() == 1) {
                allBox.get("Standard").add(boxForStandard);
            }

            System.out.println("Стандартная посылка добавлена в список на отправку!");
        } else if (typeParcel == 2) {
            ParcelData parcelData = showMenuForAddParcel();
            FragileParcel parcel = new FragileParcel(parcelData.getDescription(), parcelData.getWeight(),
                    parcelData.getDeliveryAddress(), parcelData.getSendDay());

            allParcels.add(parcel);

            allTrackable.add(parcel);

            if (!boxForFragile.addParcel(parcel)) {
                boxForFragile = new ParcelBox<>(parcel.getWeight() + 300);
                boxForFragile.addParcel(parcel);
            }

            if (boxForFragile.getAllParcels().size() == 1) {
                allBox.get("Fragile").add(boxForFragile);
            }

            System.out.println("Хрупкая посылка добавлена в список на отправку!");
        } else if (typeParcel == 3) {
            ParcelData parcelData = showMenuForAddParcel();
            System.out.println("Введите время хранения посылки в днях:");
            int timeToLive = Integer.parseInt(scanner.nextLine());
            PerishableParcel parcel = new PerishableParcel(parcelData.getDescription(), parcelData.getWeight(),
                    parcelData.getDeliveryAddress(), parcelData.getSendDay(), timeToLive);

            allParcels.add(parcel);

            if (!boxForPerishable.addParcel(parcel)) {
                boxForPerishable = new ParcelBox<>(parcel.getWeight() + 300);
                boxForPerishable.addParcel(parcel);
            }

            if (boxForPerishable.getAllParcels().size() == 1) {
                allBox.get("Perishable").add(boxForPerishable);
            }

            System.out.println("Скоропортящаяся посылка добавлена в список на отправку!");
        } else {
            System.out.println("Неверный тип посылки!");
        }
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int priceForAll = 0;
        for (Parcel parcel : allParcels) {
            priceForAll += parcel.calculateDeliveryCost();
        }
        System.out.println("Стоимость отправки всех посылок: " + priceForAll);
    }

    private static void reportStatus() {
        if (allTrackable.isEmpty()) {
            System.out.println("Нет посылок с возможностью отслеживания.");
        } else {
            for (Trackable parcel : allTrackable) {
                System.out.println("Введите локацию:");
                String location = scanner.nextLine();
                parcel.reportStatus(location);
            }
        }
    }

    private static void showContentsOfBox() {
        System.out.println("Выберите вид посылок: 1 - Обычная 2 - Хрупкая 3 - Скоропортящаяся");
        int command = Integer.parseInt(scanner.nextLine());
        if (command == 1) {
            int counter = 1;
            ArrayList<ParcelBox<? extends Parcel>> allStandardBox = allBox.get("Standard");
            System.out.println("Количество коробок со стандартными посылками " + allStandardBox.size());
            for (ParcelBox<? extends Parcel> box : allStandardBox) {
                System.out.println("Все посылки в коробке номер: " + counter);
                for (Parcel parcel : box.getAllParcels()) {
                    System.out.println(parcel);
                }
                counter++;
            }
        } else if (command == 2) {
            int counter = 1;
            ArrayList<ParcelBox<? extends Parcel>> allFragileBox = allBox.get("Fragile");
            System.out.println("Количество коробок с хрупкими посылками " + allFragileBox.size());
            for (ParcelBox<? extends Parcel> box : allFragileBox) {
                System.out.println("Все посылки в коробке номер: " + counter);
                for (Parcel parcel : box.getAllParcels()) {
                    System.out.println(parcel);
                }
                counter++;
            }
        } else if (command == 3) {
            int counter = 1;
            ArrayList<ParcelBox<? extends Parcel>> allPerishableBox = allBox.get("Perishable");
            System.out.println("Количество коробок со скоропортящимися посылками" + allPerishableBox.size());
            for (ParcelBox<? extends Parcel> box : allPerishableBox) {
                System.out.println("Все посылки в коробке номер: " + counter);
                for (Parcel parcel : box.getAllParcels()) {
                    System.out.println(parcel);
                }
                counter++;
            }

        } else {
            System.out.println("Неверный тип посылки!");
        }
    }
}