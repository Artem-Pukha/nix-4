package com.spnsolo.algorithm.complexity;

import java.util.List;

public class DefinerOfFirstUniqueName {
    public static int defineFirstUnique(List<String> names){
        int indexOfFirstUnique = -1;

        int countOfRepetition;
        for (int i = 0; i < names.size(); i++) {
            countOfRepetition = 0;
            for(int j = i + 1; j <  names.size(); j++){
                if(names.get(i).equals(names.get(j))){
                    countOfRepetition++;
                    break;
                }
            }
            if(countOfRepetition==0){
                indexOfFirstUnique = i;
                break;
            }
        }
        return indexOfFirstUnique;
    }
}
