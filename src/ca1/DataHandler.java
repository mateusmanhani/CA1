package ca1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Mateus Manhani
 */
public class DataHandler {
    
    public static void readStudents() throws Exception{
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
                // Skip Blank Lines
                if (line.trim().isEmpty()) continue;
                //Read first and Last names and validate
                String[] studentName = verifyName(line);
                firstName = studentName[0];
                lastName = studentName[1];

                
                // Read the number of classes
                line = reader.readLine();
                numClasses = verifyClasses(line);
                // Read student ID
                line = reader.readLine();
                studentID = verifyID(line);

                // Create new student Object
                Student newStudent = new Student(firstName, lastName, studentID, numClasses);
                // Add newStudent to student Repository
                StudentRepository.addStudent(newStudent);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void writeStudents(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("status.txt"))){
            //Iterate through students in studentRepository
            for (Student student : StudentRepository.getStudents()){
                //Write Student ID and last Name to first line
                writer.write(student.getStudentID()+ " - " + student.getLastName());
                writer.newLine();
                
                // Write workload
                writer.write(student.getWorkload());
                writer.newLine();
                
                // Write a blank line to separate entries
                writer.newLine();
            }
        } catch (Exception e){
            System.out.println("Unable to write to file.");
            e.printStackTrace();
        }
    }
    
    public static String[] verifyName(String line) throws Exception{
        String[] result = new String[2];
        if (line.matches("^[a-zA-Z]+ [a-zA-Z0-9]+$")){
            String[] splitName = line.split(" ");
            // assign first and last name to the corresponding index
            result[0] = splitName[0];
            result[1] = splitName[1];
        }
        else throw new Exception("Name inavlid.");
        return result;
    }
    public static int verifyClasses(String line) throws Exception{
        // Validate the number of classes
        int result = 0;
        if (line.matches("^[1-8]$")){
            result = Integer.parseInt(line);
        } else throw new Exception("Inavlid number of classes");
        return result;
    }
    
    public static String verifyID(String line) throws Exception{
        String result = "invalid";
        /* Regex pattern validating studentID starts with a two digit number higher then 20
        Then matches two more letters
        Then matches zero or more alphanumeric Characters
        Then matches one or two numeric characters, ensuring the number is between 1 and 99.*/
        
         // Validate student ID
        String idPattern = "^[2-9][0-9][A-Za-z]{2}[A-Za-z0-9]*[1-9][0-9]?$"; 
        if (line.matches(idPattern)){
            result = line;
        } else throw new Exception("Invalid student ID.");
        return result;
    }
    
}
