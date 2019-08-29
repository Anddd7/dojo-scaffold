package parkinglot;

import static parkinglot.ParkingLotTest.FullParkingLotFixture;

import org.junit.Assert;
import org.junit.Test;
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

    Assert.assertNotNull(result);
    Assert.assertNotNull(first.getCar(result));
  }

  @Test
  public void should_get_ticket_of_second_parking_lot_when_park_given_parking_boy_have_2_parking_lots_and_first_have_no_enough_lots_second_have_available_lots() {
    ParkingLot first = FullParkingLotFixture;
    ParkingLot second = new ParkingLot(1);
    ParkingBoy parkingBoy = ParkingBoyBuilder.graduate().parkingLot(first).parkingLot(second).build();
    Car car = new Car();

    Ticket result = parkingBoy.park(car);

    Assert.assertNotNull(result);
    Assert.assertNotNull(second.getCar(result));
  }

  @Test(expected = NoAvailableLotException.class)
  public void should_receive_no_empty_lot_error_when_park_given_parking_boy_have_2_parking_lots_which_are_all_full() {
    ParkingLot first = FullParkingLotFixture;
    ParkingLot second = FullParkingLotFixture;
    ParkingBoy parkingBoy = ParkingBoyBuilder.graduate().parkingLot(first).parkingLot(second).build();
    Car car = new Car();

    parkingBoy.park(car);
  }

  @Test
  public void should_get_car_of_the_ticket_when_get_car_given_ticket_is_valid_in_parking_boy() {
    ParkingBoy parkingBoy = ParkingBoyBuilder.graduate().parkingLot(1).parkingLot(1).build();
    Car car = new Car();
    Ticket ticket = parkingBoy.park(car);

    Car result = parkingBoy.getCar(ticket);

    Assert.assertEquals(car, result);
  }

  @Test(expected = InvalidTicketException.class)
  public void should_receive_invalid_ticket_error_when_get_car_given_ticket_is_invalid_in_parking_boy() {
    ParkingBoy parkingBoy = ParkingBoyBuilder.graduate().parkingLot(1).parkingLot(1).build();
    Car car = new Car();
    parkingBoy.park(car);

    parkingBoy.getCar(new Ticket());
  }
}
