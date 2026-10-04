package mx.edu.utez.P5exercises.exception.custom;

public class CustomBadRequest extends RuntimeException {
    public CustomBadRequest (String mensaje){
        super(mensaje);
    }
}