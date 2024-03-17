import java.io.Serializable;
public interface Items extends Serializable{
    String getName();
    int getAmount();
    double getPrice();
    String getType();
    void setName(String name);
    void setAmount(int amount);
    void setPrice(double price);
    void setType(String type);
}
