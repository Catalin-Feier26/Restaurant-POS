import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.util.Iterator;

public class Table implements Serializable {
    private static int lastId=1;
    public int nrSeats;
    public double totalBill;
    public double tip;
    public int tableId;
    public boolean availability;
    public List<foodItems> order;
    //Table constructor, sets everything to 0
    public Table(){
        nrSeats=0;
        totalBill=0.0;
        tableId=lastId++;
        availability=true;
        tip=0.0;
        order= new ArrayList<>();
        readTable();
    }
    private void readTable(){
        String input=JOptionPane.showInputDialog("Enter nr. of seats:");
        try{
            int nr=Integer.parseInt(input);
            if(nr>0 && nr<30)
                this.nrSeats=nr;
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"invalid");
        }
    }
    public int getTableId(){
        return tableId;
    }
    //Adds an item to the table order
    public void addItem(foodItems obj) {
        if (obj != null) {
            if(order==null){
                order=new ArrayList<>();
            }
            order.add(obj);
        } else {
            System.out.println("Invalid item or type not supported.");
        }
    }
    //Removes an item from the table order
    public void displaySomething(){
        for(foodItems aa: order)
            System.out.println(aa.getName());
    }
    public void removeItem(foodItems obj) {

        if (obj != null) {
            System.out.println("Removing item: " + obj.getName());
            Iterator<foodItems> iterator=order.iterator();
            while(iterator.hasNext()){
                foodItems food=iterator.next();
                System.out.println(food.getName());
                if(food.getName().equalsIgnoreCase(obj.getName()))
                {
                    System.out.println("Item removed successfully");
                    iterator.remove();
                }
            }
            System.out.println("not removed");
        } else {
            System.out.println("Item is null; nothing to remove");
        }
    }

    //Resets the orders
    public void clearOrder(){
        order.clear();
        totalBill=0.0;
        tip=0.0;
    }
    //Resets the table
    public void cleanTable(){
        clearOrder();
        reserve(true);
    }
    public double calculateTotal(){
        for(foodItems item: order){
            totalBill=totalBill+item.getPrice();
        }
        return totalBill;
    }
    //the bill
    public void updateBill() {
        totalBill = 0.0; // Reset totalBill to 0 before calculating
        for (foodItems item : order) {
            totalBill += item.getPrice();
        }
    }
    //The tip for the waiter
    public void setTip(double money){
        this.tip=money;
    }
    public void reserve(boolean status){
        this.availability=status;
    }
    public void displayTableInfo(){
        System.out.println("Table ID: " + tableId);
        System.out.println("Number of seats " + nrSeats);
        System.out.println("Availability: " +(availability?"Available":"Reserved"));
        updateBill();
        System.out.println("Total Bill: " + totalBill);
    }
    public void givingTip(){
        String tipPreference = JOptionPane.showInputDialog("Would you like to leave a tip? (yes/no)");
        if (tipPreference != null && tipPreference.equalsIgnoreCase("yes")) {
            String tipType = JOptionPane.showInputDialog("Choose tip type: Percentage or Fixed? (per/fix)");

            if (tipType != null && tipType.equalsIgnoreCase("per")) {
                String percentageInput = JOptionPane.showInputDialog("Enter the percentage:");
                try {
                    double percentage = Double.parseDouble(percentageInput);
                    if (percentage >= 0) {
                        double tipAmount = calculateTotal() * percentage / 100;
                        setTip(tipAmount);
                        JOptionPane.showMessageDialog(null, "Tip of $" + tipAmount + " added.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid percentage value.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input for percentage.");
                }
            } else if (tipType != null && tipType.equalsIgnoreCase("fix")) {
                String amountInput = JOptionPane.showInputDialog("Enter the tip amount:");
                try {
                    double amount = Double.parseDouble(amountInput);
                    if (amount >= 0) {
                        setTip(amount);
                        JOptionPane.showMessageDialog(null, "Tip of $" + amount + " added.");
                    } else {
                        JOptionPane .showMessageDialog(null, "Invalid tip amount.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input for tip amount.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid tip preference.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tip given.");
        }
    }
    public void displayOrder(JFrame frame){
        StringBuilder info= new StringBuilder();
        updateBill();
        for(foodItems contents: order){
            info.append(contents.getName()).append("   ").append(contents.getPrice()).append("\n");
        }
        info.append("Total bill: ").append(totalBill).append("\n");
        JOptionPane.showMessageDialog(frame,info.toString(),"Table Information", JOptionPane.INFORMATION_MESSAGE);
    }
    public static List<Table> loadObjectsFromFile(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Table>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    //Saves the current objects to the file for further assessment;

    public static void saveObjectsToFile(List<Table> iteme, String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(iteme);
        } catch (IOException e) {
            System.out.println("Hmm table file not found or wrong");
        }
    }
}
