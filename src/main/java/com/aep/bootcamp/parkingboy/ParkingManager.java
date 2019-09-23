package com.aep.bootcamp.parkingboy;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.InvalidTicketException;
import com.aep.bootcamp.NoAvailableLotsException;
import com.aep.bootcamp.Ticket;
import java.util.Arrays;
import java.util.List;

public class ParkingManager implements ParkingBoyFrontier {

  private List<ParkingBoySupport> parkingBoys;

  public ParkingManager(ParkingBoySupport... parkingBoys) {
    this.parkingBoys = Arrays.asList(parkingBoys);
  }

  @Override
  public Ticket park(Car car) {
    return parkingBoys.stream()
        .filter(ParkingBoySupport::hasAvailableLots)
        .findFirst()
        .orElseThrow(NoAvailableLotsException::new)
        .park(car);
  }

  @Override
  public Car pick(Ticket ticket) {
    return parkingBoys.stream()
        .filter(parkingBoy -> parkingBoy.isValidTicket(ticket))
        .findFirst()
        .orElseThrow(InvalidTicketException::new)
        .pick(ticket);
  }
}
