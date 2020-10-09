package C482_PA_D_Jacobs.Model;

/**
 * This class extends Part and takes on the form of parts that are external to the company, with a special Company Name.
 * */

public class OutsourcedPart extends Part {
    private String companyName;

    /**
     * Constructor
     * */
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, boolean inHouse, String companyName) {
        super(id, name, price, stock, min, max, inHouse);
        this.companyName = companyName;
    }

    /**
     * This method returns the Company Name.
     * @return String Company Name
     * */
    public String getCompanyName() {

        return companyName;
    }

    /** This method sets the Company Name.
     * This method sets the Company Name special field.
     * @param companyName The Company Name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
