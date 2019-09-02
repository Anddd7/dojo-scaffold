package parkinglot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static parkinglot.ParkingLotTest.FullParkingLotFixture;

import org.junit.jupiter.api.Test;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;
import parkinglot.factory.ParkingBoyBuilder;
import parkinglot.resources.Car;
import parkinglot.resources.Ticket;

public class GraduateParkingBoyTest {

  @Test
  public void should_get_ticket_of_first_parking_lot_when_park_given_parking_boy_have_2_parking_lots_and_first_have_available_lots() {
    ParkingLot first = new ParkingLot(1);
    ParkingLot second = new ParkingLot(1);
    ParkingBoy parkingBoy = ParkingBoyBuilder.graduate().parkingLot(first).parkingLot(second).build();
    Car car = new Car();

    Ticket result = parkingBoy.park(car);

    assertThat(result).isNotNull();
    assertThat(first.getCar(result)).isNotNull();
  }

  @Test
  public void should_get_ticket_of_second_parking_lot_when_park_given_parking_boy_have_2_parking_lots_and_first_have_no_enough_lots_second_have_available_lots() {
    ParkingLot first = FullParkingLotFixture;
    ParkingLot second = new ParkingLot(1);
    ParkingBoy parkingBoy = ParkingBoyBuilder.graduate().parkingLot(first).parkingLot(second).build();
    Car car = new Car();

    Ticket result = parkingBoy.park(car);

    assertThat(result).isNotNull();
    assertThat(second.getCar(result)).isNotNull();
  }

  @Test
  public void should_receive_no_empty_lot_error_when_park_given_parking_boy_have_2_parking_lots_which_are_all_full() {
    ParkingLot first = FullParkingLotFixture;
    ParkingLot second = FullParkingLotFixture;
    ParkingBoy parkingBoy = ParkingBoyBuilder.graduate().parkingLot(first).parkingLot(second).build();
    Car car = new Car();

    assertThrows(NoAvailableLotException.class, () -> parkingBoy.park(car));
  }

  @Test
  public void should_get_car_of_the_ticket_when_get_car_given_ticket_is_valid_in_parking_boy() {
    ParkingBoy parkingBoy = ParkingBoyBuilder.graduate().parkingLot(1).parkingLot(1).build();
    Car car = new Car();
    Ticket ticket = parkingBoy.park(car);

    Car result = parkingBoy.getCar(ticket);

    assertThat(car).isEqualTo(result);
  }

  @Test
  public void should_receive_invalid_ticket_error_when_get_car_given_ticket_is_invalid_in_parking_boy() {
    ParkingBoy parkingBoy = ParkingBoyBuilder.graduate().parkingLot(1).parkingLot(1).build();
    Car car = new Car();
    parkingBoy.park(car);

    assertThrows(InvalidTicketException.class, () -> parkingBoy.getCar(new Ticket()));
  }
}
