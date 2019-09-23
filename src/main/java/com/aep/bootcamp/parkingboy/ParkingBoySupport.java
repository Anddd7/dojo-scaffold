package com.aep.bootcamp.parkingboy;

import com.aep.bootcamp.Ticket;

public interface ParkingBoySupport extends ParkingBoyFrontier {

  boolean hasAvailableLots();

  boolean isValidTicket(Ticket ticket);
}
