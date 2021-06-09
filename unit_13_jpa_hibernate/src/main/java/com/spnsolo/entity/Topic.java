package com.spnsolo.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic extends BaseEntity{

    @NaturalId
    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "topic",fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "topic",fetch = FetchType.LAZY)
    private Set<StudentHasMark> studentHasMarks;

    public Topic(){
        lessons = new ArrayList<>();
        studentHasMarks = new HashSet<>();
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void addLessons(Lesson lesson) {
        lessons.add(lesson);
        lesson.setTopic(this);
    }

    public Set<StudentHasMark> getStudentHasMarks() {
        return studentHasMarks;
    }

    public void addStudentHasMarks(StudentHasMark studentHasMark) {
        studentHasMarks.add(studentHasMark);
        studentHasMark.setTopic(this);
    }
}
