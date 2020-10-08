package C482_PA_D_Jacobs.Model;

public class OutsourcedPart extends Part {
    private String companyName;

    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, boolean inHouse, String companyName) {
        super(id, name, price, stock, min, max, inHouse);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
