package ru.vladislav.models;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int score;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Score() {
    }

    public Score(Long id, int score, Lesson lesson, Student student) {
        this.id = id;
        this.score = score;
        this.lesson = lesson;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public Student getStudent() {
        return student;
    }
}
