package parkinglot;

import java.util.Arrays;
import java.util.List;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;

public class GraduateParkingBoy {

  private final List<ParkingLot> parkingLots;

  public GraduateParkingBoy(ParkingLot... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  public Ticket park(Car car) {
    return parkingLots.stream()
        .filter(ParkingLot::hasAvailableLots)
        .findFirst()
        .orElseThrow(NoAvailableLotException::new)
        .park(car);
  }

  public Car getCar(Ticket ticket) {
    return parkingLots.stream()
        .filter(parkingLot -> parkingLot.isValidTicket(ticket))
        .findFirst()
        .orElseThrow(InvalidTicketException::new)
        .getCar(ticket);
  }
}
