import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PersonTest.java
 * JUnit 5 tests for the Person class.
 * Tests constructors, setters, and all additional specified methods.
 * Getter-only methods are not tested per assignment requirements.
 *
 * @author Kirby Fortney
 */
class PersonTest
{
    // Test Person instances created fresh before each test
    private Person person;
    private Person personSameID;
    private Person personDiffID;

    /**
     * Runs before every test — sets up fresh Person objects so tests don't affect each other.
     */
    @BeforeEach
    void setUp()
    {
        person       = new Person("000001", "Bilbo", "Baggins", "Esq.", 1985);
        personSameID = new Person("000001", "Frodo", "Baggins", "Esq.", 1990); // same ID, different name
        personDiffID = new Person("000002", "Samwise", "Gamgee", "Mr.", 1988); // different ID
    }

    // ── Constructor Tests ────────────────────────────────────────────────────

    @Test
    void testFullConstructor()
    {
        // Verify the full constructor sets all fields correctly
        assertEquals("000001", person.getID());
        assertEquals("Bilbo",   person.getFirstName());
        assertEquals("Baggins", person.getLastName());
        assertEquals("Esq.",    person.getTitle());
        assertEquals(1985,      person.getYOB());
    }

    @Test
    void testDefaultConstructor()
    {
        // Verify the no-arg constructor creates a valid Person with defaults
        Person defaultPerson = new Person();
        assertEquals("000000", defaultPerson.getID());
        assertEquals("Unknown", defaultPerson.getFirstName());
        assertEquals("Unknown", defaultPerson.getLastName());
        assertEquals("N/A",    defaultPerson.getTitle());
        assertEquals(1970,     defaultPerson.getYOB());
    }

    // ── Setter Tests ─────────────────────────────────────────────────────────

    @Test
    void setFirstName()
    {
        // Expected: firstName changes to new value
        person.setFirstName("Gandalf");
        assertEquals("Gandalf", person.getFirstName());
    }

    @Test
    void setLastName()
    {
        // Expected: lastName changes to new value
        person.setLastName("Grey");
        assertEquals("Grey", person.getLastName());
    }

    @Test
    void setTitle()
    {
        // Expected: title changes to new value
        person.setTitle("Prof.");
        assertEquals("Prof.", person.getTitle());
    }

    @Test
    void setYOB()
    {
        // Expected: valid YOB in range updates successfully
        person.setYOB(2000);
        assertEquals(2000, person.getYOB());

        // Expected: out-of-range YOB does NOT change the value
        person.setYOB(1800); // too low
        assertEquals(2000, person.getYOB()); // should still be 2000

        person.setYOB(2050); // too high
        assertEquals(2000, person.getYOB()); // should still be 2000
    }

    // ── Override Tests ───────────────────────────────────────────────────────

    @Test
    void testToString()
    {
        // Expected: toString contains key fields
        String result = person.toString();
        assertTrue(result.contains("000001"));
        assertTrue(result.contains("Bilbo"));
        assertTrue(result.contains("Baggins"));
        assertTrue(result.contains("Esq."));
        assertTrue(result.contains("1985"));
    }

    @Test
    void testEquals()
    {
        // Expected: same ID = equal, even if names differ
        assertEquals(person, personSameID);

        // Expected: different ID = not equal
        assertNotEquals(person, personDiffID);
    }

    // ── Additional Method Tests ───────────────────────────────────────────────

    @Test
    void fullName()
    {
        // Expected: "firstName lastName"
        assertEquals("Bilbo Baggins", person.fullName());
    }

    @Test
    void formalName()
    {
        // Expected: "title firstName lastName"
        assertEquals("Esq. Bilbo Baggins", person.formalName());
    }

    @Test
    void getAge()
    {
        // Expected: current year minus YOB — use Calendar so test stays valid year to year
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String expected = String.valueOf(currentYear - 1985);
        assertEquals(expected, person.getAge());
    }

    @Test
    void testGetAge()
    {
        // Expected: age for a specific year: 2000 - 1985 = 15
        assertEquals("15", person.getAge(2000));
    }

    @Test
    void toCSV()
    {
        // Expected: comma-delimited string matching file format
        String expected = "000001, Bilbo, Baggins, Esq., 1985";
        assertEquals(expected, person.toCSV());
    }

    @Test
    void toJSON()
    {
        // Expected: JSON string contains all key fields
        String result = person.toJSON();
        assertTrue(result.contains("\"ID\": \"000001\""));
        assertTrue(result.contains("\"firstName\": \"Bilbo\""));
        assertTrue(result.contains("\"lastName\": \"Baggins\""));
        assertTrue(result.contains("\"title\": \"Esq.\""));
        assertTrue(result.contains("\"YOB\": 1985"));
    }

    @Test
    void toXML()
    {
        // Expected: XML string contains all key tags and values
        String result = person.toXML();
        assertTrue(result.contains("<ID>000001</ID>"));
        assertTrue(result.contains("<firstName>Bilbo</firstName>"));
        assertTrue(result.contains("<lastName>Baggins</lastName>"));
        assertTrue(result.contains("<title>Esq.</title>"));
        assertTrue(result.contains("<YOB>1985</YOB>"));
    }
}
