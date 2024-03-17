public class Pizza implements foodItems{
    private String name;
    private String allergies;
    private double price;
    private double calories;
    private String toppings;

    public Pizza(){
        this.name="";
        this.allergies="";
        this.price=0.0;
        this.calories=0.0;
        this.toppings="";
    }
    public Pizza(String name1, String allergies1, double price1, double calories1, String toppings1){
        this.name=name1;
        this.allergies=allergies1;
        this.price=price1;
        this.calories=calories1;
        this.toppings=toppings1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

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

    public String getToppings() {
        return ("The "+ name+ " pizza has the following toppings: "+ toppings);
    }
}
