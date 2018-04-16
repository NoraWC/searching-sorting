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
            //System.out.print(hand[i].value+". ");
        }
        //System.out.print("end ");
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
        for(int x = 0; x < hand.length-1; x++) {
            Card lowest = hand[x];
            int lowestIndex = x;
            for (int i = x+1; i < hand.length; i ++) {
                //if the card here is lower than the current lowest
                if (hand[i].value < lowest.value) {
                    //change the value of lowest to this card
                    lowest = hand[i];
                    //change the index too
                    lowestIndex = i;
                }
            }
            //now that we have the lowest card, move it to the beginning of the array
            if(lowestIndex != x) {
                swap(hand, lowestIndex, x);
            }
        }
    }
    public void mergeSortHand() {
        //keep halving array until you get to 1s
        //sort each of the arrays + merge into pairs, 4s, etc


        //need to make sure the arrays will split down all the way
        //how to swap the arrays?

        Card[] sorted = new Card[(hand.length)];

        for(int i = 0; i < hand.length; i ++) {
            //splits array for 1st time (halves)
            Card[] halfHand1 = new Card[(hand.length)/2];
            Card[] halfHand2 = new Card[(hand.length)/2];
            System.arraycopy(hand, 0, halfHand1, 0, halfHand1.length);
            System.arraycopy(hand, hand.length/2, halfHand2, 0, halfHand2.length);

            //splits array again (quarters)
            Card[] tempArray = new Card[(halfHand1.length)/2];
            Card[] tempArray2 = new Card[(halfHand1.length)/2];
            Card[] tempArray3 = new Card[(halfHand2.length)/2];
            Card[] tempArray4 = new Card[(halfHand2.length)/2];

            System.arraycopy(halfHand1, 0, tempArray, 0, tempArray.length);
            System.arraycopy(halfHand1, halfHand1.length/2, tempArray2, 0, tempArray.length);

            System.arraycopy(halfHand2, 0, tempArray3, 0, tempArray.length);
            System.arraycopy(halfHand2, halfHand2.length/2, tempArray4, 0, tempArray.length);


            //swaps within the quarters
            for(int x = 0; x < halfHand1.length-1; x++) {
                if(tempArray[x].value > tempArray[x+1].value) {
                    swap(tempArray, x, x+1);
                }
                if(tempArray2[x].value > tempArray2[x+1].value) {
                    swap(tempArray2, x, x+1);
                }

                if(tempArray3[x].value > tempArray3[x+1].value) {
                    swap(tempArray3, x, x+1);
                }
                if(tempArray4[x].value > tempArray4[x+1].value) {
                    swap(tempArray4, x, x+1);
                }

                System.arraycopy(tempArray, 0, halfHand1, 0, halfHand1.length);
                System.arraycopy(tempArray2, halfHand1.length/2, halfHand1, 0, halfHand1.length);

                System.arraycopy(tempArray3, 0, halfHand2, 0, halfHand2.length);
                System.arraycopy(tempArray4, halfHand2.length/2, halfHand2, 0, halfHand2.length);
            }
            System.arraycopy(halfHand1, 0, hand, 0, hand.length);
            System.arraycopy(halfHand2, hand.length/2, hand, 0, hand.length);
        }

    }

    public void binarySearch(int suit, int face) {

    }
}
