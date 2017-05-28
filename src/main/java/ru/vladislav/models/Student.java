package ru.vladislav.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassOfStudents classOfStudents;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Score> scores;

    public Student() {
    }

    public Student(Long id, String lastName, String firstName, String patronymic, ClassOfStudents classOfStudents, List<Score> scores) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.classOfStudents = classOfStudents;
        this.scores = scores;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public ClassOfStudents getClassOfStudents() {
        return classOfStudents;
    }

    public List<Score> getScores() {
        return scores;
    }


}
