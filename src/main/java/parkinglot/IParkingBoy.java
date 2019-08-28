package parkinglot;

import java.util.Optional;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;

public interface IParkingBoy {

  default Ticket park(Car car) {
    return tryPark(car).orElseThrow(NoAvailableLotException::new);
  }

  default Car getCar(Ticket ticket) {
    return tryGetCar(ticket).orElseThrow(InvalidTicketException::new);
  }

  Optional<Ticket> tryPark(Car car);

  Optional<Car> tryGetCar(Ticket ticket);
}
