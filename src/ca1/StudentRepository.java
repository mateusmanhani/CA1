package ca1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mateus Manhani
 */
public class StudentRepository {
    public static List<Student> studentsList = new ArrayList<Student>();
    
    public static void addStudent(Student newStudent){
        studentsList.add(newStudent);
    }
    
    public static List<Student> getStudents(){
        return studentsList;
    }
}
