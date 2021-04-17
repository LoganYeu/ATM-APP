/**
 * The Atm class holds the methods that are called in the main method (AtmRunner class). The methods include printing the welcome screen, using a existing card,
 * creating a new card, and printing the internal log.
 *
 * @author (Logan Yeubanks)
 * @version (Version 1)
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Atm {
    private Bank bank;
    private ArrayList<String> internalLog;

    public Atm() {
        bank = new Bank();
        internalLog = new ArrayList<>();
    }

    /**
     *prints the welcome screen consisting of a welcome and options.
     */
    public static void printWelcomeScreen() {
        System.out.println("Welcome to this ATM!");
        System.out.println("Press 1 to use an existing card.");
        System.out.println("Press 2 to create a new card");
        System.out.println("Press 3 to check the internal log of this ATM");
    }



    /**
     * contains all the logic and switch cases for validating a card and then performing actions to accounts.
     */
    public void useExistingCard() {
        boolean cardNotValidated = true;
        int wrongPinAttempts = 0;
        //main while loop that is currently set to true.
        while(cardNotValidated) {
            //gets user input for card number and pin.
            Scanner userInput = new Scanner(System.in);
            System.out.println("What is your card number? ");
            int cardNumber = userInput.nextInt();
            System.out.println("What is your cards pin number? ");
            int pin = userInput.nextInt();
            //prints the options a user has if card number and pin number are correct.
            if (bank.validateCard(cardNumber, pin)) {
                //prevents previous while loop from happening again.
                cardNotValidated = false;
                boolean running = true;
                while (running) {
                    System.out.println("Press 1 to make a deposit");
                    System.out.println("Press 2 to make a withdrawal");
                    System.out.println("Press 3 to check a balance");
                    System.out.println("Press 4 to transfer money between accounts associated with this card");
                    System.out.println("Press 5 to create a new account on this card");
                    System.out.println("Press 6 to end this session");
                    int menuAnswer = userInput.nextInt();
                    int accountNumber;
                    int accountPin;
                    boolean notValid;
                    String transaction;
                    String newBalanceAfterTransaction;
                    switch (menuAnswer) {
                        case 1:
                            System.out.println("Enter the account number you would like to deposit ");
                            accountNumber = userInput.nextInt();
                            System.out.println("Enter the pin for this account ");
                            accountPin = userInput.nextInt();
                            notValid = true;
                            while (notValid) {
                                if (bank.validateAccount(accountNumber, accountPin)) {
                                    System.out.println("Please enter a envelope with cash and/or checks");
                                    System.out.println("How much money is expected to be inside the envelope? ");
                                    int envelopeContents = userInput.nextInt();
                                    if (bank.verifyContents()) {
                                        bank.deposit(accountNumber, envelopeContents);
                                        transaction = Clock.dateAndTime() + " Machine location: Gaylord, Deposited $" + envelopeContents + " into account " + accountNumber;
                                        newBalanceAfterTransaction = "Account " + accountNumber + " now has a balance of $" + bank.getBalance(accountNumber);
                                        internalLog.add(transaction);
                                        internalLog.add(newBalanceAfterTransaction);
                                        System.out.println(transaction);
                                        System.out.println(newBalanceAfterTransaction);

                                    }
                                    notValid = false;
                                } else {
                                    System.out.println("Invalid account number or pin, try again");
                                    break;
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Enter the account number you would like to withdrawal from ");
                            accountNumber = userInput.nextInt();
                            System.out.println("Enter the pin for this account ");
                            accountPin = userInput.nextInt();
                            notValid = true;
                            while (notValid) {
                                if (bank.validateAccount(accountNumber, accountPin)) {
                                    boolean validAmount = false;
                                    while (!validAmount) {
                                        System.out.println("How much money would you like to withdraw? ");
                                        int withdrawAmount = userInput.nextInt();
                                        if (withdrawAmount % 20 == 0 && withdrawAmount <= bank.getBalance(accountNumber)) {
                                            validAmount = true;
                                            bank.withdraw(accountNumber, withdrawAmount);
                                            transaction = Clock.dateAndTime() + " Machine location: Gaylord, $" + withdrawAmount + " withdrew from account " + accountNumber;
                                            System.out.println(transaction);
                                            newBalanceAfterTransaction = "Account " + accountNumber + " now has a balance of $" + bank.getBalance(accountNumber);
                                            System.out.println(newBalanceAfterTransaction);
                                            internalLog.add(transaction);
                                            internalLog.add(newBalanceAfterTransaction);
                                            notValid = false;
                                        } else {
                                            System.out.println("Invalid amount entered, please try again. ");
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.println("Invalid account number or pin, try again");
                                    break;
                                }
                            }
                            break;

                        case 3:
                            System.out.println("Enter the account number you would like to check the balance of ");
                            accountNumber = userInput.nextInt();
                            System.out.println("Enter the pin for this account ");
                            accountPin = userInput.nextInt();
                            notValid = true;
                            while (notValid) {
                                if (bank.validateAccount(accountNumber, accountPin)) {
                                    System.out.println("Your balance is: $" + bank.getBalance(accountNumber));
                                    transaction = Clock.dateAndTime() + " Machine location: Gaylord, Balance inquiry for account " + accountNumber;
                                    internalLog.add(transaction);
                                    break;
                                } else {
                                    System.out.println("Invalid account number or pin, try again");
                                    break;
                                }
                            }
                            break;

                        case 4:
                            System.out.println("Enter the account number you would like to withdraw from ");
                            int withdrawAccountNumber = userInput.nextInt();
                            System.out.println("Enter the pin for this account ");
                            int withdrawAccountPin = userInput.nextInt();
                            System.out.println("Enter the account number you would like to deposit ");
                            int depositAccountNumber = userInput.nextInt();
                            System.out.println("Enter the pin for this account ");
                            int depositAccountPin = userInput.nextInt();
                            notValid = true;
                            while (notValid) {
                                if (bank.validateAccount(withdrawAccountNumber, withdrawAccountPin) && bank.validateAccount(depositAccountNumber, depositAccountPin)) {
                                    boolean validAmount = false;
                                    while (!validAmount) {
                                        System.out.println("How much money would you like to transfer? ");
                                        int transferAmount = userInput.nextInt();
                                        if (transferAmount % 20 == 0 && transferAmount <= bank.getBalance(withdrawAccountNumber)) {
                                            validAmount = true;
                                            bank.withdraw(withdrawAccountNumber, transferAmount);
                                            bank.deposit(depositAccountNumber, transferAmount);
                                            transaction = Clock.dateAndTime() + " Machine location: Gaylord, $" + transferAmount + " was added into account " + depositAccountNumber + " from account " + withdrawAccountNumber;
                                            System.out.println(transaction);
                                            newBalanceAfterTransaction = "Account " + depositAccountNumber + " new balance of $" + bank.getBalance(depositAccountNumber);
                                            System.out.println(newBalanceAfterTransaction);
                                            //adds the transaction and balance update to the internal log
                                            internalLog.add(transaction);
                                            internalLog.add(newBalanceAfterTransaction);
                                            break;
                                        } else {
                                            System.out.println("Invalid amount entered, please try again. ");
                                            break;
                                        }
                                    }

                                } else {
                                    System.out.println("Invalid account number or pin, try again");
                                    break;
                                }
                                break;
                            }
                            break;

                        case 5:
                            System.out.println("What would you like the pin for this account to be? ");
                            accountPin = userInput.nextInt();
                            accountNumber = bank.createAccount(accountPin);
                            System.out.println("Your account number is " + accountNumber);
                            transaction = Clock.dateAndTime() + " New account created with account number " + accountNumber;
                            internalLog.add(transaction);
                            break;
                        case 6:
                            transaction = Clock.dateAndTime() + " Session ended";
                            internalLog.add(transaction);
                            return;

                        default:
                            System.out.println("Please select a valid option!");
                            break;
                    }

                }

            }
            else {
                System.out.println("Card number or pin number was wrong, or card was retained. Please try again.");
                wrongPinAttempts += 1;
                if (wrongPinAttempts == 3) {
                    bank.retainCard(cardNumber);
                    cardNotValidated = false;
                }
            }
        }


    }

    public void createNewCard() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("What would you like the pin to be for your new card? ");
        int answer = userInput.nextInt();
        int cardNumber = bank.createNewCard(answer);
        System.out.println("Your new card number is " + cardNumber);

    }

    public void printInternalLog() {
        for(String s : internalLog) {
            System.out.println(s);
        }
    }
}
