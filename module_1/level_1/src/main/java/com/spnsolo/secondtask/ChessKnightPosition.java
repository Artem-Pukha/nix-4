package com.spnsolo.secondtask;

import com.spnsolo.excpt.IncorrectPosition;

public class ChessKnightPosition {
    private static String[][] board = new String[8][8];
    private static final String[] LETTERS = {"A","B","C","D","E","F","G","H"};
    static {
       refreshBoard();
    }

    private static void refreshBoard(){
        for(int i = 0; i < 8;++i) {
            for (int j = 0; j < 8; ++j) {
                board[i][j] = " " + LETTERS[i] + (j + 1) + " ";
            }
        }
    }
    public static boolean availablePosition(String position) throws IncorrectPosition {
        position = " " + position + " ";
        for(int i = 0; i < 8;++i){
            for(int j = 0; j < 8;++j){
                if(board[i][j].equals(position)) return true;
            }
        }
        throw new IncorrectPosition();
    }

    public static void printBoard(){
        for(int i = 0; i < 8;++i) {
            for (int j = 0; j < 8; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void stepKnight(String currentPosition, String nextPosition) throws IncorrectPosition {
        if(availablePosition(currentPosition) && availablePosition(nextPosition)){
            if(switchPosition(currentPosition,nextPosition)){
                System.out.println("Board (\"[]\"- current/ \"()\" - next)");
                for(int i = 0; i < 8;++i) {
                    for (int j = 0; j < 8; ++j) {
                        if(board[i][j].equals(" " + currentPosition + " "))board[i][j] = "[" + currentPosition + "]";
                        if(board[i][j].equals((" " + nextPosition + " ")))board[i][j] = "(" + nextPosition + ")";
                    }
                }
                ChessKnightPosition.printBoard();
                ChessKnightPosition.refreshBoard();
            }else{
                System.out.println("Unavailable step!");
            }
        }

    }
    private static boolean switchPosition(String currentPosition, String nextPosition) {
        char currentLetter = currentPosition.charAt(0);
        char nextLetter = nextPosition.charAt(0);

        int currentNumber = currentPosition.charAt(1);
        int nextNumber = nextPosition.charAt(1);

        int differenceL = Math.abs(currentLetter - nextLetter);
        int differenceN = Math.abs(currentNumber - nextNumber);

        return differenceL == 2 && differenceN == 1 || differenceN == 1 && differenceL == 2;

    }
}
