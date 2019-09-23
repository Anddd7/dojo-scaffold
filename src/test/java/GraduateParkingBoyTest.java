import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GraduateParkingBoyTest {

  @Test
  void should_return_ticket_of_first_parking_lots_when_park_given_parking_boy_manages_2_parking_lot_with_available_lots() {
    ParkingLot first = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
        first,
        new ParkingLot(1)
    );
    Car car = new Car();

    Ticket ticket = graduateParkingBoy.park(car);

    assertThat(ticket).isNotNull();
    assertThat(first.pick(ticket)).isEqualTo(car);
  }

  @Test
  void should_return_ticket_of_second_parking_lots_when_park_given_only_second_parking_lot_has_available_lots_which_is_managed_by_parking_boy() {
    ParkingLot second = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
        new ParkingLot(0),
        second
    );
    Car car = new Car();

    Ticket ticket = graduateParkingBoy.park(car);

    assertThat(ticket).isNotNull();
    assertThat(second.pick(ticket)).isEqualTo(car);
  }

  @Test
  void should_return_no_available_lots_error_when_park_given_parking_boy_manages_2_parking_lot_without_available_lots() {
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(
        new ParkingLot(0),
        new ParkingLot(0)
    );

    assertThrows(NoAvailableLotsException.class, () -> graduateParkingBoy.park(new Car()));
  }
}
