/**
 * The bank class holds information about accounts and cards as well as gives methods to validate and create new ones.
 * Also gives methods for the different types of transactions performed at the atm.
 *
 * @author (Logan Yeubanks)
 * @version (Version 1)
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    //fields for Bank class.
    private HashMap<Card, ArrayList<Account>> cardToAssociatedAccounts;
    private Card currentCard;
    private ArrayList<Integer> retainedCards;

    public Bank() {
        // sets currentCard to null and makes a new Hashmap for cardToAssociatedAccounts and ArrayList for retainedCards
        cardToAssociatedAccounts = new HashMap<>();
        currentCard = null;
        retainedCards = new ArrayList<>();
    }

    /**
     * method that creates a new card with the pin specified
     *
     * @param pin
     * @return newCard.getCardNumber()
     */
    public int createNewCard(int pin) {
        Card newCard = new Card(pin);
        cardToAssociatedAccounts.put(newCard, new ArrayList<>());
        return newCard.getCardNumber();
    }

    /**
     * validates the card by checking to make sure it has not been retained, and then compares user answer to to the map containing existing cards and their pins.
     *
     * @param cardNumber pin
     * @param pin
     * @return false or entry.getKey().validateCard(pin)
     */
    public boolean validateCard(int cardNumber, int pin) {
        //returns false if cardNumber is found in list of retained card
        if(retainedCards.contains(cardNumber)) {
            return false;
        }
        for(Map.Entry<Card, ArrayList<Account>> entry : cardToAssociatedAccounts.entrySet()) {
            if(entry.getKey().getCardNumber() == cardNumber) {
                currentCard = entry.getKey();
                return entry.getKey().validateCard(pin);
            }
        }
        return false;
    }

    /**
     * creates new account with given pin and adds it to list of accounts that the current card has access to.
     *
     * @param pin
     * @return createdAccount.getAccountNumber()
     */
    public int createAccount(int pin) {
        Account createdAccount = new Account(pin);
        cardToAssociatedAccounts.get(currentCard).add(createdAccount);
        return createdAccount.getAccountNumber();

    }

    /**
     * validates the account inputted by user by comparing it to list of accounts and their pins.
     *
     * @param accountNumber
     * @param pin
     * @return i.validatePin(pin) or false
     */
    public boolean validateAccount(int accountNumber, int pin) {
        ArrayList<Account> accounts = cardToAssociatedAccounts.get(currentCard);
        for(Account i : accounts) {
            if(i.getAccountNumber() == accountNumber) {
                return i.validatePin(pin);
            }
        }
        return false;
    }

    public boolean verifyContents() {
        return true;
    }

    /**
     * Deposits the amount specified into the specified account related to the current card.
     *
     * @param accountNumber
     * @param envelopeContents
     */
    public void deposit(int accountNumber, int envelopeContents) {
        ArrayList<Account> accounts = cardToAssociatedAccounts.get(currentCard);
        for(Account i : accounts) {
            if(i.getAccountNumber() == accountNumber) {
                i.deposit(envelopeContents);
            }
        }
    }

    /**
     * returns the balance of the specified account linked to the current card.
     *
     * @param accountNumber
     * @return
     */
    public int getBalance(int accountNumber) {
        ArrayList<Account> accounts = cardToAssociatedAccounts.get(currentCard);
        for(Account i : accounts) {
            if(i.getAccountNumber() == accountNumber) {
               return i.getBalance();
            }
        }
        return 0;
    }

    /**
     * returns the balance for the account after the amount specified is taken out of the balance.
     *
     * @param accountNumber
     * @param withdrawAmount
     * @return
     */
    public int withdraw(int accountNumber, int withdrawAmount) {
        ArrayList<Account> accounts = cardToAssociatedAccounts.get(currentCard);
        for(Account i : accounts) {
            if(i.getAccountNumber() == accountNumber) {
                i.withdraw(withdrawAmount);
                return i.getBalance();
            }
        }
        return 0;
    }

    /**
     * adds card numbers to a list that holds retained cards.
     *
     * @param cardNumber
     */
    public void retainCard(int cardNumber) {
        retainedCards.add(cardNumber);
    }
}
