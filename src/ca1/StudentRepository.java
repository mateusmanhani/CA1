package ca1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mateus Manhani
 */
public class StudentRepository {
    
    // Instatiate List of student objects
    public static List<Student> studentsList = new ArrayList<>();
    
    // add student static method;
    public static void addStudent(Student newStudent){
        studentsList.add(newStudent);
    }
    // getter for studentList
    public static List<Student> getStudents(){
        return studentsList;
    }
}
