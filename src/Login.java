import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
public class Login {
    private  final String managerFile="manager_data.ser";
    private  final String waiterFile="waiter_data.ser";
    private final String tableFile="table_data.ser";
    private List<Table> tables;
    private List<Employee> managers;
    private List<Employee> waiters;
    private Map<String,Employee> employees;
    public Login(){
        managers=new ArrayList<>();
        waiters=new ArrayList<>();
        tables=new ArrayList<>();
        employees=new HashMap<>();
        loadFiles();
        populateEmployees();
    }
    private void populateEmployees(){
        for(Employee manager: managers)
            employees.put(manager.getUsername(),manager);
        for(Employee waiter: waiters)
            employees.put(waiter.getUsername(),waiter);
    }
    public void printEmployeeHashMap() {
        System.out.println("Employees:");
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            String username = entry.getKey();
            Employee employee = entry.getValue();
            System.out.println("Username: " + username + ", Name: " + employee.getName() + ", Type: " + employee.getClass().getSimpleName());
        }
    }

    public Employee login(String username, String password, JFrame frame) {
        Employee employee = null;

            Employee foundEmployee = employees.get(username);

            if (foundEmployee != null && foundEmployee.getPassword().equals(password)) {
                employee = foundEmployee;
                //System.out.println("Login successful. Welcome, " + employee.getName() + "!");
                JOptionPane.showMessageDialog(frame,"Login successfully!");
            } else {
                JOptionPane.showMessageDialog(frame,"Invalid credentials");
            }

        return employee;
    }

    public void addWaiter(Waiter waiter){
        if(waiter!=null)
        {
            waiters.add(waiter);
            employees.put(waiter.getUsername(),waiter);
            saveObjectsToFile(waiters,waiterFile);
            waiters=loadObjectsFromFile(waiterFile);
        }
    }
    public void addManager(Manager manager){
        if(manager!=null)
        {
            employees.put(manager.getUsername(),manager);
            managers.add(manager);
            saveObjectsToFile(managers,managerFile);
            managers=loadObjectsFromFile(managerFile);
        }
    }
    public void addTable(Table table){
        if(table != null)
        {
            tables.add(table);
            Table.saveObjectsToFile(tables,tableFile);
            tables=Table.loadObjectsFromFile(tableFile);
            updateTables();
        }
    }
    public void removeTable(Table table){
        if(table!=null){
                tables.removeIf(table::equals);
                Table.saveObjectsToFile(tables,tableFile);
                tables=Table.loadObjectsFromFile(tableFile);
                updateTables();
            }
    }

    private void updateTables(){
        if(tables!=null){
            for (int i = 0; i < tables.size(); i++) {
                tables.get(i).tableId = i + 1;
            }
        }
    }
    public boolean removeManager(Manager manager){
        boolean ok=false;
        if(manager!=null){
           ok=managers.removeIf(manager::equals);
        }
        return ok;
    }
    public boolean removeWaiter(Waiter waiter){
        boolean ok=false;
        if(waiter!=null){
            ok=waiters.removeIf(waiter::equals);
            employees.remove(waiter.getName());
            saveObjectsToFile(waiters,waiterFile);
            waiters=loadObjectsFromFile(waiterFile);
        }
        return ok;
    }
    public void updateTableFile(){
        Table.saveObjectsToFile(tables,tableFile);
        tables=Table.loadObjectsFromFile(tableFile);
    }
    public void updateWaiters(){
        saveObjectsToFile(waiters,waiterFile);
        waiters=loadObjectsFromFile(waiterFile);
    }
    private static List<Employee> loadObjectsFromFile(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Employee>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    //Saves the current objects to the file for further assessment;

    private static void saveObjectsToFile(List<Employee> iteme, String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(iteme);
        } catch (IOException e) {
            e.getCause();
        }
    }
    public void saveFiles(){
        saveObjectsToFile(managers,managerFile);
        saveObjectsToFile(waiters,waiterFile);
        Table.saveObjectsToFile(tables,tableFile);
    }
    public void loadFiles(){
        managers=loadObjectsFromFile(managerFile);
        waiters=loadObjectsFromFile(waiterFile);
        tables=Table.loadObjectsFromFile(tableFile);
        updateTables();
    }
    public void printAllUsers() {
        System.out.println("Managers:");
        try {
            for (Employee manager : managers) {
                System.out.println("Username: " + manager.getUsername() + " password " + manager.getPassword() + ", Name: " + manager.getName() + ", Type: Manager");
            }
        }catch (NumberFormatException e){
            System.out.println("No mangers");
        }
        System.out.println("\nWaiters:");
        try{
        for (Employee waiter : waiters) {
            System.out.println("Username: " + waiter.getUsername() + " password: " + waiter.getPassword() + ", Name: " + waiter.getName() + ", Type: Waiter");
        }
        }catch (NullPointerException e){
            System.out.println("No waiters");
        }
        System.out.println("\nTables");{
           try {
               for (Table table : tables) {
                   table.displayTableInfo();
                   Waiter waiter1=tableAssignment(table);
                   if(waiter1!=null)
                       System.out.println(waiter1.getName());
                   else
                       System.out.println("No waiter");
               }
           }catch (NullPointerException e){
               System.out.println("Empty tables");
           }
        }
    }
    public Manager findManager(String name) {
        for (Employee manager : managers) {
            if (manager.getName().equalsIgnoreCase(name) && manager instanceof Manager) {
                return (Manager) manager;
            }
        }
        return null;
    }

    public Waiter findWaiter(String name) {
        for (Employee waiter : waiters) {
            if (waiter.getName().equalsIgnoreCase(name) && waiter instanceof Waiter) {
                return (Waiter) waiter;
            }
        }
        return null;
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<Employee> getManagers() {
        return managers;
    }

    public List<Employee> getWaiters() {
        return waiters;
    }
    public Waiter tableAssignment(Table table){
        Waiter waiter=null;
        for(Employee waiter1: waiters){
            if(((Waiter)waiter1).findTableInWaiter(table)){
                waiter=(Waiter)waiter1;
            }
        }
        return waiter;
    }

}
