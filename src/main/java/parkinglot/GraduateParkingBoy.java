package parkinglot;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GraduateParkingBoy implements IParkingBoy {

  private final List<ParkingLot> parkingLots;

  List<ParkingLot> getParkingLots() {
    return parkingLots;
  }

  public GraduateParkingBoy(ParkingLot... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  @Override
  public Optional<Ticket> tryPark(Car car) {
    return getParkingLots().stream()
        .filter(ParkingLot::hasAvailableLots)
        .findFirst()
        .map(parkingLot -> parkingLot.park(car));
  }

  @Override
  public Optional<Car> tryGetCar(Ticket ticket) {
    return getParkingLots().stream()
        .filter(parkingLot -> parkingLot.isValidTicket(ticket))
        .findFirst()
        .map(parkingLot -> parkingLot.getCar(ticket));
  }
}
