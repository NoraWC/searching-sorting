import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
public class Deck {
    Card[] allCards;
    Card[] hand;

    public Deck(boolean val) {
        if (val) {
            //create 52 cards
            allCards = new Card[52];
            for (int i = 0; i < 52; i++) {
                int value = 0;
                String name = "";
                int suit;
                if (i <= 13) {
                    suit = 1;
                    name = " of diamonds";
                    value += i + 1;
                } else if (i <= 26) {
                    suit = 2;
                    name = " of clubs";
                    value += i - 13;
                } else if (i <= 39) {
                    suit = 3;
                    name = " of spades";
                    value += i - 26;
                } else {
                    suit = 4;
                    name = " of hearts";
                    value += i - 39;
                }

                if (value < 11 && value > 1) {
                    name = value + name;
                } else {
                    if (value == 1) {
                        name = "ace" + name;
                    }
                    if (value == 11) {
                        name = "jack" + name;
                    }
                    if (value == 12) {
                        name = "queen" + name;
                    }
                    if (value == 13) {
                        name = "king" + name;
                    }
                }
                Card newCard = new Card(suit, value, name);
                allCards[i] = newCard;
            }
        } else {
            //create 13 cards
            allCards = new Card[13];
            int suit = ThreadLocalRandom.current().nextInt(1, 5);
            for (int i = 0; i < 13; i++) {
                int value = i + 1;
                String name = "";

                if (value < 11 && value > 1) {
                    name = value + name;
                } else {
                    if (value == 1) {
                        name = "ace" + name;
                    }
                    if (value == 11) {
                        name = "jack" + name;
                    }
                    if (value == 12) {
                        name = "queen" + name;
                    }
                    if (value == 13) {
                        name = "king" + name;
                    }

                    Card newCard = new Card(suit, value, name);
                    allCards[i] = newCard;
                }
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
    }

    public void listHand() {
        for(int i = 0; i < hand.length; i++) {
            System.out.print(hand[i].name+". ");
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

        //not perfected--need to make sure the arrays will split down all the way

        Card[] sorted = new Card[hand.length];
        //System.arraycopy(hand, 0, sorted, 0, hand.length);

        Card[] halfHand1 = new Card[(hand.length / 2)];
        Card[] halfHand2 = new Card[(hand.length / 2)+1];
        System.arraycopy(hand, 0, halfHand1, 0, halfHand1.length);
        System.arraycopy(hand, (hand.length-1)/2, halfHand2, 0, halfHand2.length);


        for(int i = 0; i < hand.length; i ++) {
            //swaps within the parts
            for(int x = 0; x < halfHand1.length-1; x++) {
                if(halfHand1[x].value > halfHand1[x+1].value) {
                    swap(halfHand1, x, x+1);
                }
                if(halfHand2[x].value > halfHand2[x+1].value) {
                    swap(halfHand2, x, x+1);
                }

                System.arraycopy(halfHand1, 0, halfHand1, 0, halfHand1.length/2);
                System.arraycopy(halfHand2, (hand.length-1)/2, halfHand2, 0, (halfHand2.length-1)/2);

            }
            System.arraycopy(halfHand1, 0, sorted, 0, (hand.length-1)/2);
            System.arraycopy(halfHand2, 0, sorted, hand.length/2, (hand.length-1)/2);
        }
        for(int z = 0; z < sorted.length-2; z++) {
            if(sorted[z].value > sorted[z+1].value) {
                swap(sorted, z, z+1);
            }
        }
        System.arraycopy(sorted, 0, hand, 0, hand.length);
    }

    public void binarySearch(int suit, int face, int interval) {
        //does its best; sometimes returns wrong value because it's not in the array
        selectionSortHand();

        Card check = new Card(suit, face, "check");

        if(check.value < hand[interval].value) {
            interval = interval/2;
            binarySearch(suit, face, interval);
        } else if (check.value > hand[interval].value) {
            interval += interval / 2;
            binarySearch(suit, face, interval);
        } else if (check.value == hand[interval].value && check.suit == hand[interval].suit) {
            System.out.print(hand[interval].suit + " " + hand[interval].value + " " + hand[interval].name + " found. ");
        }
    }

}
