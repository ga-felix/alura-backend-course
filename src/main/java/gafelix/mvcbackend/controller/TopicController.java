package gafelix.mvcbackend.controller;

import gafelix.mvcbackend.model.Course;
import gafelix.mvcbackend.model.Topic;
import gafelix.mvcbackend.repository.CourseRepository;
import gafelix.mvcbackend.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private TopicRepository topicRepository;
    private CourseRepository courseRepository;

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) { this.courseRepository = courseRepository;}

    @GetMapping
    public List<TopicDto> listTopics(@RequestParam String courseName) {
        return TopicDto.convertListToDto(topicRepository.findByCourseName(courseName));
    }

    @PostMapping
    public ResponseEntity<TopicDto> createTopic(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
        Topic topic = form.convert(this.courseRepository);
        this.topicRepository.save(topic);
        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }
}
