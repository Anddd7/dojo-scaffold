package com.aep.bootcamp.parkingboy.impl;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.InvalidTicketException;
import com.aep.bootcamp.NoAvailableLotsException;
import com.aep.bootcamp.Ticket;
import com.aep.bootcamp.parkingboy.ParkingBoyFrontier;
import com.aep.bootcamp.parkingboy.ParkingBoySupport;
import com.aep.bootcamp.parkingboy.SmartParkingBoySupport;
import com.aep.bootcamp.parkinglot.ParkingLotSupport;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ParkingManager implements ParkingBoyFrontier, SmartParkingBoySupport {

  private List<ParkingLotSupport> parkingLots;
  private List<ParkingBoySupport> parkingBoys;

  public ParkingManager(List<ParkingLotSupport> parkingLots, ParkingBoySupport... parkingBoys) {
    this.parkingLots = parkingLots;
    this.parkingBoys = Arrays.asList(parkingBoys);
  }

  @Override
  public List<ParkingLotSupport> getParkingLots() {
    return parkingLots;
  }

  @Override
  public Ticket park(Car car) {
    for (ParkingBoySupport parkingBoy : parkingBoys) {
      Optional<Ticket> optionalTicket = parkingBoy.tryPark(car);
      if (optionalTicket.isPresent()) {
        return optionalTicket.get();
      }
    }

    return tryPark(car).orElseThrow(NoAvailableLotsException::new);
  }

  @Override
  public Car pick(Ticket ticket) {
    for (ParkingBoySupport parkingBoy : parkingBoys) {
      Optional<Car> optionalCar = parkingBoy.tryPick(ticket);
      if (optionalCar.isPresent()) {
        return optionalCar.get();
      }
    }

    return tryPick(ticket).orElseThrow(InvalidTicketException::new);
  }
}
