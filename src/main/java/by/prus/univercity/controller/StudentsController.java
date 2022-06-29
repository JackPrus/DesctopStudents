package by.prus.univercity.controller;

import by.prus.univercity.dao.StudentDao;
import by.prus.univercity.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class StudentsController {

    @Autowired
    private StudentDao studentDao;

    @RequestMapping("create-person")
    public Student createStudent(Student student) {
        return studentDao.save(student);
    }

    @GetMapping("get-all-students")
    public List<Student> getAllStudents() {
        return (List<Student>) studentDao.findAll();
    }

    @DeleteMapping()
    public void deleteStudent(Student student) {
        studentDao.delete(student);
    }

}
