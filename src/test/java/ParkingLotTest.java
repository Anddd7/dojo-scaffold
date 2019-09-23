import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {

  @Test
  void should_return_ticket_when_park_given_parking_lot_have_available_lots() {
    Ticket result = new ParkingLot(1).park(new Car());

    Assertions.assertThat(result).isNotNull();
  }
}
