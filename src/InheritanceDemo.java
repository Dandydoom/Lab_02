import java.util.ArrayList;

/**
 * InheritanceDemo.java
 * Main program demonstrating the Person -> Worker -> SalaryWorker
 * inheritance hierarchy. Creates a mix of hourly Workers and salaried
 * SalaryWorkers, stores them polymorphically in a single ArrayList<Worker>,
 * and simulates three weekly pay periods for all of them — a normal
 * 40-hour week, a 50-hour crunch week, and a normal week again.
 *
 * @author Kirby Fortney
 */
public class InheritanceDemo
{
    public static void main(String[] args)
    {
        ArrayList<Worker> workers = new ArrayList<>();

        // Three hourly Workers, with reasonable hourly rates
        workers.add(new Worker("100001", "Sam",   "Wilson",  "Mr.", 1990, 18.50));
        workers.add(new Worker("100002", "Priya", "Nair",    "Ms.", 1995, 22.00));
        workers.add(new Worker("100003", "Diego", "Alvarez", "Mr.", 1988, 20.75));

        // Three salaried SalaryWorkers, with reasonable annual salaries.
        // SalaryWorker IS-A Worker, so it goes into the same ArrayList<Worker>.
        workers.add(new SalaryWorker("200001", "Alex",   "Rivera", "Ms.", 1988, 52000.00));
        workers.add(new SalaryWorker("200002", "Morgan", "Chen",   "Dr.", 1979, 78000.00));
        workers.add(new SalaryWorker("200003", "Jordan", "Patel",  "Mr.", 1992, 61000.00));

        // Week 1: normal 40-hour week. Week 2: crunch time, everyone works 50.
        // Week 3: back to a normal 40-hour week.
        double[] weeklyHours = { 40.0, 50.0, 40.0 };

        for (int week = 1; week <= weeklyHours.length; week++)
        {
            double hoursThisWeek = weeklyHours[week - 1];

            System.out.println();
            System.out.println("============================================================");
            System.out.printf("Week %d — %.0f hours worked%n", week, hoursThisWeek);
            System.out.println("============================================================");
            System.out.printf("%-20s %-10s %-12s%n", "Name", "Type", "Weekly Pay");
            System.out.println("------------------------------------------------------------");

            for (Worker w : workers)
            {
                // Polymorphism: calculateWeeklyPay() dispatches to Worker's
                // hourly logic or SalaryWorker's overridden fixed-salary logic
                // depending on the actual runtime type of each object.
                String type = (w instanceof SalaryWorker) ? "Salaried" : "Hourly";
                double pay = w.calculateWeeklyPay(hoursThisWeek);
                System.out.printf("%-20s %-10s $%,10.2f%n", w.fullName(), type, pay);
            }
        }
    }
}
