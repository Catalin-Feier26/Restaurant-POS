public class Food implements Items {
    private String name;
    private String type;
    private int amount;
    private double price;
    private double totalCost;
    public Food(){
        name="";
        type="";
        amount=0;
        price=0.0;
        totalCost=0.0;
    }
    public Food(String name, String type, int amount, double price){
        this.name=name;
        this.type=type;
        this.amount=amount;
        this.price=price;
        setTotalCost();
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost() {
        this.totalCost = this.price*this.amount;
    }

}
