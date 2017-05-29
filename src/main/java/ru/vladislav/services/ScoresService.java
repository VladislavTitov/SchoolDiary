package ru.vladislav.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vladislav.converters.ScoreConverter;
import ru.vladislav.dto.ScoreDto;
import ru.vladislav.models.Lesson;
import ru.vladislav.models.Score;
import ru.vladislav.models.Student;
import ru.vladislav.repo.LessonsRepo;
import ru.vladislav.repo.ScoresRepo;
import ru.vladislav.repo.StudentsRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScoresService {

    @Qualifier("scoresRepo")
    @Autowired
    private ScoresRepo scoresRepo;
    @Qualifier("studentsRepo")
    @Autowired
    private StudentsRepo studentsRepo;
    @Qualifier("lessonsRepo")
    @Autowired
    private LessonsRepo lessonsRepo;


    public ScoreDto save(ScoreDto scoreDto) {
        Score score = scoresRepo.save(ScoreConverter.toScore(scoreDto));
        return ScoreConverter.toScoreDto(score);
    }

    public ScoreDto getById(Long id){
        return ScoreConverter.toScoreDto(scoresRepo.findOne(id));
    }

    public List<ScoreDto> getAllByStudentId(Long studentId){
        Student student = studentsRepo.findOne(studentId);
        if (student == null){
            return new ArrayList<>();
        }
        return student.getScores().stream().map(ScoreConverter::toScoreDto).collect(Collectors.toList());
    }

    public List<ScoreDto> getAllByLessonId(Long lessonId){
        Lesson lesson = lessonsRepo.findOne(lessonId);
        if (lesson == null){
            return new ArrayList<>();
        }
        return lesson.getScores().stream().map(ScoreConverter::toScoreDto).collect(Collectors.toList());
    }

    public boolean update(ScoreDto scoreDto){
        Score oldScore = scoresRepo.findOne(scoreDto.getId());
        if (oldScore == null){
            return false;
        }
        if (scoreDto.getScore() < 1 && scoreDto.getScore() > 5 && scoreDto.getScore() == oldScore.getScore()){
            return false;
        }
        int code = scoresRepo.update(scoreDto.getId(), scoreDto.getScore());
        return code == 1;
    }

    public void remove(Long id){
        scoresRepo.delete(id);
    }
}
