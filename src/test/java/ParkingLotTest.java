import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParkingLotTest {

  @Test
  void should_return_ticket_when_park_given_parking_lot_have_available_lots() {
    ParkingLot parkingLot = new ParkingLot(1);
    Ticket result = parkingLot.park(new Car());

    assertThat(result).isNotNull();
  }

  @Test
  void should_return_no_enough_space_error_when_park_given_parking_lot_have_no_available_lots() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());

    assertThrows(NoAvailableLotsException.class, () -> parkingLot.park(new Car()));
  }

}
