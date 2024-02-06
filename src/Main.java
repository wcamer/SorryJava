//Author: William C.
//Latest update: 2/6/24
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
     //System.out.println("Wellcome to Sorry Java!"); //error
    Player p2 = new Player();
    public static void main(String[] args) {
    /*This section is test code that works
      //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

     */
        System.out.println("Welcome to Sorry Java!\n");
        System.out.println("The object of the game is to get all your pawns to the target number by way of dice rolls (0-12) (card and special effects will be in future updates).");
        System.out.println("The user will be player 1 and 3 Cpu's that will act randomly will follow.  The first to get all pawns to the target number will win.");

        //Constructs Player class from Player.java
        Player play = new Player(); //creates a player object
        //this will be the user
        System.out.println("What is your name?");
        play.name = userInput();


        //create 3 CPUs to play agains
        Player cpu1 = new Player();
        cpu1.name = "Cpu 1";
        Player cpu2 = new Player();
        cpu2.name= "Cpu 2";
        Player cpu3 = new Player();
        cpu3.name = "Cpu 3";

        //give player and CPUs their pawns
        give_player_pawns(play); //works
        give_player_pawns(cpu1);
        give_player_pawns(cpu2);
        give_player_pawns(cpu3);
        //all pawns are created at this point


        //I may not use this part
        System.out.println("Here are your opponents");
        System.out.println(cpu1.name);
        System.out.println(cpu2.name);
        System.out.println(cpu3.name);



        ////This will hold the player and CPUs and then we will index the array to determine who's move it is.
        ArrayList<Player> turnTable = new ArrayList<>();
        turnTable.add(play);
        turnTable.add(cpu1);
        turnTable.add(cpu2);
        turnTable.add(cpu3);
        System.out.println("the turn function is happening..........");

        //turnTable needs to be the first argument and an integer has to 2nd argument (can randomize if future updates)
        turn(turnTable,0);//player,0);


    }//end of main method


    static String userInput() { //this works
        System.out.println("Enter Response");
        Scanner input = new Scanner(System.in);
        String userSaid = input.nextLine();
        return userSaid;

    }//End of userInput() method


    static void give_player_pawns(Player player){

        for (int i = 0; i < 4; i++) {
            if (player.name.equals("Cpu 1")|| player.name.equals("Cpu 2") || player.name.equals("Cpu 3")){
                Pawn p = new Pawn();
                p.pawn_owner = player.name;
                p.pawn_name = player.name + " pawn number:" + (i+1);
                p.pawn_number = i+1;
                p.status = "active"; // might change to boolean instead but for now will default to not active
                p.board_position = 0; //means it's in start area

                player.pawns.add(p);
                System.out.println("here is the name");
                System.out.println(player.pawns.get(i).pawn_name);
            }
            else {
            Pawn p = new Pawn();
            p.pawn_owner = player.name;
            System.out.println(p.pawn_owner + ", what name will you give pawn number..." + (i+1) +" ?");
            p.pawn_name = userInput();
            p.pawn_number = i+1;
            p.status = "active"; // might change to boolean instead
            p.board_position = 0; //means it's in start area

            player.pawns.add(p);
            System.out.println("here is the name for pawn number...." + (i+1));
            System.out.println(player.pawns.get(i).pawn_name);}





        }
        System.out.println(player.name + " now has all their pawns!  And here they are...");
        System.out.println(player.pawns.get(0).pawn_name);
        System.out.println(player.pawns.get(1).pawn_name);
        System.out.println(player.pawns.get(2).pawn_name);
        System.out.println(player.pawns.get(3).pawn_name);

    }// end of give_player_pawn method

    static void turn(ArrayList array, Integer picker){//(Player player, Integer picker ){
        if (picker  > 3){ //this will make sure to keep the index within the range of the array length
            picker = 0;
        }

        //For when the array holding the player objects is the argument with the integer picker
        Player player = (Player) array.get(picker);

        System.out.println(player.name + "'s turn...\n");//test line


        //This is the dice roll between 0 and 12.  Replace with cards
        Random random = new Random();
        int max = 12;
        int number = random.nextInt(max);
        System.out.println(player.name + ", rolls the dice...and rolled a... " + number +"!!!");


        //This will take current player and random number to apply to a selected pawn in this function
        System.out.println();
        move_pawn(player,number);//send in player object and the spaces that the pawn will move

        //win check
        System.out.println("\nNow checking for a winner...\nResults are....");
        if (player.pawnsInHome != 4){
            System.out.println("There isn't a winner.  Moving to the next player");
            //next player function
            turn(array, picker + 1);
        }else{
            System.out.println("\nWinner Winner Chicken Dinner!!!\nCongratulations..."+ player.name + "\nThank you for playing!!!!");

        }




    }////end of turn method


    static void move_pawn(Player player, Integer number){
        int choice;
        ArrayList<Pawn> pawn_list = player.pawns;
        int target = player.target;

        //If the player object isn't a Cpu
        if (!player.name.equals("Cpu 1") && (!player.name.equals("Cpu 2") && (!player.name.equals("Cpu 3")))){ ////Checking to make sure the player's name isn't CPu 1-3
            //need to check if player has any active pawns...

            ArrayList options = new ArrayList(); //dynamic array
            boolean found = false;
            for (int i = 0; i <= pawn_list.size()-1; i++) {
                if (pawn_list.get(i).status.equals("active")) {
                    found = true;
                    options.add(pawn_list.get(i).pawn_name);
                }//else
            }//end of for loop

            if (found == true){// if player has at least 1 active pawn...
                System.out.println(player.name + " has at least 1 pawn that can move"); //test


                System.out.println("Enter the option number for the pawn you want to move.\nHere are your options: ");
                for (int i = 0; i <= options.size()-1; i++){System.out.println("Option " + (i +1) + ": " +options.get(i));};  //dynamically will print available options array
                int chosen = Integer.parseInt(userInput())-1; //this will grab the name of the pawn from options


                //this will convert the "chosen" option (name of the pawn) from option array and convert it to the index needed to pick the pawn
                for (int i = 0; i <= pawn_list.size()-1; i++){
                    if (options.get(chosen).equals(pawn_list.get(i).pawn_name)) {//does the chosen option name match the pawn in the pawn list?
                       // System.out.println("match found " + options.get(chosen) + " == " +pawn_list.get(i).pawn_name +"\n chosen before reassignment: "+ chosen);
                        chosen = i;//this is converted to the index in the pawn list that matches the option name

                        //System.out.println("here is chosen in the for loop after reassignment before caluculations ,,,,,,,"+ chosen);// test
                        break;// breaks out of the this block
                    }
                    //test section
                    /*else{
                        System.out.println("no match " + options.get(chosen) +" != " + pawn_list.get(i).pawn_name);
                    }


                     */
                    /*
                    //test section
                    System.out.println("Here is chosen: "+ chosen +" \nhere is i: " + i + "\n here is pawn name: " +pawn_list.get(i).pawn_name);
                    System.out.println("***************************************************************");

                     */
                }



                //Check if pawn is active and pawn doesn't exceed target
                if (pawn_list.get(chosen).status.equals("active") && pawn_list.get(chosen).board_position <= target) { //Check if pawn is active and pawn doesn't exceed target
                    //System.out.println("The choice of..." + pawn_list.get(chosen).pawn_name + " is active.  The current board position is..." + pawn_list.get(chosen).board_position);
                    pawn_list.get(chosen).board_position += number;
                    //System.out.println("The board position has been increased by..." + number); //test
                    System.out.println("The pawn '" + pawn_list.get(chosen).pawn_name + "' is active and went from board position '" + (pawn_list.get(chosen).board_position - number)+ "' to '" +pawn_list.get(chosen).board_position + "'");// + " is the new board position");

                    //test lines for safe zone check
                    //System.out.println("\n testing safe zone check therefor changing pawn's board position to target minus 6");
                    //pawn_list.get(chosen).board_position = (target - 6);

                    //Test lines for reaching target
                    //pawn_list.get(chosen).board_position = (target);

                    //safe zone check
                    if (pawn_list.get(chosen).board_position >= (target - 6) || (pawn_list.get(chosen).board_position == target)) { // checking if pawn has reached "safe zone" which starts 6 spaces before target
                        System.out.println("\n" + pawn_list.get(chosen).pawn_name + " , is now in the safe zone yet.");
                        pawn_list.get(chosen).safe_zone = true;
                        pawn_list.get(chosen).status = "safe";
                        //System.out.println(pawn_list.get(chosen).safe_zone + "\n" + pawn_list.get(chosen).status); //test
                    }
                    else {
                        System.out.println("\n" + pawn_list.get(chosen).pawn_name + " , has not reached the safe zone yet.");
                    }

                    //Target check
                    if (pawn_list.get(chosen).board_position == target) { // checking if target has reached target exactly
                        System.out.println("\n" + pawn_list.get(chosen).pawn_name + ", Has reached home");
                        pawn_list.get(chosen).home = true;
                        pawn_list.get(chosen).status = "home";
                        player.pawnsInHome += 1;
                        System.out.println(pawn_list.get(chosen).safe_zone + "\n" + pawn_list.get(chosen).status + "\n" + player.name + "\npawns at home " + player.pawnsInHome);

                    }

                }//else{ }



            }
            else{
                System.out.println("player doesn't have an active pawn");}




        } // end of player block

        else{ //this player up is a Cpu///
            ArrayList cpu_choice = new ArrayList<>(); // this will hold the index number of the cpu's active pawns
            System.out.println(player.name+ ", Options");

            //for (int i = 0; i <= options.size()-1; i++){System.out.println("Option " + (i +1) + ": " +options.get(i));}

            for (int i = 0; i < player.pawns.size(); i++){

                //System.out.println(player.pawns.get(i).pawn_name);

                if(pawn_list.get(i).status.equals("active")){ //if the pawn is active then add it to list of choices to make
                    //System.out.println("Cpu pawn..." + player.pawns.get(i).pawn_name +" has been added to cpu choices");
                    //cpu_choice.add(pawn_list.get(i));//store a number
                    cpu_choice.add(i);//stores the index of where the active choices are
                }
                /*else{
                    //testing lines
                    System.out.println(pawn_list.get(i).status);
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
                }
                 */
            }

            //if there are no choices then need to move on
            if (cpu_choice.size() == 0){
                System.out.println(player.name + ", cannot make any moves...");
                //needs to end here



            }else{ //means that player or cpu has at least 1 active pawn
                for (int i = 0; i <= cpu_choice.size()-1; i++){System.out.println("Option " + (i +1) + ": " +pawn_list.get(i).pawn_name);}
                Random random = new Random();
                int top = cpu_choice.size() - 1;
                int pick = random.nextInt(top);
                //System.out.println("Printing top....." + top +" and pick........."+ pick ); // test
                //System.out.println("Printing cpu choice that was randomly selected out of choices\n" + cpu_choice.get(pick));//test

                choice = (int) cpu_choice.get(pick); // testing

                //can now proceed to move the randomly selected pawn
                if (pawn_list.get(choice).status.equals("active") && pawn_list.get(choice).board_position <= target){
                    System.out.println("The choice of..." + pawn_list.get(choice).pawn_name + " is active.  The current board position is..." + pawn_list.get(choice).board_position);
                    pawn_list.get(choice).board_position += number;
                    System.out.println("The board position has been increased by..." + number);
                    System.out.println(pawn_list.get(choice).board_position + " is the new board position");

                    //test for safe zone
                    //pawn_list.get(choice).board_position = (target - 6);//works
                    //test for reaching target and going home
                    //pawn_list.get(choice).board_position = target; //works

                    //Cpu safe zone check
                    if(pawn_list.get(choice).board_position >= (target - 6) || (pawn_list.get(choice).board_position == target)){ // checking if pawn has reached "safe zone" which starts 6 spaces before target
                        System.out.println("\n"+pawn_list.get(choice).pawn_name + " , is now in the safe zone.");
                        pawn_list.get(choice).status = "safe";
                        pawn_list.get(choice).safe_zone = true;
                        System.out.println(pawn_list.get(choice).safe_zone + "\n" + pawn_list.get(choice).status);


                    }else{
                        System.out.println("\n"+ pawn_list.get(choice).pawn_name + " , has not reached the safe zone yet.");
                    }

                    //Cpu target check
                    if(pawn_list.get(choice).board_position == target){ // checking if target has reached target exactly
                        System.out.println("\n" + pawn_list.get(choice).pawn_name + " Has reached home");
                        pawn_list.get(choice).home = true;
                        pawn_list.get(choice).status = "home";
                        player.pawnsInHome += 1;
                        System.out.println(pawn_list.get(choice).home + "\n" + pawn_list.get(choice).status + "\n" + player.name + "\npawns at home " + player.pawnsInHome);


                    }




                }



        }//end of cpu has at least 1 active pawn




        }//end of cpu block

        //show end of active player's action report
        report(player);
        //System.out.println("End of move_pawn method....................");
        System.out.println();
    }// end of move_pawn method

    //This will show an overview of the player's or Cpu's status
    static void report(Player player){
        String name = player.name;
        int pawnsInStart = player.pawnsInStart;
        int pawnsInHome = player.pawnsInHome;
        int target = player.target;
        System.out.println("-------------------------Player Report----------------------------------");
        System.out.println("Player name: " + name + "\nPawns In Start: " + pawnsInStart+ "\nPawns In Home: "+pawnsInHome+ "\nTarget: "+ target);
        for (int i = 0; i < player.pawns.size(); i++){
            String pawnName = player.pawns.get(i).pawn_name;
            String status = player.pawns.get(i).status;
            int board_position = player.pawns.get(i).board_position;
            System.out.println("Pawn name: "+ pawnName +"    Status: " + status + "   Board Position: "+ board_position);
        }
    }// end of report method



}////end of main public class

