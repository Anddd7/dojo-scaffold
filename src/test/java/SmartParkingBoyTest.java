import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SmartParkingBoyTest {

  @Test
  void should_return_ticket_of_first_parking_lots_when_park_given_parking_boy_manages_2_parking_lot_with_same_available_lots() {
    ParkingLot first = new ParkingLot(1);
    SmartParkingBoy parkingBoy = new SmartParkingBoy(
        first,
        new ParkingLot(1)
    );
    Car car = new Car();

    Ticket ticket = parkingBoy.park(car);

    assertThat(ticket).isNotNull();
    assertThat(first.pick(ticket)).isEqualTo(car);
  }

  @Test
  void should_return_ticket_of_second_parking_lots_when_park_given_second_parking_lot_has_more_available_lots_which_is_managed_by_parking_boy() {
    ParkingLot second = new ParkingLot(2);
    SmartParkingBoy graduateParkingBoy = new SmartParkingBoy(
        new ParkingLot(1),
        second
    );
    Car car = new Car();

    Ticket ticket = graduateParkingBoy.park(car);

    assertThat(ticket).isNotNull();
    assertThat(second.pick(ticket)).isEqualTo(car);
  }

  @Test
  void should_return_no_available_lots_error_when_park_given_parking_boy_manages_2_parking_lot_without_available_lots() {
    SmartParkingBoy graduateParkingBoy = new SmartParkingBoy(
        new ParkingLot(0),
        new ParkingLot(0)
    );

    assertThrows(NoAvailableLotsException.class, () -> graduateParkingBoy.park(new Car()));
  }

  @Test
  void should_return_the_car_when_pick_given_a_valid_ticket_and_car_is_parked_in_parking_manager() {
    SmartParkingBoy graduateParkingBoy = new SmartParkingBoy(
        new ParkingLot(1),
        new ParkingLot(1)
    );
    Car car = new Car();
    Ticket ticket = graduateParkingBoy.park(car);

    Car result = graduateParkingBoy.pick(ticket);

    assertThat(ticket).isNotNull();
    assertThat(result).isEqualTo(car);
  }

  @Test
  void should_return_invalid_ticket_error_when_pick_given_an_invalid_ticket() {
    SmartParkingBoy graduateParkingBoy = new SmartParkingBoy(
        new ParkingLot(1),
        new ParkingLot(1)
    );
    graduateParkingBoy.park(new Car());

    assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pick(new Ticket()));
  }
}