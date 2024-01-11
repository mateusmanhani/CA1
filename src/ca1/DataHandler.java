package ca1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Mateus Manhani
 */
public class DataHandler {
    
    public static void readStudents(){
        // Use try & catch to try to read the file
        try{
            BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
            String line;
            
            String firstName = null;
            String lastName = null;
            String studentID = null;
            int numClasses = 0;
            
            // Loop until there are no more lines to read in the file
            while ((line = reader.readLine()) != null) {
                
                //Read first and Last names and validate
                if (line.matches("^[a-zA-Z]+ [a-zA-Z0-9]+$")){
                    String[] splitName = line.split(" ");
                    // assign first and last name to the corresponding variable
                    firstName = splitName[0];
                    lastName = splitName[1];
                }
                else System.out.println("Name invalid");
                
                // Read the number of classes
                line = reader.readLine();
                if (line.matches("^[1-8]$")){
                    numClasses = Integer.parseInt(line);
                } else System.out.println("Invalid Number of Classes");
                
                // Read student ID
                
                /* Regex pattern validating studentID starts with a two digit number higher then 20
                Then matches two more letters
                Then matches zero or more alphanumeric Characters
                Then matches one or two numeric characters, ensuring the number is between 1 and 99.*/
                String idPattern = "^[2-9][0-9][A-Za-z]{2}[A-Za-z0-9]*[1-9][0-9]?$"; 
                line = reader.readLine();
                if (line.matches(idPattern)){
                    studentID = line;
                } else System.out.println("Inavlid Student ID");
                // Create new student Object
                Student newStudent = new Student(firstName, lastName, studentID, numClasses);
                // Add newStudent to student Repository
                StudentRepository.addStudent(newStudent);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
