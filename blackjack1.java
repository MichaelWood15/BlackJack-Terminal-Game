import java.util.ArrayList;
import java.util.Scanner;

public class blackjack1{

    public static void main(String[] args){
        
        ArrayList<Integer> deck = new ArrayList<>();
        for(int i = 2; i < 15; i++){
            for(int j = 0; j < 4; j++){
                deck.add(i);
            }
        }
        int size = deck.size();
        System.out.println("Welcome to BlackJack, type '1' to play");
        Scanner scanner = new Scanner(System.in);
        String gamemode = scanner.nextLine();
        System.out.println();

        if(gamemode.equals("1")){
            ArrayList<Integer> userhand = new ArrayList<>();
            ArrayList<Integer> dealerhand = new ArrayList<>();

            int firstcard = shuffler(deck);
            userhand.add(firstcard);
            removecard(deck, firstcard);
            int facedown = shuffler(deck);
            dealerhand.add(facedown);
            removecard(deck, facedown);
            int secondcard = shuffler(deck);
            userhand.add(secondcard);
            removecard(deck, secondcard);
            int faceup = shuffler(deck);
            dealerhand.add(faceup);
            removecard(deck, faceup);

            System.out.println("Dealer");
            System.out.println("*");
            if(faceup == 11){
                System.out.println("J");
            }
            else if(faceup == 12){
                System.out.println("Q");
            }
            else if(faceup == 13){
                System.out.println("K");
            }
            else if(faceup == 14){
                System.out.println("A");
            }
            else{
            System.out.println(dealerhand.get(1));
            }
            System.out.println();
            System.out.println("Player");
            if(firstcard == 11){
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
            System.out.println("Would you like to hit? Type 'Yes' or 'No'");
            String hit = scanner.nextLine();

            ArrayList<Integer> acecheck = new ArrayList<>();
                    for(int i = 0; i < userhand.size(); i++){
                        acecheck.add(userhand.get(i));
                    }
            
            while(!hit.equals("No")){
                int hitcard = shuffler(deck);
                userhand.add(hitcard);
                acecheck.add(hitcard);
                removecard(deck, hitcard);
                playertotal += facecardvalue(hitcard);

                
                if(playertotal > 21){
                    for(int i = 0; i < acecheck.size(); i++){
                        if(acecheck.get(i) == 14){
                            acecheck.remove(i);
                            playertotal -= 10;
                            break;
                        }
                    }
                    
                    if(playertotal > 21){
                        playertotal = 0;
                        hit = "No";
                    }
                }
                if(playertotal < 21 && hit != "No"){
                    System.out.println("Your hand");
                    for(int i = 0; i < userhand.size(); i++){
                        System.out.println(valuetocard(userhand.get(i)));
                    }
                    System.out.println(" Would you like to hit? type 'No' to stay at " + playertotal);
                    hit = scanner.nextLine();
                }
                else{
                    System.out.println("your total is 21!!!");
                    hit = "No";
                }
            }

            int dealertotal = facecardvalue(facedown) + facecardvalue(faceup);
            System.out.println("Dealers Hand");
            System.out.println();
            for(int i = 0; i < dealerhand.size(); i++){
                System.out.println(valuetocard(dealerhand.get(i)));
            }
            System.out.println();
            
            while(dealertotal < 16){
                int dealernext = shuffler(deck);
                dealerhand.add(dealernext);
                removecard(deck, dealernext);
                dealertotal += facecardvalue(dealernext);
                boolean acexception = false;
                if(dealertotal > 21){
                    if(dealernext == 14){
                        acexception = true;
                        }
                    if(acexception == true){
                        dealertotal -= 10;
                        }
                }
            }
            if(dealertotal > 21){
                dealertotal = 0;
            }

            System.out.println("Dealers Hand");
            for(int i = 0; i < dealerhand.size(); i++){
                System.out.println(valuetocard(dealerhand.get(i)));
            }
            System.out.println(); 
            System.out.println("Your Hand");
            for(int i = 0; i < userhand.size(); i++){
                System.out.println(valuetocard(userhand.get(i)));
            }
            System.out.println();

            if(playertotal > dealertotal){
                System.out.println("You Win!");
            }
            else if(playertotal == dealertotal){
                System.out.println("It's a tie.");
            }
            else{
                System.out.println("You lose :(");
            }

        }
        } 
    

    public static int shuffler(ArrayList<Integer> deck){
        int size = deck.size();
        int card = deck.get((int)(size * Math.random()));
        return card;
    }

    public static ArrayList<Integer> removecard(ArrayList<Integer> ogdeck, int card){
        for(int i = 0; i < ogdeck.size(); i++){
            if(ogdeck.get(i) == card){
                ogdeck.remove(i);
                return ogdeck;
            }
        }
        return ogdeck;
    }
    
    public static int facecardvalue(int n){
        if(n == 11 || n == 12 || n == 13){
            return 10;
        }
        else if(n == 14){
            return 11;
        }
        else{
            return n;
        }
        
    }

    public static String valuetocard(int n){
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