package gafelix.mvcbackend.controller;

import gafelix.mvcbackend.model.Course;
import gafelix.mvcbackend.model.Topic;
import gafelix.mvcbackend.model.TopicDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController // Adiciona @ResponseBody em todos os métodos do Controller
public class TopicController {

    @GetMapping("/topics")
    public List<TopicDto> listTopics() {
        Topic topic = new Topic(new Course("Dúvida", "Spring Boot API"), 1L, "Não entendi...", "Dúvida com Spring");
        return TopicDto.convertListToDto(Arrays.asList(topic, topic, topic));
    }
}
