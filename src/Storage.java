import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
public class Storage {
    private final String utensilsFile = "utens_data.ser";
    private final String foodFile = "food_data.ser";
    private final String miscFile = "misc_data.ser";

    private List<Items> ustensile;
    private List<Items> mancare;
    private List<Items> misc;

    private Map<String,Items> inventar;

    public Storage(){
        this.ustensile  = new ArrayList<>();
        this.mancare=new ArrayList<>();
        this.misc=new ArrayList<>();
        loadFiles();
        inventar= new HashMap<>();
        populateItems();
    }
    public void populateItems(){
        for(Items item: mancare){
            inventar.put(item.getName(),item);
        }
        for(Items item: misc){
            inventar.put(item.getName(),item);
        }
        for(Items item: ustensile){
            inventar.put(item.getName(),item);
        }
    }
    public List<Items> getAllItems(){
        List<Items> allItems = new ArrayList<>(mancare);
        allItems.addAll(ustensile);
        allItems.addAll(misc);
        return allItems;

    }
    public void loadFiles(){
        this.ustensile=loadObjectsFromFile(utensilsFile);
        this.mancare=loadObjectsFromFile(foodFile);
        this.misc=loadObjectsFromFile(miscFile);
    }
    public Items findItemByName(String name){
        return inventar.get(name);
    }
    public void addItem(Items obj){
        if(obj!=null){
            if(obj instanceof  Utensils){
                ustensile.add(obj);
                saveObjectsToFile(ustensile,utensilsFile);
                ustensile=loadObjectsFromFile(utensilsFile);
                inventar.put(obj.getName(),obj);
            }
            else if(obj instanceof Food){
                mancare.add(obj);
                saveObjectsToFile(mancare,foodFile);
                mancare=loadObjectsFromFile(foodFile);
                inventar.put(obj.getName(),obj);
            }
            else if(obj instanceof Miscellaneous){
                misc.add(obj);
                saveObjectsToFile(misc,miscFile);
                misc=loadObjectsFromFile(miscFile);
                inventar.put(obj.getName(),obj);
            }
        }
    }
    public void removeItem(Items obj){
        if(obj!=null){
            String name=obj.getName();
            if(obj instanceof Utensils){
                ustensile.removeIf(p->p.getName().equalsIgnoreCase(name));
                saveObjectsToFile(ustensile,utensilsFile);
                ustensile=loadObjectsFromFile(utensilsFile);
                inventar.remove(obj.getName());
            }
            else if(obj instanceof Food){
                mancare.removeIf(p->p.getName().equalsIgnoreCase(name));
                saveObjectsToFile(mancare,foodFile);
                mancare=loadObjectsFromFile(foodFile);
                inventar.remove(obj.getName());
            }else if(obj instanceof Miscellaneous){
                misc.removeIf(p->p.getName().equalsIgnoreCase(name));
                saveObjectsToFile(misc,miscFile);
                misc=loadObjectsFromFile(miscFile);
                inventar.remove(obj.getName());
            }
        }
    }
    public void displayStorage() {
        System.out.println("------ Utensils ------");
        displayItems(ustensile);
        System.out.println("------ Food ------");
        displayItems(mancare);
        System.out.println("------ Miscellaneous ------");
        displayItems(misc);
    }

    private void displayItems(List<Items> itemList) {
        if (itemList.isEmpty()) {
            System.out.println("No items in this category.");
            return;
        }

        String format = "| %-20s | %-10s | %-10s | %-10s |%n";
        System.out.format("+----------------------+---------------+------------+------------+%n");
        System.out.format("| Name                 | Type          | Amount     | Price      |%n");
        System.out.format("+----------------------+---------------+------------+------------+%n");

        for (Items item : itemList) {
            System.out.format(format, item.getName(), item.getType(), item.getAmount(), item.getPrice());
        }
        System.out.format("+----------------------+---------------+------------+------------+%n");
    }
    private static List<Items> loadObjectsFromFile(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Items>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    //Saves the current objects to the file for further assessment;

    private static void saveObjectsToFile(List<Items> iteme, String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(iteme);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
