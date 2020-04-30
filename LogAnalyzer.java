/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;


    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[31];
        monthCounts = new int [12];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }

    /**
     * Analyze the monthly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        analyzeHourlyData();
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
        /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printDailyCounts()
    {
        analyzeDailyData();
        System.out.println("Day: Count");
        for(int day = 1; day < dayCounts.length; day++) {
            System.out.println(day + ": " + dayCounts[day]);
        }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }

    /**
     * Return the number of accesses recorded in the log file.
     */
    public int numberOfAccesses()
    {
        int totalAccesses = 0;
        for (int hourCount : hourCounts){

            totalAccesses = totalAccesses + hourCount;
        }

        return totalAccesses;
    }

    /**
     * This should give the busiest hour.
     */
    public int busiestHour()
    {
        analyzeHourlyData();
        int busiestHour = 0;
        for(int hours = 1; hours < hourCounts.length; hours++) {
            if(hourCounts[hours] > hourCounts[busiestHour]) {
                busiestHour = hours;
            }
        }
        return busiestHour;
    }

    /**
     * This should give the least busy hour of the day.
     */
    public int quietestHour()
    {
        analyzeHourlyData();
        int quietestHour = 0;
        for(int hours = 1; hours < hourCounts.length; hours++) {
            if(hourCounts[hours] < hourCounts[quietestHour]) {
                quietestHour = hours;
            }
        }
        return quietestHour;
    }

    /**
     * Return the two-hour period which is busiest.
     */
    public int busiestTwoHourPeriod()
    {
        analyzeHourlyData();
        int count = 0;
        int busiestTwoHours = 0;
        for(int hours = 0; hours < hourCounts.length - 1; hours++) {
            int twoHourCount = hourCounts[hours+1] + hourCounts[hours];
            if(twoHourCount > busiestTwoHours) {
                busiestTwoHours = hours;
                count = twoHourCount;
            }
        }
        return busiestTwoHours;
    } 

    /**
     * This should give the least busy day.
     */
    public int quietestDay()
    {
        analyzeDailyData();
        int quietestDay = 0;
        for(int day = 1; day <dayCounts.length; day++) {
            if(dayCounts[day] < dayCounts[quietestDay]) {
                quietestDay = day;
            }
        }
        return quietestDay;
    }

    /**
     * This should give the busiest day.
     */
    public int busiestDay()
    {
        analyzeDailyData();
        int busiestDay = 0;
        for(int day = 1; day < dayCounts.length; day++) {
            if(dayCounts[day] > dayCounts[busiestDay]) {
                busiestDay = day;
            }
        }
        return busiestDay;
    }

    /**
     * Return the number of accesses recorded in the log file.
     */
    public void totalAccessesPerMonth()
    {
        analyzeMonthlyData();
        int totalAccessesPerMonth = 0;
        for (int month: monthCounts){
            totalAccessesPerMonth = 0;
            for (int hourCount : hourCounts){
                totalAccessesPerMonth = totalAccessesPerMonth + hourCount;
            }
            System.out.println("Month: " + month +"Total Accesses: " + totalAccessesPerMonth);
        }
    }
    
    /**
     * This should give the least busy month.
     */
    public int quietestMonth()
    {
        analyzeMonthlyData();
        int quietestMonth = 0;
        for(int month = 1; month <monthCounts.length; month++) {
            if(monthCounts[month] < monthCounts[quietestMonth]) {
                quietestMonth = month;
            }
        }
        return quietestMonth;
    }
    
    /**
     * This should give the busiest month.
     */
    public int busiestMonth()
    {
        analyzeMonthlyData();
        int busiestMonth = 0;
        for(int month = 1; month < monthCounts.length; month++) {
            if(monthCounts[month] > monthCounts[busiestMonth]) {
                busiestMonth = month;
            }
        }
        return busiestMonth;
    }
}
