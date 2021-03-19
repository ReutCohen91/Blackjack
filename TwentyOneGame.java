/*This class represents the card game "Twenty-One". It creates decks for the dealer which is the neutral deck of cards
* from which cards are drawn, the PC which is the rival and the User which is the human player of the game.
* Then, the game is played by showing an interactive message window which shows the drawn cards and receives instructions
* from the User about drawing more cards or ending the turn. The computer will also draw cards by its own rules that would
* help him increase the chances of winning so that the game would be more challenging for the user */
import javax.swing.*;
import java.util.Random;

public class TwentyOneGame  {
//Private attributes of the class
    //The decks of the game which use DeckOfCards objects
    private DeckOfCards pcD;
    private DeckOfCards userD;
    private DeckOfCards dealer;
    //The goal of the game and the higher value of an Ace face
    private static final int GOAL = 21;
    private static final int ACE_VAL = 11;

//The default (and only) constructor of the class creates the decks and then calls the game executing method
    public TwentyOneGame() {
        dealer = new DeckOfCards();//Calls the non parameter constructor which creates a full deck
        userD = new DeckOfCards(1);//Calls the constructor with an integer which creates an empty deck
        pcD =new DeckOfCards(1);//Calls the constructor with an integer which creates an empty deck

    }

    public Card shuffledDraw(){/*Drawing a random card from the dealer's deck*/

        Random rand = new Random();
        int rnd = rand.nextInt(dealer.getDeck().size());//setting the maximal possible random number
        Card randCard = dealer.getDeck().get(rnd);//getting the random chosen card from the dealer's deck

        dealer.getDeck().remove(rnd);//removing that card from the dealer's deck so it cannot be drawn again
        return randCard;
    }

    //The method which executes the game. This method interacts with the user to create his deck and finish his turn.
    //It also creates the deck of the PC and eventually decided who the Winner is and then announces it.
    public void twentyOne(){
        //The current points of each player are initialized to 0 at the beginning of the game
        int userRes=0;
        int pcRes=0;


        /*Drawing two cards for each player and adding to the score*/
        for (int k = 0; k <2 ; k++) {
            userD.getDeck().add(shuffledDraw());
            userRes+=userD.getDeck().get(k).getValue();

        }
        int k=1;
        /*asking the user if he wants to draw more cards or end the game*/
        int reply = JOptionPane.showConfirmDialog(null, "Your cards are:" +userD.toString()+
                                                "\nWould you like to draw another card?");


        while(reply==0) {//while the user's answer is yes
            userD.getDeck().add(shuffledDraw());//continue to add cards to his deck
            k++;
            userRes += userD.getDeck().get(k).getValue();//add to the current score
            if(userRes>GOAL) {//If the score is over 21, tell the user he lost
                JOptionPane.showMessageDialog(null, "Oh no, you're over 21... You lose :(");
                break;//the game ends
            }//After every drawn card, show the user his cards and ask him what his next move is
            reply = JOptionPane.showConfirmDialog(null, "Your cards are:" + userD.toString()+
                    "\nWould you like to draw another card?");
        }
        if(userD.contains("Ace")) {//if the user drew an Ace, determine its value according to the current score
            if (userRes + ACE_VAL <= GOAL)
                userRes +=  ACE_VAL;
        }

        k=0;
        if (reply == 1) {//If the user decides to finish his turn
            int PCGOAL = 17;//the goal of the PC is set to 17 which will give it the best chances of winning
            while (pcRes < PCGOAL) {//as long as the pc didn't yet reach its goal
                pcD.getDeck().add(shuffledDraw());//continue to add more cards
                pcRes += pcD.getDeck().get(k).getValue();//keep adding to the PC's score
                k++;
                if (pcRes > GOAL) {//If  the PC is over 21, that means the user wins
                    JOptionPane.showMessageDialog(null, "YOU WIN! The computer is over 21");
                    break;//the game is over
                }
            }
            if (pcRes <= GOAL) {//if the PC is not over 21, it finished its turn and now it's time to announce the winner
                JOptionPane.showMessageDialog(null, "Alright, the game is over and the " +
                                              "winner is...\n\t\t(Click ok to find out)");
                if (userRes > pcRes) {//If the user's score is higher, show the cards and tell the user he won
                    JOptionPane.showMessageDialog(null, "YOU WIN!!! \n Your cards are:"+ userD.toString());
                    JOptionPane.showMessageDialog(null, "The PC cards are:" + pcD.toString() +"\n"+
                                                "(Your score is: " + userRes +" while your rival's score is: " + pcRes + ")");
                }
                else if (userRes < pcRes)//If the user's score is lower, show the cards and tell the user he lost
                    JOptionPane.showMessageDialog(null, "The computer :( Oh no... You lost :( \n" +
                                                "Your cards are:" + userD.toString()+"\nThe PC cards are:"+ pcD.toString() +
                                                "\n(Your score is: " + userRes + " while your rival's score is: "+pcRes+")");
                else//if the scores are equal, show the cards and announce a draw
                    JOptionPane.showMessageDialog(null, "It's a draw!!! Your cards are:" +userD.toString()+
                                                "\nThe PC cards are:"+pcD.toString()+ userRes + ")");
            }
        }//After the game is over, ask the user if he would like to play again
        reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
        if(reply==0) {//if he says yes, call the executing method again with new decks
            TwentyOneGame t = new TwentyOneGame();
            t.twentyOne();
        }
        else//if he says no, say goodbye and the method would terminate
            JOptionPane.showMessageDialog(null, "Ok. Bye!");
    }
}
