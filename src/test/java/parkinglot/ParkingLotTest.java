package parkinglot;

import org.junit.Assert;
import org.junit.Test;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;
import parkinglot.resources.Car;
import parkinglot.resources.Ticket;

public class ParkingLotTest {

  @Test
  public void should_get_parking_lot_when_create_parking_lot_given_a_valid_capacity() {
    ParkingLot result = new ParkingLot(1);

    Assert.assertNotNull(result);
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_receive_illegal_argument_error_when_create_parking_lot_given_an_invalid_capacity() {
    new ParkingLot(-1);
  }

  @Test
  public void should_get_ticket_of_the_car_when_park_given_parking_lot_have_enough_lots() {
    ParkingLot parkingLot = new ParkingLot(1);
    Car car = new Car();

    Ticket result = parkingLot.park(car);

    Assert.assertNotNull(result);
  }

  @Test
  public void should_get_ticket_of_the_car_when_park_given_parking_lot_with_existing_cars_have_enough_lots() {
    ParkingLot parkingLot = new ParkingLot(2);
    parkingLot.park(new Car());
    Car car = new Car();

    Ticket result = parkingLot.park(car);

    Assert.assertNotNull(result);
  }

  @Test(expected = NoAvailableLotException.class)
  public void should_receive_no_empty_lot_error_when_park_given_parking_lot_have_no_available_lots() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());
    Car car = new Car();

    parkingLot.park(car);
  }

  @Test
  public void should_get_car_of_the_ticket_when_get_car_given_ticket_is_valid_in_parking_lot() {
    ParkingLot parkingLot = new ParkingLot(1);
    Car car = new Car();
    Ticket ticket = parkingLot.park(car);

    Car result = parkingLot.getCar(ticket);

    Assert.assertEquals(car, result);
  }

  @Test(expected = InvalidTicketException.class)
  public void should_receive_invalid_ticket_error_when_get_car_given_ticket_is_invalid_in_parking_lot() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());

    parkingLot.getCar(new Ticket());
  }

  public static ParkingLot FullParkingLotFixture = getFullParkingLotFixture();

  private static ParkingLot getFullParkingLotFixture() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());
    return parkingLot;
  }
}
