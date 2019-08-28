package parkinglot;

import java.util.Comparator;
import java.util.Optional;
import parkinglot.resources.Car;
import parkinglot.resources.Ticket;

public class SmartParkingBoy extends GraduateParkingBoy implements ParkingBoy {

  public SmartParkingBoy(ParkingLot... parkingLots) {
    super(parkingLots);
  }

  @Override
  protected Optional<Ticket> tryPark(Car car) {
    return getParkingLots().stream()
        .max(Comparator.comparing(ParkingLot::availableLots))
        .filter(ParkingLot::hasAvailableLots)
        .map(parkingLot -> parkingLot.park(car));
  }
}
