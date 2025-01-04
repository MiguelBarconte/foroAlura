package forum.api.foro.infra.errors;

public class ValidacionException extends RuntimeException {
    public ValidacionException(String msj) {
        super(msj);
    }
}
