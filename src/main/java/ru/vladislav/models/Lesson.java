package ru.vladislav.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.vladislav.converters.LocalDateAttributeConverter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "date_lesson")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate dateLesson;

    @Column
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassOfStudents classOfStudents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Homework> homeworks;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Score> scores;

    public Lesson() {
    }

    public Lesson(Long id, String name, LocalDate dateLesson, Integer number, Subject subject, ClassOfStudents classOfStudents, User user, List<Homework> homeworks, List<Score> scores) {
        this.id = id;
        this.name = name;
        this.dateLesson = dateLesson;
        this.number = number;
        this.subject = subject;
        this.classOfStudents = classOfStudents;
        this.user = user;
        this.homeworks = homeworks;
        this.scores = scores;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateLesson() {
        return dateLesson;
    }

    public Integer getNumber() {
        return number;
    }

    public Subject getSubject() {
        return subject;
    }

    public ClassOfStudents getClassOfStudents() {
        return classOfStudents;
    }

    public User getUser() {
        return user;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public List<Score> getScores() {
        return scores;
    }
}
