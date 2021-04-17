/**
 * The Account class gives methods to generate and get account numbers. Also gives the math behind the various transactions and a method to validate account pins.
 *
 * @author (Logan Yeubanks)
 * @version (Version 1)
 */

import java.util.ArrayList;
import java.util.Random;

public class Account {
    //fields for Account class.
    private int pin;
    private int accountNumber;
    private static ArrayList<Integer> accountNumberList;
    private int balance;

    public Account(int pin) {
        //creates new list of account numbers if there is not one already.
        if(accountNumberList == null) {
            this.accountNumberList = new ArrayList<>();
        }
        this.pin = pin;
        this.accountNumber = generateAccountNumber();
        balance = 0;
    }

    /**
     * generates a 6 digit account number randomly and returns it.
     *
     * @return num
     */
    private int generateAccountNumber() {
        Random rand = new Random();
        int max = 100000;
        int num = rand.nextInt(max);
        while(accountNumberList.contains(num)) {
            num = rand.nextInt();
        }
        accountNumberList.add(num);
        return num;
    }

    /**
     * returns the accountNumber.
     *
     * @return accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * validates the pin passed in and returns true or false.
     *
     * @param pin
     * @return true or false
     */
    public boolean validatePin(int pin) {
        if(pin == this.pin) {
            return true;
        }
        return false;
    }

    /**
     * math behind depositing money into a account. Takes envelope contents and adds it to the current balance.
     *
     * @param envelopeContents
     */
    public void deposit(int envelopeContents) {
        balance += envelopeContents;
    }

    /**
     * method to return balance. Used to check balance of an account later.
     *
     * @return balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * math behind witdrawing money from an account. Subtracts the specified withdraw amount and then sets balance to the result.
     *
     * @param withdrawAmount
     */
    public void withdraw(int withdrawAmount) {
        balance -= withdrawAmount;
    }
}
