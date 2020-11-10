package JSON;

import GameObjects.PlayingCard;

public class Message {

    public Boolean permission;
    public String action;
    public String port;
    public PlayingCard[] firstHand;
    public PlayingCard card;
    public Boolean Deck;
    public int amountDeckCards;
    public PlayingCard topDeckCard;
    public int playerLife;
    public int playerMana;
    public String name;
    public String type;
    public Boolean end;

    //public *** effectThrown; // after card

    public Message(){
        super();
    }

    public  Message(Boolean permission){
        this.permission = permission;
    }

    /*public Message(String action, Boolean Deck, int amountDeckCards, PlayingCard topDeckCard, int playerLife, int playerMana) {
        this.action = action;
        this.Deck = Deck;
        this.amountDeckCards = amountDeckCards;
        this.topDeckCard = topDeckCard;
        this.playerLife = playerLife;
        this.playerMana = playerMana;
    }

    public Message(String action, PlayingCard card, Boolean end){
        this.action = action;
        this.card = card;
        this.end = end;
    }*/

    public Message(String action, PlayingCard[] firstHand, PlayingCard topDeckCard){
        this.action = action;
        this.firstHand = firstHand;
        this.topDeckCard = topDeckCard;
    }

    public Message(String action, String port){
        this.action = action;
        this.port = port;
    }

    public Message(Boolean permission, int playerLife, int playerMana){}



}
