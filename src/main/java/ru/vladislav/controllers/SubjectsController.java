package ru.vladislav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vladislav.dto.SubjectDto;
import ru.vladislav.services.SubjectsService;

import java.util.List;

@RestController
public class SubjectsController {

    @Qualifier("subjectsService")
    @Autowired
    private SubjectsService subjectsService;

    @PostMapping("users/{userId}/subjects")
    public ResponseEntity<SubjectDto> add(@PathVariable("userId") Long userId, @RequestBody SubjectDto subjectDto){
        subjectDto.setUserId(userId);
        return new ResponseEntity<SubjectDto>(subjectsService.save(subjectDto),HttpStatus.CREATED);
    }

    @GetMapping("users/{userId}/subjects")
    public ResponseEntity<List<SubjectDto>> getAll(@PathVariable("userId") Long userId){
        return new ResponseEntity<List<SubjectDto>>(subjectsService.getAll(userId), HttpStatus.OK);
    }

    @GetMapping("users/{userId}/subjects/{subjectId}")
    public ResponseEntity<SubjectDto> getById(@PathVariable("userId") Long userId, @PathVariable("subjectId") Long subjectId){
        return new ResponseEntity<SubjectDto>(subjectsService.getById(subjectId), HttpStatus.OK);
    }

    @DeleteMapping("users/{userId}/subjects/{subjectId}")
    public ResponseEntity<Object> remove(@PathVariable("userId") Long userId, @PathVariable("subjectId") Long subjectId){
        subjectsService.remove(subjectId);
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

}
