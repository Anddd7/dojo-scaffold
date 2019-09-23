package com.aep.bootcamp.parkinglot;

import com.aep.bootcamp.Ticket;

public interface ParkingLotSupport extends ParkingLotFrontier {

  int getAvailableLots();

  boolean hasAvailableLots();

  boolean isValidTicket(Ticket ticket);
}
