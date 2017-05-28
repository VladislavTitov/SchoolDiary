package ru.vladislav.converters;

import ru.vladislav.dto.HomeworkDto;
import ru.vladislav.models.Homework;
import ru.vladislav.models.Lesson;

public class HomeworksConverter {

    public static Homework toHomework(HomeworkDto homeworkDto){
        if (homeworkDto == null){
            return null;
        }
        Lesson lesson = new Lesson(homeworkDto.getLessonId(), null, null, null, null, null, null, null, null);
        return new Homework(homeworkDto.getId(), homeworkDto.getTitle(), homeworkDto.getDescription(), lesson);
    }

    public static HomeworkDto toHomeworkDto(Homework homework){
        if (homework == null){
            return null;
        }
        return new HomeworkDto(homework.getId(), homework.getTitle(), homework.getDescription(), homework.getLesson().getId());
    }

}
