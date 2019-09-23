package com.aep.bootcamp.parkingboy;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.InvalidTicketException;
import com.aep.bootcamp.NoAvailableLotsException;
import com.aep.bootcamp.Ticket;
import com.aep.bootcamp.parkinglot.ParkingLotSupport;
import java.util.Arrays;
import java.util.List;

public class GraduateParkingBoy implements ParkingBoyFrontier, ParkingBoySupport {

  private List<ParkingLotSupport> parkingLots;

  public GraduateParkingBoy(ParkingLotSupport... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  @Override
  public Ticket park(Car car) {
    return parkingLots.stream()
        .filter(ParkingLotSupport::hasAvailableLots)
        .findFirst()
        .orElseThrow(NoAvailableLotsException::new)
        .park(car);
  }

  @Override
  public Car pick(Ticket ticket) {
    return parkingLots.stream()
        .filter(parkingLot -> parkingLot.isValidTicket(ticket))
        .findFirst()
        .orElseThrow(InvalidTicketException::new)
        .pick(ticket);
  }

  @Override
  public boolean hasAvailableLots() {
    return parkingLots.stream().anyMatch(ParkingLotSupport::hasAvailableLots);
  }

  @Override
  public boolean isValidTicket(Ticket ticket) {
    return parkingLots.stream().anyMatch(parkingLot -> parkingLot.isValidTicket(ticket));
  }
}
