package rocketseat.com.passin.execption;

public class EventIsFullException extends RuntimeException{

  public EventIsFullException() {
    super("Event is Full!");
  }
}
