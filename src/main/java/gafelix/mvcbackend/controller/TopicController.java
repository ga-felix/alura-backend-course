package gafelix.mvcbackend.controller;

import gafelix.mvcbackend.model.Course;
import gafelix.mvcbackend.model.Topic;
import gafelix.mvcbackend.model.TopicDto;
import gafelix.mvcbackend.repository.TopicRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Adiciona @ResponseBody em todos os métodos do Controller
public class TopicController {

    private TopicRepository topicRepository;

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping("/topics")
    public List<TopicDto> listTopics(@RequestParam String courseName) {
        return TopicDto.convertListToDto(topicRepository.findByCourseName(courseName));
    }
}
