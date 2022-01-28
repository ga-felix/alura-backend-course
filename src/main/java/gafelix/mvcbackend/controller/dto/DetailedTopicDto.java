package gafelix.mvcbackend.controller.dto;

import gafelix.mvcbackend.model.Topic;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DetailedTopicDto {

    private long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private String status;
    private List<AnswerDto> answers;

    public DetailedTopicDto(Topic topic) {
        this.id = topic.getId().longValue();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createdAt = topic.getCreatedAt();
        this.status = topic.getStatus().name();
        this.answers = new ArrayList<>();
        this.answers.addAll(topic.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList()));
    }

    public static List<DetailedTopicDto> convertListToDto(List<Topic> topics) {
        return topics.stream().map(DetailedTopicDto::new).collect(Collectors.toList());
    }

}
