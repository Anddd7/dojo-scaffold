package parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;

public class SmartParkingBoy implements IParkingBoy {

  private final List<ParkingLot> parkingLots;

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  @Override
  public Ticket park(Car car) {
    return tryPark(car).orElseThrow(NoAvailableLotException::new);
  }

  @Override
  public Car getCar(Ticket ticket) {
    return tryGetCar(ticket).orElseThrow(InvalidTicketException::new);
  }

  @Override
  public Optional<Ticket> tryPark(Car car) {
    return parkingLots.stream()
        .max(Comparator.comparing(ParkingLot::availableLots))
        .map(parkingLot -> parkingLot.park(car));
  }

  @Override
  public Optional<Car> tryGetCar(Ticket ticket) {
    return parkingLots.stream()
        .filter(parkingLot -> parkingLot.isValidTicket(ticket))
        .findFirst()
        .map(parkingLot -> parkingLot.getCar(ticket));
  }
}
