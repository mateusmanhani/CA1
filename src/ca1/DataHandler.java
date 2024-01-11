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
            
            // Loop until there are no more lines to read in the file
            while ((line = reader.readLine()) != null) {
                //Read first and Last names
                String[] nameParts = line.split(" ");
                String firstName = nameParts[0];
                String lastName = nameParts[1];
                
                // Read the number of classes
                int numClasses = Integer.parseInt(reader.readLine());
                
                // Read student ID
                String studentID = reader.readLine();
                
                // Create new student Object
                Student newStudent = new Student(firstName, lastName, studentID, numClasses);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
