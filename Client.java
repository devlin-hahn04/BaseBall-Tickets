public class Client {

    private String ClientName;
    private String ClientEmail;
    private String ClientPhoneNumber;

    Client(String ClientName, String ClientEmail, String ClientPhoneNumber){

        this.ClientName= ClientName;
        this.ClientEmail= ClientEmail;
        this.ClientPhoneNumber= ClientPhoneNumber;

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

    //Methods

    @Override
    public String toString(){

        return "Client{name='" + ClientName + "', email='" + ClientEmail + "', phoneNumber='" + ClientPhoneNumber + "'}";

    }
    
    







}
