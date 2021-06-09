package com.spnsolo.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "marks")
public class Mark extends BaseEntity implements Comparable<Mark>{

    @NaturalId
    @Column(nullable = false)
    private Integer value;

    @OneToMany(mappedBy = "mark",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<StudentHasMark> studentHasMarks;

    public Mark(){
        studentHasMarks = new ArrayList<>();
    }
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<StudentHasMark> getStudentHasMarks() {
        return studentHasMarks;
    }

    public void addStudentHasMark(StudentHasMark studentHasMark) {
        studentHasMarks.add(studentHasMark);
        studentHasMark.setMark(this);
    }


    @Override
    public int compareTo(Mark o) {
        return this.value - o.getValue();
    }
}
