package co.com.sofka.tickets.infra.generic;

public class DeserializeException extends RuntimeException {
    public DeserializeException(Throwable cause) {
        super(cause);
    }
}