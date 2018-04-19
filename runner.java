public class runner {
    public static void main (String[] args) {
        Deck d = new Deck(false);
        d.shuffleDeck();
        d.dealHand(6);
        //d.binarySearch(3,1,5);
        d.listHand();
    }
}

