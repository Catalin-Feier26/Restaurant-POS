public class Miscellaneous implements Items {
    private String name;
    private String type;
    private int amount;
    private double price;
    public Miscellaneous(){
        name="";
        type="";
        amount=0;
        price=0;
    }
    public Miscellaneous(String name, String type, int amount, double price){
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
