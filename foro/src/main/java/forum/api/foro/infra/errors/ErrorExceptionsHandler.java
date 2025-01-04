package forum.api.foro.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorExceptionsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //para poder enviar informacion sobre el error se hace lo siguiete:
    public ResponseEntity error400(MethodArgumentNotValidException e){

        var errores = e.getFieldErrors().stream()
                .map(datosErrorValidacion::new)
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionException.class)
    //para poder enviar informacion sobre el error se hace lo siguiete:
    public ResponseEntity errorValidacion(ValidacionException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record datosErrorValidacion(String campo, String error){
        public datosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
