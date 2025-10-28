package vallegrandeg.vg.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MunicipalidadNotFoundException extends RuntimeException {
    public MunicipalidadNotFoundException(UUID id) {
        super("No se encontró la municipalidad con ID: " + id);
    }
}