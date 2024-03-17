import java.io.Serial;
import java.io.Serializable;

public class Pasta implements foodItems, Serializable {
    private String name;
    private String typeOfPasta;
    private String allergies;
    private double price;
    private double calories;


    public Pasta(){
        name="";
        typeOfPasta="";
        price=0.0;
        allergies="";
        calories=0.0;

    }
    public Pasta(String name1, String typeOfPasta1, String allergies1, double price1, double calories1){
        this.name=name1;
        this.typeOfPasta=typeOfPasta1;
        this.allergies=allergies1;
        this.price=price1;
        this.calories=calories1;

    }
    public void setName(String name) {
        this.name = name;
    }

    public void setTypeOfPasta(String typeOfPasta) {
        this.typeOfPasta = typeOfPasta;
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

    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public double getCalories(){
        return calories;
    }
    public String getAllergies(){
        return ("This item might contain the following allergens: "+allergies);
    }
    public String getTypeOfPasta(){return typeOfPasta;}
}
