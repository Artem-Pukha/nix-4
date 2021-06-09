package com.spnsolo.util;

import com.spnsolo.entity.Lesson;

import java.util.ArrayList;
import java.util.List;

public class EarliestLesson {
    public static Long defineLesson(List<Lesson> lessons){

        List<Lesson> futureLessons = new ArrayList<>();
        for(Lesson l:lessons){
            if((l.getDate().getTime()-System.currentTimeMillis()) >= 0){
                futureLessons.add(l);
            }
        }

        long minTime = futureLessons.get(0).getDate().getTime();
        long idMin = futureLessons.get(0).getId();
        long currentTimeLesson = 0;
        for (int i = 1; i < futureLessons.size(); i++) {
            currentTimeLesson = futureLessons.get(i).getDate().getTime();
            if(currentTimeLesson < minTime){
                minTime = currentTimeLesson;
                idMin = futureLessons.get(i).getId();
            }
        }
        return idMin;

    }
}
