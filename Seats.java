public class Seats {
    
    private String SeatSection;
    private String SeatRow;
    private String SeatNumber;

    public Seats(String SeatSection, String SeatRow, String SeatNumber){

        this.SeatSection= SeatSection;
        this.SeatRow= SeatRow;
        this.SeatNumber= SeatNumber;

    }

    // Setters
    public void setSeatSection(String SeatSection) {
        this.SeatSection= SeatSection;
    }

    public void setSeatRow(String SeatRow) {
        this.SeatRow= SeatRow;
    }

    public void setSeatNumber(String SeatNumber) {
        this.SeatNumber= SeatNumber;
    }

    // Getters
    public String getSeatSection() {
        return SeatSection;
    }

    public String getSeatRow() {
        return SeatRow;
    }

    public String getSeatNumber() {
        return SeatNumber;
    }

    //Methods

    @Override
    public String toString() {
        return SeatSection+"("+SeatRow+"-"+SeatNumber+")";
    }


}
