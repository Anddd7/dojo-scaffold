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
}
