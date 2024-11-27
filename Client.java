import java.util.ArrayList;

public class Client {

    private String ClientName;
    private String ClientEmail;
    private String ClientPhoneNumber;
    private int ClientSeatCnt;
    private int ClientOriginalSeatCnt;
    private ArrayList<Seats> ClientSeats;

    Client(String ClientName, String ClientEmail, String ClientPhoneNumber, int ClientSeatCnt, int ClientOriginalSeatCnt, ArrayList<Seats> ClientSeats){

        this.ClientName= ClientName;
        this.ClientEmail= ClientEmail;
        this.ClientPhoneNumber= ClientPhoneNumber;
        this.ClientSeatCnt= ClientSeatCnt;
        this.ClientOriginalSeatCnt= ClientOriginalSeatCnt;
        this.ClientSeats= ClientSeats;

    }

    //Getters
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
    
    //Setters
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
    
    
    //Methods
    
    @Override
    public String toString(){

        return "Client{name='" + ClientName + "', email='" + ClientEmail + "', phoneNumber='" + ClientPhoneNumber + "'}";

    }
    
    







}
