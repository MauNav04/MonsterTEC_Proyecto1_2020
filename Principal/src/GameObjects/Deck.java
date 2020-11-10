package GameObjects;

public class Deck {
    private PlayingCard[] completeDeck;
    //private PlayingCard[] playingDeck;

    public Deck(){

    }

    public Deck(PlayingCard[] completeDeck){
        this.completeDeck = completeDeck;
    }

    public PlayingCard[] getCompleteDeck() {
        return completeDeck;
    }
}
