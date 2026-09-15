import java.util.Calendar;
import java.util.Objects;

/**
 * Person.java
 * Represents a person with an ID, name, title, and year of birth.
 * Provides methods to format the person's data as CSV, JSON, and XML.
 *
 * @author Kirby Fortney
 */
public class Person
{
    // ── Fields ──────────────────────────────────────────────────────────────
    private String firstName;
    private String lastName;
    private final String ID;   // ID should never change after construction
    private String title;
    private int YOB;           // Year of Birth — valid range: 1940–2010

    // ── Constructors ────────────────────────────────────────────────────────

    /**
     * Full constructor — creates a Person with all fields supplied.
     *
     * @param ID        unique identifier string, never changes
     * @param firstName person's first name
     * @param lastName  person's last name
     * @param title     honorific prefix (Mr., Mrs., Dr., Esq., etc.)
     * @param YOB       year of birth, must be between 1940 and 2010 inclusive
     */
    public Person(String ID, String firstName, String lastName, String title, int YOB)
    {
        this.ID        = ID;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.title     = title;
        this.YOB       = YOB;
    }

    /**
     * Default no-arg constructor — creates a Person with placeholder values.
     */
    public Person()
    {
        this.ID        = "000000";
        this.firstName = "Unknown";
        this.lastName  = "Unknown";
        this.title     = "N/A";
        this.YOB       = 1970;
    }

    // ── Getters ─────────────────────────────────────────────────────────────

    /**
     * Returns the person's first name.
     * @return firstName
     */
    public String getFirstName() { return firstName; }

    /**
     * Returns the person's last name.
     * @return lastName
     */
    public String getLastName()  { return lastName; }

    /**
     * Returns the person's ID. No setter — ID never changes.
     * @return ID
     */
    public String getID()        { return ID; }

    /**
     * Returns the person's title.
     * @return title
     */
    public String getTitle()     { return title; }

    /**
     * Returns the person's year of birth.
     * @return YOB
     */
    public int getYOB()          { return YOB; }

    // ── Setters (no setter for ID — it must never change) ───────────────────

    /**
     * Sets the person's first name.
     * @param firstName new first name
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Sets the person's last name.
     * @param lastName new last name
     */
    public void setLastName(String lastName)   { this.lastName = lastName; }

    /**
     * Sets the person's title.
     * @param title new honorific prefix
     */
    public void setTitle(String title)         { this.title = title; }

    /**
     * Sets the person's year of birth. Must be between 1940 and 2010.
     * If the value is out of range, the YOB is not changed.
     *
     * @param YOB new year of birth
     */
    public void setYOB(int YOB)
    {
        if (YOB >= 1940 && YOB <= 2010)
        {
            this.YOB = YOB;
        }
        else
        {
            System.out.println("YOB out of range (1940–2010): " + YOB + " — not updated.");
        }
    }

    // ── Overrides ────────────────────────────────────────────────────────────

    /**
     * Returns a human-readable summary of this Person.
     * @return formatted string with all fields
     */
    @Override
    public String toString()
    {
        return "Person{ID='" + ID + "', name='" + fullName() +
               "', title='" + title + "', YOB=" + YOB + "}";
    }

    /**
     * Two Person objects are equal if they share the same ID.
     * @param obj object to compare
     * @return true if both Persons have the same ID
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person other = (Person) obj;
        return Objects.equals(this.ID, other.ID);
    }

    // ── Additional Methods ───────────────────────────────────────────────────

    /**
     * Returns the person's full name as first name, space, last name.
     * @return "firstName lastName"
     */
    public String fullName()
    {
        return firstName + " " + lastName;
    }

    /**
     * Returns the person's formal name as title, space, full name.
     * @return "title firstName lastName"
     */
    public String formalName()
    {
        return title + " " + fullName();
    }

    /**
     * Returns the person's age based on the current calendar year.
     * Uses Calendar to get today's year dynamically.
     * @return age as a String
     */
    public String getAge()
    {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return String.valueOf(currentYear - YOB);
    }

    /**
     * Returns the person's age for a specified year.
     * Uses Calendar to validate intent; calculation is year minus YOB.
     *
     * @param year the year for which to calculate age
     * @return age as a String for the given year
     */
    public String getAge(int year)
    {
        return String.valueOf(year - YOB);
    }

    /**
     * Returns a comma-separated value (CSV) String of this Person's fields.
     * Suitable for writing directly to a text file.
     * Field order: ID, firstName, lastName, title, YOB
     *
     * @return CSV-formatted string
     */
    public String toCSV()
    {
        return ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
    }

    /**
     * Returns a JSON-formatted String representation of this Person.
     * Useful for web APIs and modern data interchange.
     *
     * @return JSON string with all fields
     */
    public String toJSON()
    {
        return "{\n" +
               "  \"ID\": \""        + ID        + "\",\n" +
               "  \"firstName\": \"" + firstName + "\",\n" +
               "  \"lastName\": \""  + lastName  + "\",\n" +
               "  \"title\": \""     + title     + "\",\n" +
               "  \"YOB\": "         + YOB       + "\n"   +
               "}";
    }

    /**
     * Returns an XML-formatted String representation of this Person.
     * Useful for legacy systems and document-style data exchange.
     *
     * @return XML string with all fields wrapped in tags
     */
    public String toXML()
    {
        return "<Person>\n" +
               "  <ID>"        + ID        + "</ID>\n"        +
               "  <firstName>" + firstName + "</firstName>\n" +
               "  <lastName>"  + lastName  + "</lastName>\n"  +
               "  <title>"     + title     + "</title>\n"     +
               "  <YOB>"       + YOB       + "</YOB>\n"       +
               "</Person>";
    }
}
