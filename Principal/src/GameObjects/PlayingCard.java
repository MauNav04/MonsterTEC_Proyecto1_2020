package GameObjects;

public class PlayingCard {
    public String type;
    public String attribute;
    public int mana;

    public PlayingCard(){
    }

    public PlayingCard(String type, String attribute, int mana){
        this.type = type;
        this.attribute = attribute;
        this.mana = mana;
    }

}
