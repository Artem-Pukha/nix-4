package com.spnsolo;

import com.spnsolo.calendar.comparator.MyDateComparator;
import com.spnsolo.calendar.data.MyDate;
import com.spnsolo.calendar.exceptions.MyDateException;
import com.spnsolo.calendar.util.CalculatorDates;
import com.spnsolo.calendar.util.ConverterToMilliseconds;
import com.spnsolo.calendar.util.InverterFromMilliseconds;
import com.spnsolo.calendar.validator.DateValidator;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CalendarApplication {
    private static final Scanner INNER = new Scanner(System.in);
    private static final DateValidator VALIDATOR = new DateValidator();
    private static final String[] FORMATS = new String[6];
    private static final String ANSWER = "What do you want to ";
    private static final String TYPES = """
                        Milliseconds(1)
                        Seconds(2)
                        Minutes(3)
                        Hours(4)
                        Days(5)
                        Years(6)
                        Centuries(7)
                        Type?:""";
    static {
        FORMATS[0]="dd/mm/yy - 01/12/34 (1 December 1934)";
        FORMATS[1]="m/d/yyyy - 3/4/2021";
        FORMATS[2]="mmm-d-yy - March 4 2021 24:23";
        FORMATS[3]="dd-mmm-yyyy 00:00 - 09 April 789 24:23";
        FORMATS[4]="dd-mm-yy - 01-12-34 (1 December 1934)";
        FORMATS[5]="m-d-yyyy - 3-4-2021 23:23";
    }


    public static void run(){
        System.out.println("/////////////Calendar/////////////");
        printAvailableFormats();
        printAvailableCombinations();
        boolean endProgram = false;
        while(!endProgram){
            System.out.println();
            System.out.println("""
                    Operations:\s
                    1. Difference between dates (in centuries, years, days, hours, minutes, seconds, milliseconds)(1)\s
                    2. Add or subtract(centuries, years, days, hours, minutes, seconds, milliseconds) to/from date(2)\s
                    3. Compare list of dates, sort them(3)\s 
                    """);
            System.out.print("What do you want?:");
            String operation = INNER.nextLine();
            selectOptions(operation);
            System.out.println("Finish the program (Yes - y/No- n)?");
            String YorNo = INNER.nextLine();
            if(YorNo.equals("y")){
                System.out.println("Thank you for using!");
                endProgram = true;
            }
        }
        INNER.close();
    }
    private static void selectOptions(String index){
        switch (index){
            case "1"->differenceBetweenDates();
            case "2"-> addOrSubtract();
            case "3"->sortDates();
        }
    }
    private static void sortDates(){
        System.out.println();
        while (true) {
            try {
                System.out.print("Enter the first date (Or print available formats(0)/combinations(1)):");
                String first = INNER.nextLine();
                if (first.equals("0") || first.equals("1")) selectPrintFunctions(first);
                else {
                    MyDate firstDate = VALIDATOR.stringToDate(first);

                    System.out.print("Enter the second one:");
                    String second = INNER.nextLine();
                    MyDate secondDate = VALIDATOR.stringToDate(second);

                    System.out.print("Enter the third one:");
                    String third = INNER.nextLine();
                    MyDate thirdDate = VALIDATOR.stringToDate(second);

                    Set<MyDate> dates = new TreeSet<>(new MyDateComparator());
                    dates.add(firstDate);
                    dates.add(secondDate);
                    dates.add(thirdDate);

                    System.out.println("The result dates: ");
                    for (MyDate o: dates) {
                        System.out.println(VALIDATOR.dateToString(o));
                    }

                    System.out.println("End function(Yes - y/No- n)?");
                    String YorNo = INNER.nextLine();
                    if (YorNo.equals("y")) {
                        break;
                    }
                }
            } catch (MyDateException e) {
                System.out.println(e);
            }
        }
    }
    private static void addOrSubtract(){
        System.out.println();
        while (true) {
            try {
                System.out.print("Enter the date (Or print available formats(0)/combinations(1)):");
                String first = INNER.nextLine();
                if (first.equals("0") || first.equals("1")) selectPrintFunctions(first);
                else {
                    MyDate date = VALIDATOR.stringToDate(first);

                    System.out.print("What do you want(add - (+)/subtract - (-))?");
                    String operation = selectOperation();

                    long operand;
                    if(operation.equals("-")) {
                        System.out.println(ANSWER + "subtract?");
                        operand = selectMeasureAndConvertToMilliseconds();
                        date = CalculatorDates.subtractMillisecondsFromDate(date,operand);
                    }else {
                        System.out.println(ANSWER + "add?");
                        operand = selectMeasureAndConvertToMilliseconds();
                        date = CalculatorDates.addMillisecondsToDate(date,operand);
                    }

                    System.out.println("The result date: " + VALIDATOR.dateToString(date));
                    System.out.println("End function(Yes - y/No- n)?");
                    String YorNo = INNER.nextLine();
                    if (YorNo.equals("y")) {
                        break;
                    }
                }
            } catch (MyDateException e) {
                System.out.println(e);
            }
        }
    }

    private static void differenceBetweenDates(){
        System.out.println();
        while (true) {
            try {
                System.out.print("Enter the first date (Or print available formats(0)/combinations(1)):");
                String first = INNER.nextLine();
                if (first.equals("0") || first.equals("1")) selectPrintFunctions(first);
                else {
                    MyDate firstDate = VALIDATOR.stringToDate(first);

                    System.out.print("Enter the second one:");
                    String second = INNER.nextLine();
                    MyDate secondDate = VALIDATOR.stringToDate(second);

                    long result = CalculatorDates.differenceBetweenDates(firstDate, secondDate);
                    printOutputOfOperationInSelectedType(result);


                    System.out.println("End function(Yes - y/No- n)?");
                    String YorNo = INNER.nextLine();
                    if (YorNo.equals("y")) {
                        break;
                    }
                }
            } catch (MyDateException e) {
                System.out.println(e);
            }
        }
    }

    private static void printAvailableFormats(){
        System.out.println("There are several available formats for entering date:");
        for (int i = 0; i < FORMATS.length; ++i) {
            System.out.println(i + " " + FORMATS[i] + ";");
        }
        System.out.println();
    }
    private static void  printAvailableCombinations(){
        System.out.println("Some available combinations:");
        System.out.println("1/10/34 - 1 October 1934 year 0 hour 0 minutes 0 seconds 0 milliseconds");
        System.out.println("1/10/34 10:20:30:40 - 1 October 1934 year 10 hour 20 minutes 30 seconds 40 milliseconds");
        System.out.println("1/10/34 10:20 - 1 October 1934 year 10 hour 20 minutes");
        System.out.println("/10/34 10:20 - 1 October 1934 year 10 hour 20 minutes");
        System.out.println("2//34 10:20 - 2 January 1934 year 10 hour 20 minutes");
        System.out.println("3-4-2021 23:23 - 2 April 1934 year 23 hour 23 minutes");
        System.out.println("09 April 789 24:23 - 9 April 789 year 24 hour 23 minutes");
        System.out.println("April 9 789 24:23 - 9 April 789 year 24 hour 23 minutes");
    }
    private static void selectPrintFunctions(String operation){
        switch (operation) {
            case "0" -> printAvailableFormats();
            case "1" -> printAvailableCombinations();
        }
    }
    private static void printOutputOfOperationInSelectedType(long milliseconds) {
        boolean endLoop;
        do {
            endLoop = true;
            System.out.println("How to display the result?");
            System.out.print("Difference in: \s" + TYPES );
            String type = INNER.nextLine();
            System.out.println();
            String s = "Difference between dates in:\n";
            switch (type) {
                case "1" -> System.out.println(s + "Milliseconds: " + milliseconds);
                case "2" -> System.out.println(s + "Seconds: " + InverterFromMilliseconds.toSeconds(milliseconds));
                case "3" -> System.out.println(s + "Minutes: " + InverterFromMilliseconds.toMinutes(milliseconds));
                case "4" -> System.out.println(s + "Hours: " + InverterFromMilliseconds.toHours(milliseconds));
                case "5" -> System.out.println(s + "Days: " + InverterFromMilliseconds.toDays(milliseconds));
                case "6" -> System.out.println(s + "Years: " + InverterFromMilliseconds.toYears(milliseconds));
                case "7" -> System.out.println(s + "Centuries: " + InverterFromMilliseconds.toCentury(milliseconds));
                default -> {
                    System.out.println("Incorrect symbol");
                    endLoop = false;
                }
            }
        }while (!endLoop);
    }
    private static String selectOperation(){
        while (true) {
            String operation = INNER.nextLine();
            switch (operation) {
                case "-":
                    return "-";
                case "+":
                    return "+";
                default:
                    System.out.println("Incorrect operation");
            }
        }
    }
    private static long selectMeasureAndConvertToMilliseconds(){
        boolean endLoop = true;
        do {
            try {
                endLoop = true;
                System.out.print(TYPES);
                String type = INNER.nextLine();
                System.out.print("Value: ");
                String value = INNER.nextLine();
                long valueInLong = Long.parseLong(value);
                switch (type) {
                    case "1" -> {
                        return valueInLong;
                    }
                    case "2" -> {
                        return ConverterToMilliseconds.secondsToMilli(valueInLong);
                    }
                    case "3" -> {
                        return ConverterToMilliseconds.minutesToMilli((valueInLong));
                    }
                    case "4" -> {
                        return ConverterToMilliseconds.hoursToMilli((valueInLong));
                    }
                    case "5" -> {
                        return ConverterToMilliseconds.daysToMilli((valueInLong));
                    }
                    case "6" -> {
                        return ConverterToMilliseconds.yearsToMilli(((int)valueInLong));
                    }
                    case "7" -> {
                        return ConverterToMilliseconds.centuryToMilli(((int)valueInLong));
                    }
                    default -> {
                        System.out.println("Incorrect type");
                        endLoop = false;
                    }
                }
            }catch (NumberFormatException e){
                System.out.println("Incorrect value");
            }
        }while (!endLoop);
        return 0;
    }

}
