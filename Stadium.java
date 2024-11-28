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
 * The Stadium class is designed to manage seat reservations, cancellations, and a waitlist system for a stadium with three levels of seating. 
 * It provides functionality to load seats into different levels, manage client reservations, handle cancellations, and waitlist clients when no seats are available.
 * The class uses various data structures to efficiently store and manage the seats, reservations, cancellations, and waitlists:
 * 
 *    -Set<Seats> - Used for storing seats in each of the three levels (Field, Main, and Grandstand). A Set is chosen because it prevents duplicate entries, ensuring that each seat is unique.
 *    -Queue<Client> - Used to store clients waiting for a seat in the respective levels. A Queue is used because it processes clients in a first-come, first-served manner, which is ideal for a waitlist system.
 *    -Stack<HashMap<String, ArrayList<Seats>>> - Used for managing reservation history. Stacks allow for easy access to the last reservation made, which is needed for cancellation purposes.
 *    -LinkedList<HashMap<String, ArrayList<Seats>>> - Used to store current reservations, where each entry maps a client’s name to the list of seats they reserved. A LinkedList is chosen because it allows efficient removal and addition of elements, particularly when reservations need to be cancelled or modified.
 *    -HashMap<String, ArrayList<Seats>> - This is used to pair a client with their reserved seats, ensuring that each client’s reservation is easy to access and modify.
 * 
 * The stadium manages three types of seats, each with its own capacity and pricing:
 *    -Field Level: Cost - $300, Max Capacity - 500
 *    -Main Level: Cost - $120, Max Capacity - 1000
 *    -Grandstand Level: Cost - $45, Max Capacity - 2000
 * 
 * The class provides methods for adding clients to the waitlist, canceling reservations, and offering available seats to waiting clients.
 * 
 * =================================
 */
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

/*===== WaitList Method =====
 * 
 * The WaitList method adds a client to the waitlist for a specific seating level. 
 * It prompts the user for the client’s information, including their name, email, phone number, 
 * and the number of seats desired. It then checks if the requested number of seats is valid 
 * (within the range of available seats for the selected level). If valid, the client is added to 
 * the appropriate waitlist based on the selected seating level (Field Level, Main Level, or GrandStand Level).
 * 
 * Purpose:
 * - To gather client information and add them to the waitlist for a specific seating level.
 * - Ensures clients are added to the correct waitlist based on their desired seating level.
 * - Validates input to ensure that the number of seats requested is within the maximum capacity.
 * 
 * Parameters:
 * - scanner: A Scanner object used for reading user input from the console.
 * - SelectedLevel: A string representing the seating level (Field Level, Main Level, or GrandStand Level) 
 *   where the client wishes to be added to the waitlist.
 * - MaxCapacity: An integer representing the maximum number of seats available for the selected seating level.
 * 
 * Return Value:
 * - void: This method does not return a value. It modifies the waitlists by adding clients to the 
 *   respective waitlist based on the selected seating level.
 * 
 * Calls:
 * - None: This method directly adds the client to one of the predefined waitlists based on the selected level.
 * 
 * Called By:
 * - The Menu system or a user interface: The `WaitList` method is called when a user opts to join the waitlist 
 *   for a specific seating level in the ticket reservation system.
 */

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

        }

        if(SelectedLevel.equals("Main Level")){

            MainWaitList.add(new Client(Name, Email, PhoneNumber, SeatsCnt, SeatsCnt,new ArrayList<>()));
            
        }
        
        if(SelectedLevel.equals("GrandStand Level")){

            GrandWaitList.add(new Client(Name, Email, PhoneNumber, SeatsCnt, SeatsCnt,new ArrayList<>()));
            
        }


        System.out.println("\nThank you, once a reservation is cancelled, you will have your tickets\n");


    }

/*===== Cancel Method =====
 * 
 * The Cancel method handles the cancellation of a client's reservation and manages the waitlist and available seats. 
 * When a client cancels a reservation, the method removes the reservation from the history and updates the seat availability. 
 * If clients are on the waitlist, the method attempts to offer the cancelled seats to the first client in the waitlist. 
 * If all the requested seats are fulfilled for a client, they are added to the reservations and their details are updated. 
 * If there are remaining unfulfilled seat requests, the client's original seat count is adjusted accordingly. 
 * Finally, the newly available seats are returned to the respective seating section.
 * 
 * Purpose:
 * - To cancel a reservation, update the seat availability, and manage the waitlist by offering cancelled seats to clients waiting for seats.
 * - Ensures that the waitlist is processed in the order clients requested their seats.
 * - Updates reservation history and available seats according to the cancelled reservations and waitlist status.
 * 
 * Parameters:
 * - historyStack: A Stack of HashMaps representing the reservation history, where each HashMap contains a client’s name and their reserved seats.
 * - WaitList: A Queue of Client objects representing the clients waiting for seats, ordered by their arrival.
 * - SectionSeats: A Set of Seats representing the available seats in the section that will be updated after the cancellation.
 * 
 * Return Value:
 * - void: This method does not return a value. It modifies the reservation history, the waitlist, and the available seats for the section.
 * 
 * Calls:
 * - None: This method directly interacts with the reservation history stack, waitlist queue, and section seat set.
 * 
 * Called By:
 * - The Menu system or a user interface: The `Cancel` method is called when a user opts to cancel a reservation in the ticket reservation system.
 */

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

                System.out.println("Latest reservation cancelled");
                System.out.println("Clients in waitlist added to reservations\n");
    
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

                System.out.println("Latest reservation cancelled");
                System.out.println("Client in waitlist did not get full anount of tickets");
                System.out.println("Added back to waitlist\n");

                CurrentClient.setClientSeatCnt(CurrentClient.getClientOriginalSeatCnt()-AssignedSeats.size());

                System.out.println("New amount needed: "+CurrentClient.getClientSeatCnt()+"\n");

                
                break;
            
            }

        }

        SectionSeats.addAll(CancelTickets);  //Adding extra tickets leftover if seats wanted from queue is fullfilled 

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
                
        // //Prints new Reservation, history, and waitlist information
        // System.out.println("Reservations: "+ Reservations);
        // System.out.println();
        // System.out.println("Stack: "+ historyStack);

        // System.out.println("Queue: "+ WaitList+"\n");
        //omitted for final version


    }


}