package ru.vladislav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vladislav.dto.ScoreDto;
import ru.vladislav.services.ScoresService;

import java.util.List;

@RestController
public class ScoresController {

    @Qualifier("scoresService")
    @Autowired
    private ScoresService scoresService;

    @PostMapping("/users/{userId}/lessons/{lessonId}/scores")
    public ResponseEntity<ScoreDto> add(@PathVariable("userId") Long userId, @PathVariable("lessonId") Long lessonId,
                                        @RequestBody ScoreDto scoreDto){
        return new ResponseEntity<ScoreDto>(scoresService.save(scoreDto), HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/lessons/{lessonId}/scores/{scoreId}")
    public ResponseEntity<ScoreDto> getById(@PathVariable("userId") Long userId, @PathVariable("lessonId") Long lessonId,
                                        @PathVariable("scoreId") Long scoreId){
        return new ResponseEntity<ScoreDto>(scoresService.getById(scoreId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/lessons/{lessonId}/scores")
    public ResponseEntity<List<ScoreDto>> getAll(@PathVariable("userId") Long userId, @PathVariable("lessonId") Long lessonId){
        return new ResponseEntity<List<ScoreDto>>(scoresService.getAllByLessonId(lessonId), HttpStatus.OK);
    }

    @PutMapping("/users/{userId}/lessons/{lessonId}/scores/{scoreId}")
    public ResponseEntity<Object> update(@PathVariable("userId") Long userId, @PathVariable("lessonId") Long lessonId,
                                           @PathVariable("scoreId") Long scoreId, @RequestBody ScoreDto scoreDto){
        boolean success = scoresService.update(scoreDto);
        if (success){
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/users/{userId}/lessons/{lessonId}/scores/{scoreId}")
    public ResponseEntity<Object> remove(@PathVariable("userId") Long userId, @PathVariable("lessonId") Long lessonId,
                                            @PathVariable("scoreId") Long scoreId){
        scoresService.remove(scoreId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
