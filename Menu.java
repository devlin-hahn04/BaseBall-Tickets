import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/*===== MENU class =====
 * 
 * The Menu class serves as the primary interface for this ticket reservation system.
 * It interacts with the Stadium class to manage ticket reservations, cancellations,
 * and waitlists. This system provides functionality for clients to order tickets, view
 * available tickets, join a waitlist, and cancel reservations.
 * 
 * Data Structures Used:
 * - ArrayList: Used to store the reserved seats for clients. ArrayLists are used here 
 *   because they allow dynamic resizing and efficient access to elements when managing 
 *   reservations.
 * - HashMap: Utilized to associate clients with their reserved seats for quick lookup 
 *   and organization. This is beneficial for tracking transactions and managing the 
 *   reservation history.
 * - Iterator: Used for iterating through seat collections to allocate and reserve seats.
 * 
 */


public class Menu {

    /*===== getValidChoice Method =====
 * 
 * The getValidChoice method is responsible for ensuring user input matches one of 
 * two valid choices: "Y" or "N". It provides robust input validation by prompting 
 * the user repeatedly until a valid response is entered. This method ensures consistent 
 * and reliable interaction with the system for binary decisions.
 * 
 * Purpose:
 * - Validate and standardize user input for yes/no decisions.
 * - Ensure the user receives clear prompts and guidance during input.
 * 
 * Parameters:
 * - Scanner: A Scanner object used to read user input from the console.
 * - String prompt: A message displayed to the user, asking them for input.
 * 
 * Returns:
 * -A valid response from the user, either "Y" or "N" as the variable "choice" 
 * which is a String
 *  
 * Calls:
 * - Scanner: Reads user input from the console for validation.
 * -toUpperCase(): changes input to uppercase to comply with if statement conditions
 * 
 * Called By:
 *   -Any part of the Menu class where user input is required 
 *    to confirm choice, such as entering a waitlist or confirming an action.
 * 
 */

    public String GetValidChoice(Scanner scanner, String prompt){ //Returns Client's choice once validated

        String choice= "";
        boolean ChoiceValid= false;

        while(!ChoiceValid){ //Will loop until the Client inputs "Y" or "N"

            System.out.println(prompt);

            try{

                choice= scanner.nextLine().toUpperCase();

                if(!choice.equals("Y") && !choice.equals("N")){

                    System.out.println("Choice not valid, choose again\n");
                }

                else{
                    
                    ChoiceValid= true;

                }

            } catch (IllegalArgumentException e){
    
                System.out.println(e.getMessage());

            }
            
        }
        
        return choice;

    }

/*===== getValidInput Method =====
 * 
 * The getValidInput method ensures that user input matches one of the specified 
 * valid options provided in an array. This method repeatedly prompts the user 
 * until a valid response is entered, ensuring accurate and consistent interaction 
 * with the system for multiple-choice inputs.
 * 
 * Purpose:
 * - Validate and standardize user input for multiple-choice selections.
 * - Guide the user in making correct input decisions by providing clear prompts 
 *   and re-prompting if necessary.
 * 
 * Parameters:
 * - Scanner input: A Scanner object used to read user input from the console.
 * - String prompt: A message displayed to the user, asking them for input.
 * - String[] validOptions: An array of valid options against which the user's 
 *   input is validated.
 * 
 * Return Value:
 * - A String representing a valid user input that matches one of the specified 
 *   options.
 * 
 * Calls:
 * - Scanner: Reads user input from the console for validation.
 * 
 * Called By:
 * - Any part of the Menu class requiring user input to match one of several 
 *   predefined options, such as selecting a seating section or confirming 
 *   a reservation action.
 * 
 */

    public int GetValidInput(Scanner scanner, int min, int max, String errorPrompt){ //Returns Client's Input once validated

        int input= -1;
        boolean ValidInput= false;
        
        while(!ValidInput){ //Will loop until the Clients inputs a number between 1-4
    
            try{
                
                input= scanner.nextInt();
    
                if(input < min || input > max){
    
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


/*===== getValidLevelInput Method =====
 * 
 * The getValidLevelInput method is responsible for validating and ensuring that 
 * the user's input matches one of the predefined seating levels: "1", "2", or "3". 
 * This method repeatedly prompts the user until a valid input is entered, ensuring 
 * accurate and reliable interaction with the system for selecting seating levels.
 * 
 * Purpose:
 * - Validate user input to ensure it corresponds to an available seating level.
 * - Provide a clear and user-friendly interface for level selection.
 * 
 * Parameters:
 * - Scanner input: A Scanner object used to read user input from the console.
 * - String prompt: A message displayed to the user, asking them to select a seating level.
 * 
 * Return Value:
 * - A String representing a valid seating level ("1", "2", or "3") selected by the user.
 * 
 * Calls:
 * - Scanner: Reads user input from the console for validation.
 * 
 * Called By:
 * - Any part of the Menu class where the user must select a seating level, such as 
 *   reserving tickets or viewing available seats by level.
 * 
 */


    public int GetValidLevelInput(Scanner scanner, int min, int max){ //Returns Client's Level Input once validated

        boolean ValidInputLevel= false;
        int levelInput= -1;

        while(!ValidInputLevel){ //Will loop until the Client inputs a number between 1-3
    
            try{
                
                levelInput= scanner.nextInt();

                if(levelInput < min || levelInput > max){

                    System.out.println("Please Select between 1-3");
                    levelInput= scanner.nextInt();
                    

                }

                ValidInputLevel= true; //exits loop if previous condition is false
                
            } catch(java.util.InputMismatchException e){
               
                System.out.println("Please enter a number and from 1-3");
                scanner.nextLine();

            }


        }

        return levelInput;


    }

/*===== orderTickets Method =====
 * 
 * The orderTickets method handles the ticket reservation process for clients. 
 * It prompts the user to select a seating level, specifies the number of tickets 
 * they want to purchase, and reserves the requested seats if available. If the 
 * desired number of seats is not available, the user is offered the option to 
 * join a waitlist.
 * 
 * Purpose:
 * - Facilitate ticket reservations by allowing users to select levels and 
 *   reserve seats based on availability.
 * - Manage waitlisting when the desired number of seats cannot be fulfilled.
 * 
 * Parameters:
 * - Scanner input: A Scanner object used to read user input from the console.
 * 
 * Return Value:
 * - void: This method does not return a value. It directly handles ticket 
 *   reservation logic and updates the system's state as needed.
 * 
 * Calls:
 * - getValidLevelInput(): Validates and retrieves a seating level input from the user.
 * - Stadium.reserveSeats(): Attempts to reserve seats for the user at the selected level.
 * - Stadium.addToWaitlist(): Adds the user to the waitlist if the requested seats 
 *   are unavailable.
 * - getValidChoice(): Validates and retrieves a yes/no response from the user when 
 *   asked to join the waitlist.
 * 
 * Called By:
 * - The Menu class's main control loop or options menu, when the user selects 
 *   the option to reserve tickets.
 * 
 */


    public static void orderTickets(Scanner scanner, Stadium stadium, Menu menu, ArrayList<Seats> reservedSeats) {
        System.out.println("Choose Level:");
        System.out.println("   1)Field Level: $300, Available Seats: " + stadium.getFieldLevelSeatCount());
        System.out.println("   2)Main Level: $120, Available Seats: " + stadium.getMainLevelSeatCount());
        System.out.println("   3)GrandStand Level: $45, Available Seats: " + stadium.getGrandStandLevelSeatCount());
        
        int SeatsCnt = -1;
    
        int levelInput = menu.GetValidLevelInput(scanner, 1, 3);
    
        String SelectedLevel = "";
    
        /*===Switch summuary ===
         * The Switch is used to handle each case of each level.
         * Each case is managed similarly than the other, with
         * their main difference being what level is being perfomed
         * on. In summuary, the Switch does as follows:
         *    > Verifies if the level is fully reserved. If so, allow the
         *      person to enter a waitlist or not reserve there.
         *    > Starts a validation while loop of the amount of Seats
         *      to reserve.
         *    > Once validated, it reserves all those Seats, updates
         *      that level, and provides feedback of the purchase.
         * Additional comments where offered in the Field Level case,
         * providing a more in-depth visualization of it if needed
         * alongside the explanation given beforehand.
          =====================*/
        switch (levelInput) {
            case 1: //Field level
                SelectedLevel = "Field Level";
                boolean FieldValidInput = false;
    
                if (stadium.getFieldLevelSeatCount() == 0) { //The Field Level is fully reserved
                    System.out.println("\nSorry, tickets in this level are sold out");
                    scanner.nextLine();
                    String choice = menu.GetValidChoice(scanner, "Wish to enter waitlist for this section? (Y or N): ");
    
                    if (choice.equals("Y")) { //stadium.WaitList() will add them to the waitlist after asking for seats and info
                        stadium.WaitList(scanner, SelectedLevel, stadium.getFieldMaxCapacity());
                        return; // Skip rest of the method and go back to the main loop
                    } else {
                        System.out.println("No ticket will be reserved for you\n");
                        return;
                    }
                }
    
                while (!FieldValidInput) { //Will continue looping until given a valid amount of Seats

                    System.out.println("   Field Level Tickets\n");
                    System.out.println("Enter amount of seats to reserve: ");
        
                    try {
                        SeatsCnt = scanner.nextInt();
                        
    
                        if (SeatsCnt < 1 || SeatsCnt > stadium.getFieldMaxCapacity()) {
                            throw new IllegalArgumentException("Amount not valid, choose again");
                        }
    
                        //At this point, the Seats ordered where valid
                        FieldValidInput = true;
                        SelectedLevel = "Field Level";
                        int price = 300;
    
                        int cnt = 0;
                        Iterator<Seats> iterator = stadium.FieldLevelSeats.iterator();
    
                        while (iterator.hasNext() && cnt < SeatsCnt) { //Adds the amount of Seats to the reserved ones
                            Seats seat = iterator.next();
                            reservedSeats.add(seat);
                            iterator.remove();
                            cnt++;
                        }
    
                        //Updates the available Seats amount
                        stadium.setFieldLevelCnt(stadium.getFieldLevelSeatCount() - SeatsCnt);
    
                        //Returns feedback of purchase
                        System.out.println("Chose " + SeatsCnt + " seats for Field Level");
                        System.out.println("Total cost: " + SeatsCnt * price);
    
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Not valid input, choose again");
                        scanner.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
    
            case 2: //Main Level
                SelectedLevel = "Main Level";
                boolean MainValidInput = false;
    
                if (stadium.getMainLevelSeatCount() == 0) {
                    System.out.println("\nSorry, tickets in this level are sold out");
                    scanner.nextLine();
                    String choice = menu.GetValidChoice(scanner, "Wish to enter waitlist for this section? (Y or N): ");
    
                    if (choice.equals("Y")) {
                        stadium.WaitList(scanner, SelectedLevel, stadium.getMainMaxCapacity());
                        return; // Skip rest of the method and go back to the main loop
                    } else {
                        System.out.println("No ticket will be reserved for you\n");
                        return;
                    }
                }
    
                System.out.println("   Main Level Tickets\n");
                System.out.println("Enter amount of seats to reserve: ");
    
                while (!MainValidInput) {
                    try {
                        SeatsCnt = scanner.nextInt();
    
                        if (SeatsCnt < 1 || SeatsCnt > stadium.getMainMaxCapacity()) {
                            throw new IllegalArgumentException("Amount not valid, choose again");
                        }
    
                        MainValidInput = true;
                        SelectedLevel = "Main Level";
                        int price = 120;
    
                        int cnt = 0;
                        Iterator<Seats> iterator = stadium.MainLevelSeats.iterator();
    
                        while (iterator.hasNext() && cnt < SeatsCnt) {
                            Seats seat = iterator.next();
                            reservedSeats.add(seat);
                            iterator.remove();
                            cnt++;
                        }
    
                        stadium.setMainLevelCnt(stadium.getMainLevelSeatCount() - SeatsCnt);
    
                        System.out.println("Chose " + SeatsCnt + " seats for Main Level");
                        System.out.println("Total cost: " + SeatsCnt * price);
    
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Not valid input, choose again");
                        scanner.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
    
            case 3: //GrandStand Level
                SelectedLevel = "GrandStand Level";
                boolean GrandValidInput = false;
    
                if (stadium.getGrandStandLevelSeatCount() == 0) {
                    System.out.println("\nSorry, tickets in this level are sold out");
                    scanner.nextLine();
                    String choice = menu.GetValidChoice(scanner, "Wish to enter waitlist for this section? (Y or N): ");
    
                    if (choice.equals("Y")) {
                        stadium.WaitList(scanner, SelectedLevel, stadium.getGrandMaxCapacity());
                        return; // Skip rest of the method and go back to the main loop
                    } else {
                        System.out.println("No ticket will be reserved for you\n");
                        return;
                    }
                }
    
                System.out.println("   GrandStand Level Tickets\n");
                System.out.println("Enter amount of seats to reserve: ");
    
                while (!GrandValidInput) {
                    try {
                        SeatsCnt = scanner.nextInt();
    
                        if (SeatsCnt < 1 || SeatsCnt > stadium.getGrandMaxCapacity()) {
                            throw new IllegalArgumentException("Amount not valid, choose again");
                        }
    
                        GrandValidInput = true;
                        SelectedLevel = "GrandStand Level";
                        int price = 45;
    
                        int cnt = 0;
                        Iterator<Seats> iterator = stadium.GrandStandLevelSeats.iterator();
    
                        while (iterator.hasNext() && cnt < SeatsCnt) {
                            Seats seat = iterator.next();
                            reservedSeats.add(seat);
                            iterator.remove();
                            cnt++;
                        }
    
                        stadium.setGrandStandLevelCnt(stadium.getGrandStandLevelSeatCount() - SeatsCnt);
    
                        System.out.println("Chose " + SeatsCnt + " seats for GrandStand Level");
                        System.out.println("Total cost: " + SeatsCnt * price);
    
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Not valid input, choose again");
                        scanner.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
    
            default:
                break;
        }
    
        /*=== Client information and reservation summary ===*/
        System.out.println("   Client Reservation Information: \n");
        scanner.nextLine();
    
        System.out.println("Client name: ");
        String Name = scanner.nextLine();
    
        System.out.println("Client email: ");
        String Email = scanner.nextLine();
    
        System.out.println("Client phone number: ");
        String PhoneNumber = scanner.nextLine();
    
        System.out.println("\n   Reservation Summary: ");
        System.out.println(Name);
        System.out.println(Email);
        System.out.println(PhoneNumber);
        System.out.println("Seats ordered: " + SeatsCnt + " in " + SelectedLevel);
        // System.out.println(reservedSeats);  omitted for final version
        /*===========================================*/
    
        //Confirms the user as a Client
        Client client = new Client(Name, Email, PhoneNumber, SeatsCnt, SeatsCnt, reservedSeats);
    
        // Adding current transaction to hashmap to match clients to seats reserved
        stadium.ClientReservationList.put(client.getClientName(), new ArrayList<>(reservedSeats));
    
        // Adding current transaction to Stadium reservation linkedlist
        stadium.Reservations.add(new HashMap<>(stadium.ClientReservationList));
    
        // Adding current transaction to reservation history stack
        HashMap<String, ArrayList<Seats>> currentReservation = new HashMap<>(stadium.ClientReservationList);
    
        if (SelectedLevel == "Field Level") {
            stadium.FieldHistory.push(new HashMap<>(currentReservation));
        }
    
        if (SelectedLevel == "Main Level") {
            stadium.MainHistory.push(new HashMap<>(currentReservation));
        }
    
        if (SelectedLevel == "GrandStand Level") {
            stadium.GrandStandHistory.push(new HashMap<>(currentReservation));
        }
    
        // System.out.println("\nSeat reservations map: " + stadium.ClientReservationList); // Shows hashmap for testing
        // System.out.println("\nStadium reservation linkedlist: " + stadium.Reservations); // Shows linkedlist for testing
        //omitted for final version
    
        // Clearing references after adding stack
        stadium.ClientReservationList.clear();
        reservedSeats.clear();
    
        // Displaying leftover seats for testing
        System.out.println("\nSeats left in Field Level: " + stadium.getFieldLevelSeatCount());
        System.out.println("Seats left in Main Level: " + stadium.getMainLevelSeatCount());
        System.out.println("Seats left in GrandStand Level: " + stadium.getGrandStandLevelSeatCount() + "\n");
    }


/*===== handleCancelReservation Method =====
 * 
 * The handleCancelReservation method manages the process of canceling a reservation. 
 * This method cancels (removes) the latest reservation done in the specified section
 * ,if reservations have been done, and adds canceled seats to clients that are in the
 * waitlist, if clients exist in waitlist.
 * 
 * Purpose:
 * - Allow users to cancel their ticket reservations.
 * - Update seat availability and manage the waitlist when reservations are canceled.
 * 
 * Parameters:
 * - Scanner input: A Scanner object used to read user input from the console.
 * 
 * Return Value:
 * - void: This method does not return a value. It modifies the system's reservation 
 *   and waitlist states as needed.
 * 
 * Calls:
 * - Stadium.getReservations(): Retrieves the reservations for the user.
 * - Stadium.cancelReservation(): Cancels the reservation for the specified user.
 * - Stadium.handleWaitlist(): Allocates seats to users on the waitlist after a cancellation.
 * 
 * Called By:
 * - The Menu class's main control loop or options menu, when the user selects 
 *   the option to cancel a reservation.
 * 
 */

    public static void handleCancelReservation(Scanner scanner, Menu menu, Stadium stadium) { //Handles the cancelled reservation in a Client's given level
        System.out.println("   Cancel Reservation\n");
        System.out.println("Enter section to remove reservation from");
        System.out.println("   1)Field Level");
        System.out.println("   2)Main Level");
        System.out.println("   3)GrandStand Level");
    
        //Lets Client decide what reservation to cancel from
        int input = menu.GetValidLevelInput(scanner, 1, 4);
    
        //Removes last reservation of given level
        if (input == 1) {
            
            stadium.Cancel(stadium.FieldHistory, stadium.FieldWaitList, stadium.FieldLevelSeats);
        } else if (input == 2) {
            
            stadium.Cancel(stadium.MainHistory, stadium.MainWaitList, stadium.MainLevelSeats);
        } else if (input == 3) {
            
            stadium.Cancel(stadium.GrandStandHistory, stadium.GrandWaitList, stadium.GrandStandLevelSeats);
        }
    }

    
    public static void showAvailableTickets(Stadium stadium) { //returns each level's available seats and its price
        System.out.println("   Available Tickets: \n");
    
        // Display available seats for Field Level
        System.out.println("Field Level: $" + 300 + ", Available Seats: " + stadium.getFieldLevelSeatCount());
    
        // Display available seats for Main Level
        System.out.println("\nMain Level: $" + 120 + ", Available Seats: " + stadium.getMainLevelSeatCount());
    
        // Display available seats for GrandStand Level
        System.out.println("\nGrandStand Level: $" + 45 + ", Available Seats: " + stadium.getGrandStandLevelSeatCount());
    
        System.out.println(); // For cleaner formatting
    }


/*===== Main Method =====
 * 
 * The main method serves as the entry point for the Menu-driven ticket reservation system. 
 * It integrates all core functionalities, allowing users to order tickets, cancel reservations, 
 * view available seats, join a waitlist, and exit the system.
 * 
 * Purpose:
 * - Serve as the control loop for the application, enabling user interaction with the system.
 * - Provide access to all functionalities, such as ordering tickets, canceling reservations, 
 *   managing the waitlist, and displaying available seats.
 * 
 * Parameters:
 * - None: The main method does not accept any parameters.
 * 
 * Return Value:
 * - void: This method does not return a value as it runs the application.
 * 
 * Calls:
 * - Menu methods (orderTickets, handleCancelReservation, displayAvailableSeats, etc.): 
 *   Executes user-selected options.
 * - getValidChoice(): Ensures valid user input for menu navigation.
 * 
 * Called By:
 * - The Java Virtual Machine to start the execution of the program.
 * 
 */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        /*=== Menu preparation ===*/
        Stadium stadium = new Stadium();
        Menu menu = new Menu();
        stadium.LoadFieldSeats();
        stadium.LoadMainSeats();
        stadium.LoadGrandStandSeats();
    
        ArrayList<Seats> reservedSeats = new ArrayList<>();
        /*======================*/
    
        System.out.println("                    Welcome To TicketOrder!\n");
    
        int input = -1;
    
        mainloop : while (input != 4) { //Will keep looping until the Menu is left
            System.out.println("Select Choice:");
            System.out.println("   1)Order Tickets");
            System.out.println("   2)Cancel Reservation");
            System.out.println("   3)Show Available Tickets");
            System.out.println("   4)Exit");
    
            input = menu.GetValidInput(scanner, 1, 4, "Please select between 1-4\n");
    
            // Ordering Tickets Menu
            if (input == 1) {
                orderTickets(scanner, stadium, menu, reservedSeats);
            }
    
            // Cancel Reservation Menu
            if (input == 2) {
                handleCancelReservation(scanner, menu, stadium);
            }
            
            // Available Tickets Menu
            if (input == 3) {
                showAvailableTickets(stadium);
            }
        }
    }





}
