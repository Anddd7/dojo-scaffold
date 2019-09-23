import org.assertj.core.api.Assertions;
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

    Assertions.assertThat(ticket).isNotNull();
    Assertions.assertThat(first.pick(ticket)).isEqualTo(car);
  }
}
