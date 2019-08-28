package parkinglot;

import java.util.List;
import java.util.Optional;

public final class ParkingManager extends SmartParkingBoy implements IParkingBoy {

  private final List<IParkingBoy> parkingBoys;

  public ParkingManager(List<IParkingBoy> parkingBoys, ParkingLot... parkingLots) {
    super(parkingLots);
    this.parkingBoys = parkingBoys;
  }

  @Override
  public Optional<Ticket> tryPark(Car car) {
    for (IParkingBoy parkingBoy : parkingBoys) {
      Optional<Ticket> ticket = parkingBoy.tryPark(car);
      if (ticket.isPresent()) {
        return ticket;
      }
    }

    return super.tryPark(car);
  }

  @Override
  public Optional<Car> tryGetCar(Ticket ticket) {
    for (IParkingBoy parkingBoy : parkingBoys) {
      Optional<Car> car = parkingBoy.tryGetCar(ticket);
      if (car.isPresent()) {
        return car;
      }
    }

    return super.tryGetCar(ticket);
  }
}
