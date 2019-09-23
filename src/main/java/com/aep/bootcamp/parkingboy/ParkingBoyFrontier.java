package com.aep.bootcamp.parkingboy;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.Ticket;

public interface ParkingBoyFrontier {

  Ticket park(Car car);

  Car pick(Ticket ticket);
}
