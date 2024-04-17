package com.t3h.demo.rest;

import com.t3h.demo.entity.Student;
import com.t3h.demo.repo.StudentRepo;
import com.t3h.demo.request.StudentRequest;
import com.t3h.demo.request.StudentUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class StudentRestController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("getAll")
    public ResponseEntity<?> getAll ()
    {
        List<Student> students = studentRepo.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @PostMapping("save")
    public ResponseEntity<?> save (@RequestBody StudentRequest studentRequest)
    {
        Student student = new Student();
        student.setFullName(studentRequest.getFullName());
        student.setGpa(studentRequest.getGpa());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        student.setBirthday(LocalDate.parse(studentRequest.getBirthday(),formatter));
        student.setGender(studentRequest.getGender());
        studentRepo.save(student);
        return new ResponseEntity<>("Created Successfully" + student, HttpStatus.OK);
    }

    @PutMapping("student/{id}")
    public ResponseEntity <?> update (@PathVariable Integer id, @RequestBody StudentRequest studentRequest )
    {
        System.out.println(id);
        System.out.println(studentRequest.getFullName());
        Student student = studentRepo.findById(id).orElse(null);
        if (student != null)
        {
            student.setFullName(studentRequest.getFullName());
            student.setGpa(studentRequest.getGpa());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            student.setBirthday(LocalDate.parse(studentRequest.getBirthday(),formatter));
            student.setGender(studentRequest.getGender());
            studentRepo.save(student);
            return new ResponseEntity<>("Updated success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found with id: "+ id,HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("student/{id}")
    public ResponseEntity <?> delete (@PathVariable Integer id)
    {
        studentRepo.deleteById(id);

        return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }

    @GetMapping("findByStudentName")
    public ResponseEntity <?> findByStudentName (@RequestParam(required = false) String studentName)
    {
        List <Student> students = studentRepo.findByFullName(studentName);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }
}
