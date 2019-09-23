package com.aep.bootcamp.parkingboy.impl;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.InvalidTicketException;
import com.aep.bootcamp.NoAvailableLotsException;
import com.aep.bootcamp.Ticket;
import com.aep.bootcamp.parkingboy.ParkingBoyFrontier;
import com.aep.bootcamp.parkingboy.ParkingBoySupport;
import com.aep.bootcamp.parkinglot.ParkingLotSupport;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GraduateParkingBoy implements ParkingBoyFrontier, ParkingBoySupport {

  private List<ParkingLotSupport> parkingLots;

  public GraduateParkingBoy(ParkingLotSupport... parkingLots) {
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
  public Optional<Ticket> tryPark(Car car) {
    return getParkingLots().stream()
        .filter(ParkingLotSupport::hasAvailableLots)
        .findFirst()
        .map(parkingLot -> parkingLot.park(car));
  }

  @Override
  public Car pick(Ticket ticket) {
    return tryPick(ticket).orElseThrow(InvalidTicketException::new);
  }
}
