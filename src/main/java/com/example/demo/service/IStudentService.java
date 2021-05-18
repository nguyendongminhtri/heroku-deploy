package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> findAll();
    Student save(Student student);
    Optional<Student> findById(Long id);
    void deleteById(Long id);
    List<Student> findAllByUserId(Long id);
}
