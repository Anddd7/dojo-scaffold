package com.aep.bootcamp.parkingboy;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.Ticket;
import com.aep.bootcamp.parkinglot.ParkingLotSupport;
import java.util.List;
import java.util.Optional;

public interface ParkingBoySupport extends ParkingBoyFrontier {

  List<ParkingLotSupport> getParkingLots();

  Optional<Ticket> tryPark(Car car);

  default Optional<Car> tryPick(Ticket ticket) {
    return getParkingLots().stream()
        .filter(parkingLot -> parkingLot.isValidTicket(ticket))
        .findFirst()
        .map(parkingLot -> parkingLot.pick(ticket));
  }
}
