package ru.vladislav.converters;

import ru.vladislav.dto.LessonDto;
import ru.vladislav.models.ClassOfStudents;
import ru.vladislav.models.Lesson;
import ru.vladislav.models.Subject;
import ru.vladislav.models.User;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class LessonsConverter {

    public static Lesson toLesson(LessonDto lessonDto){
        if (lessonDto == null){
            return null;
        }
        User user = new User(lessonDto.getUser_id(), null, null, null, null, null, null);
        ClassOfStudents classOfStudents = new ClassOfStudents(lessonDto.getClassId(), null, user);
        Subject subject = new Subject(lessonDto.getSubject_id(), null, null, null);
        return new Lesson(lessonDto.getId(), lessonDto.getName(), Instant.ofEpochMilli(lessonDto.getDate()).atZone(ZoneId.systemDefault()).toLocalDate(), lessonDto.getNumber(), subject, classOfStudents, user, null, null);
    }

    public static LessonDto toLessonDto(Lesson lesson){
        if (lesson == null){
            return null;
        }
        return new LessonDto(lesson.getId(), lesson.getName(), lesson.getDateLesson().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(), lesson.getNumber(), lesson.getClassOfStudents().getId(), lesson.getUser().getId(), lesson.getSubject().getId());
    }

}
