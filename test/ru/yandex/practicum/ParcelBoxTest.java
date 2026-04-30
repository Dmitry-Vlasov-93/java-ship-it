package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.yandex.practicum.delivery.*;

public class ParcelBoxTest {
    StandardParcel standardParcel = new StandardParcel("Стандартная", 5, "Адрес", 1);
    StandardParcel standardParcel2 = new StandardParcel("Стандартная", 5, "Адрес", 1);
    StandardParcel standardParcel3 = new StandardParcel("Стандартная", 10, "Адрес", 1);
    ParcelBox<StandardParcel> box = new ParcelBox<>(10);

    @Test
    public void addTest() {
        assertTrue(box.addParcel(standardParcel));
        assertTrue(box.addParcel(standardParcel2));
        assertFalse(box.addParcel(standardParcel3));
    }
}
