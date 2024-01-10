package ca1;

/**
 *
 * @author Mateus
 */
public class Student {
    public String firstName;
    public String lastName;
    public String workLoad;
    public String studentID;
    public int numClasses;
    
    public Student(String firstName, String lastName, String studentID, int numClasses){
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.numClasses = numClasses;
        this.workLoad = null;
    }
}
