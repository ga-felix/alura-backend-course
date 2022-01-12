package gafelix.mvcbackend.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public class Topic {

	@NonNull private Course course;
	@NonNull private String message;
	@NonNull private String title;
	private List<Answer> answers = new ArrayList<>();
	private User author;
	private LocalDateTime createdAt = LocalDateTime.now();
	private Long id;
	private TopicStatuses status = TopicStatuses.NOT_ANSWERED;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
