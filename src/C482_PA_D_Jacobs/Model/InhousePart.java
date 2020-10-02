package C482_PA_D_Jacobs.Model;

public class InhousePart extends Part {

    private int machineID;

    public InhousePart(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
