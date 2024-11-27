import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class Menu {

    public String GetValidChoice(Scanner scanner, String prompt){

        String choice= "";
        boolean ChoiceValid= false;

        while(!ChoiceValid){

            System.out.println(prompt);

            try{

                choice= scanner.nextLine().toUpperCase();

                if(!choice.equals("Y") && !choice.equals("N")){

                    throw new IllegalArgumentException("Choice not valid, choose again");                                        
                    
                }

                ChoiceValid= true;

            } catch (IllegalArgumentException e){
    
                System.out.println(e.getMessage());

            }
            
        }
        
        return choice;

    }
    
    public int GetValidInput(Scanner scanner, int min, int max, String errorPrompt){

        int input= -1;
        boolean ValidInput= false;
        
        while(!ValidInput){
    
            try{
                
                input= scanner.nextInt();
    
                if(input < 1 || input > 4){
    
                    System.out.println("Please Select between 1-4\n");
                    scanner.nextLine();
    
                }
    
                ValidInput= true; //exits loop if previous condition is false
                
            } catch(java.util.InputMismatchException e){
               
                System.out.println("Please enter a number and from 1-4");
                scanner.nextLine();
    
    
            }
    
    
        }

        return input;

    }

    public int GetValidLevelInput(Scanner scanner, int min, int max){

        boolean ValidInputLevel= false;
        int levelInput= -1;

        while(!ValidInputLevel){
    
            try{
                
                levelInput= scanner.nextInt();

                if(levelInput < min || levelInput > max){

                    System.out.println("Please Select between 1-3");
                    scanner.nextInt();

                }

                ValidInputLevel= true; //exits loop if previous condition is false
                
            } catch(java.util.InputMismatchException e){
               
                System.out.println("Please enter a number and from 1-3");
                scanner.nextLine();

            }


        }

        return levelInput;


    }


    
    public static void main(String[] args){

        Scanner scanner= new Scanner(System.in);

        Stadium stadium= new Stadium();
        Menu menu= new Menu();
        stadium.LoadFieldSeats();
        stadium.LoadMainSeats();
        stadium.LoadGrandStandSeats();

        ArrayList<Seats> ResrevedSeats= new ArrayList<>();

        System.out.println("                    Welcome To TicketOrder!\n");

        int input= -1;

        menuloop : while(input != 4){

            System.out.println("Select Choice:");
            System.out.println("   1)Order Tickets");
            System.out.println("   2)Cancel Reservation");
            System.out.println("   3)Show Available Tickets");
            System.out.println("   4)Exit");
            
            input = menu.GetValidInput(scanner, 1, 4, "Please select between 1-4\n");

    

            //Ordering Tickets Menu
            if(input == 1){

                System.out.println("Choose Level:");
                System.out.println("   1)Field Level: $300, Available Seats: "+stadium.getFieldLevelSeatCount());
                System.out.println("   2)Main Level: $120, Available Seats: "+stadium.getMainLevelSeatCount());
                System.out.println("   3)GrandStand Level: $45, Available Seats: "+stadium.getGrandStandLevelSeatCount());
                
                int levelInput = menu.GetValidLevelInput(scanner, 1, 3);
                
                int SeatsCnt= -1;
                String SelectedLevel= "";
                
                switch (levelInput) {
                    

                    case 1:
                        
                        SelectedLevel= "Field Level";
                        boolean FieldValidInput= false;

                        if(stadium.getFieldLevelSeatCount() == 0){

                            System.out.println("\nSorry, tickets in this level are sold out");
                            scanner.nextLine();
                            String choice= menu.GetValidChoice(scanner, "Wish to enter waitlist for this section? (Y or N): ");
                        
                            if(choice.equals("Y")){

                                stadium.WaitList(scanner, SelectedLevel);
                                continue menuloop;

                            }

                            else{

                                System.out.println("No ticket will be reserved for you\n");
                                continue menuloop;
                            }

                        }

                        System.out.println("   Field Level Tickets\n");
                        System.out.println("Enter amount of seats to reserve: ");

                       while(!FieldValidInput){
                           
                           try {
                                
                                SeatsCnt= scanner.nextInt();
       
                                if(SeatsCnt < 1 || SeatsCnt > stadium.getFieldMaxCapacity()){
       
                                    throw new IllegalArgumentException("Amount not valid, choose again");
       
                                }
       
                                FieldValidInput= true;
                                SelectedLevel= "Field Level";
                                int price= 300;

                                int cnt= 0;
                                Iterator<Seats> iterator = stadium.FieldLevelSeats.iterator();

                                while(iterator.hasNext() && cnt < SeatsCnt){

                                    Seats seat= iterator.next();
                                    ResrevedSeats.add(seat);
                                    iterator.remove();
                                    cnt++;

                                }

                                stadium.setFieldLevelCnt(stadium.getFieldLevelSeatCount()-SeatsCnt);
       
                                System.out.println("Chose "+ SeatsCnt+ " seats for Field Level");
                                System.out.println("Total cost: "+SeatsCnt*price);
       
                            } catch (java.util.InputMismatchException e) {
                                
                                System.out.println("Not valid input, choose again");
                                scanner.nextLine();
       
                            } catch (IllegalArgumentException e){

                                System.out.println(e.getMessage());

                            }

                       } 

                        break;


                    case 2:

                        SelectedLevel= "Main Level";
                        boolean MainValidInput = false;

                        if(stadium.getMainLevelSeatCount() == 0){

                            System.out.println("\nSorry, tickets in this level are sold out");
                            scanner.nextLine();
                            String choice = menu.GetValidChoice(scanner, "Wish to enter waitlist for this section? (Y or N): ");
                            
                            if(choice.equals("Y")){

                                stadium.WaitList(scanner, SelectedLevel);
                                continue menuloop;

                            } 
                            
                            else{

                                System.out.println("No ticket will be reserved for you\n");
                                continue menuloop;
                                
                            }
                        }

                        System.out.println("   Main Level Tickets\n");
                        System.out.println("Enter amount of seats to reserve: ");

                        while(!MainValidInput){
                        
                        try {
                                
                                SeatsCnt= scanner.nextInt();
    
                                if(SeatsCnt < 1 || SeatsCnt > stadium.getMainMaxCapacity()){
    
                                    throw new IllegalArgumentException("Amount not valid, choose again");
    
                                }
    
                                MainValidInput= true;
                                SelectedLevel= "Main Level";
                                int price= 120;

                                int cnt= 0;
                                Iterator<Seats> iterator = stadium.MainLevelSeats.iterator();

                                while(iterator.hasNext() && cnt < SeatsCnt){

                                    Seats seat= iterator.next();
                                    ResrevedSeats.add(seat);
                                    iterator.remove();
                                    cnt++;

                                }
       
                                stadium.setMainLevelCnt(stadium.getMainLevelSeatCount()-SeatsCnt);
    
                                System.out.println("Chose "+ SeatsCnt+ " seats for Main Level");
                                System.out.println("Total cost: "+SeatsCnt*price);
    
                            } catch (java.util.InputMismatchException e) {
                                
                                System.out.println("Not valid input, choose again");
                                scanner.nextLine();
    
                            } catch (IllegalArgumentException e){

                                System.out.println(e.getMessage());

                            }

                        } 

                        break;

                    case 3:

                        SelectedLevel= "GrandStand Level";
                        boolean GrandValidInput= false;

                        if(stadium.getGrandStandLevelSeatCount() == 0){

                            System.out.println("\nSorry, tickets in this level are sold out");
                            scanner.nextLine();
                            String choice = menu.GetValidChoice(scanner, "Wish to enter waitlist for this section? (Y or N): ");
                            
                            if(choice.equals("Y")){

                                stadium.WaitList(scanner, SelectedLevel);
                                continue menuloop;

                            } 
                            
                            else{

                                System.out.println("No ticket will be reserved for you\n");
                                continue menuloop;

                            }
                        }

                        System.out.println("   Main Level Tickets\n");
                        System.out.println("Enter amount of seats to reserve: ");

                        while(!GrandValidInput){
                        
                        try {
                                
                                SeatsCnt= scanner.nextInt();

                                if(SeatsCnt < 1 || SeatsCnt > stadium.getGrandMaxCapacity()){

                                    throw new IllegalArgumentException("Amount not valid, choose again");

                                }

                                GrandValidInput= true;
                                SelectedLevel= "GrandStand Level";
                                int price= 45;

                                int cnt= 0;
                                Iterator<Seats> iterator = stadium.GrandStandLevelSeats.iterator();

                                while(iterator.hasNext() && cnt < SeatsCnt){

                                    Seats seat= iterator.next();
                                    ResrevedSeats.add(seat);
                                    iterator.remove();
                                    cnt++;

                                }
       
                                stadium.setGrandStandLevelCnt(stadium.getGrandStandLevelSeatCount()-SeatsCnt);

                                System.out.println("Chose "+ SeatsCnt+ " seats for GrandStand Level");
                                System.out.println("Total cost: "+SeatsCnt*price);

                            } catch (java.util.InputMismatchException e) {
                                
                                System.out.println("Not valid input, choose again");
                                scanner.nextLine();

                            } catch (IllegalArgumentException e){

                                System.out.println(e.getMessage());

                            }

                        } 

                        break;
                
                    default:
                        break;
                }

                System.out.println("   Client Reservation Information: \n");
                
                scanner.nextLine();

                System.out.println("Client name: ");
                String Name= scanner.nextLine();

                System.out.println("Client email: ");
                String Email= scanner.nextLine();

                System.out.println("Client phone number: ");
                String PhoneNumber= scanner.nextLine();

                System.out.println("\n   Reservation Summary: ");
                System.out.println(Name);
                System.out.println(Email);
                System.out.println(PhoneNumber);
                System.out.println("Seats ordered: "+SeatsCnt+ " in "+SelectedLevel);
                System.out.println(ResrevedSeats);

                Client client= new Client(Name, Email, PhoneNumber, SeatsCnt, SeatsCnt,ResrevedSeats);

                System.out.println("Seats resevred by client: "+ client.getClientSeatCnt());

                //Adding current transaction to hashmap to match clients to seats reserved
                stadium.ClientReservationList.put(client.getClientName(), new ArrayList<>(ResrevedSeats));

                //Adding current transaction to Stadium reservation linkedlist
                stadium.Reservations.add(new HashMap<>(stadium.ClientReservationList));

                //Adding current transaction to reservation history stack

                HashMap<String, ArrayList<Seats>>currentReservation= new HashMap<>(stadium.ClientReservationList);

                if(SelectedLevel == "Field Level"){

                    stadium.FieldHistory.push(new HashMap<>(currentReservation));

                }

                if(SelectedLevel == "Main Level"){

                    stadium.MainHistory.push(new HashMap<>(currentReservation));

                }

                if(SelectedLevel == "GrandStand Level"){

                    stadium.GrandStandHistory.push(new HashMap<>(currentReservation));

                }

                System.out.println("\nSeat reservations map: "+stadium.ClientReservationList); //Shows hashmap for testing
                System.out.println("\nStadium reservation linkedlist: "+stadium.Reservations); //Shows linkedlist for testing

                //clearing references after adding stack
                stadium.ClientReservationList.clear(); 
                ResrevedSeats.clear(); 
                

                //displaying leftover seats for testing
                System.out.println("\nSeats left in Field Level: "+stadium.getFieldLevelSeatCount());
                System.out.println("Seats left in Main Level: "+stadium.getMainLevelSeatCount());
                System.out.println("Seats left in GrandStand Level: "+stadium.getGrandStandLevelSeatCount()+"\n");


            }

            //Cancel Reservation Menu
            if(input == 2){

                System.out.println("   Cancel Reservation\n");
                System.out.println("Enter section to remove reservation from");
                System.out.println("   1)Field Level");
                System.out.println("   2)Main Level");
                System.out.println("   3)GrandStand Level");

                input = menu.GetValidLevelInput(scanner, 1, 4);

                if(input == 1){

                    System.out.println("field cancel");

                    stadium.Cancel(stadium.FieldHistory, stadium.FieldWaitList, stadium.FieldLevelSeats);

                    //begin making method in stadium class for cancel
                    //pass on the section that was chosen to decide which stack to access
                    //check if stack is empty 

                }

                if(input  == 2){

                    System.out.println("main cancel");
                    stadium.Cancel(stadium.MainHistory, stadium.MainWaitList, stadium.MainLevelSeats);


                }

                if(input == 3){

                    System.out.println("grand cancel");
                    stadium.Cancel(stadium.GrandStandHistory, stadium.GrandWaitList, stadium.GrandStandLevelSeats);


                }

               






            }
            //Showing Available Tickets Menu
            if(input == 3){
                System.out.println("   Available Tickets: \n");
            
                // Display available seats for Field Level
                System.out.println("Field Level: $" + 300 + ", Available Seats: " + stadium.getFieldLevelSeatCount());
                System.out.println("Seat Numbers: " + stadium.FieldLevelSeats);
            
                // Display available seats for Main Level
                System.out.println("\nMain Level: $" + 120 + ", Available Seats: " + stadium.getMainLevelSeatCount());
                System.out.println("Seat Numbers: " + stadium.MainLevelSeats);
            
                // Display available seats for GrandStand Level
                System.out.println("\nGrandStand Level: $" + 45 + ", Available Seats: " + stadium.getGrandStandLevelSeatCount());
                System.out.println("Seat Numbers: " + stadium.GrandStandLevelSeats);
            
                System.out.println(); // For cleaner formatting
            }





            
            

        }


        
        
        

    }





}
