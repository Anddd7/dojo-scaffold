package parkinglot.exceptions;

public class InvalidTicketException extends RuntimeException {

  public InvalidTicketException() {
    super("parkinglot.resources.Ticket is invalid in this parkinglot.ParkingLot");
  }
}
