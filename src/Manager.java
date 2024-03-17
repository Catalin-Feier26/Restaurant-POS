import javax.swing.JOptionPane;
public class Manager extends Employee{
    private String contactInfo;
    public Manager(){
        super();
        contactInfo="";
    }
    public Manager(String contactInfo, String name, String username, String password, int age){
        super(age,name,username,password);
        this.contactInfo=contactInfo;
    }
    public void setContactInfo(String contactInfo){
        this.contactInfo=contactInfo;
    }
    public String getContactInfo() {
        return contactInfo;
    }
    public void modifyMenu(Menu menu){
        String input=JOptionPane.showInputDialog("Do you want to modify the menu?yes/no");
        if(input.equalsIgnoreCase("yes")){
            input=JOptionPane.showInputDialog("Do you want to remove, add or modify and item from there?");
            if(input.equalsIgnoreCase("modify")){
                modifyItem(menu);
            } else if (input.equalsIgnoreCase("remove")) {
                String item = JOptionPane.showInputDialog("What would you like to remove? ");
                foodItems aha = menu.findItem(item);
                menu.removeItem(aha);
            }else if(input.equalsIgnoreCase("add")){
                String input2=JOptionPane.showInputDialog("Do you want to add pizza, pasta, salad or a drink?");
                if(input2.equalsIgnoreCase("pizza")){
                     String name=JOptionPane.showInputDialog("What is the name of the pizza?");
                     String allergies=JOptionPane.showInputDialog("What are the allergies for this pizza?");
                     double calories=0;
                    String input3 = JOptionPane.showInputDialog("Enter calories:");
                    try {
                         calories  = Double.parseDouble(input3);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "invalid");
                    }
                     double price=0;
                    input3=JOptionPane.showInputDialog("Enter a new price:");
                    try{
                         price=Double.parseDouble(input3);
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null,"invalid");
                    }
                     String toppings=JOptionPane.showInputDialog("What are the toppings for this pizza?");
                    Pizza pizza=new Pizza(name,allergies,price,calories,toppings);
                    menu.addItem(pizza);
                }else if(input2.equalsIgnoreCase("pasta")){
                     String name=JOptionPane.showInputDialog("What is the name of the pasta");
                     String typeOfPasta=JOptionPane.showInputDialog("What is the noodle type of pasta");
                     String allergies=JOptionPane.showInputDialog("What are the allergies for this dish?");
                     double price=0;
                     double calories=0;
                    String input3 = JOptionPane.showInputDialog("Enter calories:");
                    try {
                        calories  = Double.parseDouble(input3);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "invalid");
                    }
                    input3=JOptionPane.showInputDialog("Enter a new price:");
                    try{
                        price=Double.parseDouble(input3);
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null,"invalid");
                    }
                    Pasta pasta=new Pasta(name,typeOfPasta,allergies,price,calories);
                    menu.addItem(pasta);
                }
                else if(input2.equalsIgnoreCase("salad")){
                    String name=JOptionPane.showInputDialog("What is the name of the salad?");
                    String allergies=JOptionPane.showInputDialog("What are the allergens?");
                    double price=0;
                    double calories=0;
                    String input3 = JOptionPane.showInputDialog("Enter calories:");
                    try {
                        calories  = Double.parseDouble(input3);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "invalid");
                    }
                    input3=JOptionPane.showInputDialog("Enter a new price:");
                    try{
                        price=Double.parseDouble(input3);
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null,"invalid");
                    }
                    String dressing=JOptionPane.showInputDialog("Enter the dressing for this salad");
                    String ingredients=JOptionPane.showInputDialog("Enter the ingredients for the salad");
                    Salad salad= new Salad(name,allergies,price,calories,dressing,ingredients);
                    menu.addItem(salad);
                }else{
                    String name=JOptionPane.showInputDialog("What is the name of the drink?");
                    String allergies=JOptionPane.showInputDialog("What are the allergens?");
                    double price=0;
                    double calories=0;
                    String input3 = JOptionPane.showInputDialog("Enter calories:");
                    try {
                        calories  = Double.parseDouble(input3);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "invalid");
                    }
                    input3=JOptionPane.showInputDialog("Enter a new price:");
                    try{
                        price=Double.parseDouble(input3);
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null,"invalid");
                    }
                    String flavour=JOptionPane.showInputDialog("Enter the flavour for this drink");
                    Beverages drink=new Beverages(name,allergies,price,calories,flavour);
                    menu.addItem(drink);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"ok");
        }
    }
    public void modifyItem(Menu menu){
        String input=JOptionPane.showInputDialog("What is the name of the item?");
        if(input!=null){
            foodItems item= menu.findItem(input);
            input=JOptionPane.showInputDialog("Name, price, calories, or allergies?");
            if(input.equalsIgnoreCase("name")){
                String input2=JOptionPane.showInputDialog("Enter the new name");
                if(input2!=null)
                    item.setName(input2);
            }else if(input.equalsIgnoreCase("price")){
                String input3=JOptionPane.showInputDialog("Enter a new price:");
                try{
                    double nr=Double.parseDouble(input3);
                    item.setPrice(nr);
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null,"invalid");
                }
            }else if(input.equalsIgnoreCase("calories")) {
                String input3 = JOptionPane.showInputDialog("Enter updated calories:");
                try {
                    double nr = Double.parseDouble(input3);
                    item.setCalories(nr);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "invalid");
                }
            }else{
                String input2=JOptionPane.showInputDialog("Enter the new allergens:");
                if(input2!=null){
                    item.setAllergies(input2);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"Something is wrong");
        }
    }
    public void modifyStorage(Storage storage){
        String input=JOptionPane.showInputDialog("Do you want to modify the storage?yes/no");
        if(input.equalsIgnoreCase("yes")){
            String input1=JOptionPane.showInputDialog("Add, modify or remove?");
            if(input1.equalsIgnoreCase("modify")){
                modifyItem(storage);
            }else if(input1.equalsIgnoreCase("remove")){
                String input2=JOptionPane.showInputDialog("The name of the item you want to remove?");
                storage.removeItem(storage.findItemByName(input2));
            }else if(input1.equalsIgnoreCase("add")){
                String input2=JOptionPane.showInputDialog("Food item, utensil, or miscellaneous item");
                if(input2.equalsIgnoreCase("food"))
                    addFood(storage);
                else if(input2.equalsIgnoreCase("utensil"))
                    addUtensil(storage);
                else
                    addMisc(storage);
            }
        }
    }
    public void modifyItem(Storage storage){
        String input=JOptionPane.showInputDialog("What is the name of the item");
        if(input!=null){
            Items obj= storage.findItemByName(input);
            input=JOptionPane.showInputDialog("Name, amount, price, or type?");
            if(input.equalsIgnoreCase("name")){
                String input2=JOptionPane.showInputDialog("New name?");
                obj.setName(input2);
            }else if(input.equalsIgnoreCase("amount")){
                String input2=JOptionPane.showInputDialog("New amount?");
                try{
                    int amount=Integer.parseInt(input2);
                    obj.setAmount(amount);
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null,"Invalid");
                }
            }else if(input.equalsIgnoreCase("price")){
                String input2=JOptionPane.showInputDialog("New price?");
                try{
                    double price=Double.parseDouble(input2);
                    obj.setPrice(price);
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null,"Invalid");
                }
            }else{
                String input2=JOptionPane.showInputDialog("Enter the new type:");
                obj.setType(input2);
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"Wrong, invalid...");
        }
    }
    public void addUtensil(Storage storage){
         String name=JOptionPane.showInputDialog("Enter the name of the utensil");
         String type=JOptionPane.showInputDialog("Enter the type name of the utensil");
         int amount=0;
         String inp=JOptionPane.showInputDialog("Enter the amount of this item");
         try{
             amount=Integer.parseInt(inp);
         }catch (NumberFormatException e){
             JOptionPane.showMessageDialog(null,"Invalid");
         }
         inp=JOptionPane.showInputDialog("Enter the price for this");
         double price=0;
         try{
             price=Double.parseDouble(inp);
         }catch(NumberFormatException e){
             JOptionPane.showMessageDialog(null,"invalid price");
         }
         Utensils utensils=new Utensils(name,type,amount,price);
         storage.addItem(utensils);
    }
    public void addFood(Storage storage){
        String name=JOptionPane.showInputDialog("Enter the name of the food");
        String type=JOptionPane.showInputDialog("Enter the type name of the food");
        int amount=0;
        String inp=JOptionPane.showInputDialog("Enter the amount of this food");
        try{
            amount=Integer.parseInt(inp);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Invalid amount");
        }
        inp=JOptionPane.showInputDialog("Enter the price for this food");
        double price=0;
        try{
            price=Double.parseDouble(inp);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"invalid price");
        }
        Food food=new Food(name,type,amount,price);
        storage.addItem(food);
    }
    public void addMisc(Storage storage){
        String name=JOptionPane.showInputDialog("Enter the name of the diverse item");
        String type=JOptionPane.showInputDialog("Enter the type name of the miscellaneous");
        int amount=0;
        String inp=JOptionPane.showInputDialog("Enter the amount of this item");
        try{
            amount=Integer.parseInt(inp);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Invalid amount");
        }
        inp=JOptionPane.showInputDialog("Enter the price for this misc");
        double price=0;
        try{
            price=Double.parseDouble(inp);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"invalid price");
        }
        Miscellaneous misc=new Miscellaneous(name,type,amount,price);
        storage.addItem(misc);
    }
    public void changeWaiterSalary(Waiter waiter) {
        String newSalaryStr = JOptionPane.showInputDialog("Enter the new salary:");
        if (newSalaryStr != null && !newSalaryStr.isEmpty()) {
            try {
                int newSalary = Integer.parseInt(newSalaryStr);
                waiter.setSalary(newSalary);
                JOptionPane.showMessageDialog(null, "Waiter salary updated!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input for salary.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input for salary.");
        }
    }

    public void addTableToWaiter(@org.jetbrains.annotations.NotNull Waiter waiter, Table table) {
        waiter.addTable(table);
        JOptionPane.showMessageDialog(null, "Table added to the Waiter!");
    }
}
