package parkinglot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;
import parkinglot.resources.Car;
import parkinglot.resources.Ticket;

public class ParkingLotTest {

  @Test
  public void should_get_parking_lot_when_create_parking_lot_given_a_valid_capacity() {
    ParkingLot result = new ParkingLot(1);

    assertThat(result).isNotNull();
  }

  @Test
  public void should_receive_illegal_argument_error_when_create_parking_lot_given_an_invalid_capacity() {
    assertThrows(IllegalArgumentException.class, () -> new ParkingLot(-1));
  }

  @Test
  public void should_get_ticket_of_the_car_when_park_given_parking_lot_have_enough_lots() {
    ParkingLot parkingLot = new ParkingLot(1);
    Car car = new Car();

    Ticket result = parkingLot.park(car);

    assertThat(result).isNotNull();
  }

  @Test
  public void should_get_ticket_of_the_car_when_park_given_parking_lot_with_existing_cars_have_enough_lots() {
    ParkingLot parkingLot = new ParkingLot(2);
    parkingLot.park(new Car());
    Car car = new Car();

    Ticket result = parkingLot.park(car);

    assertThat(result).isNotNull();
  }

  @Test
  public void should_receive_no_empty_lot_error_when_park_given_parking_lot_have_no_available_lots() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());
    Car car = new Car();

    assertThrows(NoAvailableLotException.class, () -> parkingLot.park(car));
  }

  @Test
  public void should_get_car_of_the_ticket_when_get_car_given_ticket_is_valid_in_parking_lot() {
    ParkingLot parkingLot = new ParkingLot(1);
    Car car = new Car();
    Ticket ticket = parkingLot.park(car);

    Car result = parkingLot.getCar(ticket);

    assertThat(car).isEqualTo(result);
  }

  @Test
  public void should_receive_invalid_ticket_error_when_get_car_given_ticket_is_invalid_in_parking_lot() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());

    assertThrows(InvalidTicketException.class, () -> parkingLot.getCar(new Ticket()));
  }

  public static ParkingLot FullParkingLotFixture = getFullParkingLotFixture();

  private static ParkingLot getFullParkingLotFixture() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());
    return parkingLot;
  }
}
