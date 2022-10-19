import java.util.ArrayList;
import java.util.Scanner;

public class blackjack1{

    public static void main(String[] args){
        
        ArrayList<Integer> deck = new ArrayList<>(); // creates representation of deck of cards in the form of an ArrayList
        for(int i = 2; i < 15; i++){
            for(int j = 0; j < 4; j++){
                deck.add(i);
            }
        }
        int size = deck.size();
        System.out.println("Welcome to BlackJack, type '1' to play"); // Press 1 to start the game
        Scanner scanner = new Scanner(System.in);
        String gamemode = scanner.nextLine();
        System.out.println();

        if(gamemode.equals("1")){
            ArrayList<Integer> userhand = new ArrayList<>(); // represents the cards that the user receives
            ArrayList<Integer> dealerhand = new ArrayList<>(); // represents the cards that the house receives

            int firstcard = shuffler(deck); // takes a random card out of the deck using method public static int shuffler(ArrayList<Integer> deck)
            userhand.add(firstcard);            // puts the first card in the users hand
            removecard(deck, firstcard);        // takes this card out of the deck
            int facedown = shuffler(deck);      //  repeats this process until all cards are dealt
            dealerhand.add(facedown);           //
            removecard(deck, facedown);         //
            int secondcard = shuffler(deck);    //
            userhand.add(secondcard);           //
            removecard(deck, secondcard);       //  
            int faceup = shuffler(deck);        //
            dealerhand.add(faceup);             //
            removecard(deck, faceup);           //

            System.out.println("Dealer"); // shows the user the dealers hand and their hand
            System.out.println("*"); // player is not supposed to see the dealers first card so it blurs it out
            if(faceup == 11){     // because arraylists cant contain numbers and letters they are represented by 11 12 and 13 and have to be put in manually when displaying the letter j q or k
                System.out.println("J");
            }
            else if(faceup == 12){
                System.out.println("Q");
            }
            else if(faceup == 13){
                System.out.println("K");
            }
            else if(faceup == 14){
                System.out.println("A"); // same with jack queen and king
            }
            else{
            System.out.println(dealerhand.get(1));
            }
            System.out.println();
            System.out.println("Player"); 
            if(firstcard == 11){            //same thing as before but now for the user
                System.out.println("J");
            }
            else if(firstcard == 12){
                System.out.println("Q");
            }
            else if(firstcard == 13){
                System.out.println("K");
            }
            else if(firstcard == 14){
                System.out.println("A");
            }
            else{
            System.out.println(firstcard);
            }
            
            if(secondcard == 11){
                System.out.println("J");
            }
            else if(secondcard == 12){
                System.out.println("Q");
            }
            else if(secondcard == 13){
                System.out.println("K");
            }
            else if(secondcard == 14){
                System.out.println("A");
            }
            else{
            System.out.println(secondcard);
            }
            
            int playertotal = facecardvalue(firstcard) + facecardvalue(secondcard);
            System.out.println("Would you like to hit? Type 'Yes' or 'No'");        //this is where the game starts and the user is prompted to "hit" or "stay"
            String hit = scanner.nextLine();

            ArrayList<Integer> acecheck = new ArrayList<>();
                    for(int i = 0; i < userhand.size(); i++){       // Because the ace card can be either 11 of 1, This creates a new array that is a copy of the user's hand
                        acecheck.add(userhand.get(i));
                    }
            
            while(!hit.equals("No")){           // If the player doesnt type "No" they have decided to ask for another card
                int hitcard = shuffler(deck);            // takes random card out of deck
                userhand.add(hitcard);                   // adds said card to the users deck
                acecheck.add(hitcard);                   // Adds card to the acecheck arraylist
                removecard(deck, hitcard);               // removes the card given to the player from the deck
                playertotal += facecardvalue(hitcard);   // adds the card value to the players total card value count

                
                if(playertotal > 21){
                    for(int i = 0; i < acecheck.size(); i++){   
                        if(acecheck.get(i) == 14){              // If the player is over the score of 21, we need to check if he has any aces
                            acecheck.remove(i);                 // If they do, remove this ace from the acecheck deck so that ace cant take off 10 the playertotal again
                            playertotal -= 10;                  // Takes 10 off the player score to represent the aces value going from 11 to 1
                            break;
                        }
                    }
                    
                    if(playertotal > 21){                       
                        playertotal = 0;                        // If the player goes over 21 and has no Aces, his hand "pushes" and his score is 0
                        hit = "No";                             // This makes it so when the code loops and checks if hit == "No" the loop ends
                    }
                }
                if(playertotal < 21 && hit != "No"){            // If the player's score is playable after they hit, this asks if they want to hit again or stay 
                    System.out.println("Your hand");
                    for(int i = 0; i < userhand.size(); i++){
                        System.out.println(valuetocard(userhand.get(i)));
                    }
                    System.out.println(" Would you like to hit? type 'No' to stay at " + playertotal); // This displays the user's score to help them decide
                    hit = scanner.nextLine();                   // checks if they decided to "hit" or not
                }
                else{
                    System.out.println("your total is 21!!!");     // If the player's total is not greater or less than 21 that means it is 21
                    hit = "No"; 
                }
            }

            //This is the start of revealing the dealers hand
            int dealertotal = facecardvalue(facedown) + facecardvalue(faceup);     //This counts up the score of the 2 original cards of the dealers deck
            System.out.println("Dealers Hand");
            System.out.println();
            for(int i = 0; i < dealerhand.size(); i++){ // This loop prints the dealers deck
                System.out.println(valuetocard(dealerhand.get(i)));
            }
            
            System.out.println();
            
            while(dealertotal < 16){                        //Dealer has to stop receiving more cards once his total score is >= 16
                int dealernext = shuffler(deck);            // takes card out of deck
                dealerhand.add(dealernext);                 // adds card to dealer hand
                removecard(deck, dealernext);               // removes card from deck
                dealertotal += facecardvalue(dealernext);   // adds the cards value to dealer's total score
                boolean acexception = false;                // sets up boolean to see if an ace is added
                if(dealertotal > 21){
                    if(dealernext == 14){
                        acexception = true;                 // makes boolean true if their is an ace
                        }
                    if(acexception == true){
                        dealertotal -= 10;                  // if their is an ace and the score is over 21 turn the ace value from 11 to 1
                        }
                }
            }
            if(dealertotal > 21){
                dealertotal = 0;                            // if there is no ace and the score is over 21 set dealers score to 0
            }

            System.out.println("Dealers Hand");
            for(int i = 0; i < dealerhand.size(); i++){             //prints dealers hand
                System.out.println(valuetocard(dealerhand.get(i)));
            }
            System.out.println(); 
            System.out.println("Your Hand");                     //prints your hand
            for(int i = 0; i < userhand.size(); i++){
                System.out.println(valuetocard(userhand.get(i)));
            }
            System.out.println();

            if(playertotal > dealertotal){                 // If the user won print you win
                System.out.println("You Win!");
            }
            else if(playertotal == dealertotal){
                System.out.println("It's a tie.");      // If they tied print tie
            }
            else{
                System.out.println("You lose :(");      // If the user lost print you lose
            }

        }
        } 
    

    public static int shuffler(ArrayList<Integer> deck){    //Takes random "card" out of the "deck" using math.random to pick a number out of the given deck size
        int size = deck.size();
        int card = deck.get((int)(size * Math.random()));   
        return card;
    }

    public static ArrayList<Integer> removecard(ArrayList<Integer> originaldeck, int card){ // removes a given card out the deck
        for(int i = 0; i < originaldeck.size(); i++){
            if(originaldeck.get(i) == card){
                originaldeck.remove(i);
                return originaldeck;
            }
        }
        return originaldeck;
    }
    
    public static int facecardvalue(int n){     // determines values of facecards
        if(n == 11 || n == 12 || n == 13){      // 11 12 and 13 represent jack queen and king respectively
            return 10;                          // jack queen and king all have a score of 10
        }
        else if(n == 14){                       // 14 represents Ace
            return 11;                          // Score of an ace is 11 or 1, but one is to only be determined in certain situations so it is set to 11 originally
        }
        else{
            return n;                           // If it is not a face card, just return the card value
        }
        
    }

    public static String valuetocard(int n){    // Given a face card integer, this returns the first letter of the card for user interpretation
        if(n == 11){                        
            return "J";
        }
        else if(n == 12){
            return "Q";
        }
        else if(n == 13){
            return "K";
        }
        else if(n == 14){
            return "A";
        }
        else{
            String str = Integer.toString(n);
            return str;
        }
    }
}