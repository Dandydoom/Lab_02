/**
 * SalaryWorker.java
 * Represents a salaried worker who is a Worker paid a fixed annual salary
 * rather than an hourly rate. Demonstrates further inheritance by extending
 * Worker (which itself extends Person), and overrides Worker's pay behavior.
 *
 * @author Kirby Fortney
 */
public class SalaryWorker extends Worker
{
    // ── Fields ──────────────────────────────────────────────────────────────
    private double annualSalary;

    // ── Constructors ────────────────────────────────────────────────────────

    /**
     * Full constructor — creates a SalaryWorker with all Person fields plus
     * an annual salary. Calls the Worker constructor via super(), passing
     * 0.0 for hourlyPayRate since a salaried worker isn't paid by the hour.
     *
     * @param ID           unique identifier string, never changes
     * @param firstName    worker's first name
     * @param lastName     worker's last name
     * @param title        honorific prefix (Mr., Mrs., Dr., Esq., etc.)
     * @param YOB          year of birth, must be between 1940 and 2010 inclusive
     * @param annualSalary the worker's fixed yearly salary
     */
    public SalaryWorker(String ID, String firstName, String lastName, String title, int YOB, double annualSalary)
    {
        super(ID, firstName, lastName, title, YOB, 0.0);
        this.annualSalary = annualSalary;
    }

    /**
     * Default no-arg constructor — creates a SalaryWorker with placeholder
     * Worker/Person values and a $0.00 annual salary.
     */
    public SalaryWorker()
    {
        super();
        this.annualSalary = 0.0;
    }

    // ── Getters / Setters ───────────────────────────────────────────────────

    /**
     * Returns the worker's annual salary.
     * @return annualSalary
     */
    public double getAnnualSalary() { return annualSalary; }

    /**
     * Sets the worker's annual salary.
     * @param annualSalary new annual salary
     */
    public void setAnnualSalary(double annualSalary) { this.annualSalary = annualSalary; }

    // ── Pay Methods (Overridden) ────────────────────────────────────────────

    /**
     * Calculates the worker's weekly pay as a flat fraction of their annual
     * salary (annualSalary / 52). The hoursWorked parameter is not used —
     * it is retained only so SalaryWorker can be used polymorphically
     * wherever a Worker is expected.
     *
     * @param hoursWorked ignored — present only for polymorphism with Worker
     * @return the flat weekly pay (1/52 of annual salary)
     */
    @Override
    public double calculateWeeklyPay(double hoursWorked)
    {
        return annualSalary / 52;
    }

    /**
     * Prints this SalaryWorker's weekly pay to the console, making clear
     * that it's a fixed fraction (1/52) of the annual salary rather than
     * hours-based pay.
     *
     * @param hoursWorked ignored — present only for polymorphism with Worker
     */
    @Override
    public void displayWeeklyPay(double hoursWorked)
    {
        double weeklyPay = calculateWeeklyPay(hoursWorked);
        System.out.printf("%s (salaried): weekly pay = $%.2f (1/52 of $%.2f annual salary)%n",
                fullName(), weeklyPay, annualSalary);
    }

    // ── Overrides ────────────────────────────────────────────────────────────

    /**
     * Returns a comma-separated value (CSV) String of this SalaryWorker's
     * fields, including the inherited Person/Worker fields plus annualSalary.
     * @return CSV-formatted string
     */
    @Override
    public String toCSV()
    {
        return super.toCSV() + ", " + annualSalary;
    }

    /**
     * Returns a JSON-formatted String representation of this SalaryWorker,
     * including the inherited Person/Worker fields plus annualSalary.
     * @return JSON string with all fields
     */
    @Override
    public String toJSON()
    {
        return "{\n" +
               "  \"ID\": \""            + getID()            + "\",\n" +
               "  \"firstName\": \""     + getFirstName()     + "\",\n" +
               "  \"lastName\": \""      + getLastName()      + "\",\n" +
               "  \"title\": \""         + getTitle()         + "\",\n" +
               "  \"YOB\": "             + getYOB()           + ",\n"   +
               "  \"hourlyPayRate\": "   + getHourlyPayRate() + ",\n"   +
               "  \"annualSalary\": "    + annualSalary        + "\n"   +
               "}";
    }

    /**
     * Returns an XML-formatted String representation of this SalaryWorker,
     * including the inherited Person/Worker fields plus annualSalary.
     * @return XML string with all fields wrapped in tags
     */
    @Override
    public String toXML()
    {
        return "<SalaryWorker>\n" +
               "  <ID>"            + getID()            + "</ID>\n"            +
               "  <firstName>"     + getFirstName()     + "</firstName>\n"     +
               "  <lastName>"      + getLastName()      + "</lastName>\n"      +
               "  <title>"         + getTitle()         + "</title>\n"         +
               "  <YOB>"           + getYOB()           + "</YOB>\n"           +
               "  <hourlyPayRate>" + getHourlyPayRate() + "</hourlyPayRate>\n" +
               "  <annualSalary>"  + annualSalary        + "</annualSalary>\n" +
               "</SalaryWorker>";
    }
}
