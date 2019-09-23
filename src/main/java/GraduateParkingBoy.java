import java.util.Arrays;
import java.util.List;

public class GraduateParkingBoy {

  private List<ParkingLot> parkingLots;

  public GraduateParkingBoy(ParkingLot... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  public Ticket park(Car car) {
    return parkingLots.stream()
        .filter(ParkingLot::hasAvailableLots)
        .findFirst()
        .orElseThrow(NoAvailableLotsException::new)
        .park(car);
  }

  public Car pick(Ticket ticket) {
    return parkingLots.stream()
        .filter(parkingLot -> parkingLot.isValidTicket(ticket))
        .findFirst()
        .orElseThrow(InvalidTicketException::new)
        .pick(ticket);
  }
}
