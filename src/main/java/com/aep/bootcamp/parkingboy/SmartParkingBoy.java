package com.aep.bootcamp.parkingboy;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.InvalidTicketException;
import com.aep.bootcamp.NoAvailableLotsException;
import com.aep.bootcamp.Ticket;
import com.aep.bootcamp.parkinglot.ParkingLotSupport;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy implements ParkingBoy {

  private List<ParkingLotSupport> parkingLots;

  public SmartParkingBoy(ParkingLotSupport... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  @Override
  public Ticket park(Car car) {
    return parkingLots.stream()
        .max(Comparator.comparingInt(ParkingLotSupport::getAvailableLots))
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
}

