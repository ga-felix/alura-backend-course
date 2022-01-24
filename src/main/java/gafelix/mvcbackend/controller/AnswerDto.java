package gafelix.mvcbackend.controller;

import gafelix.mvcbackend.model.Answer;

import java.time.LocalDateTime;

public class AnswerDto {

    private Long id;
    private String message;
    private LocalDateTime createdDate;
    private String author;

    public AnswerDto(Answer answer) {
        this.id = answer.getId();
        this.message = answer.getMessage();
        this.createdDate = answer.getCreatedDate();
        this.author = answer.getAuthor().getName();
    }

}
