import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * WorkerTest.java
 * JUnit 5 tests for the Worker class.
 * Tests constructors, the setter, weekly pay calculation (regular and
 * overtime), and the overridden toCSV/toJSON/toXML methods.
 *
 * @author Kirby Fortney
 */
class WorkerTest
{
    private Worker worker;

    /**
     * Runs before every test — sets up a fresh Worker so tests don't affect each other.
     */
    @BeforeEach
    void setUp()
    {
        worker = new Worker("100001", "Sam", "Wilson", "Mr.", 1990, 20.00);
    }

    // ── Constructor Tests ────────────────────────────────────────────────────

    @Test
    void testFullConstructor()
    {
        // Verify the full constructor sets inherited Person fields plus hourlyPayRate
        assertEquals("100001", worker.getID());
        assertEquals("Sam",    worker.getFirstName());
        assertEquals("Wilson", worker.getLastName());
        assertEquals("Mr.",    worker.getTitle());
        assertEquals(1990,     worker.getYOB());
        assertEquals(20.00,    worker.getHourlyPayRate());
    }

    @Test
    void testDefaultConstructor()
    {
        // Verify the no-arg constructor creates a valid Worker with defaults
        Worker defaultWorker = new Worker();
        assertEquals("000000", defaultWorker.getID());
        assertEquals(0.0,      defaultWorker.getHourlyPayRate());
    }

    // ── Setter Tests ─────────────────────────────────────────────────────────

    @Test
    void setHourlyPayRate()
    {
        // Expected: hourlyPayRate changes to new value
        worker.setHourlyPayRate(25.50);
        assertEquals(25.50, worker.getHourlyPayRate());
    }

    // ── Pay Calculation Tests ────────────────────────────────────────────────

    @Test
    void calculateWeeklyPayExactlyForty()
    {
        // 40 hours at $20/hr = $800, no overtime
        assertEquals(800.00, worker.calculateWeeklyPay(40), 0.001);
    }

    @Test
    void calculateWeeklyPayUnderForty()
    {
        // 30 hours at $20/hr = $600
        assertEquals(600.00, worker.calculateWeeklyPay(30), 0.001);
    }

    @Test
    void calculateWeeklyPayWithOvertime()
    {
        // 50 hours: 40 regular ($800) + 10 overtime at 1.5x ($300) = $1100
        assertEquals(1100.00, worker.calculateWeeklyPay(50), 0.001);
    }

    // ── Override Tests ───────────────────────────────────────────────────────

    @Test
    void toCSV()
    {
        // Expected: Person's CSV fields followed by hourlyPayRate
        String expected = "100001, Sam, Wilson, Mr., 1990, 20.0";
        assertEquals(expected, worker.toCSV());
    }

    @Test
    void toJSON()
    {
        // Expected: JSON string contains inherited fields plus hourlyPayRate
        String result = worker.toJSON();
        assertTrue(result.contains("\"ID\": \"100001\""));
        assertTrue(result.contains("\"firstName\": \"Sam\""));
        assertTrue(result.contains("\"hourlyPayRate\": 20.0"));
    }

    @Test
    void toXML()
    {
        // Expected: XML string contains inherited tags plus hourlyPayRate
        String result = worker.toXML();
        assertTrue(result.contains("<ID>100001</ID>"));
        assertTrue(result.contains("<firstName>Sam</firstName>"));
        assertTrue(result.contains("<hourlyPayRate>20.0</hourlyPayRate>"));
    }
}
