public class Client {

    private String ClientName;
    private String ClientEmail;
    private String ClientPhoneNumber;
    private int ClientSeatCnt;

    Client(String ClientName, String ClientEmail, String ClientPhoneNumber, int ClientSeatCnt){

        this.ClientName= ClientName;
        this.ClientEmail= ClientEmail;
        this.ClientPhoneNumber= ClientPhoneNumber;
        this.ClientSeatCnt= ClientSeatCnt;

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

    //Methods

    @Override
    public String toString(){

        return "Client{name='" + ClientName + "', email='" + ClientEmail + "', phoneNumber='" + ClientPhoneNumber + "'}";

    }
    
    







}
