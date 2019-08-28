package exceptions;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

  private final int capacity;
  private Map<Ticket, Car> ticketCarMap = new HashMap<>();

  public ParkingLot(int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("exceptions.ParkingLot should have at least 1 lot");
    }
    this.capacity = capacity;
  }

  public Ticket park(Car car) {
    if (ticketCarMap.size() < capacity) {
      Ticket ticket = new Ticket();
      ticketCarMap.put(ticket, car);
      return ticket;
    }
    throw new NoAvailableLotException();
  }

  public Car getCar(Ticket ticket) {
    Car car = ticketCarMap.get(ticket);
    if (car == null) {
      throw new InvalidTicketException();
    }
    return car;
  }
}
