package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.service.impl.StudentServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    UserServiceImpl userService;
    @GetMapping("/student")
    public ResponseEntity<?> listStudent(){
        List<Student> students = studentService.findAll();
        if(students.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @PostMapping("/student")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @GetMapping("/student/get-all-by-user/{id}")
    public ResponseEntity<?> getByUser(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Student> studentList = studentService.findAllByUserId(user.get().getId());
        if(studentList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }

}
