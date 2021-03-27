package com.spnsolo;

import com.spnsolo.exception.InvalidSymbol;

import java.util.Stack;

public class ValidString {

    private static char reverse(char c) throws InvalidSymbol {
        switch (c){
            case ')': return '(';
            case ']':return '[';
            case '}':return '{';
            default: throw new InvalidSymbol();
        }
    }

    public static void valid(String string) throws InvalidSymbol {
        Stack<Character> stack = new Stack<Character>();
        int checker = 0;
        for(int i = 0; i < string.length(); i++) {
            if (checker > 0) break;
            if ((string.charAt(i) == '(') || (string.charAt(i) == '[') || (string.charAt(i) == '{')){
                stack.push(string.charAt(i));
            }
            if ((string.charAt(i) == ')') || (string.charAt(i) == ']') || (string.charAt(i) == '}')) {
                if (stack.empty() || (stack.peek() != reverse(string.charAt(i)))) checker = 1;
                else stack.pop();
            }
        }

        if (!stack.empty()) checker = 1;
        if (checker > 0) System.out.println("The entered string invalid!");
        else System.out.println("Your string is validated!");
    }
}
