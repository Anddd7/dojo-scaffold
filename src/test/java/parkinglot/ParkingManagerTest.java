package parkinglot;

import static java.util.Arrays.asList;

import org.junit.Assert;
import org.junit.Test;
import parkinglot.exceptions.InvalidTicketException;
import parkinglot.exceptions.NoAvailableLotException;
import parkinglot.resources.Car;
import parkinglot.resources.Ticket;

public class ParkingManagerTest {

  private ParkingLot buildFullParkingLot() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());
    return parkingLot;
  }

  private ParkingBoy buildFullGraduateParkingBoy() {
    return new GraduateParkingBoy(buildFullParkingLot());
  }


  private ParkingBoy buildFullSmartParkingBoy() {
    return new SmartParkingBoy(buildFullParkingLot());
  }

  @Test
  public void should_get_ticket_of_the_car_when_park_given_parking_manager_have_graduate_and_smart_with_available_lots() {
    ParkingBoy graduate = new GraduateParkingBoy(new ParkingLot(1));
    ParkingBoy smart = new SmartParkingBoy(new ParkingLot(1));
    ParkingBoy parkingManager = new ParkingManager(asList(graduate, smart), new ParkingLot(1));
    Car car = new Car();

    Ticket result = parkingManager.park(car);

    Assert.assertNotNull(result);
  }

  @Test
  public void should_get_ticket_of_the_car_when_park_given_parking_manager_have_graduate_and_smart_and_only_graduate_have_available_lots() {
    ParkingBoy graduate = new GraduateParkingBoy(new ParkingLot(1));
    ParkingBoy smart = buildFullSmartParkingBoy();
    ParkingBoy parkingManager = new ParkingManager(asList(graduate, smart), buildFullParkingLot());
    Car car = new Car();

    Ticket result = parkingManager.park(car);

    Assert.assertNotNull(result);
  }

  @Test
  public void should_get_ticket_of_the_car_when_park_given_parking_manager_have_graduate_and_smart_and_only_smart_have_available_lots() {
    ParkingBoy graduate = buildFullGraduateParkingBoy();
    ParkingBoy smart = new SmartParkingBoy(new ParkingLot(1));
    ParkingBoy parkingManager = new ParkingManager(asList(graduate, smart), buildFullParkingLot());
    Car car = new Car();

    Ticket result = parkingManager.park(car);

    Assert.assertNotNull(result);
  }

  @Test
  public void should_get_ticket_of_the_car_when_park_given_parking_manager_have_graduate_and_smart_and_only_manager_have_available_lots() {
    ParkingBoy graduate = buildFullGraduateParkingBoy();
    ParkingBoy smart = buildFullSmartParkingBoy();
    ParkingBoy parkingManager = new ParkingManager(asList(graduate, smart), new ParkingLot(1));
    Car car = new Car();

    Ticket result = parkingManager.park(car);

    Assert.assertNotNull(result);
  }

  @Test(expected = NoAvailableLotException.class)
  public void should_receive_no_empty_lot_error_when_park_given_parking_manager_and_its_parking_boys_have_no_available_lots() {
    ParkingBoy graduate = buildFullGraduateParkingBoy();
    ParkingBoy smart = buildFullSmartParkingBoy();
    ParkingBoy parkingManager = new ParkingManager(asList(graduate, smart), buildFullParkingLot());
    Car car = new Car();

    parkingManager.park(car);
  }

  @Test
  public void should_get_car_of_the_ticket_when_get_car_given_ticket_is_valid_in_parking_manager() {
    ParkingBoy graduate = new GraduateParkingBoy(new ParkingLot(1));
    ParkingBoy smart = new SmartParkingBoy(new ParkingLot(1));
    ParkingBoy parkingManager = new ParkingManager(asList(graduate, smart), new ParkingLot(1));
    Car car = new Car();
    Ticket ticket = parkingManager.park(car);

    Car result = parkingManager.getCar(ticket);

    Assert.assertEquals(result, car);
  }

  @Test
  public void should_get_car_of_the_ticket_when_get_car_given_ticket_is_valid_in_graduate_parking_boy_which_managed_by_parking_marnager() {
    ParkingBoy graduate = new GraduateParkingBoy(new ParkingLot(1));
    ParkingBoy smart = buildFullSmartParkingBoy();
    ParkingBoy parkingManager = new ParkingManager(asList(graduate, smart), buildFullParkingLot());
    Car car = new Car();
    Ticket ticket = graduate.park(car);

    Car result = parkingManager.getCar(ticket);

    Assert.assertEquals(result, car);
  }

  @Test
  public void should_get_car_of_the_ticket_when_get_car_given_ticket_is_valid_in_smart_parking_boy_which_managed_by_parking_marnager() {
    ParkingBoy graduate = buildFullGraduateParkingBoy();
    ParkingBoy smart = new SmartParkingBoy(new ParkingLot(1));
    ParkingBoy parkingManager = new ParkingManager(asList(graduate, smart), buildFullParkingLot());
    Car car = new Car();
    Ticket ticket = smart.park(car);

    Car result = parkingManager.getCar(ticket);

    Assert.assertEquals(result, car);
  }

  @Test
  public void should_get_car_of_the_ticket_when_get_car_given_ticket_is_valid_in_parking_lot_which_managed_by_parking_marnager() {
    ParkingBoy graduate = buildFullGraduateParkingBoy();
    ParkingBoy smart = buildFullSmartParkingBoy();
    ParkingLot parkingLot = new ParkingLot(1);
    ParkingBoy parkingManager = new ParkingManager(asList(graduate, smart), parkingLot);
    Car car = new Car();
    Ticket ticket = parkingLot.park(car);

    Car result = parkingManager.getCar(ticket);

    Assert.assertEquals(result, car);
  }

  @Test(expected = InvalidTicketException.class)
  public void should_receive_invalid_ticket_error_when_get_car_given_ticket_is_invalid_any_of_parking_boys_or_parking_lots() {
    ParkingBoy graduate = new GraduateParkingBoy(new ParkingLot(1));
    ParkingBoy smart = new SmartParkingBoy(new ParkingLot(1));
    ParkingBoy parkingManager = new ParkingManager(asList(graduate, smart), new ParkingLot(1));
    Car car = new Car();
    parkingManager.park(car);

    parkingManager.getCar(new Ticket());
  }
}
