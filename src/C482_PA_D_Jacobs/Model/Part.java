package C482_PA_D_Jacobs.Model;

/**
 * This abstract class is the model that derived parts will extend.
 * */
public abstract class Part {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private boolean inHouse;

    /**
     * Constructor
     * @param id ID
     * @param name name
     * @param price price
     * @param stock stock inventory
     * @param min minimum inventory
     * @param max maximum inventory
     * @param inHouse flag for inhouse or outsourced part
     * */
    public Part(int id, String name, double price, int stock, int min, int max, boolean inHouse){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.inHouse = inHouse;
    }

    /**
     * This method returns the ID.
     * @return int id
     * */
    public int getId() {
        return id;
    }

    /** This method sets the ID.
     * This method sets the ID field.
     * @param id The id
     * */
    public void setId(int id) {

        this.id = id;
    }

    /**
     * This method returns the Part Name.
     * @return String name
     * */
    public String getName() {

        return name;
    }

    /** This method sets the Name.
     * This method sets the Name field.
     * @param name The name
     * */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * This method returns the Part Price.
     * @return double price
     * */
    public double getPrice() {

        return price;
    }

    /** This method sets the Price.
     * This method sets the Price field.
     * @param price The price
     * */
    public void setPrice(double price) {

        this.price = price;
    }

    /**
     * This method returns the Part Stock.
     * @return int stock
     * */
    public int getStock() {

        return stock;
    }

    /** This method sets the Stock.
     * This method sets the Stock field.
     * @param stock The stock
     * */
    public void setStock(int stock) {

        this.stock = stock;
    }

    /**
     * This method returns the Part Minimum Inventory.
     * @return int min
     * */
    public int getMin() {

        return min;
    }

    /** This method sets the Part Minimum Inventory.
     * This method sets the Part Minimum Inventory field.
     * @param min The min
     * */
    public void setMin(int min) {

        this.min = min;
    }

    /**
     * This method returns the Part Maximum Inventory.
     * @return int max
     * */
    public int getMax() {

        return max;
    }

    /** This method sets the Part Maximum Inventory.
     * This method sets the Part Maximum Inventory field.
     * @param max The max
     * */
    public void setMax(int max) {

        this.max = max;
    }

    /**
     * This method returns the true if the Part is Inhouse.
     * @return boolean inHouse
     * */
    public boolean isInHouse() {

        return inHouse;
    }

    /** This method sets the part to Inhouse.
     * This method sets an identifier, indicating if the Part is Inhouse or not.
     * @param inHouse The part is Inhouse or Outsourced
     * */
    public void setInHouse(boolean inHouse) {

        this.inHouse = inHouse;
    }
}
