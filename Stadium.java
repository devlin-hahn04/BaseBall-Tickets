
/*Administers seats available, 
client reservations or registry 
of transactions
*/

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;

public class Stadium{

    /*Sets to be used to store available
    seats by section (Field, Main, GrandStand)
    */
    Set<String> FieldLevelSeats= new HashSet<>();
    Set<String> MainLevelSeats= new HashSet<>();
    Set<String> GrandStandLevelSeats= new HashSet<>();

    /*LinkedList used to store reservation history
    ()
    */               //Name     Seats Bought
    LinkedList<HashMap<String, ArrayList<String>>> Reservations= new LinkedList<>();


    private int TotalSeats;
    private int FieldLevelCnt;
    private int MainLevelCnt;
    private int GrandStandLevelCnt;
    
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

        for(int i= 0; i < 10; i++){

            FieldLevelSeats.add("F"+i);

        }

        setFieldLevelCnt(FieldLevelSeats.size());

    }

    public void LoadMainSeats(){

        for(int i= 0; i < 10; i++){

            MainLevelSeats.add("M"+i);

        }

        setMainLevelCnt(MainLevelSeats.size());

    }

    public void LoadGrandStandSeats(){

        for(int i= 0; i < 10; i++){

            GrandStandLevelSeats.add("G"+i);

        }

        setGrandStandLevelCnt(GrandStandLevelSeats.size());

    }















}