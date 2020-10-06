package C482_PA_D_Jacobs.Model;

public class InhousePart extends Part {

    private int machineID;

    public InhousePart(String name, double price, int stock, int min, int max, int machineID) {
        super(name, price, stock, min, max);
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
