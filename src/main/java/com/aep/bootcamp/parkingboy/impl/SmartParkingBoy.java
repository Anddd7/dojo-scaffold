package com.aep.bootcamp.parkingboy.impl;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.InvalidTicketException;
import com.aep.bootcamp.NoAvailableLotsException;
import com.aep.bootcamp.Ticket;
import com.aep.bootcamp.parkingboy.ParkingBoyFrontier;
import com.aep.bootcamp.parkingboy.SmartParkingBoySupport;
import com.aep.bootcamp.parkinglot.ParkingLotSupport;
import java.util.Arrays;
import java.util.List;

public class SmartParkingBoy implements ParkingBoyFrontier, SmartParkingBoySupport {

  private List<ParkingLotSupport> parkingLots;

  public SmartParkingBoy(ParkingLotSupport... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  @Override
  public List<ParkingLotSupport> getParkingLots() {
    return parkingLots;
  }

  @Override
  public Ticket park(Car car) {
    return tryPark(car).orElseThrow(NoAvailableLotsException::new);
  }

  @Override
  public Car pick(Ticket ticket) {
    return tryPick(ticket).orElseThrow(InvalidTicketException::new);
  }
}

