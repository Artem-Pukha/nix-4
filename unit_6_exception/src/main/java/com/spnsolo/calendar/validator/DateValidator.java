package com.spnsolo.calendar.validator;

import com.spnsolo.calendar.data.MyDate;
import com.spnsolo.calendar.exceptions.MyDateException;


public class DateValidator {

    private final String[] MONTHS_NAMES = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    private final String EXCEPTION_MESSAGE = "data";

    public String dateToString(MyDate date){
        String dateString = "";

        if(date.getDay() == 0)dateString += "1";
        dateString += date.getDay() + " ";

        for(int i = 0;i<MONTHS_NAMES.length;++i){
            if(i == (date.getMonth() - 1))dateString += MONTHS_NAMES[i] + " ";
        }
        dateString += date.getYear() + " ";

        dateString += changeZeroToDoubleZero(date.getHours()) + ":";
        dateString += changeZeroToDoubleZero(date.getMinutes()) + ":";
        dateString += changeZeroToDoubleZero(date.getSeconds()) + ":";
        dateString += changeZeroToDoubleZero(date.getMilliseconds());

        return dateString;
    }

    public MyDate stringToDate(String entered) throws MyDateException {
        MyDate date;
        if(entered.contains("/")||entered.contains("-"))date = formatWithDelimiter(entered);
        else date = formatWithOutDelimiter(entered);
        return date;

    }

    private MyDate formatWithDelimiter(String input) throws MyDateException, NumberFormatException{
        MyDate date = new MyDate();

        String delimiter;
        if(input.contains("/"))delimiter ="/";
        else delimiter = "-";

        String[] split = new String[4];

        switch (delimiter){
            case "/":
                split = input.split("[/ ]");
                break;
            case"-":
                split = input.split("[- ]");
                break;
        }
        try {
            if (checkIfEntered(split[0])) {
                date.setDay(Integer.parseInt(split[0]));
            }
            if (checkIfEntered(split[1])) {
                date.setMonth(Integer.parseInt(split[1]));
            }
            if (checkIfEntered(split[2]) && split[2].length() == 2) {
                if(Integer.parseInt(split[2]) <= 21) date.setYear(2000 + Integer.parseInt(split[2]));
                else date.setYear(1900 + Integer.parseInt(split[2]));
            } else {
                date.setYear(Integer.parseInt(split[2]));
            }

            if(date.getDay() < 0 && date.getDay() > 24)throw new MyDateException("day");
            if(date.getMonth() < 0 && date.getMonth() > 12)throw new MyDateException("month");
            if(date.getYear() < 0)throw new MyDateException("year");

            if (split.length > 3) setTime(date, split[3]);
            return date;
        }catch (NumberFormatException e){
            throw new NumberFormatException("Invalid entered " + EXCEPTION_MESSAGE);
        }

    }

    private MyDate formatWithOutDelimiter(String input) throws MyDateException {

        MyDate date = new MyDate();

        String[] split = input.split("[ ]");

        try {
            boolean endOfLoop = false;
            int indexOfMonth = -1;
            for (String s : split) {
                indexOfMonth++;
                for (int j = 0; j < MONTHS_NAMES.length; j++) {
                    if (s.equals(MONTHS_NAMES[j])) {
                        date.setMonth(j + 1);
                        endOfLoop = true;
                        break;
                    }
                }
                if(endOfLoop)break;

            }
            if (indexOfMonth == 1 && split.length >= 3) {
                date.setDay(Integer.parseInt(split[0]));
                date.setYear(Integer.parseInt(split[2]));
            }
            else if (indexOfMonth == 0) {
                date.setDay(Integer.parseInt(split[1]));
                date.setYear(Integer.parseInt(split[2]));
            }
            else throw new MyDateException("format");

            if(date.getDay() < 0 && date.getDay() > 31)throw new MyDateException("day");
            if(date.getMonth() < 0 && date.getMonth() > 12)throw new MyDateException("month");
            if(date.getYear() < 0)throw new MyDateException("year");

            if (input.contains(":")) setTime(date, split[split.length - 1]);

            return date;
        }catch (NumberFormatException e){
            throw new NumberFormatException();
        }
    }
    private void setTime(MyDate date, String time) throws NumberFormatException, MyDateException {

        String[]split = time.split(":");
        try {
            for (int i = 0; i < split.length; ++i) {
                switch (i) {
                    case 0:
                        date.setHours(Integer.parseInt(split[0]));
                        if(date.getHours() < 0 && date.getHours() > 24)throw new MyDateException();
                        break;
                    case 1:
                        date.setMinutes(Integer.parseInt(split[1]));
                        if(date.getMinutes() < 0 && date.getMinutes() > 60)throw new MyDateException();
                        break;
                    case 2:
                        date.setSeconds(Integer.parseInt(split[2]));
                        if(date.getSeconds() < 0 && date.getSeconds() > 60)throw new MyDateException();
                        break;
                    case 3:
                        date.setMilliseconds(Integer.parseInt(split[3]));
                        if(date.getMilliseconds() < 0 && date.getMilliseconds() > 99)throw new MyDateException();
                        break;
                }
            }
        }catch (NumberFormatException e){
            throw new NumberFormatException("Invalid entered " + EXCEPTION_MESSAGE);
        }catch (MyDateException e){
            throw new MyDateException("time");
        }
    }
    private boolean checkIfEntered(String str){
        return !str.equals("");
    }
    private String changeZeroToDoubleZero(int value){
        if(value < 10) return "0" + value;
        return value + "";
    }


}
