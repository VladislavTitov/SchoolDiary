package ru.vladislav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vladislav.dto.StudentDto;
import ru.vladislav.services.StudentsService;

import java.util.List;

@RestController
public class StudentsController {

    @Qualifier("studentsService")
    @Autowired
    private StudentsService studentsService;

    @PostMapping("/users/{user_id}/classes/{class_id}/students")
    public ResponseEntity<StudentDto> add(@PathVariable("user_id") Long userId, @PathVariable("class_id") Long classId,
                                          @RequestBody StudentDto studentDto){
        studentDto.setClassId(classId);
        return new ResponseEntity<StudentDto>(studentsService.save(studentDto), HttpStatus.CREATED);
    }

    @GetMapping("/users/{user_id}/classes/{class_id}/students/{studentId}")
    public ResponseEntity<StudentDto> getById(@PathVariable("user_id") Long userId, @PathVariable("class_id") Long classId,
                                              @PathVariable("studentId") Long studentId){
        return new ResponseEntity<StudentDto>(studentsService.getById(studentId), HttpStatus.OK);
    }

    @GetMapping("/users/{user_id}/classes/{class_id}/students")
    public ResponseEntity<List<StudentDto>> getAll(@PathVariable("user_id") Long userId, @PathVariable("class_id") Long classId){
        return new ResponseEntity<>(studentsService.getAll(classId), HttpStatus.OK);
    }

    @DeleteMapping("/users/{user_id}/classes/{class_id}/students/{studentId}")
    public ResponseEntity<Object> remove(@PathVariable("user_id") Long userId, @PathVariable("class_id") Long classId,
                                         @PathVariable("studentId") Long studentId){
        studentsService.remove(studentId);
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

}
