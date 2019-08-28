package parkinglot;

import org.junit.Assert;
import org.junit.Test;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;

public class SmartParkingBoyTest {

  private ParkingLot buildFullParkingLot() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());
    return parkingLot;
  }

  @Test
  public void should_get_ticket_of_first_parking_lot_when_park_given_parking_boy_have_2_parking_lots_and_first_have_more_available_lots() {
    ParkingLot first = new ParkingLot(2);
    ParkingLot second = new ParkingLot(1);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(first, second);
    Car car = new Car();

    Ticket result = smartParkingBoy.park(car);

    Assert.assertNotNull(result);
    Assert.assertNotNull(first.getCar(result));
  }

  @Test
  public void should_get_ticket_of_second_parking_lot_when_park_given_parking_boy_have_2_parking_lots_and_first_have_no_enough_lots_second_have_more_available_lots() {
    ParkingLot first = new ParkingLot(1);
    ParkingLot second = new ParkingLot(2);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(first, second);
    Car car = new Car();

    Ticket result = smartParkingBoy.park(car);

    Assert.assertNotNull(result);
    Assert.assertNotNull(second.getCar(result));
  }

  @Test(expected = NoAvailableLotException.class)
  public void should_receive_no_empty_lot_error_when_park_given_parking_boy_have_2_parking_lots_which_are_all_full() {
    ParkingLot first = buildFullParkingLot();
    ParkingLot second = buildFullParkingLot();
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(first, second);
    Car car = new Car();

    smartParkingBoy.park(car);
  }

  @Test
  public void should_get_car_of_the_ticket_when_get_car_given_ticket_is_valid_in_parking_boy() {
    ParkingLot first = new ParkingLot(1);
    ParkingLot second = new ParkingLot(1);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(first, second);
    Car car = new Car();
    Ticket ticket = smartParkingBoy.park(car);

    Car result = smartParkingBoy.getCar(ticket);

    Assert.assertEquals(car, result);
  }

  @Test(expected = InvalidTicketException.class)
  public void should_receive_invalid_ticket_error_when_get_car_given_ticket_is_invalid_in_parking_boy() {
    ParkingLot first = new ParkingLot(1);
    ParkingLot second = new ParkingLot(1);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(first, second);
    Car car = new Car();
    smartParkingBoy.park(car);

    smartParkingBoy.getCar(new Ticket());
  }
}
