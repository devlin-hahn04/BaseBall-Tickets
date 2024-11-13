import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class Menu {
    
    public static void main(String[] args){

        Scanner scanner= new Scanner(System.in);

        Stadium stadium= new Stadium();
        stadium.LoadFieldSeats();
        stadium.LoadMainSeats();
        stadium.LoadGrandStandSeats();

        ArrayList<String> ResrevedSeats= new ArrayList<>();
        HashMap<String, ArrayList<String>> ClientReservationList= new HashMap<>();


        System.out.println("                    Welcome To TicketOrder!\n");

        int input= -1;

        while(input != 4){

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
                        
                        boolean FieldValidInput= false;

                        if(stadium.getFieldLevelSeatCount() == 0){

                            System.out.println("\n Sorry, tickets in this level are sold out, try another level\n");
                            continue;

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

                                int cnt= 0;
                                Iterator<String> iterator = stadium.FieldLevelSeats.iterator();

                                while(iterator.hasNext() && cnt < SeatsCnt){

                                    String seat= iterator.next();
                                    ResrevedSeats.add(seat);
                                    iterator.remove();
                                    cnt++;

                                }

                                stadium.setFieldLevelCnt(stadium.getFieldLevelSeatCount()-SeatsCnt);
       
                                System.out.println("Chose "+ SeatsCnt+ " seats for Field Level");
       
                            } catch (java.util.InputMismatchException e) {
                                
                                System.out.println("Not valid input, choose again");
                                scanner.nextLine();
       
                            } catch (IllegalArgumentException e){

                                System.out.println(e.getMessage());

                            }

                       } 

                        break;


                    case 2:

                        boolean MainValidInput= false;

                        if(stadium.getMainLevelSeatCount() == 0){

                            System.out.println("\n Sorry, tickets in this level are sold out, try another level\n");
                            continue;

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

                                int cnt= 0;
                                Iterator<String> iterator = stadium.MainLevelSeats.iterator();

                                while(iterator.hasNext() && cnt < SeatsCnt){

                                    String seat= iterator.next();
                                    ResrevedSeats.add(seat);
                                    iterator.remove();
                                    cnt++;

                                }
       
                                stadium.setMainLevelCnt(stadium.getMainLevelSeatCount()-SeatsCnt);
    
                                System.out.println("Chose "+ SeatsCnt+ " seats for Main Level");
    
                            } catch (java.util.InputMismatchException e) {
                                
                                System.out.println("Not valid input, choose again");
                                scanner.nextLine();
    
                            } catch (IllegalArgumentException e){

                                System.out.println(e.getMessage());

                            }

                        } 

                        break;

                    case 3:

                        boolean GrandValidInput= false;

                        if(stadium.getMainLevelSeatCount() == 0){

                            System.out.println("\n Sorry, tickets in this level are sold out, try another level\n");
                            continue;

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

                                int cnt= 0;
                                Iterator<String> iterator = stadium.GrandStandLevelSeats.iterator();

                                while(iterator.hasNext() && cnt < SeatsCnt){

                                    String seat= iterator.next();
                                    ResrevedSeats.add(seat);
                                    iterator.remove();
                                    cnt++;

                                }
       
                                stadium.setGrandStandLevelCnt(stadium.getGrandStandLevelSeatCount()-SeatsCnt);

                                System.out.println("Chose "+ SeatsCnt+ " seats for GrandStand Level");

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

                Client client= new Client(Name, Email, PhoneNumber);

                //Adding current transaction to hashmap to match clients to seats reserved
                ClientReservationList.put(client.getClientName(), new ArrayList<>(ResrevedSeats));

                stadium.Reservations.clear(); //clearing to add updated map of clients-reservations
                //Adding current transaction to Stadium reservation linkedlist
                stadium.Reservations.add(ClientReservationList);

                System.out.println("\nSeat reservations: "+ClientReservationList); //Shows hashmap for testing
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
