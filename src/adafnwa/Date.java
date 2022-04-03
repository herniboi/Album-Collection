package adafnwa;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * This class stores and compares dates and checks if the dates are valid.
 * Stores day, month and year to validate, access and compare.
 * @author Andy Li, Henry Lin
 */

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Constructor method given a String of the date.
     * @param date format "mm/dd/yyyy"
     */
    public Date(String date) {
        StringTokenizer tokenizer = new StringTokenizer(date, "/");
        this.month = Integer.parseInt(tokenizer.nextToken());
        this.day = Integer.parseInt(tokenizer.nextToken());
        this.year = Integer.parseInt(tokenizer.nextToken());
    } //take “mm/dd/yyyy” and create a Date object

    /**
     * Constructor method given no parameters.
     * Sets the date to current date.
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DATE);
        this.year = calendar.get(Calendar.YEAR);
    } //create an object with today’s date (see Calendar class)

    // statics
    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;
    private static final int THE_EIGHTYS = 1980;
    private static final int SUBTRACT_TO_COMPARE = 1;
    private static final int TWENTY_NINTH_DAY = 29;
    private static final int THIRTIETH_DAY = 30;
    private static final int THIRTY_FIRST_DAY = 31;
    private static final int IS_DIVISIBLE = 0;
    private static final int BEFORE = -1;
    private static final int SAME = 0;
    private static final int AFTER = 1;

    /**
     * Checks if the date is valid
     * refer to google doc for all cases of valid dates.
     * @return true if date is valid and false otherwise
     */
    public boolean isValid() {
        if (this.year < THE_EIGHTYS){
            return false;
        }
        int month = this.month - SUBTRACT_TO_COMPARE;
        Calendar calendar = Calendar.getInstance();
        if (month > (Calendar.DECEMBER)){
            return false;
        }
        if (this.day > THIRTY_FIRST_DAY && (month == Calendar.JANUARY || month == Calendar.MARCH
                    || month == Calendar.MAY || month == Calendar.JULY || month == Calendar.AUGUST
                    || month == Calendar.OCTOBER || month == Calendar.DECEMBER )){
            return false;
        }
        if (this.day > THIRTIETH_DAY && (month == Calendar.APRIL || month == Calendar.JUNE
                || month == Calendar.SEPTEMBER || month == Calendar.NOVEMBER)){
            return false;
        }
        if (this.day > TWENTY_NINTH_DAY && month == Calendar.FEBRUARY){
            return false;
        }
        if (this.day == TWENTY_NINTH_DAY && month == Calendar.FEBRUARY){
            if (this.year % QUADRENNIAL != IS_DIVISIBLE || this.year % CENTENNIAL != IS_DIVISIBLE || this.year % QUATERCENTENNIAL != IS_DIVISIBLE){
                return false;
            }
        }
        if (this.year > calendar.get(Calendar.YEAR)){
            return false;
        }
        else if (this.year == calendar.get(Calendar.YEAR) && month > calendar.get(Calendar.MONTH)){
            return false;
        }
        else return this.year != calendar.get(Calendar.YEAR) || month != calendar.get(Calendar.MONTH)
                    || this.day <= calendar.get(Calendar.DATE);
    }

    /**
     * Compares two dates and returns whether it is before, after, or on the same date as the respective date.
     * @param date in format mm/dd/yyyy
     * @return AFTER if date after date, BEFORE if date is before date, SAME if the dates are the same
     */
    @Override
    public int compareTo(Date date) {
        if (this.year > date.year){
            return AFTER;
        }
        else if (this.year < date.year){
            return BEFORE;
        }
        else{
            if (this.month > date.month){
                return AFTER;
            }
            else if (this.month < date.month){
                return BEFORE;
            }
            else{
                if (this.day > date.day){
                    return AFTER;
                }
                else if (this.day < date.day) {
                    return BEFORE;
                }
            }
        }
        return SAME;
    }

    /**
     * Returns date as string.
     * @return date
     */
    public String toString(){
        String date;
        date = this.month + "/" + this.day + "/" + this.year;
        return date;
    }

    /**
     * Testbedmain for the program to test various test cases for the isValid() class
     * @param args
     */
    public static void main(String[] args){
        //Testing the isValid method

        //Test Case 1: Date with valid day and month but with the year < 1980
        System.out.println("Running test case 1");
        Date date1 = new Date("11/21/800");
        if (!date1.isValid()){
            System.out.print("Test Case #1, invalid date (year before 1980), PASSED.");
        } else{
            System.out.print("Test Case #1, invalid date (year before 1980), FAILED.");
        }

        //Test Case 2: A date with the month = 2, day > 28, and the year is a non-leap year
        System.out.println("\nRunning test case 2");
        Date date2 = new Date("2/29/2001");
        if (!date2.isValid()){
            System.out.print("Test Case #2, invalid date (month = 2, day > 28 for non-leap year), PASSED.");
        } else{
            System.out.print("Test Case #2, invalid date (month = 2, day > 28 for non-leap year), FAILED.");
        }

        //Test Case 3: A date with an invalid month
        System.out.println("\nRunning test case 3");
        Date date3 = new Date("13/7/2019");
        if (!date3.isValid()){
            System.out.print("Test Case #3, invalid date (invalid month), PASSED.");
        } else{
            System.out.print("Test Case #3, invalid date (invalid month), FAILED.");
        }

        //Test Case 4: A date with a month = 1, 3, 5, 7, 8, 10, 12, but day > 31
        System.out.println("\nRunning test case 4");
        Date date4 = new Date("5/32/2019");
        if (!date4.isValid()){
            System.out.print("Test Case #4, invalid date (day > 31 for months 1, 3, 5, 7, 8, 10, 12), PASSED.");
        } else{
            System.out.print("Test Case #4, invalid date (day > 31 for months 1, 3, 5, 7, 8, 10, 12), FAILED.");
        }

        //Test Case 5: A date with a valid year and month = 4, 6, 9, 11, but day> 30
        System.out.println("\nRunning test case 5");
        Date date5 = new Date("4/31/2019");
        if (!date5.isValid()){
            System.out.print("Test Case #5, invalid date (month = 4, 6, 9, 11, but day> 30), PASSED.");
        } else{
            System.out.print("Test Case #5, invalid date (month = 4, 6, 9, 11, but day> 30), FAILED.");
        }

        //Test Case 6: A date with the month = 2, day > 29, and the year is a leap year
        System.out.println("\nRunning test case 6");
        Date date6 = new Date("2/30/2001");
        if (!date6.isValid()){
            System.out.print("Test Case #6, invalid date (month = 2, day > 29, and the year is a leap year), PASSED.");
        } else{
            System.out.print("Test Case #6, invalid date (month = 2, day > 29, and the year is a leap year), FAILED.");
        }

        //Test Case 7.1: A date with the year > current year
        System.out.println("\nRunning test case 7.1");
        Date date71 = new Date("9/27/2022");
        if (!date71.isValid()){
            System.out.print("Test Case #7.1, invalid date (year > current year), PASSED.");
        } else{
            System.out.print("Test Case #7.1, invalid date (year > current year), FAILED.");
        }

        //Test Case 7.2: A date with year = to current year and the month is > current month
        System.out.println("\nRunning test case 7.2");
        Date date72 = new Date("10/27/2021");
        if (!date72.isValid()){
            System.out.print("Test Case #7.2, invalid date (year = to current year and the month is > current month), PASSED.");
        } else{
            System.out.print("Test Case #7.2, invalid date (year = to current year and the month is > current month), FAILED.");
        }

        //Test Case 7.3: A date with  year = to current year and month = current month but the date > current date
        System.out.println("\nRunning test case 7.3");
        Date date73 = new Date("9/28/2021");
        if (!date73.isValid()){
            System.out.print("Test Case #7.3, invalid date (year = to current year and month = current month but the date > current date), PASSED.");
        } else{
            System.out.print("Test Case #7.3, invalid date (year = to current year and month = current month but the date > current date), FAILED.");
        }

        //Test Case 8.1: A date with month = 1, 3, 5, 7, 8, 10, 12, and day = 31
        System.out.println("\nRunning test case 8.1");
        Date date81 = new Date("1/31/2021");
        if (date81.isValid()){
            System.out.print("Test Case #8.1, valid date (month = 1, 3, 5, 7, 8, 10, 12, and day = 31), PASSED.");
        } else{
            System.out.print("Test Case #8.1, valid date (month = 1, 3, 5, 7, 8, 10, 12, and day = 31), FAILED.");
        }

        //Test Case 8.2: A date with month = 4, 6, 9, 11, and day = 30
        System.out.println("\nRunning test case 8.2");
        Date date82 = new Date("3/11/2009");
        if (date82.isValid()){
            System.out.print("Test Case #8.2, valid date (month = 4, 6, 9, 11, and day = 30), PASSED.");
        } else{
            System.out.print("Test Case #8.2, valid date (month = 4, 6, 9, 11, and day = 30), FAILED.");
        }

        //Test Case 8.3: A date with a month = 4, 6, 9, 11, and day = 30
        System.out.println("\nRunning test case 8.3");
        Date date83 = new Date("4/30/2021");
        if (date83.isValid()){
            System.out.print("Test Case #8.3, valid date (month = 4, 6, 9, 11, and day = 30), PASSED.");
        } else{
            System.out.print("Test Case #8.3, valid date (month = 4, 6, 9, 11, and day = 30), FAILED.");
        }

        //Test Case 8.4: A date with month = 2, day = 28, and the year is a non-leap year
        System.out.println("\nRunning test case 8.4");
        Date date84 = new Date("2/28/2001");
        if (date84.isValid()){
            System.out.print("Test Case #8.4, valid date (month = 2, day = 28, and the year is a non-leap year), PASSED.");
        } else{
            System.out.print("Test Case #8.4, valid date (month = 2, day = 28, and the year is a non-leap year), FAILED.");
        }

        //Test Case 8.5: A date with year > 1980 and date < current date
        System.out.println("\nRunning test case 8.5");
        Date date85 = new Date("1/17/1990");
        if (date85.isValid()){
            System.out.print("Test Case #8.5, valid date (A date with year > 1980 and date < current date), PASSED.");
        } else{
            System.out.print("Test Case #8.5, valid date (A date with year > 1980 and date < current date), FAILED.");
        }
    }
}
