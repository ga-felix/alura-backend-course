package gafelix.alurabackendcourse.repository;

import gafelix.alurabackendcourse.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByCourseName(String courseName);
    Page<Topic> findByCourseName(String courseName, Pageable pages);

}
