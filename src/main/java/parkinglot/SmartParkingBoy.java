package parkinglot;

import java.util.Comparator;
import java.util.Optional;

public class SmartParkingBoy extends GraduateParkingBoy implements IParkingBoy {

  public SmartParkingBoy(ParkingLot... parkingLots) {
    super(parkingLots);
  }

  @Override
  public Optional<Ticket> tryPark(Car car) {
    return getParkingLots().stream()
        .max(Comparator.comparing(ParkingLot::availableLots))
        .filter(ParkingLot::hasAvailableLots)
        .map(parkingLot -> parkingLot.park(car));
  }
}
