package com.tcet.student_management.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.tcet.student_management.entity.Student;
import com.tcet.student_management.repository.StudentRepository;
import com.tcet.student_management.response.StudentResponse;


@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;
   public StudentController(StudentRepository studentRepository){
    this.studentRepository = studentRepository;
   }
    @PostMapping
    
    public StudentResponse addStudent(@RequestBody Student student) 
    {

        Student savedStudent = studentRepository.save(student);

        return new StudentResponse(
            "Student added successfully",
            savedStudent
        );
    }

    @GetMapping
    public StudentResponse getAllStudents() { 
        return new StudentResponse(
            "Students retrieved successfully", studentRepository.findAll() 
        ); 
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {

    Optional<Student> student =
            studentRepository.findById(id);

    if (student.isPresent()) {

        return new StudentResponse(
            "Student found",
            student.get()
        );
    }

    return new StudentResponse(
        "Student not found",
        null
    );

}

@PutMapping("/{id}")
public StudentResponse updateStudent(
        @PathVariable Long id,
        @RequestBody Student studentDetails) {

    Optional<Student> existingStudent =
            studentRepository.findById(id);

    if (existingStudent.isPresent()) {

        Student student = existingStudent.get();

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setDepartment(studentDetails.getDepartment());

        Student updatedStudent =
                studentRepository.save(student);

        return new StudentResponse(
                "Student updated successfully",
                updatedStudent);
    }

    return new StudentResponse(
            "Student not found",
            null);
}

@DeleteMapping("/{id}")
public StudentResponse deleteStudent(@PathVariable Long id) {
  Optional<Student> student = studentRepository.findById(id);
  if (student.isPresent()) {
  studentRepository.deleteById(id);
  return new StudentResponse("Student deleted successfully", null); 
  }
  return new StudentResponse("Student not found", null);
}

@PatchMapping("/{id}")
public ResponseEntity<StudentResponse>
updateDepartment(
    @PathVariable Long id,
    @RequestBody Student studentDetails)
{
    Optional<Student> existingStudent =studentRepository.findById(id);
        if(existingStudent.isPresent())
                {
                    Student student = existingStudent.get();student.setDepartment(studentDetails.getDepartment());  
                    studentRepository.save(student);
                    return ResponseEntity.ok(new StudentResponse(
                        "Department Updated",
                        student)
                    );
                } 
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StudentResponse(
                    "Student Not Found", 
                    null));
}
}

