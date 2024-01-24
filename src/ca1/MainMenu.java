
package ca1;

import java.util.Scanner;

/**
 * @author Mateus Manhani
 */
public class MainMenu {
    public static void showMenu() throws Exception{
        
        boolean exit = false;
        
        while(exit!=true){
            System.out.println("Welcome to this student application. Read carefully.");
            System.out.println("1. Read data from local file");
            System.out.println("2. Manually input data");
            System.out.println("Please enter the number corresponding to the desired option: ");

            Scanner sc = new Scanner(System.in);
            int choice = 0;
            String input = sc.nextLine();

            try{
                choice = Integer.parseInt(input);
            }catch(NumberFormatException e){
                System.out.println("Not an Integer");
            }
            
            switch (choice){
                case 1:
                    // Read data from local file
                    DataHandler.readStudents();
                    DataHandler.writeStudents();
                    exit = true;
                    break;
                case 2:
                    //Read data from user Input
                    takeUserInput(sc);
                    DataHandler.writeStudents();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            } 
        }
    }
    
    public static void takeUserInput(Scanner sc) {
        while (true) {
            String[] studentName = getValidName(sc); 
            String firstName = studentName[0];
            String lastName = studentName[1];
            int numClasses = getValidNumClasses(sc);
            String studentID = getValidStudentID(sc);

            // If all validation passes, create and add new Student
            Student newStudent = new Student(firstName, lastName, studentID, numClasses);
            StudentRepository.addStudent(newStudent);

            // Ask the user if they want to add another student
            if (!askForAnotherStudent(sc)) {
                break; // Exit the loop if not "yes"
            }
        }
    }   
    private static String[] getValidName (Scanner sc){    
        //Take input for fisrt and last name
        while (true){
            try{
                System.out.println("Please enter your first and last name separated by one single space:");
                String input = sc.nextLine();
                return DataHandler.verifyName(input);               
            } catch(DataHandler.ValidationException e){
                System.out.println("Error " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
    private static int getValidNumClasses(Scanner sc){
        // Take input for number of classes
        while (true) {
            try{
                System.out.println("Please enter the number of classes (1 through 8):");
                return DataHandler.verifyClasses(sc.nextLine());
            } catch (DataHandler.ValidationException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please Try again.");
            }      
        }
    }
    private static String getValidStudentID(Scanner sc){
        // Take input for student ID
        while (true) {
            try{
                System.out.println("Please enter the student ID: ");
                return DataHandler.verifyID(sc.nextLine()); 
            } catch (DataHandler.ValidationException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please Try again.");
            }
        }
    }
    private static boolean askForAnotherStudent (Scanner sc){
        System.out.println("Would you like to add another student? (yes/no): ");
        String addAnother = sc.nextLine().toLowerCase();
        return addAnother.equals("yes"); // return true if user types in yes
    }
}