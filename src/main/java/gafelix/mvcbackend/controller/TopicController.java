package gafelix.mvcbackend.controller;

import gafelix.mvcbackend.model.Course;
import gafelix.mvcbackend.model.Topic;
import gafelix.mvcbackend.repository.CourseRepository;
import gafelix.mvcbackend.repository.TopicRepository;
import gafelix.mvcbackend.service.RepositoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private RepositoryManager repositories;

    @Autowired
    public void setRepositories(RepositoryManager repositories) {
        this.repositories = repositories;
    }

    @GetMapping
    public List<TopicDto> listTopics(@RequestParam String courseName) {
        return TopicDto.convertListToDto(repositories.getTopic().findByCourseName(courseName));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDto> createTopic(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
        Topic topic = form.convert(repositories.getCourse());
        repositories.getTopic().save(topic);
        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailTopic(@PathVariable Long id) {
        Topic topic = repositories.getTopicById(id);
        if(topic != null) return ResponseEntity.ok(new DetailedTopicDto(topic));
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateTopic(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {
        Topic topic = repositories.getTopicById(id);
        if(topic != null) {
            topic.setMessage(form.getMessage());
            topic.setTitle(form.getTitle());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        Topic topic = repositories.getTopicById(id);
        if(topic != null) {
            repositories.getTopic().deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
