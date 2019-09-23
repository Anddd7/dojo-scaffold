import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParkingLotTest {

  @Test
  void should_throw_error_when_build_parking_lot_given_lots_is_not_positive_integer() {
    assertThrows(IllegalArgumentException.class, () ->  new ParkingLot(-1));
  }

  @Test
  void should_return_ticket_when_park_given_parking_lot_have_available_lots() {
    ParkingLot parkingLot = new ParkingLot(1);
    Ticket result = parkingLot.park(new Car());

    assertThat(result).isNotNull();
  }

  @Test
  void should_return_ticket_when_park_given_parking_lot_have_parking_car_and_available_lots() {
    ParkingLot parkingLot = new ParkingLot(2);
    parkingLot.park(new Car());
    Ticket result = parkingLot.park(new Car());

    assertThat(result).isNotNull();
  }

  @Test
  void should_return_no_available_lots_error_when_park_given_parking_lot_have_no_available_lots() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());

    assertThrows(NoAvailableLotsException.class, () -> parkingLot.park(new Car()));
  }

  @Test
  void should_return_the_car_when_pick_given_a_valid_ticket_of_parking_lot_which_parked_the_car() {
    ParkingLot parkingLot = new ParkingLot(1);
    Car car = new Car();
    Ticket ticket = parkingLot.park(car);

    Car result = parkingLot.pick(ticket);

    assertThat(result).isEqualTo(car);
  }

  @Test
  void should_return_invalid_ticket_error_when_pick_given_an_invalid_ticket_of_parking_lot() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());

    assertThrows(InvalidTicketException.class, () -> parkingLot.pick(new Ticket()));
  }
}
