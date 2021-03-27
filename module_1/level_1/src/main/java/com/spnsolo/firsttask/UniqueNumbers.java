package com.spnsolo.firsttask;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class UniqueNumbers {
    public static int unique(Integer[]array){
        Set<Integer> unique = new LinkedHashSet<Integer>(Arrays.asList(array));
        return unique.size();
    }
}
