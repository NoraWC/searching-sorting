import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
public class Deck {
    Card[] allCards;
    Card[] hand;

    public Deck(boolean val) {
        if(val) {
            //create 52 cards
            allCards = new Card[52];
            for(int i = 0; i < 52; i++) {
                int value;

                if(i < 13) {
                    value = i+1;
                } else {
                    value = 1;
                }

                int suit;
                if(i <= 13){
                    suit = 1;
                } else if(i <= 26) {
                    suit = 2;
                } else if (i <= 39){
                    suit = 3;
                } else {
                    suit = 4;
                }

                Card newCard = new Card(suit,value,"card");
                allCards[i] = newCard;
            }
        } else {
            //create 13 cards
            allCards = new Card[13];
            int suit = ThreadLocalRandom.current().nextInt(1,5);
            for(int i = 0; i < 13; i++) {
                int value = i+1;
                Card newCard = new Card(suit, value,"card");
                allCards[i] = newCard;
            }
        }
    }
    public void swap(Card[] array, int first, int second) {
        // swaps 2 cards according to position
        Card placeholder = array[first];
        array[first] = array[second];
        array[second] = placeholder;
    }

    public void dealHand(int numCards) {
        hand = new Card[numCards];
        for(int i = 0; i < numCards; i++) {
            hand[i] = allCards[i];
        }
        //listHand();
    }

    public void listHand() {
        for(int i = 0; i < hand.length; i++) {
            System.out.print(hand[i].suit+" "+hand[i].value+" "+hand[i].name+". ");
        }
    }
    public void shuffleDeck() {
        Random hold = new Random();
        for(int i = 0; i < allCards.length; i++) {
            int randomPos = hold.nextInt(allCards.length);
            swap(allCards, i, randomPos);
        }
    }

    //sorting:
    public void bubbleSortHand() {
        for(int x = 0; x < hand.length; x++) {
            for(int i = 0; i < hand.length-1-x; i++) {
                if(hand[i].value > hand[i+1].value) {
                    swap(hand, i, i+1);
                }
            }
        }
    }
    public void selectionSortHand() {

    }
    public void mergeSortHand() {

    }
    public void binarySearch(int suit, int face) {

    }
}
