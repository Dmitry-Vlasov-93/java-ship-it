package ru.yandex.practicum;

import ru.yandex.practicum.delivery.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelTest {

    StandardParcel standardParcel = new StandardParcel("Стандартная", 10, "Адрес", 1);
    FragileParcel fragileParcel = new FragileParcel("Хрупкая", 10, "Адрес", 1);
    PerishableParcel perishableParcel = new PerishableParcel("Скоропортящаяся", 10, "Адрес", 1, 10);

    @Test
    public void costsTest() {
        assertEquals(20, standardParcel.calculateDeliveryCost());
        assertEquals(40, fragileParcel.calculateDeliveryCost());
        assertEquals(30, perishableParcel.calculateDeliveryCost());
    }

    @Test
    public void isExpiredTest() {
        assertTrue(perishableParcel.isExpired(12));
        assertFalse(perishableParcel.isExpired(11));
        assertFalse(perishableParcel.isExpired(1));
    }
}
