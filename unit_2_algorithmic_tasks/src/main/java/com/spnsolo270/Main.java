package com.spnsolo270;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        /////////1/////////
        System.out.println("---------------------------First task---------------------------");
        System.out.println();
        System.out.print("Enter your string: ");
        Scanner scanner1 = new Scanner(System.in);
        String enteredS1 = scanner1.nextLine();
        char[] chars = new char[enteredS1.length()];

        for(int i = 0; i < enteredS1.length(); ++i){
            chars[i] = enteredS1.charAt(i);
        }

        int sum = 0;//Sum of numbers into the string.
        int checker;//For checking if a character is number.
        for(char c: chars){
            checker = c;

            //Numbers into Unicode table from 48 to 57.
            if( 48 <= checker && checker <= 57){
                sum += (checker - 48);
            }
        }
        if(sum == 0){
            System.out.println("No one numbers into entered string!");
        }else{
            System.out.println("Sum of numbers into entered string: " + sum);
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        ///////////////////


        /////////2/////////
        System.out.println("---------------------------Second task--------------------------");
        System.out.println();

        System.out.print("Enter your string: ");
        Scanner scanner2 = new Scanner(System.in);
        String enteredS2 = scanner2.nextLine();
        System.out.println();

        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я]");
        Matcher matcher = pattern.matcher("a"+ enteredS2);

        String neededChars = new String();
        if(matcher.find()==false) {
            System.out.println("There aren't corrected symbols!");
        }else {
            while (matcher.find()) {
                neededChars += matcher.group();
            }

            int length = neededChars.length();
            int[] massOfSymbols = new int[length];
            for (int i = 0; i < length; ++i) {
                massOfSymbols[i] = neededChars.charAt(i);
            }
            Arrays.sort(massOfSymbols);

            int number = 0;
            for (int i = 0; i < length; ++i) {
                int quantity = 1;
                char symbol = (char) massOfSymbols[i];

                if (i != 0 && massOfSymbols[i] == massOfSymbols[i - 1]) continue;
                for (int j = i + 1; j < length; ++j) {
                    if (massOfSymbols[i] == massOfSymbols[j]) quantity++;
                    else break;
                }
                ++number;
                System.out.println(number + ". " + symbol + " - " + quantity);
            }
        }

        System.out.println();
        System.out.println("----------------------------------------------------------------");
        ///////////////////

        /////////3/////////
        System.out.println("---------------------------Third task---------------------------");
        System.out.println();

        try{
            System.out.print("Enter lesson number(1 - 10): ");
            Scanner scanner3 = new Scanner(System.in);
            String lessonNumberS = scanner3.nextLine();

            int lessonNumber = Integer.parseInt(lessonNumberS);

            double HinS = 3600;//Hour in seconds.

            double StartOfLessons = 9.00 * HinS;
            double LessonDuration = 0.75 * HinS;
            int numberBreaks = lessonNumber - 1;
            double BreaksDuration = (numberBreaks * 10 - numberBreaks % 2 * 5)*60;
            double timeEnd = (StartOfLessons + lessonNumber * LessonDuration + BreaksDuration)/ HinS;

            int hour = (int)timeEnd;
            int minutes = (int)Math.round(60 * (timeEnd - hour));

            String OutputTime = hour + ":" + minutes;
            System.out.println("The lesson" +'(' +lessonNumber + ')'+ " ends at "+ OutputTime + " o'clock");

        }catch(NumberFormatException e){
            System.out.println("You haven't written a number!");
        }

        System.out.println();
        System.out.println("----------------------------------------------------------------");
        ///////////////////
    }
}
