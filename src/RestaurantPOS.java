import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class RestaurantPOS extends JFrame {
    public final Login login = new Login();
    public final Menu menu = new Menu();
    public final Storage storage= new Storage();
    private boolean isMenuFrameVisible=false;
    private JFrame menuFrame;
    private boolean isStorageFrameVisible = false;
    private JFrame storageFrame;
    private JFrame frame;
    private JPanel loginPanel;
    private JPanel signupPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RestaurantPOS() {
        frame = new JFrame("RestaurantPOS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Terminal");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        loginPanel = createLoginPanel(loginButton, signupButton);

        frame.add(title, BorderLayout.NORTH);
        frame.add(loginPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Login button action
                String username = usernameField.getText();
                char[] passChars = passwordField.getPassword();
                String password = new String(passChars);
                Employee empl = login.login(username, password, frame);
                if (empl instanceof Manager) {
                    frame.remove(loginPanel);
                    managerMode(empl);
                } else if (empl instanceof Waiter) {
                    frame.remove(loginPanel);
                    managerMode(empl);
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton signInButton = new JButton("Sign In");
                JTextField usernameField2 = new JTextField(20);
                JPasswordField passwordField2 = new JPasswordField(20);
                JTextField ageField = new JTextField(20);
                JTextField nameField = new JTextField(20);
                JCheckBox manager = new JCheckBox("Manager");
                JButton backButton1 = new JButton("Back");

                // Switch to signup panel
                signupPanel = makeSignUpPanel(backButton1, manager, signInButton, usernameField2, passwordField2, ageField, nameField);
                frame.remove(loginPanel);
                frame.add(signupPanel, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                backButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switchToLogin(signupPanel);
                    }
                });
                signInButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name, ag, pass;
                        String username;
                        char[] password;
                        int age = 18;
                        boolean status, isValid;
                        isValid = true;
                        name = nameField.getText();
                        if (name.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Name shouldn't be empty");
                            isValid = false;
                        }
                        username = usernameField2.getText();
                        if (username.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Username shouldn't be empty");
                            isValid = false;
                        }
                        password = passwordField2.getPassword();
                        pass = new String(password);
                        if (pass.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Password should NOT be empty");
                            isValid = false;
                        }
                        ag = ageField.getText();
                        try {
                            age = Integer.parseInt(ag);
                            if (age < 18) {
                                JOptionPane.showMessageDialog(frame, "Age should be a valid number");
                                isValid = false;
                            }
                        } catch (NumberFormatException ee) {
                            JOptionPane.showMessageDialog(frame, "Age should be a valid number");
                            isValid = false;
                        }
                        status = manager.isSelected();
                        if (isValid) {
                            if (status) {
                                String contact = JOptionPane.showInputDialog(frame, "Enter your contact info");
                                Manager mang = new Manager(contact, name, username, pass, age);
                                login.addManager(mang);
                                //login.loadFiles();
                                JOptionPane.showMessageDialog(frame, "New manager account created successfully, now login");
                                login.printAllUsers();
                            } else {
                                Waiter waiter = new Waiter(name, username, pass, age);
                                login.addWaiter(waiter);
                                //login.loadFiles();
                                JOptionPane.showMessageDialog(frame, "New waiter account created successfully, now login");
                                login.printAllUsers();
                            }
                            switchToLogin(signupPanel);
                        }

                    }
                });
            }
        });
    }

    private void switchToLogin(JPanel panel1) {
        frame.getContentPane().removeAll();
        frame.remove(panel1);
        JLabel title = new JLabel("Terminal");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        frame.add(title, BorderLayout.NORTH);

        frame.add(loginPanel, BorderLayout.CENTER);
        frame.validate();
        frame.repaint();
    }

    private JPanel createLoginPanel(JButton loginButton, JButton signupButton) {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcLogin = new GridBagConstraints();
        gbcLogin.insets = new Insets(10, 10, 10, 10);

        gbcLogin.gridx = 0;
        gbcLogin.gridy = 0;
        panel.add(new JLabel("Username: "), gbcLogin);

        gbcLogin.gridx = 1;
        gbcLogin.gridy = 0;
        panel.add(usernameField, gbcLogin);

        gbcLogin.gridx = 0;
        gbcLogin.gridy = 1;
        panel.add(new JLabel("Password: "), gbcLogin);

        gbcLogin.gridx = 1;
        gbcLogin.gridy = 1;
        panel.add(passwordField, gbcLogin);

        gbcLogin.gridx = 0;
        gbcLogin.gridy = 2;
        gbcLogin.gridwidth = 2;
        panel.add(loginButton, gbcLogin);

        gbcLogin.gridx = 0;
        gbcLogin.gridy = 3;
        panel.add(signupButton, gbcLogin);

        return panel;
    }

    private JPanel makeSignUpPanel(JButton backButton, JCheckBox manager, JButton button, JTextField usernameField2, JPasswordField passwordField2, JTextField ageField, JTextField nameField) {

        JLabel nameLabel = new JLabel("Name: ");
        JLabel ageLabel = new JLabel("Age: ");
        JLabel passLabel = new JLabel("Password: ");
        JLabel usernameLabel = new JLabel("Username: ");

        // Create a panel for the signup form using GridBagLayout
        JPanel panel1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcSignup = new GridBagConstraints();
        gbcSignup.insets = new Insets(10, 10, 10, 10);

        // Add the backButton at the top-left corner of the panel
        gbcSignup.anchor = GridBagConstraints.NORTHWEST;
        gbcSignup.gridx = 0;
        gbcSignup.gridy = 0;
        panel1.add(backButton, gbcSignup);

        // Add other components below the backButton
        gbcSignup.gridx = 0;
        gbcSignup.gridy = 1;
        panel1.add(usernameLabel, gbcSignup);

        gbcSignup.gridx = 1;
        gbcSignup.gridy = 1;
        panel1.add(usernameField2, gbcSignup);

        gbcSignup.gridx = 0;
        gbcSignup.gridy = 2;
        panel1.add(nameLabel, gbcSignup);

        gbcSignup.gridx = 1;
        gbcSignup.gridy = 2;
        panel1.add(nameField, gbcSignup);

        gbcSignup.gridx = 0;
        gbcSignup.gridy = 3;
        panel1.add(ageLabel, gbcSignup);

        gbcSignup.gridx = 1;
        gbcSignup.gridy = 3;
        panel1.add(ageField, gbcSignup);

        gbcSignup.gridx = 0;
        gbcSignup.gridy = 4;
        panel1.add(passLabel, gbcSignup);

        gbcSignup.gridx = 1;
        gbcSignup.gridy = 4;
        panel1.add(passwordField2, gbcSignup);

        gbcSignup.gridx = 0;
        gbcSignup.gridy = 5;
        panel1.add(manager, gbcSignup);

        gbcSignup.gridx = 1;
        gbcSignup.gridy = 5;
        panel1.add(button, gbcSignup);

        return panel1;
    }

    private void managerMode(Employee employee) {
        JPanel managerPanel = new JPanel(new BorderLayout());

        JButton exitButton = new JButton("Exit");
        JButton logOutButton = new JButton("Log out");
        JButton addTableButton=new JButton("Add Table");


        String[] menuOptions = {"Menu","See menu", "Modify menu"};
        JComboBox<String> menuDropdown = new JComboBox<>(menuOptions);

        String[] storageOptions = {"Storage","See storage", "Modify storage"};
        JComboBox<String> storageDropdown = new JComboBox<>(storageOptions);

        String[] waitersOptions = {"Waiters","See waiters","Fire waiter", "Modify waiter's salary"};
        JComboBox<String> waitersDropdown = new JComboBox<>(waitersOptions);

        storageDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<?> source = (JComboBox<?>) e.getSource();
                String selectedAction = (String) source.getSelectedItem();
                if (selectedAction != null && !selectedAction.equals("Storage")) {
                    handleStorageAction(employee,selectedAction);
                }
            }
        });
        menuDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<?> source = (JComboBox<?>) e.getSource();
                String selectedAction = (String) source.getSelectedItem();
                if (selectedAction != null && !selectedAction.equals("Menu")) {
                    handleMenuAction(employee,selectedAction,menuDropdown);
                }
            }
        });
        waitersDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<?> source = (JComboBox<?>) e.getSource();
                String selectedAction = (String) source.getSelectedItem();
                if (selectedAction != null && !selectedAction.equals("Waiters")) {
                    handleWaiterAction(employee,selectedAction);
                }
            }
        });

        JPanel topButtonPanel = new JPanel();
        topButtonPanel.setLayout(new BoxLayout(topButtonPanel, BoxLayout.X_AXIS));
        topButtonPanel.add(logOutButton);
        topButtonPanel.add(exitButton);
        topButtonPanel.add(menuDropdown);
        topButtonPanel.add(storageDropdown);
        topButtonPanel.add(waitersDropdown);
        topButtonPanel.add(addTableButton);

        topButtonPanel.setBorder(BorderFactory.createEmptyBorder(5,1,75,0));

        managerPanel.add(topButtonPanel, BorderLayout.NORTH);

        JPanel tableDisplayPanel = new JPanel();

        managerPanel.add(tableDisplayPanel, BorderLayout.CENTER);

        this.frame.setContentPane(managerPanel);
        this.frame.revalidate();
        this.frame.repaint();

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToLogin(managerPanel);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        addTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Table newTable = new Table();
                login.addTable(newTable);

                displayTables(tableDisplayPanel, employee);
            }
        });
        displayTables(tableDisplayPanel, employee);
    }

    private void displayTables(JPanel tableDisplayPanel, Employee employee) {
        if(tableDisplayPanel!=null)
            tableDisplayPanel.removeAll();

        List<Table> tables = login.getTables();
        int maxTablesPerRow = 4;
        int totalTables = tables.size();
        int rows = (int) Math.ceil((double) totalTables / maxTablesPerRow);

        JPanel tableLayout = new JPanel(new GridLayout(rows, maxTablesPerRow, 10, 10));

        for (Table table : tables) {
            JComboBox<String> tableDropdown = new JComboBox<>(){
                @Override
                public void setUI(ComboBoxUI ui){
                    super.setUI(ui);
                    if(table.availability){
                        setBackground(new Color(118, 254, 144));
                    }
                    else{
                        setBackground(new Color(255,77,106));
                    }
                }
            };
            tableDropdown.addItem("Select an action");
            tableDropdown.addItem("Select waiter");
            tableDropdown.addItem("Reserve");
            tableDropdown.addItem("Add To Order");
            tableDropdown.addItem("Remove From Order");
            tableDropdown.addItem("Display Order");
            tableDropdown.addItem("Display Info");
            tableDropdown.addItem("Clean Table");
            tableDropdown.addItem("Get Tip");
            tableDropdown.addItem("Remove table");

            tableDropdown.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox<?> source = (JComboBox<?>) e.getSource();
                    String selectedAction = (String) source.getSelectedItem();
                    if (selectedAction != null && !selectedAction.equals("Select an action")) {
                        handleTableAction(employee, selectedAction, tableDropdown, table);
                    }
                }
            });

            JPanel tablePanel = new JPanel(new BorderLayout());
            tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            tablePanel.add(new JLabel("Table " + table.getTableId() + ", with " + table.nrSeats + " seats:"), BorderLayout.NORTH);
            tablePanel.add(tableDropdown, BorderLayout.CENTER);
            tableLayout.add(tablePanel);
        }

        tableDisplayPanel.add(tableLayout);
        tableDisplayPanel.revalidate();
        tableDisplayPanel.repaint();
    }

    private void handleTableAction(Employee employee, String action, JComboBox<String> tableDropdown, Table table) {
        JPanel tableDisplayPane;
        switch (action) {
            case "Select waiter":
                if (employee instanceof Manager) {
                    String input = JOptionPane.showInputDialog(frame, "Enter waiter's name");
                    Waiter waiter = login.findWaiter(input);

                    if (waiter == null) {
                        JOptionPane.showMessageDialog(frame, "Waiter not found");
                    } else {
                        List<Employee> waiters = login.getWaiters();

                        for (Employee employee1 : waiters) {
                            if (employee1 instanceof Waiter) {
                                Waiter currentWaiter = (Waiter) employee1;

                                // Check if the current waiter is already assigned to the table
                                if (currentWaiter.findTableInWaiter(table)) {
                                    currentWaiter.removeTable(table.tableId);
                                    break;
                                }
                            }
                        }
                        ((Manager) employee).addTableToWaiter(waiter, table);
                    }
                }else if (employee instanceof Waiter) {
                    // Check if the current waiter is already assigned to the table
                  JOptionPane.showMessageDialog(frame,"The manager makes the table assignment.");
                }
                login.saveFiles();
                login.loadFiles();
                login.printAllUsers();
                tableDisplayPane=(JPanel) tableDropdown.getParent().getParent();
                displayTables(tableDisplayPane, employee);
                break;
            case "Reserve":
                // Logic for reserving the table
                table.reserve(!table.availability);
                login.updateTableFile();
                tableDisplayPane=(JPanel) tableDropdown.getParent().getParent();
                displayTables(tableDisplayPane, employee);
                break;
            case "Display Order":
                table.displayOrder(frame);
                login.updateTableFile();
                tableDisplayPane=(JPanel) tableDropdown.getParent().getParent();
                displayTables(tableDisplayPane, employee);
                break;
            case "Remove From Order":
                // Logic for removing items from the table's order

                if(employee instanceof  Waiter){
                    ((Waiter)employee).removeFromOrder(frame,menu,table);
                } else{
                    JOptionPane.showMessageDialog(frame,"Waiters should take of orders from a table");
                }
                login.updateTableFile();
                tableDisplayPane=(JPanel) tableDropdown.getParent().getParent();
                displayTables(tableDisplayPane, employee);
                break;
            case "Add To Order":
                // Logic for adding items to the table's order
                if(employee instanceof Waiter){
                    table.reserve(false);
                    ((Waiter)employee).addToOrder(menu,table);
                } else{
                    JOptionPane.showMessageDialog(frame,"Waiters should take of orders from a table");
                }
                login.updateTableFile();
                tableDisplayPane=(JPanel) tableDropdown.getParent().getParent();
                displayTables(tableDisplayPane, employee);
                break;
            case "Display Info":
                // Logic for displaying table information
                Waiter tableWaiter=login.tableAssignment(table);
                StringBuilder info= new StringBuilder();
                table.updateBill();
                info.append("Table ID: ").append(table.tableId).append("\n");
                info.append("Number of seats: ").append(table.nrSeats).append("\n");
                info.append("Availability: ").append(table.availability?"Available":"Reserved").append("\n");
                info.append("Total Bill: ").append(table.totalBill).append("\n");
                if(tableWaiter!=null){
                    info.append("Waiter: ").append(tableWaiter.getName()).append("\n");
                }
                else
                    info.append("No waiter assigned as of now").append("\n");
                JOptionPane.showMessageDialog(frame,info.toString(),"Table Information", JOptionPane.INFORMATION_MESSAGE);
                tableDisplayPane=(JPanel) tableDropdown.getParent().getParent();
                displayTables(tableDisplayPane, employee);
                break;
            case "Clean Table":
                // Logic for cleaning the table
                if(employee instanceof Waiter){
                    ((Waiter)employee).cleanTable2(table);
                    login.saveFiles();
                    login.loadFiles();
                    JOptionPane.showMessageDialog(frame,"Table has been cleaned.");
                }
                else{
                    JOptionPane.showMessageDialog(frame,"Managers do not clean tables.");
                }
                tableDisplayPane=(JPanel) tableDropdown.getParent().getParent();
                displayTables(tableDisplayPane, employee);
                break;
            case "Get Tip":
                // Logic for getting the tip for the table
                if(employee instanceof Waiter){
                    ((Waiter)employee).getTips(frame,table);
                    login.saveFiles();
                    login.loadFiles();
                }else{
                    JOptionPane.showMessageDialog(frame,"Only waiters get tips.");
                }
                tableDisplayPane=(JPanel) tableDropdown.getParent().getParent();
                displayTables(tableDisplayPane, employee);
                break;
            case "Remove table":
                login.removeTable(table);
                tableDisplayPane=(JPanel) tableDropdown.getParent().getParent();
                displayTables(tableDisplayPane, employee);
                break;
            default:
                tableDisplayPane=(JPanel) tableDropdown.getParent().getParent();
                displayTables(tableDisplayPane, employee);
                break;
        }
    }
    private void handleWaiterAction(Employee employee, String action){
        switch (action){
            case "See waiters":
                showWaiters();
                break;
            case "Fire waiter":
                if(employee instanceof Waiter)
                    JOptionPane.showMessageDialog(frame,"Only managers can access this");
                else if(employee instanceof Manager){
                    String name=JOptionPane.showInputDialog(frame,"Enter waiter's name");
                    Waiter waiter=login.findWaiter(name);
                    boolean fired=login.removeWaiter(waiter);
                    if(fired)
                        JOptionPane.showMessageDialog(frame,"Fired the waiter "+waiter.getName());
                    else
                        JOptionPane.showMessageDialog(frame,"not found");
                }
                break;
            case "Modify waiter's salary":
                if(employee instanceof Waiter)
                    JOptionPane.showMessageDialog(frame,"Only managers can access this");
                else if(employee instanceof Manager){
                    String name=JOptionPane.showInputDialog(frame,"Enter waiter's name");
                    Waiter waiter=login.findWaiter(name);
                    if(waiter!=null) {
                        ((Manager) employee).changeWaiterSalary(waiter);
                        login.updateWaiters();
                    }else JOptionPane.showMessageDialog(frame,"Waiter not found");
                }
                break;
        }
    }
    private void handleStorageAction(Employee employee, String action) {
        switch (action) {
            case "See storage":
                showInventar();
                break;
            case "Modify storage":
                if (employee instanceof Waiter) {
                    JOptionPane.showMessageDialog(frame, "Waiters cannot modify the storage");
                } else {
                    ((Manager) employee).modifyStorage(storage);
                }
                break;
        }
    }
    private void handleMenuAction(Employee employee, String action, JComboBox<String> menuDropdown){
        switch(action){
            case "See menu":
                showMenu();
                break;
            case "Modify menu":
            {
                if(employee instanceof Waiter)
                {
                    JOptionPane.showMessageDialog(frame,"Waiters cannot modify the menu.");
                    break;
                }
                ((Manager)employee).modifyMenu(menu);
                break;
            }
            default:break;
        }
    }

    private void showInventar() {
        if (!isStorageFrameVisible) {
            JFrame storageFrame = new JFrame("Storage");
            storageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            storageFrame.setSize(800, 600);
            storageFrame.setLayout(new BorderLayout());

            // Retrieve items from storage
            List<Items> items = storage.getAllItems();

            List<Items> foodItems=new ArrayList<>();
            List<Items> utensilItems=new ArrayList<>();
            List<Items> miscItems=new ArrayList<>();

            for(Items item: items){
                if(item instanceof Food){
                    foodItems.add(item);
                }else if(item instanceof Utensils){
                    utensilItems.add(item);
                }else if(item instanceof Miscellaneous){
                    miscItems.add(item);
                }
            }

            JTable foodTable=createTable(foodItems);
            JTable utensilTable=createTable(utensilItems);
            JTable miscTable= createTable(miscItems);

            JScrollPane foodScrollPane=new JScrollPane(foodTable);
            JScrollPane utensilScrollPane=new JScrollPane(utensilTable);
            JScrollPane miscScrollPane=new JScrollPane(miscTable);

            JTabbedPane tabbedPane=new JTabbedPane();
            tabbedPane.addTab("Food Ingredients",foodScrollPane);
            tabbedPane.addTab("Utensil",utensilScrollPane);
            tabbedPane.addTab("Miscellaneous",miscScrollPane);

            storageFrame.add(tabbedPane,BorderLayout.CENTER);


            storageFrame.setVisible(true);
            isStorageFrameVisible = true;

            storageFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    isStorageFrameVisible = false;
                }
            });
        }
        else {
            storageFrame.toFront();
        }
    }
    private JTable createTable(List<Items> items){
        String[] columnNames={"Name","Type","Amount","Price"};
        Object[][] data=new Object[items.size()][columnNames.length];
        int row=0;
        for(Items item: items){
            data[row][0]=item.getName();
            data[row][1]=item.getType();
            data[row][2]=item.getAmount();
            data[row][3]=item.getPrice();
            row++;
        }
        JTable table=new JTable(data,columnNames);
        table.setFont(new Font("Arial",Font.PLAIN,16));
        return table;
    }
    private void showMenu(){
        if(!isMenuFrameVisible){
            JFrame menuFrame=new JFrame("Menu");
            menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            menuFrame.setSize(800, 600);
            menuFrame.setLayout(new BorderLayout());

            List<foodItems> foodItemsList=menu.getAll();

            List<foodItems> pizza=new ArrayList<>();
            List<foodItems> pasta= new ArrayList<>();
            List<foodItems> drinks=new ArrayList<>();
            List<foodItems> salads=new ArrayList<>();

            for(foodItems fud:foodItemsList){
                if(fud instanceof Pizza){
                    pizza.add(fud);
                } else if(fud instanceof Pasta){
                    pasta.add(fud);
                }else if(fud instanceof Beverages){
                    drinks.add(fud);
                }else if(fud instanceof Salad){
                    salads.add(fud);
                }
            }
            JTable pizzaTable=createTable1(pizza);
            JTable pastaTable=createTable1(pasta);
            JTable drinkTable=createTable1(drinks);
            JTable saladsTable=createTable1(salads);

            JScrollPane pizzaScroll= new JScrollPane(pizzaTable);
            JScrollPane saladScroll=new JScrollPane(saladsTable);
            JScrollPane drinkScroll=new JScrollPane(drinkTable);
            JScrollPane pastaScroll=new JScrollPane(pastaTable);

            JTabbedPane tabbedPane=new JTabbedPane();
            tabbedPane.addTab("Pizza",pizzaScroll);
            tabbedPane.addTab("Salad",saladScroll);
            tabbedPane.addTab("Pasta",pastaScroll);
            tabbedPane.addTab("Drinks",drinkScroll);

            menuFrame.add(tabbedPane,BorderLayout.CENTER);

            menuFrame.setVisible(true);
            isMenuFrameVisible=true;

            menuFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    isMenuFrameVisible=false;
                }
            });
        }
        else{
            menuFrame.toFront();
        }
    }
    private JTable createTable1(List<foodItems> foods){
        String[] columnNames={"Name","Price","Calories"};
        Object[][] data=new Object[foods.size()][columnNames.length];
        int row=0;
        for(foodItems fud: foods){
            data[row][0]=fud.getName();
            data[row][1]=fud.getPrice();
            data[row][2]=fud.getCalories();
            row++;
        }
        JTable table=new JTable(data,columnNames);
        table.setFont(new Font("Arial",Font.PLAIN,16));
        return table;
    }
    private void showWaiters(){
        JFrame waitersFrame=new JFrame("Waiters");
        waitersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        waitersFrame.setSize(800,600);
        waitersFrame.setLayout(new BorderLayout());
        List<Employee> waiters=login.getWaiters();

        String[] columnNames={"Name","Age","Salary"};
        Object[][] data=new Object[waiters.size()][columnNames.length];
        int row=0;
        for(Employee waiter: waiters){
            data[row][0]=waiter.getName();
            data[row][1]=waiter.getAge();
            data[row][2]=((Waiter)waiter).getSalary();
            row++;
        }
        JTable table=new JTable(data,columnNames);
        table.setFont(new Font("Arial",Font.PLAIN,16));

        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        waitersFrame.add(scrollPane,BorderLayout.CENTER);
        waitersFrame.setVisible(true);
    }

}