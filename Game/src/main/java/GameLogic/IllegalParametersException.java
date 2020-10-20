package GameLogic;

public class IllegalParametersException extends RuntimeException {
    public IllegalParametersException(String message){
        System.err.println(message);
    }
}
