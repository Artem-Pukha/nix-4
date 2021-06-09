package com.spnsolo.util;

import com.spnsolo.comparator.MarkComparator;
import com.spnsolo.entity.Group;
import com.spnsolo.entity.Mark;
import com.spnsolo.entity.Student;
import com.spnsolo.entity.StudentHasMark;

import java.util.ArrayList;
import java.util.List;

public class GroupMedianMarkUtil {

    public static long defineIdGroupWithBiggestMedian(List<Group> groups){
        double maxMedian = defineGroupMedianMarkForFinalExam(groups.get(1));
        double currentMedian = 0;
        long bestGroupId = groups.get(1).getId();
        for (int i = 1; i < groups.size(); i++) {
            currentMedian = defineGroupMedianMarkForFinalExam(groups.get(i));
            if(currentMedian > maxMedian){
                maxMedian = currentMedian;
                bestGroupId = groups.get(i).getId();
            }
        }
        return bestGroupId;
    }


    public static double defineGroupMedianMarkForFinalExam(Group group){
        List<Student> students = group.getStudents();
        List<Mark> marks = new ArrayList<>();

        for(Student s:students){
            for(StudentHasMark shm:s.getStudentHasMarks()){
                if(shm.getTopics().getTitle().equals("Final exam"))marks.add(shm.getMark());
            }
        }
        marks.sort(new MarkComparator());

        if(marks.size() % 2 != 0)return Double.valueOf(marks.get(marks.size()/2+1).getValue());
        else{
            return (double)((marks.get(marks.size() / 2).getValue() + marks.get(marks.size() / 2 + 1).getValue()) / 2);
        }
    }
}
