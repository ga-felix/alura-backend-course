package gafelix.mvcbackend.controller;

import gafelix.mvcbackend.model.Topic;
import gafelix.mvcbackend.repository.CourseRepository;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class TopicForm {

    @NotNull @NotEmpty @Size(min = 5)
    private String title;
    @NotNull @NotEmpty @Size(min = 5)
    private String message;
    @NotNull @NotEmpty @Size(min = 3)
    private String courseName;

    public Topic convert(CourseRepository courseRepository) {
        return new Topic(this.getMessage(), this.getTitle(), courseRepository.findByName(this.getCourseName()));
    }

}
