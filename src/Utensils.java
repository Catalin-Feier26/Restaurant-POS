public class Utensils implements Items {
    private String name;
    private String type;
    private int amount;
    private double price;
    public Utensils(){
        this.name="";
        this.type="";
        this.amount=0;
        this.price=0.0;
    }
    public Utensils(String name, String type, int amount, double price){
        this.name=name;
        this.type=type;
        this.amount=amount;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
