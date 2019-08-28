package parkinglot;

import java.util.List;
import java.util.Optional;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;
import parkinglot.resources.Car;
import parkinglot.resources.Ticket;

abstract class AbstractParkingBoy implements ParkingBoy {

  private final List<ParkingLot> parkingLots;

  AbstractParkingBoy(List<ParkingLot> parkingLots) {
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

  abstract Optional<Ticket> tryPark(Car car);

  abstract Optional<Car> tryGetCar(Ticket ticket);

  List<ParkingLot> getParkingLots() {
    return parkingLots;
  }
}
