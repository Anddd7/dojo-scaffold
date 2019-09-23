import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

  private int capacity;
  private Map<Ticket, Car> lots;

  public ParkingLot(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Lots cannot be negative integer");
    }
    this.capacity = capacity;
    this.lots = new HashMap<>(capacity);
  }

  public Ticket park(Car car) {
    if (!hasAvailableLots()) {
      throw new NoAvailableLotsException();
    }

    Ticket ticket = new Ticket();
    lots.put(ticket, car);
    return ticket;
  }

  boolean hasAvailableLots() {
    return lots.size() < capacity;
  }

  public Car pick(Ticket ticket) {
    if (!isValidTicket(ticket)) {
      throw new InvalidTicketException();
    }

    return lots.get(ticket);
  }

  boolean isValidTicket(Ticket ticket) {
    return lots.containsKey(ticket);
  }
}
