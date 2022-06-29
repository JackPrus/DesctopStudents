package by.prus.univercity.entity;

import com.sun.istack.Nullable;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;
    private String name;
    private String secondName;
    private String fatherName;
    private Date birthDate;
    private String studyGroup;

    public Student(){}

    public Student(String name, String secondName, String fatherName, Date birthDate, String studyGroup) {
        this.name = name;
        this.secondName = secondName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.studyGroup = studyGroup;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSecondName() { return secondName; }
    public void setSecondName(String secondName) { this.secondName = secondName; }
    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public String getStudyGroup() { return studyGroup; }
    public void setStudyGroup(String studyGroup) { this.studyGroup = studyGroup; }
}
