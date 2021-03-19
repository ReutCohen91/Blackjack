import java.util.ArrayList;
//This class represents a standard deck of cards which contains 52 different cards (without Jokers).
//The deck is implemented through an ArrayList.

public class DeckOfCards {
    //The attributes of the deck
    private static final String[] face = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
            , "Ten", "Jack", "Queen", "King"};//The different faces of the cards in the deck

    private static final String[] suit = {"Hearts", "Spades", "Diamonds", "Clubs"};//The different suits of the cards

    private static final int MAX_FACE = 13;//The number of possible faces in the deck
    private static final int MAX_SUIT = 4;//The number of possible suits in the deck
    private final int ROYAL_VAL = 10;//The value of King, Queen and Jack

    private ArrayList <Card> deck = new ArrayList<Card>();//The ArrayList of the deck



    public DeckOfCards() { /*A non parameter constructor would create a full deck containing 52 cards*/

        for (int i = 0; i < MAX_FACE; i++) {/*Creating a card of each face and suit */
            for (int j = 0; j < MAX_SUIT; j++) {
                deck.add(new Card(face[i], suit[j]));//adding the card to the ArrayList

            }
        }
        int value=0;
        for (int i = 0; i <=deck.size()-1 ; i++) {
            /*If the card's face is neither Jack, nor Queen nor King, its' value will be its' face's value*/
            if((deck.get(i).getFace()!="Jack")&&(deck.get(i).getFace()!="Queen")&&(deck.get(i).getFace()!="King")) {
                if (i % MAX_SUIT == 0)//When we get to the fourth suit it means we already initialized the values of
                    //all the cards with the same face and so we increment the value by one
                    value++;
            }
            else/*Otherwise if it's a royal card, it will have a special royal value*/
                value = ROYAL_VAL;
            deck.get(i).setValue(value);//Now we will set the value of the card
        }
    }

    public DeckOfCards(int i){/*This constructor has an integer parameter which signals creating of an empty deck*/
        return;
    }

    public ArrayList<Card> getDeck() {//returning the deck
        return deck;
    }


    //overriding the toString method in order to print the cards in the deck
    public String toString() {
        return "" + deck ;
    }

    //This method checks if a specific face is within the deck (the suit is not important here).
    //It receives a String which is the face we're looking for and looks for it within the cards of the deck
    public boolean contains(String f){
        for (int i = 0; i <this.deck.size() ; i++) {//if we find the face within the deck, we'll return true
            if(this.deck.get(i).getFace()==f)
                return true;
        }
        return false;
    }


}




