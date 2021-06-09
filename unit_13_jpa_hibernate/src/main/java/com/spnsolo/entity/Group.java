package com.spnsolo.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "groupT")
public class Group extends BaseEntity{

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date created;

    @OneToMany(mappedBy = "group",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Student> students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Group(){
        created = new Date(System.currentTimeMillis());
        students = new ArrayList<>();
    }
    public Date getCreated() {
        return created;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setGroup(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
