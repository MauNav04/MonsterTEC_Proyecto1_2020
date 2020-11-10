package GameObjects;

public class Player{

    public String name;
    private int vida;
    private int mana;
    private boolean value;


    public Player(String name, int vida, int mana, boolean value) {
        this.name = name;
        this.vida = vida;
        this.mana = mana;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void min_Mana(int n){
        this.mana= mana-n;
    }

    public void max_Mana(int n){
        this.mana= mana+n;
    }


    public void min_Vida(int n){
        this.vida= vida-n;
    }

    public void max_Vida(int n){
        this.vida= vida+n;
    }


}
