package ru.vladislav.converters;

import ru.vladislav.dto.SubjectDto;
import ru.vladislav.models.Subject;
import ru.vladislav.models.User;

public class SubjectConverter {

    public static Subject toSubject(SubjectDto subjectDto){
        if (subjectDto == null){
            return null;
        }

        User user = new User(subjectDto.getUserId(), null, null, null, null, null, null);
        return new Subject(subjectDto.getId(), subjectDto.getName(), user, null);
    }

    public static SubjectDto toSubjectDto(Subject subject){
        if (subject == null){
            return null;
        }
        return new SubjectDto(subject.getId(), subject.getName(), subject.getUser().getId());
    }

}
