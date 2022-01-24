package gafelix.mvcbackend.service;

import gafelix.mvcbackend.model.Topic;
import gafelix.mvcbackend.repository.CourseRepository;
import gafelix.mvcbackend.repository.TopicRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Service
public class RepositoryManager {

    private TopicRepository topic;
    private CourseRepository course;

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topic = topicRepository;
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.course = courseRepository;
    }

    public Topic getTopicById(Long id) {
        Optional<Topic> topic = getTopic().findById(id);
        if(topic.isPresent()) return topic.get();
        else return null;
    }

}
