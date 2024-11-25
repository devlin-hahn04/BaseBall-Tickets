import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class Menu {

    public void WaitList(Queue<Client> FieldWaitList, Queue<Client> MainWaitList, Queue<Client> GrandWaitList, Scanner scanner, String SelectedLevel){

        System.out.println("   Ticket Waitlist");
        System.out.println("Enter client information to add to waitlist");    
        
        System.out.println("Client name: ");
        String Name= scanner.nextLine();

        System.out.println("Client email: ");
        String Email= scanner.nextLine();

        System.out.println("Client phone number: ");
        String PhoneNumber= scanner.nextLine();

        System.out.println("Number of seats desired: ");
        int SeatsCnt= scanner.nextInt();

        if(SelectedLevel.equals("Field Level")){

            FieldWaitList.add(new Client(Name, Email, PhoneNumber, SeatsCnt));
            System.out.println("field wait");

        }

        if(SelectedLevel.equals("Main Level")){

            MainWaitList.add(new Client(Name, Email, PhoneNumber, SeatsCnt));
            System.out.println("Main wait");


        }
        
        if(SelectedLevel.equals("GrandStand Level")){

            GrandWaitList.add(new Client(Name, Email, PhoneNumber, SeatsCnt));
            System.out.println("Grand wait");


        }


        System.out.println("\nThank you, once a reservation is cancelled, you will have your tickets\n");


    }

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




    
    public static void main(String[] args){

        Scanner scanner= new Scanner(System.in);

        Stadium stadium= new Stadium();
        Menu menu= new Menu();
        stadium.LoadFieldSeats();
        stadium.LoadMainSeats();
        stadium.LoadGrandStandSeats();

        ArrayList<Seats> ResrevedSeats= new ArrayList<>();
        HashMap<String, ArrayList<Seats>> ClientReservationList= new HashMap<>();
        Queue<Client> FieldWaitList= new LinkedList<>();
        Queue<Client> MainWaitList= new LinkedList<>();
        Queue<Client> GrandWaitList= new LinkedList<>();


        System.out.println("                    Welcome To TicketOrder!\n");

        int input= -1;

        menuloop : while(input != 4){

            System.out.println("Select Choice:");
            System.out.println("   1)Order Tickets");
            System.out.println("   2)Cancel Reservation");
            System.out.println("   3)Show Available Tickets");
            System.out.println("   4)Exit");
            
            boolean ValidInput= false;
    
            while(!ValidInput){
    
                try{
                    
                    input= scanner.nextInt();
    
                    if(input < 1 || input > 4){
    
                        throw new IllegalArgumentException("PLease Select between 1-4");
    
                    }
    
                    ValidInput= true; //exits loop if previous condition is false
                    
                } catch(java.util.InputMismatchException e){
                   
                    System.out.println("Please enter a number and from 1-4");
                    scanner.nextLine();
    
                }
    
    
            }

            System.out.println();
            

            //Ordering Tickets Menu
            if(input == 1){

                System.out.println("Choose Level:");
                System.out.println("   1)Field Level: $300, Available Seats: "+stadium.getFieldLevelSeatCount());
                System.out.println("   2)Main Level: $120, Available Seats: "+stadium.getMainLevelSeatCount());
                System.out.println("   3)GrandStand Level: $45, Available Seats: "+stadium.getGrandStandLevelSeatCount());
                
                boolean ValidInputLevel= false;
                int levelInput= -1;

                while(!ValidInputLevel){
    
                    try{
                        
                        levelInput= scanner.nextInt();
        
                        if(levelInput < 1 || levelInput > 3){
        
                            throw new IllegalArgumentException("Please Select between 1-3");
        
                        }
        
                        ValidInputLevel= true; //exits loop if previous condition is false
                        
                    } catch(java.util.InputMismatchException e){
                       
                        System.out.println("Please enter a number and from 1-3");
                        scanner.nextLine();
        
                    }
        
        
                }
                
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

                                menu.WaitList(FieldWaitList, MainWaitList, GrandWaitList, scanner, SelectedLevel);
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
       
                                if(SeatsCnt < 1 || SeatsCnt > stadium.getFieldLevelSeatCount()){
       
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

                                menu.WaitList(FieldWaitList, MainWaitList, GrandWaitList, scanner, SelectedLevel);
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
    
                                if(SeatsCnt < 1 || SeatsCnt > stadium.getMainLevelSeatCount()){
    
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

                                menu.WaitList(FieldWaitList, MainWaitList, GrandWaitList, scanner, SelectedLevel);
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

                                if(SeatsCnt < 1 || SeatsCnt > stadium.getGrandStandLevelSeatCount()){

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

                Client client= new Client(Name, Email, PhoneNumber, SeatsCnt);

                System.out.println("Seats resevred by client: "+ client.getClientSeatCnt());

                //Adding current transaction to hashmap to match clients to seats reserved
                ClientReservationList.put(client.getClientName(), new ArrayList<>(ResrevedSeats));

                stadium.Reservations.clear(); //clearing to add updated map of clients-reservations
                //Adding current transaction to Stadium reservation linkedlist
                stadium.Reservations.add(ClientReservationList);

                System.out.println("\nSeat reservations map: "+ClientReservationList); //Shows hashmap for testing
                System.out.println("\nStadium reservation linkedlist: "+stadium.Reservations); //Shows linkedlist for testing

                ResrevedSeats.clear(); //Clears arraylist to make way for next reservations
                

                //displaying leftover seats for testing
                System.out.println("\nSeats left in Field Level: "+stadium.getFieldLevelSeatCount());
                System.out.println("Seats left in Main Level: "+stadium.getMainLevelSeatCount());
                System.out.println("Seats left in GrandStand Level: "+stadium.getGrandStandLevelSeatCount()+"\n");


            }

            //Cancel Reservation Menu






            //Showing Available Tickets Menu
            

        }


        
        
        

    }





}
