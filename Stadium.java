import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Iterator;

/*===== STADIUM class explanation =====
 * 
 * The Stadium class is a complex class which uses the classes
 * Seat and Client in order to reserve the seats in this Stadium.
 * This class also allows waiting for Seats in its waitlist as well as
 * cancelations of reservations done by Clients. This Stadium offers
 * three type of Seats:
 *    > Field Level: (Cost - $300, Max Capacity - 500)
 *    > Main Level: (Cost - $120, Max Capacity - 1000)
 *    > Grandstand Level: (Cost - $45, Max Capacity - 2000)
 * 
   =================================*/

public class Stadium{

    /*=== Stadium properties ===*/
    
    /*
     * Sets have been used here since they are no
     * Seat duplicates. These store the available Seats
     * in each field. 
    */
    Set<Seats> FieldLevelSeats= new HashSet<>();
    Set<Seats> MainLevelSeats= new HashSet<>();
    Set<Seats> GrandStandLevelSeats= new HashSet<>();

    /*
     * Queques have been used for efficient lookups
     * for the first person to have entered the waitlist.
     * The waitlists are used to keep the people waiting
     * there while the seats become available
     */
    Queue<Client> FieldWaitList= new LinkedList<>();
    Queue<Client> MainWaitList= new LinkedList<>();
    Queue<Client> GrandWaitList= new LinkedList<>();

    /*
     * Stacks have been used here for efficient lookups
     * for the last person to have reserved in the Stadium.
     * These are used to access the information of the
     * reservations to be cancelled more efficiently
     */
    Stack<HashMap<String, ArrayList<Seats>>> FieldHistory= new Stack<>();
    Stack<HashMap<String, ArrayList<Seats>>> MainHistory= new Stack<>();
    Stack<HashMap<String, ArrayList<Seats>>> GrandStandHistory= new Stack<>();

    /*
     * LinkedLists have been used here to store the
     * reservations and to easily eliminate them in a
     * faster pace than ArrayLists. This value stores
     * the information of Clients and the Seats they have
     * reserved. Its values are stored in HashMaps as follows:
     *    > Key = Client's name
     *    > Value = List of their Seats
     *      - ArrayList used for easier random access
     */
    LinkedList<HashMap<String, ArrayList<Seats>>> Reservations= new LinkedList<>();

    /*
     * HashMaps have been used here to pair the Client
     * with the Seats they have reserved. Its values are
     * stored in it as follows:
     *    > Key = Client's name
     *    > Value = List of their Seats
     */
    HashMap<String, ArrayList<Seats>> ClientReservationList= new HashMap<>();


    private int TotalSeats;
    private int FieldLevelCnt;
    private int MainLevelCnt;
    private int GrandStandLevelCnt;

    private int FieldMaxCapacity= 500;
    private int MainMaxCapacity= 1000;
    private int GrandMaxCapacity= 2000;
    /*=======================*/
    
    /*=== Getters ===*/
    public int getTotalSeats(){
        return TotalSeats;
    }
    
    public int getFieldLevelSeatCount() {
        return FieldLevelCnt;
    }

    public int getMainLevelSeatCount() {
        return MainLevelCnt;
    }

    public int getGrandStandLevelSeatCount() {
        return GrandStandLevelCnt;
    }

    public int getFieldMaxCapacity() {
        return FieldMaxCapacity;
    }

    public int getMainMaxCapacity() {
        return MainMaxCapacity;
    }

    public int getGrandMaxCapacity() {
        return GrandMaxCapacity;
    }
    /*==============*/



    /*=== Setters ===*/
    public void setTotalSeats(int TotalSeats){
        
        this.TotalSeats= TotalSeats;
        
    }

    public void setFieldLevelCnt(int FieldLevelCnt){

        this.FieldLevelCnt= FieldLevelCnt;

    }

    public void setMainLevelCnt(int MainLevelCnt){

        this.MainLevelCnt= MainLevelCnt;

    }

    public void setGrandStandLevelCnt(int GrandStandLevelCnt){

        this.GrandStandLevelCnt= GrandStandLevelCnt;

    }
    /*==============*/

    /*=== Methods ===*/

    public void LoadFieldSeats(){ //Prepares the FieldLevelSeats for usage

        int RowCnt= FieldMaxCapacity;

        for(int i= 1; i <= FieldMaxCapacity; i++){

            FieldLevelSeats.add(new Seats("F", String.valueOf(RowCnt), String.valueOf(i)));
            RowCnt--;

        }

        setFieldLevelCnt(FieldLevelSeats.size());

    }

    public void LoadMainSeats(){ //Prepares the MainLevelSeats for usage

        int RowCnt= MainMaxCapacity;

        for(int i= 1; i <= MainMaxCapacity; i++){

            MainLevelSeats.add(new Seats("M", String.valueOf(RowCnt), String.valueOf(i)));
            RowCnt--;

        }

        setMainLevelCnt(MainLevelSeats.size());

    }

    public void LoadGrandStandSeats(){ //Prepares the GrandStandSeats for usage

        int RowCnt= GrandMaxCapacity;

        for(int i= 1; i <= GrandMaxCapacity; i++){

            GrandStandLevelSeats.add(new Seats("G", String.valueOf(RowCnt), String.valueOf(i)));
            RowCnt--;

        }

        setGrandStandLevelCnt(GrandStandLevelSeats.size());

    }

    /*=== WaitList method ===
     * @param scanner, level, max capacity
     * When a Client is sent to the WaitList, it asks for its information
     * in order to add them. If there is n Clients in that level's waitlist
     * before this Client, they will be the (n + 1)th person to be offered
     * a seat after a Seat reservation in that level is cancelled.
      ====================*/
    public void WaitList(Scanner scanner, String SelectedLevel, int MaxCapacity){

        System.out.println("   Ticket Waitlist");

        //Asks Client's information and seats desired to add them to the waitlist
        System.out.println("Enter client information to add to waitlist");    
        
        System.out.println("Client name: ");
        String Name= scanner.nextLine();

        System.out.println("Client email: ");
        String Email= scanner.nextLine();

        System.out.println("Client phone number: ");
        String PhoneNumber= scanner.nextLine();

        System.out.println("Number of seats desired: ");
        int SeatsCnt=0;

        while(true){ //This while loop will ask the seats desired until it's given valid data

            if(scanner.hasNextInt()){

                SeatsCnt= scanner.nextInt();

                if(SeatsCnt >= 1 && SeatsCnt <= MaxCapacity){

                    break;

                }

                else{

                    System.out.println("Invalid amount of seats wanted, try again");

                }

            }

            else{

                System.out.println("Invalid data, try again");
                scanner.next();

            }

        }

        //After selecting the Level, it will add them to that level and give feedback
        if(SelectedLevel.equals("Field Level")){

            FieldWaitList.add(new Client(Name, Email, PhoneNumber, SeatsCnt, SeatsCnt,new ArrayList<>()));
            System.out.println("field wait");

        }

        if(SelectedLevel.equals("Main Level")){

            MainWaitList.add(new Client(Name, Email, PhoneNumber, SeatsCnt, SeatsCnt,new ArrayList<>()));
            System.out.println("Main wait");


        }
        
        if(SelectedLevel.equals("GrandStand Level")){

            GrandWaitList.add(new Client(Name, Email, PhoneNumber, SeatsCnt, SeatsCnt,new ArrayList<>()));
            System.out.println("Grand wait");


        }


        System.out.println("\nThank you, once a reservation is cancelled, you will have your tickets\n");


    }

    /*=== Cancel method ===
     * @param reservation history, waitlist, section seats
     * When a Client cancels Seats from a level, it will access the
     * reservation history, remove those reservations, and make the
     * Seats available again. Then, it verifies the waitlist in case a Client
     * is waiting for Seats and offers them. 
       ===================*/
    public void Cancel(Stack<HashMap<String, ArrayList<Seats>>> historyStack, Queue<Client> WaitList, Set<Seats> SectionSeats){

        if(historyStack.isEmpty()){

            System.out.println("No reservations have been made for this section, try another option\n");
            return;

        }

        //Sets the reservations to be cancelled by the latest reservation done
        HashMap<String, ArrayList<Seats>> latestReservation= historyStack.pop();
        Reservations.remove(latestReservation);

        ArrayList<Seats> CancelTickets= new ArrayList<>();

        for(ArrayList<Seats> seatList : latestReservation.values()){

            CancelTickets.addAll(seatList);

        }

        //Verifies if there is Clients in the waitlist. If not, lets all cancelled seats be freed
        if(WaitList.isEmpty()){

            System.out.println("No clients in the waitlist\n");
            SectionSeats.addAll(CancelTickets);
            


        }

            

        
        /*
         * If they are Clients waiting for Seats in that level, it starts offering these Seats
         * to the first Client to have entered the waitlist. Then, the while loop continues until
         * all Clients have been given their wanted Seats, or they are no more Seats to offer
         */
        while(!WaitList.isEmpty() && !CancelTickets.isEmpty()){

            //It gets the Client's Seats and adds all possible Seats at the while loop
            Client CurrentClient= WaitList.peek();
            ArrayList<Seats> AssignedSeats= CurrentClient.getClientSeats();
    
            Iterator<Seats> it = CancelTickets.iterator();

            while(it.hasNext() && AssignedSeats.size() < CurrentClient.getClientOriginalSeatCnt()){

                AssignedSeats.add(it.next());
                it.remove();

            }    

            
            //If all the seats have been successfully given to that Client, it updates the information and finishes
            if(AssignedSeats.size() == CurrentClient.getClientOriginalSeatCnt() ){

                System.out.println("Amount of seats wanted fullfiled ");
    
                //Adding client and seats to pairing hashmap
                HashMap<String, ArrayList<Seats>> newReservation= new HashMap<>();

                // CurrentClient.getClientSeats().addAll(AssignedSeats);
                newReservation.put(CurrentClient.getClientName(), new ArrayList<>(AssignedSeats));
        
                //Adding reservation to reservation linked list
                Reservations.add(new HashMap<>(newReservation));
        
                //Adding latest reservation to section stack
                historyStack.push(new HashMap<>(newReservation));
    
                WaitList.poll();                
    
            }

            else{ //Some seats the Client wanted were not given, so updates the Client's wanted Seats

                System.out.println("Not enough seats to fulfill requirement");

                CurrentClient.setClientSeatCnt(CurrentClient.getClientOriginalSeatCnt()-AssignedSeats.size());

                System.out.println("New amount needed: "+CurrentClient.getClientSeatCnt());

                
                break;
            
            }

        }

        SectionSeats.addAll(CancelTickets);  //Adding extra tickets leftover if seats wanted from queue is fullfilled 
        System.out.println("Updated section: "+SectionSeats);

        //Frees the new Seats to the respective level
        if(SectionSeats == FieldLevelSeats){

            setFieldLevelCnt(SectionSeats.size());

        } 
        
        else if(SectionSeats == MainLevelSeats){

            setMainLevelCnt(SectionSeats.size());
        } 
        
        else if(SectionSeats == GrandStandLevelSeats){

            setGrandStandLevelCnt(SectionSeats.size());

        }
                
        //Prints new Reservation, history, and waitlist information
        System.out.println("Reservations: "+ Reservations);
        System.out.println();
        System.out.println("Stack: "+ historyStack);

        System.out.println("Queue: "+ WaitList+"\n");


    }


}