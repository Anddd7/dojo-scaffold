package parkinglot.exceptions;

public class InvalidTicketException extends RuntimeException {

  public InvalidTicketException() {
    super("parkinglot.Ticket is invalid in this parkinglot.ParkingLot");
  }
}
