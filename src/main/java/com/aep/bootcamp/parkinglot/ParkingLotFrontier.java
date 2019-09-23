package com.aep.bootcamp.parkinglot;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.Ticket;

public interface ParkingLotFrontier {

  Ticket park(Car car);

  Car pick(Ticket ticket);
}
