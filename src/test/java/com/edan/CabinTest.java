package com.edan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CabinTest {

    @Test
    void addUser1() {
        Cabin cabin = new Cabin("01", "Buca-Giron", 1);
        User user = new User("Eduard", 18);
        User user2 = new User("Andres", 20);
        cabin.addPassenger(user);
        assertFalse(cabin.addPassenger(user2));
    }

    @Test
    void addUser2() {
        Cabin cabin = new Cabin("01", "Buca-Giron", 2);
        User user = new User("Eduard", 18);
        User user2 = new User("Andres", 20);
        cabin.addPassenger(user);
        assertTrue(cabin.addPassenger(user2));
    }

    @Test
    void quantityUsers() {
        Cabin cabin = new Cabin("01", "Buca-Giron", 2);
        User user = new User("Eduard", 18);
        User user2 = new User("Andres", 20);
        cabin.addPassenger(user);
        cabin.addPassenger(user2);
        assertEquals(2, cabin.getPassengers().size());
    }
}