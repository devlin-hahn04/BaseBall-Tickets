import java.util.ArrayList;

/*===== CLIENT Class =====
 *
 * The Client class is used to store and manage information about users who wish 
 * to make reservations at the stadium. Each client has essential personal information 
 * such as their name, email, phone number, and the seats they reserve. This class also 
 * tracks the number of seats reserved by the client and manages the seats as an 
 * ArrayList for dynamic seat allocation.
 * 
 * Purpose:
 * - Store client details for reservation management.
 * - Allow modifications and retrieval of client-specific data.
 * - Keep track of the seats reserved by each client and their original seat count for 
 *   cancellation purposes.
 * 
 * Data Structures Used:
 * - ArrayList: An ArrayList is used to store the reserved seats for the client. This 
 *   allows flexible management of seat allocations, as ArrayLists can grow or shrink 
 *   dynamically as clients add or cancel reservations. Using ArrayLists also allows 
 *   efficient access to the client's reserved seats when needed.
 * 
 */
public class Client {

    /*=== Client Properties ===*/
    private String ClientName;            // Stores the name of the client.
    private String ClientEmail;           // Stores the email address of the client.
    private String ClientPhoneNumber;     // Stores the phone number of the client.
    private int ClientSeatCnt;            // The number of seats reserved by the client.
    private int ClientOriginalSeatCnt;    // The original number of seats reserved (used for cancellations).
    private ArrayList<Seats> ClientSeats; // Stores the seats reserved by the client.
    /*=====================*/

    /*=== Client Constructor ===
     * Initializes a new Client object with the given personal details and reserved seats.
     * 
     * Parameters:
     * - String ClientName: The client's name.
     * - String ClientEmail: The client's email address.
     * - String ClientPhoneNumber: The client's phone number.
     * - int ClientSeatCnt: The number of seats reserved by the client.
     * - int ClientOriginalSeatCnt: The original number of seats reserved (for cancellation tracking).
     * - ArrayList<Seats> ClientSeats: The list of seats reserved by the client.
     */
    Client(String ClientName, String ClientEmail, String ClientPhoneNumber, int ClientSeatCnt, int ClientOriginalSeatCnt, ArrayList<Seats> ClientSeats) {
        this.ClientName = ClientName;
        this.ClientEmail = ClientEmail;
        this.ClientPhoneNumber = ClientPhoneNumber;
        this.ClientSeatCnt = ClientSeatCnt;
        this.ClientOriginalSeatCnt = ClientOriginalSeatCnt;
        this.ClientSeats = ClientSeats;
    }

    /*=== Getters ===
     * Provide methods to retrieve individual properties of the client.
     */
    public String getClientName() {
        return this.ClientName;
    }

    public String getClientEmail() {
        return this.ClientEmail;
    }

    public String getClientPhoneNumber() {
        return this.ClientPhoneNumber;
    }

    public int getClientSeatCnt() {
        return this.ClientSeatCnt;
    }

    public int getClientOriginalSeatCnt() {
        return this.ClientOriginalSeatCnt;
    }

    public ArrayList<Seats> getClientSeats() {
        return ClientSeats;
    }
    /*==============*/
    
    /*=== Setters ===
     * Provide methods to modify individual properties of the client.
     */
    public void setClientName(String ClientName) {
        this.ClientName = ClientName;
    }

    public void setClientEmail(String ClientEmail) {
        this.ClientEmail = ClientEmail;
    }

    public void setClientPhoneNumber(String ClientPhoneNumber) {
        this.ClientPhoneNumber = ClientPhoneNumber;
    }

    public void setClientSeatCnt(int ClientSeatCnt) {
        this.ClientSeatCnt = ClientSeatCnt;
    }

    public void setClientSeats(ArrayList<Seats> ClientSeats) {
        this.ClientSeats = ClientSeats;
    }
    /*==============*/
    
    /*=== Methods ===
     * The toString method returns a string representation of the client's information 
     * for easy viewing.
     */
    @Override
    public String toString() {
        return "Client{name='" + ClientName + "', email='" + ClientEmail + "', phoneNumber='" + ClientPhoneNumber + "'}";
    }
    /*=============*/
}
