package parkinglot;

import java.util.List;
import java.util.Optional;
import parkinglot.resources.Car;
import parkinglot.resources.Ticket;

public final class ParkingManager extends SmartParkingBoy implements ParkingBoy {

  private final List<ParkingBoy> parkingBoys;

  public ParkingManager(List<ParkingBoy> parkingBoys, ParkingLot... parkingLots) {
    super(parkingLots);
    this.parkingBoys = parkingBoys;
  }

  public ParkingManager(List<ParkingBoy> parkingBoys, List<ParkingLot> parkingLots) {
    super(parkingLots);
    this.parkingBoys = parkingBoys;
  }

  @Override
  protected Optional<Ticket> tryPark(Car car) {
    for (ParkingBoy parkingBoy : parkingBoys) {
      Optional<Ticket> ticket = ((AbstractParkingBoy) parkingBoy).tryPark(car);
      if (ticket.isPresent()) {
        return ticket;
      }
    }

    return super.tryPark(car);
  }

  @Override
  protected Optional<Car> tryGetCar(Ticket ticket) {
    for (ParkingBoy parkingBoy : parkingBoys) {
      Optional<Car> car = ((AbstractParkingBoy) parkingBoy).tryGetCar(ticket);
      if (car.isPresent()) {
        return car;
      }
    }

    return super.tryGetCar(ticket);
  }
}
