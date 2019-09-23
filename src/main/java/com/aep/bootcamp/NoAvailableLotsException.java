package com.aep.bootcamp;

public class NoAvailableLotsException extends RuntimeException{

  public NoAvailableLotsException() {
    super("Cannot park, no available lots");
  }
}
