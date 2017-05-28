package ru.vladislav.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.vladislav.converters.HomeworksConverter;
import ru.vladislav.dto.HomeworkDto;
import ru.vladislav.models.Homework;
import ru.vladislav.models.Lesson;
import ru.vladislav.repo.HomeworksRepo;
import ru.vladislav.repo.LessonsRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeworksService {

    @Qualifier("homeworksRepo")
    @Autowired
    private HomeworksRepo homeworksRepo;
    @Qualifier("lessonsRepo")
    @Autowired
    private LessonsRepo lessonsRepo;


    public HomeworkDto save(HomeworkDto homeworkDto){
        Homework homework = homeworksRepo.save(HomeworksConverter.toHomework(homeworkDto));
        return HomeworksConverter.toHomeworkDto(homework);
    }

    public HomeworkDto getById(Long id){
        return HomeworksConverter.toHomeworkDto(homeworksRepo.findOne(id));
    }

    public List<HomeworkDto> getAll(Long lessonId){
        Lesson lesson = lessonsRepo.findOne(lessonId);
        if (lesson == null){
            return  new ArrayList<>();
        }
        return lesson.getHomeworks().stream().map(HomeworksConverter::toHomeworkDto).collect(Collectors.toList());
    }

    public void remove(Long id){
        homeworksRepo.delete(id);
    }
}
