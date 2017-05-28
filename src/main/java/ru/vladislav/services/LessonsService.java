package ru.vladislav.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vladislav.converters.LessonsConverter;
import ru.vladislav.dto.LessonDto;
import ru.vladislav.models.ClassOfStudents;
import ru.vladislav.models.Lesson;
import ru.vladislav.models.Subject;
import ru.vladislav.models.User;
import ru.vladislav.repo.ClassesRepo;
import ru.vladislav.repo.LessonsRepo;
import ru.vladislav.repo.SubjectsRepo;
import ru.vladislav.repo.UsersRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LessonsService {

    @Qualifier("lessonsRepo")
    @Autowired
    private LessonsRepo lessonsRepo;
    @Qualifier("subjectsRepo")
    @Autowired
    private SubjectsRepo subjectsRepo;
    @Qualifier("usersRepo")
    @Autowired
    private UsersRepo usersRepo;
    @Qualifier("classesRepo")
    @Autowired
    private ClassesRepo classesRepo;


    public LessonDto save(LessonDto lessonDto){
        Lesson lesson = lessonsRepo.save(LessonsConverter.toLesson(lessonDto));
        return LessonsConverter.toLessonDto(lesson);
    }

    public LessonDto getById(Long id){
        return LessonsConverter.toLessonDto(lessonsRepo.findOne(id));
    }

    public List<LessonDto> getAllBySubjectId(Long subjectId){
        Subject subject = subjectsRepo.findOne(subjectId);
        if (subject == null){
            return new ArrayList<>();
        }
        return subject.getLessons().stream().map(LessonsConverter::toLessonDto).collect(Collectors.toList());
    }

    public List<LessonDto> getAllByClassId(Long classId){
        ClassOfStudents classOfStudents = classesRepo.findOne(classId);
        if (classOfStudents == null){
            return new ArrayList<>();
        }
        return classOfStudents.getLessons().stream().map(LessonsConverter::toLessonDto).collect(Collectors.toList());
    }

    public List<LessonDto> getAllByUserId(Long userId){
        User user = usersRepo.findOne(userId);
        if (user == null){
            return new ArrayList<>();
        }
        return user.getLessons().stream().map(LessonsConverter::toLessonDto).collect(Collectors.toList());
    }

    public void remove(Long id){
        lessonsRepo.delete(id);
    }
}
