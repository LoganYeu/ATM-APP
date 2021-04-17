/**
 * The Card class holds card numbers and gives methods to generate, validate, and get card numbers.
 *
 * @author (Logan Yeubanks)
 * @version (Version 1)
 */

import java.util.ArrayList;
import java.util.Random;

public class Card {
    //fields for Card class.
    private int cardNumber;
    private int pin;
    private static ArrayList<Integer> cardNumberList;

    public Card(int pin) {
        //creates new list of cards if cardNumberList is empty.
        if(cardNumberList == null) {
            this.cardNumberList = new ArrayList<>();
        }
        this.pin = pin;
        this.cardNumber = generateCardNumber();
    }

    /**
     * generates a card number with a max of 8 digits, adds it to list of card numbers, and returns it,
     *
     * @return num
     */
    private int generateCardNumber() {
        Random rand = new Random();
        int max = 10000000;
        int num = rand.nextInt(max);
        while (cardNumberList.contains(num)) {
            num = rand.nextInt();
        }
        cardNumberList.add(num);
        return num;
    }

    /**
     * validates the pin number
     *
     * @param pin
     * @return true or false
     */
    public boolean validateCard(int pin) {
        if(pin == this.pin) {
            return true;
        }
        return false;
    }

    /**
     * returns cardNumber for later use.
     *
     * @return cardNumber
     */
    public int getCardNumber() {
        return cardNumber;
    }

}
