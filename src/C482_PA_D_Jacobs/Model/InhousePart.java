package C482_PA_D_Jacobs.Model;

/**
 * This class extends Part and takes on the form of parts that are internal to the company, with a special machine ID.
 * */
public class InhousePart extends Part {

    private int machineID;

    /**
     * Constructor
     * @param id ID
     * @param name name
     * @param price price
     * @param stock stock inventory
     * @param min minimum inventory
     * @param max maximum inventory
     * @param machineID machine ID
     * */
    public InhousePart(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;

    }

    /**
     * This method returns the Machine ID.
     * @return int machineID
     * */
    public int getMachineID() {

        return machineID;
    }

    /** This method sets the Machine ID.
     * This method sets the Machine ID special field.
     * @param machineID The machineID
     * */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
