package parkinglot;

import java.util.HashMap;
import java.util.Map;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;

public class ParkingLot {

  private final int capacity;
  private Map<Ticket, Car> ticketCarMap = new HashMap<>();

  public ParkingLot(int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("parkinglot.ParkingLot should have at least 1 lot");
    }
    this.capacity = capacity;
  }

  public Ticket park(Car car) {
    if (hasAvailableLots()) {
      Ticket ticket = new Ticket();
      ticketCarMap.put(ticket, car);
      return ticket;
    }
    throw new NoAvailableLotException();
  }


  public Car getCar(Ticket ticket) {
    if (isValidTicket(ticket)) {
      return ticketCarMap.get(ticket);
    }
    throw new InvalidTicketException();
  }

  boolean hasAvailableLots() {
    return ticketCarMap.size() < capacity;
  }

  int availableLots() {
    return capacity - ticketCarMap.size();
  }

  boolean isValidTicket(Ticket ticket) {
    return ticketCarMap.containsKey(ticket);
  }
}
