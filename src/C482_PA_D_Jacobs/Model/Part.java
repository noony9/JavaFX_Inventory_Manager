package C482_PA_D_Jacobs.Model;

public abstract class Part {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private boolean inHouse;

    public Part(int id, String name, double price, int stock, int min, int max, boolean inHouse){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.inHouse = inHouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public int getMin() {

        return min;
    }

    public void setMin(int min) {

        this.min = min;
    }

    public int getMax() {

        return max;
    }

    public void setMax(int max) {

        this.max = max;
    }

    public boolean isInHouse() {
        return inHouse;
    }

    public void setInHouse(boolean inHouse) {
        this.inHouse = inHouse;
    }
}
