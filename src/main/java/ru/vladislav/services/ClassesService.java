package ru.vladislav.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vladislav.converters.ClassesConverter;
import ru.vladislav.dto.ClassDto;
import ru.vladislav.models.ClassOfStudents;
import ru.vladislav.models.User;
import ru.vladislav.repo.ClassesRepo;
import ru.vladislav.repo.UsersRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClassesService {

    @Qualifier("classesRepo")
    @Autowired
    private ClassesRepo classesRepo;

    @Autowired
    private UsersRepo usersRepo;

    public ClassDto addClass(ClassDto classDto){
        ClassOfStudents classOfStudents = classesRepo.save(ClassesConverter.toClassOfStudents(classDto));
        return ClassesConverter.toClassDto(classOfStudents);
    }

    public ClassDto getById(Long id){
        return ClassesConverter.toClassDto(classesRepo.findOne(id));
    }

    public List<ClassDto> getAll(Long userId){
        User user = usersRepo.findOne(userId);
        if (user == null){
            return new ArrayList<>();
        }
        List<ClassOfStudents> classesOfStudents = user.getClassesOfStudents();
        return classesOfStudents.stream().map(ClassesConverter::toClassDto).collect(Collectors.toList());
    }

    public ClassDto update(ClassDto classDto){
        int opcode = classesRepo.update(classDto.getId(), classDto.getName());
        if (opcode == 1){
            return classDto;
        }else {
            return ClassesConverter.toClassDto(classesRepo.findOne(classDto.getId()));
        }
    }

    public void removeClass(Long id){
        classesRepo.delete(id);
    }
}
