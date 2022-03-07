package gafelix.alurabackendcourse.controller;

import gafelix.alurabackendcourse.controller.dto.DetailedTopicDto;
import gafelix.alurabackendcourse.controller.dto.TopicDto;
import gafelix.alurabackendcourse.controller.form.TopicForm;
import gafelix.alurabackendcourse.controller.form.UpdateTopicForm;
import gafelix.alurabackendcourse.model.Topic;
import gafelix.alurabackendcourse.service.RepositoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private RepositoryManager repositories;

    @Autowired
    public void setRepositories(RepositoryManager repositories) {
        this.repositories = repositories;
    }

    @GetMapping
    @Cacheable("listaDeTopicos")
    public Page<TopicDto> listTopics(@RequestParam(required = false) String courseName, @PageableDefault(direction = Sort.Direction.ASC) Pageable pages) {
        if(courseName == null || courseName.isBlank()) return TopicDto.convertListToDto(repositories.getTopic().findAll(pages));
        return TopicDto.convertListToDto(repositories.getTopic().findByCourseName(courseName, pages));
    }

    @PostMapping
    @Transactional
    @CacheEvict("listaDeTopicos")
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
    @CacheEvict("listaDeTopicos")
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
    @CacheEvict("listaDeTopicos")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        Topic topic = repositories.getTopicById(id);
        if(topic != null) {
            repositories.getTopic().deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
