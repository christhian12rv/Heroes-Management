package br.com.gubee.interview.core.Exceptions;

public class HeroNotFoundException extends RuntimeException {
    public HeroNotFoundException() {
    }

    public HeroNotFoundException(String message) {
        super(message);
    }

    public HeroNotFoundException(Throwable cause) {
        super(cause);
    }

    public HeroNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
