package sen.saloum.Ramli.exceptions;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String message){
        super(message);
    }
}
