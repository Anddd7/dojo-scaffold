package parkinglot.exceptions;

public class NoAvailableLotException extends RuntimeException {

  public NoAvailableLotException() {
    super("No available lot in this parkinglot.ParkingLot");
  }
}
