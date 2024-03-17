public class Salad implements foodItems{

    private String name;
    private String allergies;
    private double price;
    private double calories;
    private String dressing;
    private String ingredients;
    public Salad(){
        this.name="";
        this.allergies="";
        this.price=0.0;
        this.calories=0.0;
        this.dressing="";
        this.ingredients="";
    }
    public Salad(String name, String allergies, double price, double calories, String dressing, String ingredients){
        this.name=name;
        this.allergies=allergies;
        this.price=price;
        this.calories=calories;
        this.dressing=dressing;
        this.ingredients=ingredients;
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

    public void setDressing(String dressing) {
        this.dressing = dressing;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDressing(){
        return ("Dressing: " + dressing);
    }
    public String getIngredients(){
        return ("This salad contains is made out of: "+ ingredients);
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
}
