package gafelix.alurabackendcourse.repository;

import gafelix.alurabackendcourse.model.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursorRepositoryTest {

    private CourseRepository cursorRepository;
    private TestEntityManager testEntityManager;

    @Autowired
    public void setCursorRepository(CourseRepository cursorRepository) {
        this.cursorRepository = cursorRepository;
    }

    @Autowired
    public void setTestEntityManager(TestEntityManager testEntityManager) {
        this.testEntityManager = testEntityManager;
    }

    @Test
    public void shouldFindCourseByName() {
        Course testCourse = new Course();
        testCourse.setName("HTML 5");
        testEntityManager.persist(testCourse);
        Course course = cursorRepository.findByName("HTML 5");
        Assertions.assertNotNull(course);
        Assertions.assertEquals(course.getName(), "HTML 5");
    }

}