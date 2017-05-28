package ru.vladislav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vladislav.dto.ClassDto;
import ru.vladislav.services.ClassesService;

import java.util.List;

@RestController
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @PostMapping("/users/{user_id}/classes")
    public ResponseEntity<ClassDto> addClass(@PathVariable("user_id") Long userId, @RequestBody ClassDto classDto){
        classDto.setUserId(userId);
        return new ResponseEntity<ClassDto>(classesService.addClass(classDto), HttpStatus.CREATED);
    }

    @GetMapping("/users/{user_id}/classes")
    public ResponseEntity<List<ClassDto>> getAllClasses(@PathVariable("user_id") Long userId){
        return new ResponseEntity<>(classesService.getAll(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{user_id}/classes/{class_id}")
    public ResponseEntity<ClassDto> getById(@PathVariable("user_id") Long userId, @PathVariable("class_id") Long classId){
        return new ResponseEntity<ClassDto>(classesService.getById(classId), HttpStatus.OK);
    }

    @PutMapping("/users/{user_id}/classes/{class_id}")
    public ResponseEntity<ClassDto> updateById(@PathVariable("user_id") Long userId, @PathVariable("class_id") Long classId, @RequestBody ClassDto classDto){
        classDto.setId(classId);
        classDto.setUserId(userId);
        return new ResponseEntity<ClassDto>(classesService.update(classDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/users/{user_id}/classes/{class_id}")
    public ResponseEntity<Object> removeClass(@PathVariable("user_id") Long userId, @PathVariable("class_id") Long classId){
        classesService.removeClass(classId);
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }
}
