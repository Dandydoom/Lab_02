import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SalaryWorkerTest.java
 * JUnit 5 tests for the SalaryWorker class.
 * Tests constructors, the setter, the overridden weekly pay calculation
 * (which ignores hoursWorked), and the overridden toCSV/toJSON/toXML methods.
 *
 * @author Kirby Fortney
 */
class SalaryWorkerTest
{
    private SalaryWorker salaryWorker;

    /**
     * Runs before every test — sets up a fresh SalaryWorker so tests don't affect each other.
     */
    @BeforeEach
    void setUp()
    {
        // $52,000/year -> exactly $1000/week, easy to check by hand
        salaryWorker = new SalaryWorker("200001", "Alex", "Rivera", "Ms.", 1988, 52000.00);
    }

    // ── Constructor Tests ────────────────────────────────────────────────────

    @Test
    void testFullConstructor()
    {
        // Verify the full constructor sets inherited Person fields plus annualSalary
        assertEquals("200001", salaryWorker.getID());
        assertEquals("Alex",   salaryWorker.getFirstName());
        assertEquals("Rivera", salaryWorker.getLastName());
        assertEquals("Ms.",    salaryWorker.getTitle());
        assertEquals(1988,     salaryWorker.getYOB());
        assertEquals(52000.00, salaryWorker.getAnnualSalary());
        // hourlyPayRate isn't meaningful for a salaried worker — should default to 0.0
        assertEquals(0.0, salaryWorker.getHourlyPayRate());
    }

    @Test
    void testDefaultConstructor()
    {
        // Verify the no-arg constructor creates a valid SalaryWorker with defaults
        SalaryWorker defaultSalaryWorker = new SalaryWorker();
        assertEquals("000000", defaultSalaryWorker.getID());
        assertEquals(0.0,      defaultSalaryWorker.getAnnualSalary());
    }

    // ── Setter Tests ─────────────────────────────────────────────────────────

    @Test
    void setAnnualSalary()
    {
        // Expected: annualSalary changes to new value
        salaryWorker.setAnnualSalary(65000.00);
        assertEquals(65000.00, salaryWorker.getAnnualSalary());
    }

    // ── Pay Calculation Tests ────────────────────────────────────────────────

    @Test
    void calculateWeeklyPayIsFixed()
    {
        // $52,000 / 52 = $1000/week, regardless of hours worked
        assertEquals(1000.00, salaryWorker.calculateWeeklyPay(40), 0.001);
    }

    @Test
    void calculateWeeklyPayIgnoresHoursWorked()
    {
        // Same weekly pay whether it's a 40-hour week or a 50-hour crunch week
        double normalWeek = salaryWorker.calculateWeeklyPay(40);
        double crunchWeek  = salaryWorker.calculateWeeklyPay(50);
        assertEquals(normalWeek, crunchWeek, 0.001);
        assertEquals(1000.00, crunchWeek, 0.001);
    }

    // ── Override Tests ───────────────────────────────────────────────────────

    @Test
    void toCSV()
    {
        // Expected: Worker's CSV fields (which include hourlyPayRate) followed by annualSalary
        String expected = "200001, Alex, Rivera, Ms., 1988, 0.0, 52000.0";
        assertEquals(expected, salaryWorker.toCSV());
    }

    @Test
    void toJSON()
    {
        // Expected: JSON string contains inherited fields plus annualSalary
        String result = salaryWorker.toJSON();
        assertTrue(result.contains("\"ID\": \"200001\""));
        assertTrue(result.contains("\"firstName\": \"Alex\""));
        assertTrue(result.contains("\"annualSalary\": 52000.0"));
    }

    @Test
    void toXML()
    {
        // Expected: XML string contains inherited tags plus annualSalary
        String result = salaryWorker.toXML();
        assertTrue(result.contains("<ID>200001</ID>"));
        assertTrue(result.contains("<firstName>Alex</firstName>"));
        assertTrue(result.contains("<annualSalary>52000.0</annualSalary>"));
    }
}
