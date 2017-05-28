package ru.vladislav.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Subject> subjects;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ClassOfStudents> classesOfStudents;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Token> tokens;

    public User() {
    }

    public User(Long id, String name, String password, List<Subject> subjects, List<ClassOfStudents> classesOfStudents, List<Lesson> lessons, List<Token> tokens) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.classesOfStudents = classesOfStudents;
        this.subjects = subjects;
        this.lessons = lessons;
        this.tokens = tokens;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<ClassOfStudents> getClassesOfStudents() {
        return classesOfStudents;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", subjects=" + subjects +
                ", classesOfStudents=" + classesOfStudents +
                ", lessons=" + lessons +
                '}';
    }
}
