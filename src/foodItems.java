import java.io.Serializable;
public interface foodItems extends Serializable{
    String getName();
    double getPrice();
    String getAllergies();
    double getCalories();
    void setName(String name);
    void setPrice(double price);
    void setCalories(double calories);
    void setAllergies(String allergies);
}
