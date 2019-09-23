public class InvalidTicketException extends RuntimeException{

  public InvalidTicketException() {
    super("Cannot pick car, ticket is invalid");
  }
}
