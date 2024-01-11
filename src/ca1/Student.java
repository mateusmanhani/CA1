package ca1;

/**
 *
 * @author Mateus
 */
public class Student {
    public String firstName;
    public String lastName;
    public String workload;
    public String studentID;
    public int numClasses;
    
    public Student(String firstName, String lastName, String studentID, int numClasses){
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.numClasses = numClasses;
        this.workload = null;
    }
    // Getter methods
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getStudentID(){
        return studentID;
    }
    
    public int getNumClasses(){
        return numClasses;
    }
    
    //Setter for workload    
    public void setWorkload(int numClasses){
        if (numClasses < 1) System.out.println("This student has no workload.");
        if (numClasses == 1) this.workload = "very light";
        else if(numClasses == 2) this.workload = "light";
        else if(numClasses >= 3 && numClasses <= 5) this.workload = "part time";
        else this.workload = "full time";
    }
}
