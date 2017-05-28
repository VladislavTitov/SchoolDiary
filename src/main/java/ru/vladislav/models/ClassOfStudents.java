package ru.vladislav.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
public class ClassOfStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "classOfStudents", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "classOfStudents", cascade = CascadeType.ALL)
    private List<Student> students;

    public ClassOfStudents() {
    }

    public ClassOfStudents(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "ClassOfStudents{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lessons=" + lessons +
                ", students=" + students +
                '}';
    }
}
