package gafelix.alurabackendcourse.controller.dto;

import gafelix.alurabackendcourse.model.Topic;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
public class TopicDto {

    private long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;

    public TopicDto(Topic topic) {
        this.id = topic.getId().longValue();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createdAt = topic.getCreatedAt();
    }

    public static Page<TopicDto> convertListToDto(Page<Topic> topics) {
        return topics.map(TopicDto::new);
    }

}
