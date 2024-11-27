import java.util.ArrayList;

/*===== CLIENT class explanation =====
 *
 * The Client class is a simpler class that is used for managing the information
 * of different users that plan to do reservations at the Stadium. When 
 * getting the user's information to become a Client, they are asked:
 *    > Name
 *    > Email
 *    > Phone number
 *
  =================================*/

public class Client {

    /*=== Client properties ===*/
    private String ClientName;
    private String ClientEmail;
    private String ClientPhoneNumber;
    private int ClientSeatCnt;
    private int ClientOriginalSeatCnt; //Used in Stadium for reservation cancelations
    private ArrayList<Seats> ClientSeats;
    /*=====================*/

    /*=== Client Constructor ===
     * @param Name, Email, Phone number, amount of seats, original amount of seats, reserved seats
      =======================*/
    Client(String ClientName, String ClientEmail, String ClientPhoneNumber, int ClientSeatCnt, int ClientOriginalSeatCnt, ArrayList<Seats> ClientSeats){

        this.ClientName= ClientName;
        this.ClientEmail= ClientEmail;
        this.ClientPhoneNumber= ClientPhoneNumber;
        this.ClientSeatCnt= ClientSeatCnt;
        this.ClientOriginalSeatCnt= ClientOriginalSeatCnt;
        this.ClientSeats= ClientSeats;

    }

    /*=== Getters ===*/
    public String getClientName(){

        return this.ClientName;

    }

    public String getClientEmail(){

        return this.ClientEmail;

    }

    public String getClientPhoneNumber(){

        return this.ClientPhoneNumber;

    }

    public int getClientSeatCnt(){

        return this.ClientSeatCnt;

    }

    public int getClientOriginalSeatCnt(){

        return this.ClientOriginalSeatCnt;

    }

    public ArrayList<Seats> getClientSeats(){
        return ClientSeats;
    }
    /*==============*/
    
    /*=== Setters ===*/
    public void setClientName(String ClientName){
        
        this.ClientName= ClientName;
        
    }
    
    public void setClientEmail(String ClientEmail){
        
        this.ClientEmail= ClientEmail;
        
    }
    
    public void setClientPhoneNumber(String ClientPhoneNumber){
        
        this.ClientPhoneNumber= ClientPhoneNumber;
        
    }
    
    public void setClientSeatCnt(int ClientSeatCnt){
        
        this.ClientSeatCnt= ClientSeatCnt;
        
    }
    
    public void setClientSeats(ArrayList<Seats> ClientSeats){
        this.ClientSeats= ClientSeats;
    }
    /*==============*/
    
    
    /*=== Methods ===*/
    @Override
    public String toString(){ //Returns the Client's information for easy view

        return "Client{name='" + ClientName + "', email='" + ClientEmail + "', phoneNumber='" + ClientPhoneNumber + "'}";

    }
    /*=============*/
    
    







}
