package com.aep.bootcamp.parkinglot;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.InvalidTicketException;
import com.aep.bootcamp.NoAvailableLotsException;
import com.aep.bootcamp.Ticket;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements ParkingLotFrontier, ParkingLotSupport {

  private int capacity;
  private Map<Ticket, Car> lots;

  public ParkingLot(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Lots cannot be negative integer");
    }
    this.capacity = capacity;
    this.lots = new HashMap<>(capacity);
  }

  @Override
  public Ticket park(Car car) {
    if (!hasAvailableLots()) {
      throw new NoAvailableLotsException();
    }

    Ticket ticket = new Ticket();
    lots.put(ticket, car);
    return ticket;
  }

  @Override
  public Car pick(Ticket ticket) {
    if (!isValidTicket(ticket)) {
      throw new InvalidTicketException();
    }

    return lots.get(ticket);
  }

  @Override
  public int getAvailableLots() {
    return capacity - lots.size();
  }

  @Override
  public boolean hasAvailableLots() {
    return getAvailableLots() > 0;
  }

  @Override
  public boolean isValidTicket(Ticket ticket) {
    return lots.containsKey(ticket);
  }
}
