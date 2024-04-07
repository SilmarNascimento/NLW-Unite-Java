package rocketseat.com.passin.execption;

public class AlreadyRegisteredException extends RuntimeException{

  public AlreadyRegisteredException(String message) {
    super(message);
  }
}
