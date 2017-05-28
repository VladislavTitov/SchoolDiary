package ru.vladislav.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    private Long id;
    private String name;
    private Long userId;

}
