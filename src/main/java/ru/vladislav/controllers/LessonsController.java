package ru.vladislav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vladislav.dto.LessonDto;
import ru.vladislav.services.LessonsService;

import java.util.List;

@RestController
public class LessonsController {


    @Qualifier("lessonsService")
    @Autowired
    private LessonsService lessonsService;

    @PostMapping("users/{userId}/lessons")
    public ResponseEntity<LessonDto> add(@PathVariable("userId") Long userId, @RequestBody LessonDto lessonDto){
        lessonDto.setUser_id(userId);
        return new ResponseEntity<LessonDto>(lessonsService.save(lessonDto), HttpStatus.CREATED);
    }

    @GetMapping("users/{userId}/lessons/{lessonId}")
    public ResponseEntity<LessonDto> getById(@PathVariable("userId") Long userId, @PathVariable("lessonId") Long lessonId){
        return new ResponseEntity<LessonDto>(lessonsService.getById(lessonId), HttpStatus.OK);
    }

    @GetMapping("users/{userId}/lessons")
    public ResponseEntity<List<LessonDto>> getAll(@PathVariable("userId") Long userId){
        List<LessonDto> lessonDtos = lessonsService.getAllByUserId(userId);
        System.out.println(lessonDtos);
        return new ResponseEntity<List<LessonDto>>(lessonDtos, HttpStatus.OK);
    }

    @DeleteMapping("users/{userId}/lessons/{lessonId}")
    public ResponseEntity<Object> remove(@PathVariable("userId") Long userId, @PathVariable("lessonId") Long lessonId){
        lessonsService.remove(lessonId);
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }
}
