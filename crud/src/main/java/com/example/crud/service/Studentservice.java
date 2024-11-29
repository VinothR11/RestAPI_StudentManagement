Package com.example.crud.service;

import com.examaple.crud.exception.StudentException;
import com.example.crud.model.Student;
import com.exmaple.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {\

@Autowired
private StudentRepository studentRepository;

@Override
public List<Student> getAllStudent() {
   return studentRepository.findAll();

}

@Override 
public Optional<Student> getstudentById(long id) {
   return studentRepository.findById(id);

}

@Override
public student createStudent(Student student) {
    String gender = student.getGender();
    if(!gender.equals("male") && !gender.equals("female") && !gender.equals("transgender")){
       throw new IllegalArguementException("Invalid gender: " + gender);
    }
    return studentRepository.save(student);
}
@Override 
public Student updateStudent(long id, Student studentDetails) throws StudentException {
     Student student = studentRepository.findById(id) .orElseThrow() -> new StudentException(id + " does not exist"));
     student.setName(studentDetails.getName());
     return studentRepository.save(student);

@Override 
public void deleteStudent(long id) {
  if (!studentRepository.existById(id)) {
      throw new IllegalArgumentException("Student ID" + id + "does not exist" );
  }
  studentRepository.deleteById(id);
}

}