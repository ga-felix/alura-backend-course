package gafelix.mvcbackend.controller;

import gafelix.mvcbackend.model.Topic;
import gafelix.mvcbackend.repository.CourseRepository;
import lombok.Getter;

@Getter
public class TopicForm {

    private String title;
    private String message;
    private String courseName;

    public Topic convert(CourseRepository courseRepository) {
        return new Topic(this.getMessage(), this.getTitle(), courseRepository.findByName(this.getCourseName()));
    }

}
