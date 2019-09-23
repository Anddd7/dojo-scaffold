package com.aep.bootcamp.parkingboy;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.InvalidTicketException;
import com.aep.bootcamp.NoAvailableLotsException;
import com.aep.bootcamp.Ticket;
import com.aep.bootcamp.parkinglot.ParkingLotSupport;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ParkingManager implements ParkingBoyFrontier {

  private List<ParkingLotSupport> parkingLots;
  private List<ParkingBoySupport> parkingBoys;

  public ParkingManager(List<ParkingLotSupport> parkingLots, ParkingBoySupport... parkingBoys) {
    this.parkingLots = parkingLots;
    this.parkingBoys = Arrays.asList(parkingBoys);
  }

  @Override
  public Ticket park(Car car) {
    Optional<ParkingBoySupport> optionalParkingBoy = parkingBoys.stream()
        .filter(ParkingBoySupport::hasAvailableLots)
        .findFirst();

    if (optionalParkingBoy.isPresent()) {
      return optionalParkingBoy.get().park(car);
    }

    return parkingLots.stream()
        .max(Comparator.comparingInt(ParkingLotSupport::getAvailableLots))
        .orElseThrow(NoAvailableLotsException::new)
        .park(car);
  }

  @Override
  public Car pick(Ticket ticket) {
    Optional<ParkingBoySupport> optionalParkingBoy = parkingBoys.stream()
        .filter(parkingLot -> parkingLot.isValidTicket(ticket))
        .findFirst();

    if (optionalParkingBoy.isPresent()) {
      return optionalParkingBoy.get().pick(ticket);
    }

    return parkingLots.stream()
        .filter(parkingLot -> parkingLot.isValidTicket(ticket))
        .findFirst()
        .orElseThrow(InvalidTicketException::new)
        .pick(ticket);
  }
}
