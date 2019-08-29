package parkinglot.factory;

import org.junit.Assert;
import org.junit.Test;
import parkinglot.GraduateParkingBoy;
import parkinglot.ParkingBoy;
import parkinglot.ParkingManager;
import parkinglot.SmartParkingBoy;
import parkinglot.factory.ParkingBoyBuilder;

public class ParkingBoyBuilderTest {

  @Test
  public void should_build_graduate_parking_boy() {
    ParkingBoy parkingBoy = ParkingBoyBuilder
        .graduate()
        .parkingLot(1)
        .build();

    Assert.assertTrue(parkingBoy instanceof GraduateParkingBoy);
  }

  @Test
  public void should_build_smart_parking_boy() {
    ParkingBoy parkingBoy = ParkingBoyBuilder
        .smart()
        .parkingLot(1)
        .build();

    Assert.assertTrue(parkingBoy instanceof SmartParkingBoy);
  }

  @Test
  public void should_build_parking_manager() {
    ParkingBoy parkingBoy = ParkingBoyBuilder
        .manager()
        .parkingLot(1)
        .parkingBoy(ParkingBoyBuilder.graduate().parkingLot(1))
        .parkingLot(1)
        .parkingBoy(ParkingBoyBuilder.smart().parkingLot(1))
        .build();

    Assert.assertTrue(parkingBoy instanceof ParkingManager);
  }
}
