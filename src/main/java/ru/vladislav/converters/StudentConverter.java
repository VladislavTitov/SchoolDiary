package ru.vladislav.converters;

import ru.vladislav.dto.StudentDto;
import ru.vladislav.models.ClassOfStudents;
import ru.vladislav.models.Student;

public class StudentConverter {

    public static Student toStudent(StudentDto studentDto){
        if (studentDto == null){
            return null;
        }

        ClassOfStudents classOfStudents = new ClassOfStudents(studentDto.getClassId(), null, null);
        return new Student(studentDto.getId(), studentDto.getLastName(), studentDto.getFirstName(), studentDto.getPatronymic(), classOfStudents, null);
    }

    public static StudentDto toStudentDto(Student student){
        if (student == null){
            return null;
        }

        return new StudentDto(student.getId(), student.getLastName(), student.getFirstName(), student.getPatronymic(), student.getClassOfStudents().getId());
    }

}
