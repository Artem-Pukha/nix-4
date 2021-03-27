package com.spnsolo.thirdtask;

import java.util.Scanner;

public class ApplicationTriangle {
    private static final Scanner inner = new Scanner(System.in);
    public static void run(){
        System.out.println("--------------Triangle square--------------");
        System.out.println();
        boolean endProgram = false;
        while (endProgram == false){
            System.out.print("Enter first vertex (for example (1,2)):");
            String firstVertex = inner.nextLine();
            int[] firstCoordinates = convert(firstVertex);

            System.out.print("Enter second one (for example (3,4)):");
            String second = inner.nextLine();
            int[] secondCoordinates = convert(firstVertex);

            System.out.print("Enter third one (for example (5,6)):");
            String thirdVertex = inner.nextLine();
            int[] thirdCoordinates = convert(firstVertex);

            System.out.print("Square of this triangle: "
                    + TriangleSquare.square(firstCoordinates,secondCoordinates,thirdCoordinates));

            System.out.println();
            System.out.print("Do you want repeat(Yes - y/No - n)?:");
            String YorN = inner.nextLine();
            if (YorN.equals("n")) {
                System.out.println("End of task!");
                endProgram = true;
            }
        }
    }
    private static int[] convert(String str) throws NumberFormatException{
        str = str.replace("(", "");
        str = str.replace(")", "");
        String[] s = str.split(",");
        int[] i = new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1])};
        return i;
    }
}
