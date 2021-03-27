package com.spnsolo.thirdtask;

public class TriangleSquare {
    public static double square(int[]a,int[]b,int[]c){
        double determinant = Math.abs((a[0]-c[0])*(b[1]-c[1])-(a[1]-c[1])*(b[0]-c[0]));
        double square = 1/2 * determinant;
        return square;
    }
}
