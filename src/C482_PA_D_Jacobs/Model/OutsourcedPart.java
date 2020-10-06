package C482_PA_D_Jacobs.Model;

public class OutsourcedPart extends Part {
    private String companyName;

    public OutsourcedPart(String name, double price, int stock, int min, int max, String companyName) {
        super(name, price, stock, min, max);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
