/**
 * The AtmRunner class holds the main method and runs the ATM and holds the initial switch cases and while loop of the program.
 *
 * @author (Logan Yeubanks)
 * @version (Version 1)
 */

import java.util.Scanner;

public class AtmRunner {
    //password used to get into ATMs internal log.
    private static final String INTERNAL_LOG_PASSWORD = "assignment7";


    public static void main(String[] args) {
    printInitialMenu(new Atm());
    }


    public static void printInitialMenu(Atm atm) {
        Scanner userInput = new Scanner(System.in);
        //main while loop
        while(true) {
            boolean validInput = false;
            while (!validInput) {
                Atm.printWelcomeScreen();
                int answer = userInput.nextInt();
                //Switch cases that take input as 1, 2, or 3 to switch between the different operations of the ATM
                switch(answer) {
                    //uses a existing card and sets validInput to true.
                    case 1:
                        atm.useExistingCard();
                        validInput = true;
                        break;
                    //creates a new card and sets validInput to true.
                    case 2:
                        atm.createNewCard();
                        validInput = true;
                        break;
                    //lets the user input the password (assignment7) to check the internal log. Then sets validInput to true.
                    case 3:
                        System.out.println("What is the password? ");
                        String passwordAnswer = userInput.next();
                        if(passwordAnswer.equals(INTERNAL_LOG_PASSWORD)) {
                            atm.printInternalLog(); // this is next, code the method to print the internal log.
                        }
                        validInput = true;
                        break;
                    //prints a statement if the user does not input 1, 2, or 3.
                    default:
                        System.out.println("Please select a valid option.");

                }
            }
        }
    }

}
