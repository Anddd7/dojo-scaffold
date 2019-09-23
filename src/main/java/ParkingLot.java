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
    if (lots.size() >= capacity) {
      throw new NoAvailableLotsException();
    }

    Ticket ticket = new Ticket();
    lots.put(ticket, car);
    return ticket;
  }

  public Car pick(Ticket ticket) {
    if (!lots.containsKey(ticket)) {
      throw new InvalidTicketException();
    }

    return lots.get(ticket);
  }
}
