package gafelix.mvcbackend.controller;

import gafelix.mvcbackend.model.Course;
import gafelix.mvcbackend.model.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController // Adiciona @ResponseBody em todos os métodos do Controller
public class TopicController {

    @GetMapping("/topics")
    public List<Topic> listTopics() {
        Topic topic = new Topic(new Course("Duvida", "Spring Boot API"), "Não entendi...", "Duvida com Spring");
        return Arrays.asList(topic, topic, topic);
    }
}
