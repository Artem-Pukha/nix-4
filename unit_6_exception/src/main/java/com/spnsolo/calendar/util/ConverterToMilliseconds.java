package com.spnsolo.calendar.util;

import com.spnsolo.calendar.data.MyDate;

public class ConverterToMilliseconds {
    static private final long MILLISECONDS = 1;
    static private final long SECOND = MILLISECONDS * 1000;
    static private final long MINUTE = SECOND * 60;
    static private final long HOUR = MINUTE * 60;
    static private final long DAY = HOUR * 24;

    static private final long COMMON_YEAR = DAY * 365;
    static private final long LEAP_YEAR = COMMON_YEAR + DAY;


    static public long secondsToMilli(long seconds){ return SECOND * seconds; }
    static public long minutesToMilli(long minutes){ return MINUTE * minutes; }
    static public long hoursToMilli(long hours){
        return HOUR * hours;
    }
    static public long daysToMilli(long days){ return DAY * days; }
    static public long yearsToMilli(int year){
        int leapYears =(year / 4) - (year / 100) + (year / 400);
        int commonYears = year - leapYears;

        long leapMilli = LEAP_YEAR * leapYears;
        long commonMilli = COMMON_YEAR * commonYears;

        return commonMilli + leapMilli;
    }
    static public long centuryToMilli(int centuries){
        return yearsToMilli(100) * centuries;
    }

    public static long dateIntoMilliseconds(MyDate date){
        long dateMilliseconds = 0;

        dateMilliseconds += yearsToMilli(date.getYear());
        dateMilliseconds += monthsBeforeCurrentMothOfDateToMilliseconds(date.getMonth(),date.getYear());
        dateMilliseconds += daysToMilli(date.getDay());
        dateMilliseconds += hoursToMilli(date.getHours());
        dateMilliseconds += minutesToMilli(date.getMinutes());
        dateMilliseconds += secondsToMilli(date.getSeconds());
        dateMilliseconds += date.getMilliseconds();

        return dateMilliseconds;
    }
    public static long monthsBeforeCurrentMothOfDateToMilliseconds(int numberOfMonth, int year){
        long monthMilliseconds = 0;
        numberOfMonth--;
        switch (numberOfMonth){
            case 1:
            case 3:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 12:
                monthMilliseconds += ConverterToMilliseconds.daysToMilli(31);
                monthMilliseconds += monthsBeforeCurrentMothOfDateToMilliseconds(numberOfMonth,year);
                break;
            case 2:
                if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0)monthMilliseconds += ConverterToMilliseconds.daysToMilli(29);
                else monthMilliseconds += ConverterToMilliseconds.daysToMilli(28);
                monthMilliseconds += monthsBeforeCurrentMothOfDateToMilliseconds(numberOfMonth,year);
                break;
            case 0:
                break;
            default:
                monthMilliseconds += ConverterToMilliseconds.daysToMilli(30);
                monthMilliseconds += monthsBeforeCurrentMothOfDateToMilliseconds(numberOfMonth,year);
        }
        return monthMilliseconds;
    }

}
