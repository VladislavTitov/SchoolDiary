package ru.vladislav.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vladislav.converters.StudentConverter;
import ru.vladislav.dto.StudentDto;
import ru.vladislav.models.ClassOfStudents;
import ru.vladislav.models.Student;
import ru.vladislav.repo.ClassesRepo;
import ru.vladislav.repo.StudentsRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentsService {

    @Qualifier("studentsRepo")
    @Autowired
    private StudentsRepo studentsRepo;
    @Qualifier("classesRepo")
    @Autowired
    private ClassesRepo classesRepo;


    public StudentDto save(StudentDto studentDto){
        Student student = studentsRepo.save(StudentConverter.toStudent(studentDto));
        return StudentConverter.toStudentDto(student);
    }

    public StudentDto getById(Long id){
        return StudentConverter.toStudentDto(studentsRepo.findOne(id));
    }

    public List<StudentDto> getAll(Long classId){
        ClassOfStudents classOfStudents = classesRepo.findOne(classId);
        if (classOfStudents == null){
            return new ArrayList<>();
        }
        return classOfStudents.getStudents().stream().map(StudentConverter::toStudentDto).collect(Collectors.toList());
    }

    public void remove(Long id){
        studentsRepo.delete(id);
    }

}
