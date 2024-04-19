package bkendfinalproject.finalpj.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(long id) {
        super(id + " non trovato!");
    }
}
