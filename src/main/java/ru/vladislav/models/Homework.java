package ru.vladislav.models;

import javax.persistence.*;

@Entity
@Table(name = "homeworks")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    public Homework() {
    }

    public Homework(Long id, String title, String description, Lesson lesson) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lesson = lesson;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
