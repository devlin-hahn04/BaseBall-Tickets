/*===== SEATS Class =====
 * 
 * The Seats class represents individual seats in the stadium. Each seat is uniquely 
 * identified by its section, row, and seat number, which collectively distinguish 
 * it from other seats. The class provides constructors, getters, setters, and a 
 * custom `toString` method for seat management and display purposes.
 * 
 * Purpose:
 * - Store detailed information about each seat in a standardized way.
 * - Allow modification and retrieval of seat data for use in other classes, 
 *   such as the Stadium class.
 * - Facilitate interaction with seat objects in the ticket reservation system.
 * 
 * Data Structures Used:
 * - This class does not use lists or collections itself, but its objects are intended 
 *   to be managed in bulk by higher-level classes like Stadium. For example, 
 *   an `ArrayList` may be used in the Stadium class to store multiple `Seats` objects 
 *   for dynamic and efficient management.
 * 
 */
public class Seats {

    /*=== Seats Properties ===*/
    private String SeatSection; // Identifies the section of the seat (e.g., "A", "B")
    private String SeatRow;     // Identifies the row of the seat (e.g., "1", "2")
    private String SeatNumber;  // Identifies the number within the row (e.g., "3", "4")
    /*======================*/

    /*=== Seats Constructor ===
     * 
     * Initializes a Seats object with the provided section, row, and seat number.
     * 
     * Parameters:
     * - String SeatSection: The section of the seat (e.g., "A").
     * - String SeatRow: The row of the seat (e.g., "1").
     * - String SeatNumber: The seat number within the row (e.g., "3").
     * 
     */
    public Seats(String SeatSection, String SeatRow, String SeatNumber) {
        this.SeatSection = SeatSection;
        this.SeatRow = SeatRow;
        this.SeatNumber = SeatNumber;
    }

    /*=== Setters ===
     * 
     * Provide methods to modify individual seat attributes.
     * 
     * Methods:
     * - setSeatSection: Updates the section of the seat.
     * - setSeatRow: Updates the row of the seat.
     * - setSeatNumber: Updates the seat number within the row.
     */
    public void setSeatSection(String SeatSection) {
        this.SeatSection = SeatSection;
    }

    public void setSeatRow(String SeatRow) {
        this.SeatRow = SeatRow;
    }

    public void setSeatNumber(String SeatNumber) {
        this.SeatNumber = SeatNumber;
    }
    /*===============*/

    /*=== Getters ===
     * 
     * Provide methods to retrieve individual seat attributes.
     * 
     * Methods:
     * - getSeatSection: Returns the section of the seat.
     * - getSeatRow: Returns the row of the seat.
     * - getSeatNumber: Returns the seat number within the row.
     */
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

    /*=== Methods ===
     * 
     * toString:
     * - Returns a formatted string representation of the seat, 
     *   combining its section, row, and seat number for display 
     *   or administrative purposes.
     */
    @Override
    public String toString() {
        return SeatSection + "(" + SeatRow + "-" + SeatNumber + ")";
    }
    /*===============*/
}
