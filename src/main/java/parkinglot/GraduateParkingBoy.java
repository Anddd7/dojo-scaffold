package parkinglot;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import parkinglot.resources.Car;
import parkinglot.resources.Ticket;

public class GraduateParkingBoy extends AbstractParkingBoy implements ParkingBoy {

  public GraduateParkingBoy(ParkingLot... parkingLots) {
    super(Arrays.asList(parkingLots));
  }

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  @Override
  protected Optional<Ticket> tryPark(Car car) {
    return getParkingLots().stream()
        .filter(ParkingLot::hasAvailableLots)
        .findFirst()
        .map(parkingLot -> parkingLot.park(car));
  }

  @Override
  protected Optional<Car> tryGetCar(Ticket ticket) {
    return getParkingLots().stream()
        .filter(parkingLot -> parkingLot.isValidTicket(ticket))
        .findFirst()
        .map(parkingLot -> parkingLot.getCar(ticket));
  }
}
