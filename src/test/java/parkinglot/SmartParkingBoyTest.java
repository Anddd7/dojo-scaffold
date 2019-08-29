package parkinglot;

import static parkinglot.ParkingLotTest.FullParkingLotFixture;

import org.junit.Assert;
import org.junit.Test;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;
import parkinglot.factory.ParkingBoyBuilder;
import parkinglot.resources.Car;
import parkinglot.resources.Ticket;

public class SmartParkingBoyTest {

  @Test
  public void should_get_ticket_of_first_parking_lot_when_park_given_parking_boy_have_2_parking_lots_and_first_have_more_available_lots() {
    ParkingLot first = new ParkingLot(2);
    ParkingLot second = new ParkingLot(1);
    ParkingBoy smartParkingBoy = ParkingBoyBuilder.smart().parkingLot(first).parkingLot(second).build();
    Car car = new Car();

    Ticket result = smartParkingBoy.park(car);

    Assert.assertNotNull(result);
    Assert.assertNotNull(first.getCar(result));
  }

  @Test
  public void should_get_ticket_of_second_parking_lot_when_park_given_parking_boy_have_2_parking_lots_and_first_have_no_enough_lots_second_have_more_available_lots() {
    ParkingLot first = new ParkingLot(1);
    ParkingLot second = new ParkingLot(2);
    ParkingBoy smartParkingBoy = ParkingBoyBuilder.smart().parkingLot(first).parkingLot(second).build();
    Car car = new Car();

    Ticket result = smartParkingBoy.park(car);

    Assert.assertNotNull(result);
    Assert.assertNotNull(second.getCar(result));
  }

  @Test(expected = NoAvailableLotException.class)
  public void should_receive_no_empty_lot_error_when_park_given_parking_boy_have_2_parking_lots_which_are_all_full() {
    ParkingBoy smartParkingBoy = ParkingBoyBuilder.smart()
        .parkingLot(FullParkingLotFixture)
        .parkingLot(FullParkingLotFixture)
        .build();
    Car car = new Car();

    smartParkingBoy.park(car);
  }

  @Test
  public void should_get_car_of_the_ticket_when_get_car_given_ticket_is_valid_in_parking_boy() {
    ParkingBoy smartParkingBoy = ParkingBoyBuilder.smart().parkingLot(1).parkingLot(1).build();
    Car car = new Car();
    Ticket ticket = smartParkingBoy.park(car);

    Car result = smartParkingBoy.getCar(ticket);

    Assert.assertEquals(car, result);
  }

  @Test(expected = InvalidTicketException.class)
  public void should_receive_invalid_ticket_error_when_get_car_given_ticket_is_invalid_in_parking_boy() {
    ParkingBoy smartParkingBoy = ParkingBoyBuilder.smart().parkingLot(1).parkingLot(1).build();
    Car car = new Car();
    smartParkingBoy.park(car);

    smartParkingBoy.getCar(new Ticket());
  }
}
