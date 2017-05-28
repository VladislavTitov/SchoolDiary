package ru.vladislav.converters;

import ru.vladislav.dto.ScoreDto;
import ru.vladislav.models.Lesson;
import ru.vladislav.models.Score;
import ru.vladislav.models.Student;

public class ScoreConverter {

    public static Score toScore(ScoreDto scoreDto){
        if (scoreDto == null){
            return null;
        }
        Lesson lesson = new Lesson(scoreDto.getLessonId(), null, null, null, null, null, null, null, null);
        Student student = new Student(scoreDto.getStudentId(), null, null, null, null, null);
        return new Score(scoreDto.getId(), scoreDto.getScore(), lesson, student);
    }

    public static ScoreDto toScoreDto(Score score){
        if (score == null){
            return null;
        }
        return new ScoreDto(score.getId(), score.getScore(), score.getLesson().getId(), score.getStudent().getId());
    }

}
