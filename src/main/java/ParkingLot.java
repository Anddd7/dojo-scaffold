
public class ParkingLot {

  private int capacity;

  public ParkingLot(int capacity) {
    this.capacity = capacity;
  }

  public Ticket park(Car car) {
    if (capacity < 1) {
      throw new NoAvailableLotsException();
    }
    capacity--;
    return new Ticket();
  }
}
