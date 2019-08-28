package exceptions;

public class InvalidTicketException extends RuntimeException {

  public InvalidTicketException() {
    super("exceptions.Ticket is invalid in this exceptions.ParkingLot");
  }
}
