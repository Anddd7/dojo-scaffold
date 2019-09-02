package parkinglot.factory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import parkinglot.GraduateParkingBoy;
import parkinglot.ParkingBoy;
import parkinglot.ParkingManager;
import parkinglot.SmartParkingBoy;

public class ParkingBoyBuilderTest {

  @Test
  public void should_build_graduate_parking_boy() {
    ParkingBoy parkingBoy = ParkingBoyBuilder
        .graduate()
        .parkingLot(1)
        .build();

    assertThat(parkingBoy instanceof GraduateParkingBoy).isTrue();
  }

  @Test
  public void should_build_smart_parking_boy() {
    ParkingBoy parkingBoy = ParkingBoyBuilder
        .smart()
        .parkingLot(1)
        .build();

    assertThat(parkingBoy instanceof SmartParkingBoy).isTrue();
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

    assertThat(parkingBoy instanceof ParkingManager).isTrue();
  }
}
