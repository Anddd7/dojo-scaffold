package com.aep.bootcamp.parkingboy;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.Ticket;
import com.aep.bootcamp.parkinglot.ParkingLotSupport;
import java.util.Comparator;
import java.util.Optional;

public interface SmartParkingBoySupport extends ParkingBoySupport {

  @Override
  default Optional<Ticket> tryPark(Car car) {
    return getParkingLots().stream()
        .max(Comparator.comparingInt(ParkingLotSupport::getAvailableLots))
        .map(parkingLot -> parkingLot.park(car));
  }
}
