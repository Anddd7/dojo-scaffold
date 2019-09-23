package com.aep.bootcamp.parkingboy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.aep.bootcamp.Car;
import com.aep.bootcamp.InvalidTicketException;
import com.aep.bootcamp.NoAvailableLotsException;
import com.aep.bootcamp.Ticket;
import com.aep.bootcamp.parkinglot.ParkingLot;
import org.junit.jupiter.api.Test;

public class ParkingManagerTest {

  @Test
  void should_return_ticket_of_graduate_when_park_given_parking_manager_manages_2_parking_boys_with_available_lots() {
    ParkingBoySupport graduate = new GraduateParkingBoy(new ParkingLot(1));
    ParkingBoySupport smart = new SmartParkingBoy(new ParkingLot(1));
    ParkingBoyFrontier parkingManager = new ParkingManager(
        graduate,
        smart
    );
    Car car = new Car();

    Ticket ticket = parkingManager.park(car);

    assertThat(ticket).isNotNull();
    assertThat(graduate.pick(ticket)).isEqualTo(car);
  }

  @Test
  void should_return_ticket_of_smart_when_park_given_only_smart_parking_boy_has_available_lots_which_is_managed_by_parking_manager() {
    ParkingBoySupport graduate = new GraduateParkingBoy(new ParkingLot(0));
    ParkingBoySupport smart = new SmartParkingBoy(new ParkingLot(1));
    ParkingBoyFrontier parkingManager = new ParkingManager(
        graduate,
        smart
    );
    Car car = new Car();

    Ticket ticket = parkingManager.park(car);

    assertThat(ticket).isNotNull();
    assertThat(smart.pick(ticket)).isEqualTo(car);
  }

  @Test
  void should_return_no_available_lots_error_when_park_given_parking_manager_manages_2_parking_boy_without_available_lots() {
    ParkingBoySupport graduate = new GraduateParkingBoy(new ParkingLot(0));
    ParkingBoySupport smart = new SmartParkingBoy(new ParkingLot(0));
    ParkingBoyFrontier parkingManager = new ParkingManager(
        graduate,
        smart
    );

    assertThrows(NoAvailableLotsException.class, () -> parkingManager.park(new Car()));
  }

  @Test
  void should_return_the_car_when_pick_given_a_valid_ticket_and_car_is_parked_in_parking_manager() {
    ParkingBoySupport graduate = new GraduateParkingBoy(new ParkingLot(1));
    ParkingBoySupport smart = new SmartParkingBoy(new ParkingLot(1));
    ParkingBoyFrontier parkingManager = new ParkingManager(
        graduate,
        smart
    );
    Car car = new Car();
    Ticket ticket = parkingManager.park(car);

    Car result = parkingManager.pick(ticket);

    assertThat(ticket).isNotNull();
    assertThat(result).isEqualTo(car);
  }

  @Test
  void should_return_invalid_ticket_error_when_pick_given_an_invalid_ticket() {
    ParkingBoySupport graduate = new GraduateParkingBoy(new ParkingLot(1));
    ParkingBoySupport smart = new SmartParkingBoy(new ParkingLot(1));
    ParkingBoyFrontier parkingManager = new ParkingManager(
        graduate,
        smart
    );
    parkingManager.park(new Car());

    assertThrows(InvalidTicketException.class, () -> parkingManager.pick(new Ticket()));
  }
}
