import java.util.ArrayList;
public class Player extends Pawn {

    //String name = "Yo mama"; //test name
    String name;
    ArrayList<Pawn> pawns = new ArrayList<>();



/*
    //this doesn't work here
    Scanner input = new Scanner(System.in);
    System.out.println("What is your name?");
    String name = input.nextline();


*/
    int pawnsInStart = 4;
    int target = 66;
    int pawnsInHome = 0; //will default to 0

    // needs to be fixed
    String report = String.format("Turn: %s   \nPawn Number: %s   Pawn Name: %s    Status: %s   \nBoard Position: %d   Safe Zone: %s   Home: %s",name, pawn_number, pawn_name, status, board_position, safe_zone, home);

}
