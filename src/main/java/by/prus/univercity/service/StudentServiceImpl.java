package by.prus.univercity.service;

import by.prus.univercity.dao.StudentDao;
import by.prus.univercity.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public boolean saveStudent(String[] studentParams) {
        try {
            studentDao.save(getStudentFromText(studentParams));
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String[]> getAllStudents() {
        List<Student> studentList = (List<Student>) studentDao.findAll();
        List<String[]> resultList = new ArrayList<>();
        for (Student student : studentList) {
            resultList.add(getTextArrFromStudent(student));
        }
        return resultList;
    }

    public boolean deleteStudent(String[] studentParams) {
        try {
            Long studenId = Long.parseLong(studentParams[0]);
            studentDao.deleteById(studenId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Student getStudentFromText(String[] studentParams) throws ParseException {
        String name = studentParams[0];
        String secondName = studentParams[1];
        String fatherName = studentParams[2];
        String group = studentParams[4];
        String stringDate = studentParams[3];
        Date birthDate = simpleDateFormat.parse(stringDate);
        return new Student(name, secondName, fatherName, birthDate, group);
    }

    private String[] getTextArrFromStudent(Student student) {
        String[] result = new String[]{
                String.valueOf(student.getId()),
                student.getName(),
                student.getSecondName(),
                student.getFatherName(),
                simpleDateFormat.format(student.getBirthDate()),
                student.getStudyGroup()
        };
        return result;
    }
}
