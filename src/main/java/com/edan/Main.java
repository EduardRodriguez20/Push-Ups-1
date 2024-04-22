package com.edan;

import java.util.*;

public class Main {
    private static final List<Cabin> cabins = new ArrayList<>();
    private static final List<User> users = new ArrayList<>();
    private static final Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        int option = 0;
        do{
            mainMenu();
            option = validInt("Enter your choice: ");
            switch (option) {
                case 1:
                    cabinMenu();
                    break;
                case 2:
                    userMenu();
                    break;
                case 3:
                    System.out.println("Program finished");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while (option != 3);
        System.out.println("Cabin management system");
    }

    public static void mainMenu(){
        System.out.println("1. Cabin administration");
        System.out.println("2. User menu");
        System.out.println("3. Exit");
    }

    public static void cabinMenu(){
        int option = 0;
        System.out.println("1. Add a new cabin");
        System.out.println("2. Remove a cabin");
        System.out.println("3. List all cabins");
        System.out.println("4. Change the management cabin");
        System.out.println("5. Departure a cabin");
        System.out.println("6. Arrive a cabin");
        System.out.println("7. Exit");
        option = validInt("Enter your choice: ");
        switch (option) {
            case 1:
                addCabin();
                break;
            case 2:
                removeCabin();
                break;
            case 3:
                listAllCabins();
                break;
            case 4:
                changeCabin();
                break;
            case 5:
                arriveCabin();
                break;
            case 6:
                departureCabin();
                break;
            case 7:
                System.out.println("Main menu");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    private static void departureCabin() {
        listAllCabins();
        System.out.println("Enter the code of the cabin to departure: ");
        String code = read.nextLine();
        Cabin existing = findCabin(code);
        if (existing!= null){
            existing.departure();
        }
    }

    private static void arriveCabin() {
        listAllCabins();
        System.out.println("Enter the code of the cabin to arrive: ");
        String code = read.nextLine();
        Cabin existing = findCabin(code);
        if (existing!= null){
            existing.arrival();
        }
    }

    private static void changeCabin() {
        listAllCabins();
        read.nextLine();
        System.out.println("Enter the code of the cabin to change: ");
        String code = read.nextLine();
        Cabin existing = findCabin(code);
        if (existing!= null){
            int newCode = validInt("Enter the action of the cabin: \n1. Auto\n2. Manual");
            if (newCode == 1){
                existing.setAutomatically(true);
            }else if (newCode == 2){
                existing.setAutomatically(false);
            }else {
                System.out.println("Invalid option");
            }
        }
    }

    private static void listAllCabins() {
        System.out.println("Cabins:");
        for (Cabin c : cabins){
            c.print();
        }
    }

    private static void removeCabin() {
        System.out.println("Enter the code of the cabin to remove: ");
        String code = read.nextLine();
        Cabin existing = findCabin(code);
        if (existing != null){
            cabins.remove(existing);
            System.out.println("Cabin deleted");
        }
    }

    private static Cabin findCabin(String code){
        Optional<Cabin> existing = cabins.stream()
                .filter(c -> c.getCode().equals(code))
                .findFirst();
        if (existing.isEmpty()){
            System.out.println("Cabin not found");
            return null;
        }else {
            return existing.get();
        }
    }

    private static void addCabin() {
        read.nextLine();
        System.out.println("Enter the code of the cabin: ");
        String code = read.nextLine();
        System.out.println("Enter the rute of the cabin: ");
        String rute = read.nextLine();
        int capacity = validInt("Enter the capacity of the cabin: ");
        Cabin cabin = new Cabin(code, rute, capacity);
        cabins.add(cabin);
        System.out.println("Cabin successfully created");
    }

    public static void userMenu(){
        int option = 0;
        System.out.println("1. Add a new user");
        System.out.println("2. Remove a user");
        System.out.println("3. List all users");
        System.out.println("4. Request a trip");
        System.out.println("5. Exit");
        option = validInt("Enter your choice: ");
        switch (option) {
            case 1:
                addUser();
                break;
            case 2:
                removeUser();
                break;
            case 3:
                listAllUsers();
                break;
            case 4:
                requestTrip();
                break;
            case 5:
                System.out.println("Main menu");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    private static void requestTrip() {
        read.nextLine();
        System.out.println("Enter the name of the user: ");
        String name = read.nextLine();
        User existing = findUser(name);
        if (existing != null){
            listAllCabins();
            System.out.println("Enter the code of the cabin: ");
            String code = read.nextLine();
            Cabin cabin = findCabin(code);
            if (cabin != null){
                if(cabin.addPassenger(existing)){
                    System.out.println("Trip requested");
                } else {
                    System.out.println("The cabin is full");
                }
            }
        }
    }

    private static void listAllUsers() {
        System.out.println("Users:");
        for (User u : users){
            System.out.println(u);
        }
    }

    private static void removeUser() {
        System.out.println("Enter the name of the user to remove");
        String name = read.nextLine();
        User existing = findUser(name);
        if (existing != null){
            users.remove(existing);
            System.out.println("User deleted");
        }
    }

    private static User findUser(String name) {
        Optional<User> existing = users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst();
        if (existing.isEmpty()) {
            System.out.println("User not found");
            return null;
        } else {
            return existing.get();
        }
    }

    private static void addUser() {
        read.nextLine();
        System.out.println("Enter the name of the user: ");
        String name = read.nextLine();
        int age = validInt("Enter the age of the user: ");
        User user = new User(name, age);
        users.add(user);
    }

    private static int validInt(String message){
        int option = 0;
        while (true){
            try{
                System.out.println(message);
                option = read.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("Digit a valid option");
                read.nextLine();
            }
        }
        return option;
    }
}