/**
 * Worker.java
 * Represents an hourly worker who is a Person that earns pay based on
 * hours worked, with overtime pay (time and a half) for hours beyond 40
 * in a week. Demonstrates inheritance by extending Person.
 *
 * @author Kirby Fortney
 */
public class Worker extends Person
{
    // ── Fields ──────────────────────────────────────────────────────────────
    private double hourlyPayRate;

    // ── Constructors ────────────────────────────────────────────────────────

    /**
     * Full constructor — creates a Worker with all Person fields plus an
     * hourly pay rate. Calls the Person constructor via super() and then
     * sets the remaining field.
     *
     * @param ID            unique identifier string, never changes
     * @param firstName     worker's first name
     * @param lastName      worker's last name
     * @param title         honorific prefix (Mr., Mrs., Dr., Esq., etc.)
     * @param YOB           year of birth, must be between 1940 and 2010 inclusive
     * @param hourlyPayRate the worker's hourly pay rate
     */
    public Worker(String ID, String firstName, String lastName, String title, int YOB, double hourlyPayRate)
    {
        super(ID, firstName, lastName, title, YOB);
        this.hourlyPayRate = hourlyPayRate;
    }

    /**
     * Default no-arg constructor — creates a Worker with placeholder Person
     * values and a $0.00 hourly pay rate.
     */
    public Worker()
    {
        super();
        this.hourlyPayRate = 0.0;
    }

    // ── Getters / Setters ───────────────────────────────────────────────────

    /**
     * Returns the worker's hourly pay rate.
     * @return hourlyPayRate
     */
    public double getHourlyPayRate() { return hourlyPayRate; }

    /**
     * Sets the worker's hourly pay rate.
     * @param hourlyPayRate new hourly pay rate
     */
    public void setHourlyPayRate(double hourlyPayRate) { this.hourlyPayRate = hourlyPayRate; }

    // ── Pay Methods ─────────────────────────────────────────────────────────

    /**
     * Calculates the worker's total pay for a week, given hours worked.
     * Hours up to 40 are paid at hourlyPayRate. Hours beyond 40 are paid
     * at time and a half (1.5x hourlyPayRate).
     *
     * @param hoursWorked total hours worked this week
     * @return total pay for the week
     */
    public double calculateWeeklyPay(double hoursWorked)
    {
        double regularHours = Math.min(hoursWorked, 40);
        double overtimeHours = Math.max(hoursWorked - 40, 0);

        double regularPay = regularHours * hourlyPayRate;
        double overtimePay = overtimeHours * hourlyPayRate * 1.5;

        return regularPay + overtimePay;
    }

    /**
     * Prints a breakdown of the worker's weekly pay to the console:
     * regular hours and pay, overtime hours and pay, and the combined total.
     *
     * @param hoursWorked total hours worked this week
     */
    public void displayWeeklyPay(double hoursWorked)
    {
        double regularHours = Math.min(hoursWorked, 40);
        double overtimeHours = Math.max(hoursWorked - 40, 0);

        double regularPay = regularHours * hourlyPayRate;
        double overtimePay = overtimeHours * hourlyPayRate * 1.5;
        double totalPay = regularPay + overtimePay;

        System.out.printf("%s: %.1f regular hrs = $%.2f, %.1f overtime hrs = $%.2f, total pay = $%.2f%n",
                fullName(), regularHours, regularPay, overtimeHours, overtimePay, totalPay);
    }

    // ── Overrides ────────────────────────────────────────────────────────────

    /**
     * Returns a comma-separated value (CSV) String of this Worker's fields,
     * including the inherited Person fields plus hourlyPayRate.
     * @return CSV-formatted string
     */
    @Override
    public String toCSV()
    {
        return super.toCSV() + ", " + hourlyPayRate;
    }

    /**
     * Returns a JSON-formatted String representation of this Worker,
     * including the inherited Person fields plus hourlyPayRate.
     * @return JSON string with all fields
     */
    @Override
    public String toJSON()
    {
        return "{\n" +
               "  \"ID\": \""          + getID()        + "\",\n" +
               "  \"firstName\": \""   + getFirstName() + "\",\n" +
               "  \"lastName\": \""    + getLastName()  + "\",\n" +
               "  \"title\": \""       + getTitle()     + "\",\n" +
               "  \"YOB\": "           + getYOB()       + ",\n"   +
               "  \"hourlyPayRate\": " + hourlyPayRate  + "\n"    +
               "}";
    }

    /**
     * Returns an XML-formatted String representation of this Worker,
     * including the inherited Person fields plus hourlyPayRate.
     * @return XML string with all fields wrapped in tags
     */
    @Override
    public String toXML()
    {
        return "<Worker>\n" +
               "  <ID>"            + getID()        + "</ID>\n"            +
               "  <firstName>"     + getFirstName() + "</firstName>\n"     +
               "  <lastName>"      + getLastName()  + "</lastName>\n"      +
               "  <title>"         + getTitle()     + "</title>\n"         +
               "  <YOB>"           + getYOB()       + "</YOB>\n"           +
               "  <hourlyPayRate>" + hourlyPayRate  + "</hourlyPayRate>\n" +
               "</Worker>";
    }
}
