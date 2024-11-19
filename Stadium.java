
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
    Set<Seats> FieldLevelSeats= new HashSet<>();
    Set<Seats> MainLevelSeats= new HashSet<>();
    Set<Seats> GrandStandLevelSeats= new HashSet<>();

    /*LinkedList used to store reservation history
    ()
    */               //Name     Seats Bought
    LinkedList<HashMap<String, ArrayList<Seats>>> Reservations= new LinkedList<>();


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















}