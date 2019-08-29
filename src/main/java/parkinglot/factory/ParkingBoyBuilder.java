package parkinglot.factory;

import java.util.ArrayList;
import java.util.List;
import parkinglot.GraduateParkingBoy;
import parkinglot.ParkingBoy;
import parkinglot.ParkingLot;
import parkinglot.ParkingManager;
import parkinglot.SmartParkingBoy;

public abstract class ParkingBoyBuilder {

  public static ParkingBoyBuilder graduate() {
    return new GraduateParkingBoyBuilder();
  }

  public static ParkingBoyBuilder smart() {
    return new SmartParkingBoyBuilder();
  }

  public static ParkingManagerBuilder manager() {
    return new ParkingManagerBuilder();
  }

  List<ParkingLot> parkingLots = new ArrayList<>();

  public ParkingBoyBuilder parkingLot(int capacity) {
    parkingLots.add(new ParkingLot(capacity));
    return this;
  }

  public ParkingBoyBuilder parkingLot(ParkingLot parkingLot) {
    parkingLots.add(parkingLot);
    return this;
  }

  abstract public ParkingBoy build();

  static class GraduateParkingBoyBuilder extends ParkingBoyBuilder {

    @Override
    public ParkingBoy build() {
      return new GraduateParkingBoy(parkingLots);
    }
  }

  static class SmartParkingBoyBuilder extends ParkingBoyBuilder {

    @Override
    public ParkingBoy build() {
      return new SmartParkingBoy(parkingLots);
    }
  }

  public static class ParkingManagerBuilder extends ParkingBoyBuilder {

    List<ParkingBoy> parkingBoys = new ArrayList<>();

    @Override
    public ParkingBoy build() {
      return new ParkingManager(parkingBoys, parkingLots);
    }

    @Override
    public ParkingManagerBuilder parkingLot(int capacity) {
      super.parkingLot(capacity);
      return this;
    }

    @Override
    public ParkingManagerBuilder parkingLot(ParkingLot parkingLot) {
      super.parkingLot(parkingLot);
      return this;
    }

    public ParkingManagerBuilder parkingBoy(ParkingBoyBuilder builder) {
      parkingBoys.add(builder.build());
      return this;
    }

    public ParkingManagerBuilder parkingBoy(ParkingBoy parkingBoy) {
      parkingBoys.add(parkingBoy);
      return this;
    }
  }
}
