package gafelix.mvcbackend.controller;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UpdateTopicForm {

    @NotNull @NotEmpty @Size(min = 5)
    private String title;
    @NotNull @NotEmpty @Size(min = 5)
    private String message;

}
