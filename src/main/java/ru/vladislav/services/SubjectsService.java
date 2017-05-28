package ru.vladislav.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vladislav.converters.SubjectConverter;
import ru.vladislav.dto.SubjectDto;
import ru.vladislav.models.Subject;
import ru.vladislav.models.User;
import ru.vladislav.repo.SubjectsRepo;
import ru.vladislav.repo.UsersRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectsService {

    @Qualifier("subjectsRepo")
    @Autowired
    private SubjectsRepo subjectsRepo;
    @Qualifier("usersRepo")
    @Autowired
    private UsersRepo usersRepo;


    public SubjectDto save(SubjectDto subjectDto){
        Subject subject = subjectsRepo.save(SubjectConverter.toSubject(subjectDto));
        return SubjectConverter.toSubjectDto(subject);
    }

    public SubjectDto getById(Long id){
        return SubjectConverter.toSubjectDto(subjectsRepo.findOne(id));
    }

    public List<SubjectDto> getAll(Long userId){
        User user = usersRepo.findOne(userId);
        if (user == null){
            return new ArrayList<>();
        }
        return user.getSubjects().stream().map(SubjectConverter::toSubjectDto).collect(Collectors.toList());
    }

    public void remove(Long id){
        subjectsRepo.delete(id);
    }

}
