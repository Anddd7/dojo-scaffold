package parkinglot;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;

public class SmartParkingBoy {

  private final List<ParkingLot> parkingLots;

  public SmartParkingBoy(ParkingLot... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  public Ticket park(Car car) {
    return parkingLots.stream()
        .max(Comparator.comparing(ParkingLot::availableLots))
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
