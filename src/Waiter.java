import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class Waiter extends Employee{
    private int salary;
    private List<Table> tables;
    public double totalTip;
    //Constructors for the waiter, the minimum wage, and if given the amount of tables they serve they will be added.
    public Waiter(String name, String username, String password, int age){
        super(age, name, username, password);
        this.salary=1218;
        this.totalTip=0.0;
        tables= new ArrayList<>();
    }
    public Waiter(){
        super();
        this.salary=1218;
        this.totalTip=0.0;
        tables=new ArrayList<>();
    }
    public void readWaiterTables(int nrTables){
        for(int i=0;i<nrTables;i++){
            tables.add(new Table());
        }
    }
    //Setters and getters


    public double getTotalTip() {
        for(Table i: tables)
            totalTip=totalTip+i.tip;
        return totalTip;
    }
    public void setSalary(int amount){
        this.salary=amount;
    }
    public int getSalary(){
        return salary;
    }
    //Removes a table from a waiter's supervision
    public void removeTable(int tableId) {
        Iterator<Table> iterator = tables.iterator();
        while (iterator.hasNext()) {
            Table table = iterator.next();
            if (table.getTableId() == tableId) {
                iterator.remove();
                System.out.println("Table with ID " + tableId + " removed from waiter's tables.");
                return;
            }
        }
        System.out.println("Table with ID " + tableId + " not found or not assigned to this waiter.");
    }
    //Adds a table to a waiter
    public void addTable(Table table) {
        if (table != null) {
            if (!tables.contains(table)) {
                tables.add(table);
                System.out.println("Table with ID " + table.getTableId() + " added to " + getName()+"'s tables.");
            } else {
                System.out.println("Table with ID " + table.getTableId() + " is already assigned to " + getName());
            }
        }
    }
    //Makes the order for a specific table
    public void makeOrder(Menu menu, int tableId) {
        for (Table table : tables) {
            if (table != null && table.getTableId() == tableId) {
                table.reserve(false);
                StringBuilder option = new StringBuilder("yes");
                while (option.toString().equalsIgnoreCase("yes")) {
                    String item = JOptionPane.showInputDialog("Food to be ordered:");
                    foodItems food = menu.findItem(item);
                    if (food != null) {
                        table.addItem(food);
                        option = new StringBuilder(JOptionPane.showInputDialog("Anything else? yes or no"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid menu item. Please enter a valid item.");
                    }
                }
                table.updateBill();
                return;
            }
        }
        System.out.println("Table with ID " + tableId + " not found or not assigned to this waiter.");
    }

    public void addToOrder(Menu menu, Table table) {
        if (table != null) {
            String option = "yes";
            while (option.equalsIgnoreCase("yes")) {
                String item = JOptionPane.showInputDialog("What would you like to add?");
                foodItems food = menu.findItem(item);
                if (food != null) {
                    table.addItem(food);
                    //option = JOptionPane.showInputDialog("Anything else? yes/no");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid menu item. Please enter a valid item.");
                }
                option = JOptionPane.showInputDialog("Anything else? yes/no");
            }
            table.updateBill();
        }
    }

    // Removes items from the order if needed
    public void removeFromOrder(JFrame frame, Menu menu, Table table) {
        String opt = JOptionPane.showInputDialog("Would you like to remove an item? yes/no");
        if (opt.equalsIgnoreCase("yes")) {
                if (table != null) {
                    String option = "yes";
                    while (option.equalsIgnoreCase("yes")) {
                        String item = JOptionPane.showInputDialog("What would you like to remove? ");
                        foodItems food = menu.findItem(item);
                        if (food != null) {
                            table.removeItem(food);
                            option = JOptionPane.showInputDialog("Remove anything else? yes or no");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid menu item. Please enter a valid item.");
                        }
                    }
                    table.updateBill();
                }
        } else {
            System.out.println("Nothing to remove");
        }
    }

    //Cleans the table
    public void cleanTable(int tableId) {
        for (Table i : tables) {
            if (i != null && i.getTableId() == tableId) {
                i.cleanTable();
                JOptionPane.showMessageDialog(null,"The table " + tableId + " has been cleaned");
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"Table with ID " + tableId + " not assigned to this waiter.");
    }
    public void cleanTable2(Table table){
        if(table !=null)
            table.cleanTable();
    }
    public void showTables(){
        for(Table i: tables){
            if(i!=null){
                i.displayTableInfo();
            }
        }
    }
    public List<Table> getTables(){
        return tables;
    }
    public void finishTable(int tableId){
        for(Table i: tables){
            if(i!=null && i.getTableId()==tableId)
                i.givingTip();
        }
    }
    public boolean findTableInWaiter(Table table){
        if(table==null)
            return false;
        for(Table table1: tables){
            if(table1.getTableId()==table.getTableId())
            {
                return true;
            }
        }
        return false;
    }
    public void getTips(JFrame frame, Table table){
        String inp=JOptionPane.showInputDialog(frame,"How much did you get as a tip?");
        try{
            double tip=Double.parseDouble(inp);
            table.tip=tip;
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(frame,"Invalid number as a tip. It should be a number like x.y");
        }
    }
    public void clockOut() {
        if(tables!=null){
            tables.clear();
        }
    }
}
