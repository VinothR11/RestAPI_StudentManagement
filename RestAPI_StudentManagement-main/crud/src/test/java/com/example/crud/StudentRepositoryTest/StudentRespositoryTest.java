import com.example.crud.model.Student;
import com.example.crud.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveAndFindById() {
        // Arrange
        Student student = new Student();
        student.setName("John Doe");
        student.setDob(LocalDate.of(2000, 5, 15));
        student.setGender("Male");
        student.setDept("CS");

        // Act
        Student savedStudent = studentRepository.save(student);
        Optional<Student> foundStudent = studentRepository.findById(savedStudent.getId());

        // Assert
        Assertions.assertTrue(foundStudent.isPresent());
        Assertions.assertEquals("John Doe", foundStudent.get().getName());
    }

    @Test
    void testFindAll() {
        // Act
        List<Student> students = studentRepository.findAll();

        // Assert
        Assertions.assertNotNull(students);
    }
}
