import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;

public class Menu {
    private List<foodItems> drinks;
    private List<foodItems> pastas;
    private List<foodItems> pizzas;
    private List<foodItems> salads;
    private Map<String, foodItems> itemsByName;


    private final String pastaFile2="pasta_dat.ser";
    private  final String drinkFile2="drink_dat.ser";
    private final String saladFile2="salad_dat.ser";
    private  final String pizzaFile2="pizza_dat.ser";


    public List<foodItems> getDrinks() {
        return drinks;
    }

    public List<foodItems> getPastas() {
        return pastas;
    }

    public List<foodItems> getPizzas() {
        return pizzas;
    }

    public List<foodItems> getSalads() {
        return salads;
    }

    // The menu constructor reads from the files the corresponding parts of the menu
    public Menu(){
        this.pastas = loadObjectsFromFile(pastaFile2);
        this.drinks = loadObjectsFromFile(drinkFile2);
        this.salads = loadObjectsFromFile(saladFile2);
        this.pizzas = loadObjectsFromFile(pizzaFile2);
        itemsByName = new HashMap<>();
        populateItems();
    }
    public List<foodItems> getAll(){
        List<foodItems> allFood=new ArrayList<>(pastas);
        allFood.addAll(pizzas);
        allFood.addAll(salads);
        allFood.addAll(drinks);
        return allFood;
    }
    public void saveNew(){
        saveObjectsToFile(drinks,drinkFile2);
        saveObjectsToFile(pizzas,pizzaFile2);
        saveObjectsToFile(salads,saladFile2);
    }
    //Method to keep track of the menu easier in a hashTable
    public void populateItems(){
        for(foodItems item: drinks){
            itemsByName.put(item.getName(),item);
        }
        for(foodItems item: pastas){
            itemsByName.put(item.getName(),item);
        }
        for(foodItems item: pizzas){
            itemsByName.put(item.getName(),item);
        }
        for(foodItems item: salads){
            itemsByName.put(item.getName(),item);
        }
    }
    //Finds a food item in the menu in the hashMap;
    public foodItems findItem(String name){
        return itemsByName.get(name);
    }
    //Adds an item in the menu and updates the whole menu
    public void addItem(foodItems item){
        if (item != null && item.getName() != null && !item.getName().isEmpty()) {
            itemsByName.put(item.getName(), item); // Add to the itemsByName map
        }
        switch (item) {
            case Pasta pasta -> {
                pastas.add(item);
                saveObjectsToFile(pastas,pastaFile2);
                pastas=loadObjectsFromFile(pastaFile2);
            }
            case Pizza pizza -> {
                pizzas.add(item);
                saveObjectsToFile(pizzas,pizzaFile2);
                pizzas=loadObjectsFromFile(pizzaFile2);
            }
            case Salad salad -> {
                salads.add(item);
                saveObjectsToFile(salads,saladFile2);
                salads=loadObjectsFromFile(saladFile2);
            }
            case null, default -> {
                drinks.add(item);
                saveObjectsToFile(drinks,drinkFile2);
                drinks=loadObjectsFromFile(drinkFile2);
            }
        }
    }
    //Removes an item of the menu;
    public void removeItem(foodItems item){
        switch (item) {
            case Pasta pasta -> {
                String name1=pasta.getName();
                pastas.removeIf(p->p.getName().equals(name1));
                itemsByName.remove(name1);
                saveObjectsToFile(pastas,pastaFile2);
                pastas=loadObjectsFromFile(pastaFile2);
            }
            case Pizza pizza -> {
                String name1=pizza.getName();
                pizzas.removeIf(p->p.getName().equals(name1));
                itemsByName.remove(name1);
                saveObjectsToFile(pizzas,pizzaFile2);
                pizzas=loadObjectsFromFile(pizzaFile2);
            }
            case Salad salad -> {
                String name1=salad.getName();
                salads.removeIf(p->p.getName().equals(name1));
                itemsByName.remove(name1);
                saveObjectsToFile(salads,saladFile2);
                salads=loadObjectsFromFile(saladFile2);
            }
            case Beverages drink -> {
                String name1=drink.getName();
                drinks.removeIf(p->p.getName().equals(name1));
                itemsByName.remove(name1);
                saveObjectsToFile(drinks,drinkFile2);
                drinks=loadObjectsFromFile(drinkFile2);
            }
            case null, default -> {}
        }
    }
    //Pretty displays the menu of the restaurant
    public void displayMenu() {
        System.out.println("Menu Items:");
        System.out.println("--------------------");

        System.out.println("Salads:");
        for (foodItems item : salads) {
            System.out.println("Name: " + item.getName());
            System.out.println("Price: " + item.getPrice());
            if (item instanceof Salad) {
                System.out.println(((Salad) item).getIngredients());
            }
            System.out.println("--------------------");
        }

        System.out.println("Pizzas:");
        for (foodItems item : pizzas) {
            System.out.println("Name: " + item.getName());
            System.out.println("Price: " + item.getPrice());
            if (item instanceof Pizza) {
                System.out.println(((Pizza) item).getToppings());
            }
            System.out.println("--------------------");
        }

        System.out.println("Pastas:");
        for (foodItems item : pastas) {
            System.out.println("Name: " + item.getName());
            System.out.println("Price: " + item.getPrice());
            if (item instanceof Pasta) {
                System.out.println(((Pasta) item).getTypeOfPasta());
            }
            System.out.println("--------------------");
        }

        System.out.println("Drinks:");
        for (foodItems item : drinks) {
            System.out.println("Name: " + item.getName());
            System.out.println("Price: " + item.getPrice());
            if (item instanceof Beverages) {
                System.out.println(((Beverages) item).getFlavour());
            }
            System.out.println("--------------------");
        }
    }
    //Read from the fileObject the objects previously used
    private static List<foodItems> loadObjectsFromFile(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<foodItems>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    //Saves the current objects to the file for further assessment;

    private static void saveObjectsToFile(List<foodItems> iteme, String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(iteme);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
