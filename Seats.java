/*===== SEATS class explanation ======
 * 
 * The Seats class is a simple class that is used in bulk in the
 * Stadium class to identify each object. Each Seat has its own 
 * arguments that seperates it from others:
 *    > Section
 *    > Row
 *    > Number
 * 
 =================================*/

public class Seats {
    /*=== Seats properties ===*/
    private String SeatSection;
    private String SeatRow;
    private String SeatNumber;
    /*======================*/

    /*=== Seats Constructor ===
     * @param Section, Row, Number
       =======================*/
    public Seats(String SeatSection, String SeatRow, String SeatNumber){

        this.SeatSection= SeatSection;
        this.SeatRow= SeatRow;
        this.SeatNumber= SeatNumber;
        
    }

    /*=== Setters ===*/
    public void setSeatSection(String SeatSection) {
        this.SeatSection= SeatSection;
    }

    public void setSeatRow(String SeatRow) {
        this.SeatRow= SeatRow;
    }

    public void setSeatNumber(String SeatNumber) {
        this.SeatNumber= SeatNumber;
    }
    /*==============*/

    /*=== Getters ===*/
    public String getSeatSection() {
        return SeatSection;
    }

    public String getSeatRow() {
        return SeatRow;
    }

    public String getSeatNumber() {
        return SeatNumber;
    }
    /*===============*/

    
    /*=== Methods ===*/

    @Override
    public String toString() { //returns the Seat information to administer its reservation
        return SeatSection+"("+SeatRow+"-"+SeatNumber+")";
    }
    /*==============*/


}
