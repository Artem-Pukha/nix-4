package com.spnsolo;

import com.spnsolo.console.helper.ConsoleHelper;
import com.spnsolo.calculator.PerformerCalculator;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        ConsoleHelper.coutl("/////////Calculator/////////");

        boolean endProgram = false;
        while (endProgram == false){
            try{
                ConsoleHelper.coutl("________________________");
                ConsoleHelper.cout("|Enter the first number: ");
                String firstNumber = ConsoleHelper.cin();

                BigInteger checkerNumber = new BigInteger(firstNumber);

                ConsoleHelper.cout("|Enter the second one: ");
                String secondNumber = ConsoleHelper.cin();

                checkerNumber = new BigInteger(secondNumber);
                ConsoleHelper.coutl("________________________");
                ConsoleHelper.cout("What operation do you want perform ('+','*','-','/') ?: ");
                String symbol = ConsoleHelper.cin();
                ConsoleHelper.endl();
                char operation = symbol.charAt(0);

                boolean exist = false;
                BigDecimal result = new BigDecimal("0");
                switch (operation){
                    case '+':
                        result = PerformerCalculator.sum(firstNumber,secondNumber);
                        exist = true;
                        break;
                    case '*':
                        result = PerformerCalculator.multi(firstNumber,secondNumber);
                        exist = true;
                        break;
                    case '-':
                        result = PerformerCalculator.subtract(firstNumber,secondNumber);
                        exist = true;
                        break;
                    case '/':
                        result = PerformerCalculator.divide(firstNumber,secondNumber);
                        exist = true;
                        break;
                    default:
                        ConsoleHelper.coutl("There is no this operation!");
                }
                if(exist == true){
                    ConsoleHelper.coutl(firstNumber + " " + operation + " " + secondNumber + " = " + result);
                }
                ConsoleHelper.coutl("______________________");
                ConsoleHelper.endl();
            }catch (NumberFormatException e){
                ConsoleHelper.coutl("It isn't number.");
                ConsoleHelper.endl();
            }catch (ArithmeticException e){
                ConsoleHelper.coutl("Isn't available to divide by zero.");
                ConsoleHelper.endl();
            }


            ConsoleHelper.cout("Do you want repeat (Yes - y/No - n)?: ");
            char YorN = ConsoleHelper.cin().charAt(0);
            if(YorN == 'y'){
                ConsoleHelper.coutl("/////////////////////////////////////////");
                continue;
            }else {
                ConsoleHelper.coutl("Thank you for using!");
                endProgram = true;
            }


        }
    }
}
