public class runner {
    public static void main (String[] args) {
        Deck d = new Deck(true);
        d.shuffleDeck();
        d.dealHand(7);
        d.bubbleSortHand();
        d.listHand();
    }
}

