package com.t3h.demo.repo;

import com.t3h.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository <Student, Integer> {
    List<Student> findByFullName (String fullName);
}
