import com.example.crud.controller.StudentController;
import com.example.crud.model.Student;
import com.example.crud.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void testGetAllStudents() throws Exception {
        // Arrange
        List<Student> students = List.of(
                new Student(1L, "Alice", LocalDate.of(1995, 10, 20), "Female", "Math"),
                new Student(2L, "Bob", LocalDate.of(1993, 8, 15), "Male", "Physics")
        );
        when(studentService.getAllStudents()).thenReturn(students);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void testCreateStudent() throws Exception {
        // Arrange
        Student student = new Student();
        student.setName("John");
        student.setGender("Male");
        student.setDob(LocalDate.of(1990, 1, 1));
        student.setDept("IT");

        when(studentService.createStudent(Mockito.any(Student.class))).thenReturn(student);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\",\"gender\":\"Male\",\"dob\":\"1990-01-01\",\"dept\":\"IT\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"));
    }
          }
