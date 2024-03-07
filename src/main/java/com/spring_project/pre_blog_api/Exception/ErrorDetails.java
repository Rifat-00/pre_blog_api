package com.spring_project.pre_blog_api.Exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
public class ErrorDetails {
    private LocalDate timestamp;
    private String message;
    private String target;

    public ErrorDetails(LocalDate timestamp, String message, String target) {
        this.timestamp = timestamp;
        this.message = message;
        this.target = target;
    }
}
