package parkinglot;

import org.junit.Assert;
import org.junit.Test;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;

public class GraduateParkingBoyTest {

  private ParkingLot buildFullParkingLot() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());
    return parkingLot;
  }

  @Test
  public void should_get_ticket_of_first_parking_lot_when_park_given_parking_boy_have_2_parking_lots_and_first_have_available_lots() {
    ParkingLot first = new ParkingLot(1);
    ParkingLot second = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(first, second);
    Car car = new Car();

    Ticket result = graduateParkingBoy.park(car);

    Assert.assertNotNull(result);
    Assert.assertNotNull(first.getCar(result));
  }

  @Test
  public void should_get_ticket_of_second_parking_lot_when_park_given_parking_boy_have_2_parking_lots_and_first_have_no_enough_lots_second_have_available_lots() {
    ParkingLot first = buildFullParkingLot();
    ParkingLot second = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(first, second);
    Car car = new Car();

    Ticket result = graduateParkingBoy.park(car);

    Assert.assertNotNull(result);
    Assert.assertNotNull(second.getCar(result));
  }

  @Test(expected = NoAvailableLotException.class)
  public void should_receive_no_empty_lot_error_when_park_given_parking_boy_have_2_parking_lots_which_are_all_full() {
    ParkingLot first = buildFullParkingLot();
    ParkingLot second = buildFullParkingLot();
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(first, second);
    Car car = new Car();

    graduateParkingBoy.park(car);
  }

  @Test
  public void should_get_car_of_the_ticket_when_get_car_given_ticket_is_valid_in_parking_boy() {
    ParkingLot first = new ParkingLot(1);
    ParkingLot second = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(first, second);
    Car car = new Car();
    Ticket ticket = graduateParkingBoy.park(car);

    Car result = graduateParkingBoy.getCar(ticket);

    Assert.assertEquals(car, result);
  }

  @Test(expected = InvalidTicketException.class)
  public void should_receive_invalid_ticket_error_when_get_car_given_ticket_is_invalid_in_parking_boy() {
    ParkingLot first = new ParkingLot(1);
    ParkingLot second = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(first, second);
    Car car = new Car();
    graduateParkingBoy.park(car);

    graduateParkingBoy.getCar(new Ticket());
  }
}
