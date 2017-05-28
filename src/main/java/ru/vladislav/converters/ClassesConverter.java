package ru.vladislav.converters;

import ru.vladislav.dto.ClassDto;
import ru.vladislav.models.ClassOfStudents;
import ru.vladislav.models.User;

public class ClassesConverter {

    public static ClassOfStudents toClassOfStudents(ClassDto classDto){
        if (classDto == null){
            return null;
        }
        User user = new User(classDto.getUserId(), null, null, null, null, null, null);
        return new ClassOfStudents(classDto.getId(), classDto.getName(), user);
    }

    public static ClassDto toClassDto(ClassOfStudents classOfStudents){
        if (classOfStudents == null){
            return null;
        }
        return new ClassDto(classOfStudents.getId(), classOfStudents.getName(), classOfStudents.getUser().getId());
    }

}
