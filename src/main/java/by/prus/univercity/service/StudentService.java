package by.prus.univercity.service;

import java.util.List;

public interface StudentService {
    public boolean saveStudent(String[] studentParams);
    public List<String[]> getAllStudents();
}
