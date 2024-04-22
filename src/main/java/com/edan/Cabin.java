package com.edan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cabin {
    private String code;
    private String rute;
    private String status = "Stopped";
    private int capacity;
    private List<User> passengers = new ArrayList<>();
    private boolean automatically = false;

    public Cabin(String code, String rute, int capacity){
        this.code = code;
        this.rute = rute;
        this.capacity = capacity;
    }

    public boolean addPassenger(User passenger){
        if (this.passengers.size() < capacity){
            this.passengers.add(passenger);
            if (this.passengers.size() == capacity && this.automatically){
                System.out.println("Cabin in auto moving rute: " + this.rute);
                departure();
            }else {
                System.out.println("Cabin has full capacity, admin can start the route");
            }
            return true;
        }else {
            return false;
        }
    }

    public void inspectCapacity(){
        if (this.passengers.size() < capacity){
            System.out.println("Capacity available: " + (this.capacity - this.passengers.size()));
        }
    }

    public void arrival(){
        this.status = "Stopped";
        this.passengers.clear();
    }

    public void departure(){
        this.status = "Moving";
    }

    public void print(){
        String mode = "Manual";
        if (this.automatically){
            mode = "Auto";
        }
        System.out.println(this.code + " - " + this.rute + " - " + this.status + " - Capacity: " + this.capacity + " - Passengers: " + this.passengers.size() + " - " + mode);
        inspectCapacity();
    }
}
