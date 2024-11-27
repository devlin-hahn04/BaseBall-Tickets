
/*Administers seats available, 
client reservations or registry 
of transactions
*/

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Iterator;

public class Stadium{

    /*Sets to be used to store available
    seats by section (Field, Main, GrandStand)
    */
    Set<Seats> FieldLevelSeats= new HashSet<>();
    Set<Seats> MainLevelSeats= new HashSet<>();
    Set<Seats> GrandStandLevelSeats= new HashSet<>();

    Queue<Client> FieldWaitList= new LinkedList<>();
    Queue<Client> MainWaitList= new LinkedList<>();
    Queue<Client> GrandWaitList= new LinkedList<>();

    //Stack to store reservation history for cancel method
    Stack<HashMap<String, ArrayList<Seats>>> FieldHistory= new Stack<>();
    Stack<HashMap<String, ArrayList<Seats>>> MainHistory= new Stack<>();
    Stack<HashMap<String, ArrayList<Seats>>> GrandStandHistory= new Stack<>();

    /*LinkedList used to store reservation history
    ()
    */               //Name     Seats Bought
    LinkedList<HashMap<String, ArrayList<Seats>>> Reservations= new LinkedList<>();

    //HashMap to pair client to seats ordered 
    HashMap<String, ArrayList<Seats>> ClientReservationList= new HashMap<>();


    private int TotalSeats;
    private int FieldLevelCnt;
    private int MainLevelCnt;
    private int GrandStandLevelCnt;

    private int FieldMaxCapacity= 10;
    private int MainMaxCapacity= 10;
    private int GrandMaxCapacity= 10;
    
    //Getters
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



    //Setters
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

    //Methods
    public void LoadFieldSeats(){

        int RowCnt= 10;

        for(int i= 1; i <= 10; i++){

            FieldLevelSeats.add(new Seats("F", String.valueOf(RowCnt), String.valueOf(i)));
            RowCnt--;

        }

        setFieldLevelCnt(FieldLevelSeats.size());

    }

    public void LoadMainSeats(){

        int RowCnt= 10;

        for(int i= 1; i <= 10; i++){

            MainLevelSeats.add(new Seats("M", String.valueOf(RowCnt), String.valueOf(i)));
            RowCnt--;

        }

        setMainLevelCnt(MainLevelSeats.size());

    }

    public void LoadGrandStandSeats(){

        int RowCnt= 10;

        for(int i= 1; i <= 10; i++){

            GrandStandLevelSeats.add(new Seats("G", String.valueOf(RowCnt), String.valueOf(i)));
            RowCnt--;

        }

        setGrandStandLevelCnt(GrandStandLevelSeats.size());

    }


    public void WaitList(Scanner scanner, String SelectedLevel, int MaxCapacity){

        System.out.println("   Ticket Waitlist");
        System.out.println("Enter client information to add to waitlist");    
        
        System.out.println("Client name: ");
        String Name= scanner.nextLine();

        System.out.println("Client email: ");
        String Email= scanner.nextLine();

        System.out.println("Client phone number: ");
        String PhoneNumber= scanner.nextLine();

        System.out.println("Number of seats desired: ");
        int SeatsCnt=0;

        while(true){

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

    public void Cancel(Stack<HashMap<String, ArrayList<Seats>>> historyStack, Queue<Client> WaitList, Set<Seats> SectionSeats){

        if(historyStack.isEmpty()){

            System.out.println("No reservations have been made for this section, try another option\n");
            return;

        }

        HashMap<String, ArrayList<Seats>> latestReservation= historyStack.pop();
        Reservations.remove(latestReservation);

        ArrayList<Seats> CancelTickets= new ArrayList<>();

        for(ArrayList<Seats> seatList : latestReservation.values()){

            CancelTickets.addAll(seatList);

        }

        if(WaitList.isEmpty()){

            System.out.println("No clients in the waitlist\n");
            SectionSeats.addAll(CancelTickets);
            


        }

            

        

        while(!WaitList.isEmpty() && !CancelTickets.isEmpty()){

            Client CurrentClient= WaitList.peek();
            ArrayList<Seats> AssignedSeats= CurrentClient.getClientSeats();
    
            Iterator<Seats> it = CancelTickets.iterator();

            while(it.hasNext() && AssignedSeats.size() < CurrentClient.getClientOriginalSeatCnt()){

                AssignedSeats.add(it.next());
                it.remove();

            }    

            
            
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

            else{

                System.out.println("Not enough seats to fulfill requirement");

                CurrentClient.setClientSeatCnt(CurrentClient.getClientOriginalSeatCnt()-AssignedSeats.size());

                System.out.println("New amount needed: "+CurrentClient.getClientSeatCnt());

                
                break;
            
            }

        }

        SectionSeats.addAll(CancelTickets);  //Adding extra tickets leftover if seats wanted from queue is fullfilled 
        System.out.println("Updated section: "+SectionSeats);

        if(SectionSeats == FieldLevelSeats){

            setFieldLevelCnt(SectionSeats.size());

        } 
        
        else if(SectionSeats == MainLevelSeats){

            setMainLevelCnt(SectionSeats.size());
        } 
        
        else if(SectionSeats == GrandStandLevelSeats){

            setGrandStandLevelCnt(SectionSeats.size());

        }
                

        System.out.println("Reservations: "+ Reservations);
        System.out.println();
        System.out.println("Stack: "+ historyStack);

        System.out.println("Queue: "+ WaitList+"\n");


    }


}