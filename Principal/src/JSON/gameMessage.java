package JSON;

import GameObjects.PlayingCard;

public class gameMessage extends Message {

    //Actualizacion de SERVER - CLIENTE
    public gameMessage(String action, Boolean Deck, int amountDeckCards, PlayingCard topDeckCard, int playerLife, int playerMana) {
        this.action = action;
        this.Deck = Deck;
        this.amountDeckCards = amountDeckCards;
        this.topDeckCard = topDeckCard;
        this.playerLife = playerLife;
        this.playerMana = playerMana;
    }

    // Se salta - o se saca de baraja
    public gameMessage(String action, Boolean end){
        this.action = action;
        this.end = end;
    }

    //Se elige jugar carta
    public gameMessage(String action, PlayingCard card, Boolean end){
        this.action = action;
        this.card = card;
        this.end = end;
    }



}
