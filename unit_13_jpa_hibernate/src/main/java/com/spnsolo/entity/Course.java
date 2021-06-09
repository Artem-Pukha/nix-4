package com.spnsolo.entity;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Table(name = "courses")
public class Course extends BaseEntity {

    @NaturalId
    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Group> groups;

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private List<Lesson> lessons;


    public Course() {
        groups = new ArrayList<>();
        lessons = new ArrayList<>();
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public List<Group> getGroups() { return groups; }

    public void addGroup(Group group){
        groups.add(group);
        group.setCourse(this);
    }

    public List<Lesson> getLessons() { return lessons; }

    public void addLesson(Lesson lesson){
        lessons.add(lesson);
        lesson.setCourse(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getTitle().equals(course.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}
