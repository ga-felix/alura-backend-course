package gafelix.mvcbackend.service;

import gafelix.mvcbackend.model.Topic;
import gafelix.mvcbackend.repository.CourseRepository;
import gafelix.mvcbackend.repository.TopicRepository;
import gafelix.mvcbackend.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Service
public class RepositoryManager {

    private TopicRepository topic;
    private CourseRepository course;
    private UserRepository user;

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topic = topicRepository;
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.course = courseRepository;
    }

    @Autowired
    public void setUser(UserRepository user) {
        this.user = user;
    }

    public Topic getTopicById(Long id) {
        Optional<Topic> topic = getTopic().findById(id);
        if(topic.isPresent()) return topic.get();
        else return null;
    }

}
