import java.util.Arrays;
import java.util.List;

public class GraduateParkingBoy {

  private List<ParkingLot> parkingLots;

  public GraduateParkingBoy(ParkingLot... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  public Ticket park(Car car) {
    return parkingLots.get(0).park(car);
  }
}
