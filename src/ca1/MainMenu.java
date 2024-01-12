
package ca1;

import java.util.Scanner;

/**
 * @author Mateus Manhani
 */
public class MainMenu {
    public static void showMenu(){
        
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
            }catch(Exception e){
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
    
    private static void takeUserInput(Scanner sc){
        //Declare nescessary variables
        String input = null;
        
        //Variables for instatiating Student
        String firstName = null;
        String lastName = null;
        String studentID = null;
        int numClasses = 0;
        
        while (true){
            //Take input for fisrt and last name
            System.out.println("Please enter your first and last name separated by one single space:");
            input = sc.nextLine();
            
            try{
                //Validate input and save to variables
                String[] splitName = DataHandler.verifyName(input);
                firstName = splitName[0];
                lastName = splitName[1];
                
                //Validate and save input for Number of classes
                System.out.println("Please enter the number of classes 1 through 8:");
                numClasses = DataHandler.verifyClasses(sc.nextLine());
                
                // Validate and save student ID
                System.out.println("Please enter the student ID: ");
                studentID = DataHandler.verifyID(sc.nextLine());
                
                // If all validation passes, create and add new Student
                Student newStudent = new Student(firstName, lastName, studentID, numClasses);
                StudentRepository.addStudent(newStudent);
                
                // Ask user  if he wants to add another student
                System.out.println("Would you like to add another student? (yes/no): ");
                String addAnother = sc.nextLine().toLowerCase();
                
                if (!addAnother.equals("yes")) break; // exit loop if not yes
            } catch(Exception e){
                System.out.println("Invalid Input. Please try again.");
            }  
        }

    }
}
