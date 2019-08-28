package parkinglot;

import java.util.Optional;

public interface IParkingBoy {

  Ticket park(Car car);

  Car getCar(Ticket ticket);

  Optional<Ticket> tryPark(Car car);

  Optional<Car> tryGetCar(Ticket ticket);
}
