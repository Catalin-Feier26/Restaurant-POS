public class Beverages implements foodItems {
    private String name;
    private String allergies;
    private double price;
    private double calories;
    private String flavour;
    //Default constructor, sets an item to "0"
    public Beverages(){
        this.name="";
        this.allergies="";
        this.price=0.0;
        this.calories=0.0;
        this.flavour="";
    }
    //Constructor if given parameters;
    public Beverages(String name, String allergies, double price, double calories, String flavour){
        this.name=name;
        this.allergies=allergies;
        this.price=price;
        this.calories=calories;
        this.flavour=flavour;
    }
    // Returns the flavour of the drink
    public String getFlavour(){
        return ("Flavour: " + flavour);
    }
    //Sets the name of the drink
    public void setName(String name) {
        this.name = name;
    }
    //Gets the allergies of a drink
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    //Sets the price of the drink
    public void setPrice(double price) {
        this.price = price;
    }
    //Sets the calories of a drink
    public void setCalories(double calories) {
        this.calories = calories;
    }
    //Sets the flavour of a drink
    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }
    //Getters for the name, allergies, price, calories
    public String getName(){
        return name;
    }
    public String getAllergies(){
        return ("This item might contain the following allergens: "+allergies);
    }
    public double getPrice(){
        return  price;
    }
    public double getCalories(){
        return calories;
    }
}
