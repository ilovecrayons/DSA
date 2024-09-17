import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class Student {
    private int id;
    private String name;
    private int grade;

    public Student (int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }   

    public int getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkValid(){
        return ((id < 0 || grade < 9 || grade > 12 || name == null || name.equals("")) ? false : true);
    }

    public String toString(){
        return "ID: " + id + " Name: " + name + " Grade: " + grade;
    }
}