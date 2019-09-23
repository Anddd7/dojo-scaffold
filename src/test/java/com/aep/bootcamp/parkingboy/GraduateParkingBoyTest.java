package com.aep.bootcamp.parkingboy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.InvalidTicketException;
import com.aep.bootcamp.NoAvailableLotsException;
import com.aep.bootcamp.Ticket;
import com.aep.bootcamp.parkingboy.impl.GraduateParkingBoy;
import com.aep.bootcamp.parkinglot.ParkingLot;
import com.aep.bootcamp.parkinglot.ParkingLotSupport;
import org.junit.jupiter.api.Test;

class GraduateParkingBoyTest {

  @Test
  void should_return_ticket_of_first_parking_lots_when_park_given_parking_boy_manages_2_parking_lot_with_available_lots() {
    ParkingLotSupport first = new ParkingLot(1);
    ParkingBoyFrontier parkingBoy = new GraduateParkingBoy(
        first,
        new ParkingLot(1)
    );
    Car car = new Car();

    Ticket ticket = parkingBoy.park(car);

    assertThat(ticket).isNotNull();
    assertThat(first.pick(ticket)).isEqualTo(car);
  }

  @Test
  void should_return_ticket_of_second_parking_lots_when_park_given_only_second_parking_lot_has_available_lots_which_is_managed_by_parking_boy() {
    ParkingLotSupport second = new ParkingLot(1);
    ParkingBoyFrontier parkingBoy = new GraduateParkingBoy(
        new ParkingLot(0),
        second
    );
    Car car = new Car();

    Ticket ticket = parkingBoy.park(car);

    assertThat(ticket).isNotNull();
    assertThat(second.pick(ticket)).isEqualTo(car);
  }

  @Test
  void should_return_no_available_lots_error_when_park_given_parking_boy_manages_2_parking_lot_without_available_lots() {
    ParkingBoyFrontier parkingBoy = new GraduateParkingBoy(
        new ParkingLot(0),
        new ParkingLot(0)
    );

    assertThrows(NoAvailableLotsException.class, () -> parkingBoy.park(new Car()));
  }

  @Test
  void should_return_the_car_when_pick_given_a_valid_ticket_and_car_is_parked_in_parking_manager() {
    ParkingBoyFrontier parkingBoy = new GraduateParkingBoy(
        new ParkingLot(1),
        new ParkingLot(1)
    );
    Car car = new Car();
    Ticket ticket = parkingBoy.park(car);

    Car result = parkingBoy.pick(ticket);

    assertThat(ticket).isNotNull();
    assertThat(result).isEqualTo(car);
  }

  @Test
  void should_return_invalid_ticket_error_when_pick_given_an_invalid_ticket() {
    ParkingBoyFrontier parkingBoy = new GraduateParkingBoy(
        new ParkingLot(1),
        new ParkingLot(1)
    );
    parkingBoy.park(new Car());

    assertThrows(InvalidTicketException.class, () -> parkingBoy.pick(new Ticket()));
  }
}
