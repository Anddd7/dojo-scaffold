package parkinglot;

import parkinglot.resources.Car;
import parkinglot.resources.Ticket;

public interface ParkingBoy {

  Ticket park(Car car);

  Car getCar(Ticket ticket);
}
