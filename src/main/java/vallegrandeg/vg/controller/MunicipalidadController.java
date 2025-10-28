package vallegrandeg.vg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vallegrandeg.vg.model.Municipalidad;
import vallegrandeg.vg.service.MunicipalidadService;
import vallegrandeg.vg.dto.ValidationResponse;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:5173", 
    allowedHeaders = "*", 
    allowCredentials = "true",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
               RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api/municipalidades")
@Validated
public class MunicipalidadController {

    private final MunicipalidadService municipalidadService;

    public MunicipalidadController(MunicipalidadService municipalidadService) {
        this.municipalidadService = municipalidadService;
    }

    @GetMapping
    public Flux<Municipalidad> getAll() {
        return municipalidadService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Municipalidad> getById(@PathVariable UUID id) {
        return municipalidadService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Municipalidad> create(@RequestBody Municipalidad municipalidad) {
        return municipalidadService.create(municipalidad);
    }

    @PatchMapping("/{id}")
    public Mono<Municipalidad> patch(@PathVariable UUID id, @RequestBody Municipalidad municipalidad) {
        municipalidad.setId(id);
        return municipalidadService.update(id, municipalidad);
    }

    @PutMapping("/{id}")
    public Mono<Municipalidad> update(@PathVariable UUID id, @RequestBody Municipalidad municipalidad) {
        municipalidad.setId(id);
        return municipalidadService.update(id, municipalidad);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable UUID id) {
        return municipalidadService.delete(id);
    }

    @GetMapping("/validate/ruc/{ruc}")
    public Mono<ValidationResponse> validateRuc(
            @PathVariable String ruc,
            @RequestParam(required = false) UUID excludeId) {
        if (ruc == null || ruc.trim().isEmpty()) {
            return Mono.just(new ValidationResponse(false, "ruc", "El RUC es requerido"));
        }
        if (ruc.length() != 11) {
            return Mono.just(new ValidationResponse(false, "ruc", "El RUC debe tener 11 dígitos"));
        }
        if (!ruc.matches("\\d+")) {
            return Mono.just(new ValidationResponse(false, "ruc", "El RUC solo debe contener números"));
        }
        
        return municipalidadService.findAll()
            .any(m -> m.getRuc() != null && 
                     m.getRuc().equals(ruc) && 
                     (excludeId == null || !m.getId().equals(excludeId)))
            .map(exists -> exists 
                ? new ValidationResponse(false, "ruc", "Este RUC ya está registrado")
                : new ValidationResponse(true, "ruc", "RUC válido"));
    }

    @GetMapping("/validate/ubigeo/{ubigeo}")
    public Mono<ValidationResponse> validateUbigeo(
            @PathVariable String ubigeo,
            @RequestParam(required = false) UUID excludeId) {
        if (ubigeo == null || ubigeo.trim().isEmpty()) {
            return Mono.just(new ValidationResponse(false, "ubigeo", "El ubigeo es requerido"));
        }
        if (ubigeo.length() != 6) {
            return Mono.just(new ValidationResponse(false, "ubigeo", "El ubigeo debe tener 6 dígitos"));
        }
        if (!ubigeo.matches("\\d+")) {
            return Mono.just(new ValidationResponse(false, "ubigeo", "El ubigeo solo debe contener números"));
        }
        
        return municipalidadService.findAll()
            .any(m -> m.getUbigeo() != null && 
                     m.getUbigeo().equals(ubigeo) && 
                     (excludeId == null || !m.getId().equals(excludeId)))
            .map(exists -> exists 
                ? new ValidationResponse(false, "ubigeo", "Este ubigeo ya está registrado")
                : new ValidationResponse(true, "ubigeo", "Ubigeo válido"));
    }
}