package com.spnsolo;

import com.spnsolo.entity.*;
import com.spnsolo.util.EarliestLesson;
import com.spnsolo.util.GroupMedianMarkUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()){
            log.info("Task 1");
            defineEarliestStudentLesson(1L,session);
            log.info("Task 2");
            defineBestTeacherGroup(2L,session);

        }
    }

    public static void defineEarliestStudentLesson(Long id, Session session){
        try {
            session.getTransaction().begin();
            Student student = session.find(Student.class,id);
            Group group = student.getGroup();
            Course course = group.getCourse();
            List<Lesson> lessons = course.getLessons();

            long idEarliestLesson = EarliestLesson.defineLesson(lessons);

            Lesson lesson = session.find(Lesson.class,idEarliestLesson);
            Date date = lesson.getDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String strDate = dateFormat.format(date);

            Teacher teacher = session.find(Teacher.class, lesson.getTeacher().getId());
            Topic topic = session.find(Topic.class, lesson.getTopic().getId());
            course = session.find(Course.class,lesson.getCourse().getId());
            log.info("Earliest lesson: " + strDate + "; teacher: " + teacher.getFirstName() + " " +
                    teacher.getSecondName() + "; topic: " + topic.getTitle() + "; course: " + course.getTitle());

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public static void defineBestTeacherGroup(Long id,Session session){
        try {
            session.getTransaction().begin();
            Teacher teacher = session.find(Teacher.class,id);
            List<Lesson> lessons = teacher.getLessons();

            Set<Course> teacherCoursers = new HashSet<>();
            for (Lesson l:lessons){
                teacherCoursers.add(session.find(Course.class,l.getCourse().getId()));
            }

            List<Group> teacherGroups = new ArrayList<>();
            for(Course c: teacherCoursers){
                teacherGroups.addAll(c.getGroups());
            }

            long bestGroupId = GroupMedianMarkUtil.defineIdGroupWithBiggestMedian(teacherGroups);
            Group bestGroup = session.find(Group.class,bestGroupId);

            log.info("Best group of " + teacher.getFirstName() + " " + teacher.getSecondName() + " is " +
                    bestGroup.getId() + " " + bestGroup.getCourse().getTitle());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
