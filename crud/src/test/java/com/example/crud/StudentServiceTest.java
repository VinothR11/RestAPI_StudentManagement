import com.example.crud.exception.StudentException;
import com.example.crud.model.Student;
import com.example.crud.repository.StudentRepository;
import com.example.crud.service.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void testGetAllStudents() {
        // Arrange
        List<Student> students = List.of(
                new Student(1L, "Alice", LocalDate.of(1995, 10, 20), "Female", "Math"),
                new Student(2L, "Bob", LocalDate.of(1993, 8, 15), "Male", "Physics")
        );
        when(studentRepository.findAll()).thenReturn(students);

        // Act
        List<Student> result = studentService.getAllStudents();

        // Assert
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testCreateStudentWithInvalidGender() {
        // Arrange
        Student student = new Student();
        student.setGender("Unknown");

        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            studentService.createStudent(student);
        });

        Assertions.assertEquals("Invalid gender: Unknown", exception.getMessage());
    }

    @Test
    void testUpdateStudentThrowsException() {
        // Arrange
        long id = 1L;
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        StudentException exception = Assertions.assertThrows(StudentException.class, () -> {
            studentService.updateStudent(id, new Student());
        });

        Assertions.assertEquals("1 does not exist", exception.getMessage());
    }
            }
  
