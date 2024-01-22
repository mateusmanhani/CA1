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
    // Regular expression for name validation
    private static final String NAME_PATTERN = "^[a-zA-Z]+ [a-zA-Z0-9]+$";
    
    //Regular expression for number of classes
    private static final String CLASSES_PATTERN = "^[1-8]$";

    
    public static void readStudents() throws Exception{
        // Use try & catch to try reading the file
        try(BufferedReader reader = new BufferedReader(new FileReader("students.txt"))){
            
            String line;
            
            String firstName = null;
            String lastName = null;
            String studentID = null;
            int numClasses = 0;
            
            // Loop until there are no more lines to read in the file
            while ((line = reader.readLine()) != null) {
                // Skip Blank Lines
                if (line.trim().isEmpty()) continue;
                
//                 Log the current line for debugging
//                System.out.println("Processing line: " + line);

                try {
                    // Read first and Last names and validate
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

                } catch (ValidationException e) {
                    // Handle the exception for file reading (print error message and skip)
                    System.out.println("Error processing line: " + line + ". " + e.getMessage());
                }
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
        }
    }
    
    public static String[] verifyName(String line) throws ValidationException{
        String[] result = new String[2];
        if (line.matches(NAME_PATTERN)){
            String[] splitName = line.split(" ");
            // assign first and last name to the corresponding index
            result[0] = splitName[0];
            result[1] = splitName[1];
        }
        else throw new ValidationException("Name inavlid.");
        return result;
    }
    public static int verifyClasses(String line) throws ValidationException{
        // Validate the number of classes
        int result = 0;
        if (line.matches(CLASSES_PATTERN)){
            result = Integer.parseInt(line);
        } else throw new ValidationException("Inavlid number of classes");
        return result;
    }
    
    public static String verifyID(String line) throws ValidationException{
        String result = "invalid";

        // Ensure minimum length requirement
        if (line.length()< 6 ) throw new ValidationException("Invalid student ID format: Minimum length is 6 characters.");

        // Extract components
        String firstTwoNumbers = line.substring(0, 2);
        String letters = line.substring(2, 4);
        char fifthChar = line.charAt(4);
        String numericPart = line.substring(5);

        // Validate each component in student ID
        try {
            int firstTwoNumbersValue = Integer.parseInt(firstTwoNumbers);
            int numericPartValue;

            // Check if 5th character is a letter or digit
            if (Character.isLetter(fifthChar)) { 
                numericPartValue = Integer.parseInt(numericPart);
            }
            else if (Character.isDigit(fifthChar)) { 
                numericPartValue = Integer.parseInt(fifthChar + numericPart);
            }
            else {
                throw new ValidationException("Invalid student ID format: 5th character must be either a letter or digit.");
            }
            // Check if components meet criteria
            if (firstTwoNumbersValue >= 20 && letters.matches("[A-Za-z]{2}") &&
            numericPartValue >=1 && numericPartValue <= 200) {
                result = line;
            } else {
                throw new ValidationException("Invalid student ID format or values out of range.");
            }
        }catch(NumberFormatException e){
            throw new ValidationException("Invalid student ID format: Numeric parts must be valid integers.");            
        }
        return result;
    }
    
    // Create class to extend exception error in order to catch it to handle differently while reading from file and when readin user input
    public static class ValidationException extends Exception {
        //take an error message as parameter
        public ValidationException(String message) {
            super(message);
        }
    }
    
}
