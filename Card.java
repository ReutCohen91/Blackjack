/*This class represents a card in a standard card deck which has a face and a suit.
Also the card has a value according to it's face*/
public class Card {

    /*The attributes of the card*/
    private String face;
    private String suit;
    private int value;

    /*The constructor which creates a new Card, given a face and a suit*/
    public Card(String face, String suit) {
        this.suit = suit;
        this.face = face;

    }
    /*Setters and Getters methods*/
    public void setValue(int val){
        this.value = val;
    }

    public int getValue(){
        return this.value;
    }

    public String getFace(){
        return this.face;
    }

    /*overriding toString method*/
    public String toString()
    {
        return face + "_of_" + suit;
    }



}
