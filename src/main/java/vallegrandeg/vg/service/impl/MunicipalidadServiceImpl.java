package vallegrandeg.vg.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vallegrandeg.vg.model.Municipalidad;
import vallegrandeg.vg.repository.MunicipalidadRepository;
import vallegrandeg.vg.service.MunicipalidadService;
import vallegrandeg.vg.exception.ValidationException;
import vallegrandeg.vg.exception.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MunicipalidadServiceImpl implements MunicipalidadService {
    
    private static final Logger log = LoggerFactory.getLogger(MunicipalidadServiceImpl.class);
    private final MunicipalidadRepository municipalidadRepository;

    @Override
    public Flux<Municipalidad> findAll() {
        return municipalidadRepository.findAll();
    }

    @Override
    public Mono<Municipalidad> findById(UUID id) {
        return municipalidadRepository.findById(id);
    }

    @Override
    public Mono<Municipalidad> create(Municipalidad municipalidad) {
        log.info("Iniciando creación de municipalidad: {}", municipalidad);
        
        // Validaciones básicas
        if (municipalidad.getNombre() == null || municipalidad.getNombre().trim().isEmpty()) {
            return Mono.error(new ValidationException("El nombre de la municipalidad es requerido"));
        }
        if (municipalidad.getRuc() == null || municipalidad.getRuc().trim().isEmpty()) {
            return Mono.error(new ValidationException("El RUC de la municipalidad es requerido"));
        }
        if (municipalidad.getUbigeo() == null || municipalidad.getUbigeo().trim().isEmpty()) {
            return Mono.error(new ValidationException("El ubigeo de la municipalidad es requerido"));
        }

        // Verificar duplicados
        return municipalidadRepository.findAll()
            .collectList()
            .flatMap(municipalidades -> {
                // Verificar ubigeo duplicado
                boolean ubigeoExists = municipalidades.stream()
                    .anyMatch(m -> m.getUbigeo().equals(municipalidad.getUbigeo()));
                if (ubigeoExists) {
                    return Mono.error(new DuplicateKeyException(
                        "Ya existe una municipalidad con el ubigeo: " + municipalidad.getUbigeo()));
                }

                // Verificar RUC duplicado
                boolean rucExists = municipalidades.stream()
                    .anyMatch(m -> m.getRuc().equals(municipalidad.getRuc()));
                if (rucExists) {
                    return Mono.error(new DuplicateKeyException(
                        "Ya existe una municipalidad con el RUC: " + municipalidad.getRuc()));
                }

                // Si no hay duplicados, proceder con la creación
                if (municipalidad.getId() != null) {
                    log.warn("La municipalidad ya tiene un ID asignado: {}", municipalidad.getId());
                }

                // Establecer valores por defecto y timestamps
                municipalidad.setCreatedAt(ZonedDateTime.now());
                municipalidad.setUpdatedAt(ZonedDateTime.now());
                municipalidad.setActivo(municipalidad.getActivo() != null ? municipalidad.getActivo() : true);
                municipalidad.setTipo(municipalidad.getTipo() != null ? municipalidad.getTipo() : "DISTRITAL");

                return municipalidadRepository.save(municipalidad)
                    .doOnSuccess(saved -> log.info("Municipalidad creada exitosamente: {}", saved))
                    .doOnError(error -> {
                        String errorMessage = error.getMessage() != null ? error.getMessage() : "Error desconocido";
                        if (errorMessage.contains("municipalidades_ubigeo_key")) {
                            throw new DuplicateKeyException("Ya existe una municipalidad con el ubigeo proporcionado");
                        } else if (errorMessage.contains("municipalidades_ruc_key")) {
                            throw new DuplicateKeyException("Ya existe una municipalidad con el RUC proporcionado");
                        }
                        log.error("Error al crear municipalidad: {}", errorMessage);
                    });
            });
    }

    @Override
    public Mono<Municipalidad> update(UUID id, Municipalidad municipalidad) {
        return municipalidadRepository.findById(id)
            .switchIfEmpty(Mono.error(new RuntimeException("Municipalidad no encontrada")))
            .flatMap(existingMunicipalidad -> {
                log.info("Actualizando municipalidad: {} con datos: {}", id, municipalidad);
                
                // Actualizar solo los campos que vienen en la solicitud
                if (municipalidad.getNombre() != null && !municipalidad.getNombre().isEmpty()) {
                    existingMunicipalidad.setNombre(municipalidad.getNombre());
                }
                if (municipalidad.getRuc() != null && !municipalidad.getRuc().isEmpty()) {
                    existingMunicipalidad.setRuc(municipalidad.getRuc());
                }
                if (municipalidad.getUbigeo() != null && !municipalidad.getUbigeo().isEmpty()) {
                    existingMunicipalidad.setUbigeo(municipalidad.getUbigeo());
                }
                if (municipalidad.getTipo() != null && !municipalidad.getTipo().isEmpty()) {
                    existingMunicipalidad.setTipo(municipalidad.getTipo());
                }
                if (municipalidad.getDepartamento() != null && !municipalidad.getDepartamento().isEmpty()) {
                    existingMunicipalidad.setDepartamento(municipalidad.getDepartamento());
                }
                if (municipalidad.getProvincia() != null && !municipalidad.getProvincia().isEmpty()) {
                    existingMunicipalidad.setProvincia(municipalidad.getProvincia());
                }
                if (municipalidad.getDistrito() != null && !municipalidad.getDistrito().isEmpty()) {
                    existingMunicipalidad.setDistrito(municipalidad.getDistrito());
                }
                if (municipalidad.getDireccion() != null && !municipalidad.getDireccion().isEmpty()) {
                    existingMunicipalidad.setDireccion(municipalidad.getDireccion());
                }
                if (municipalidad.getTelefono() != null) {
                    existingMunicipalidad.setTelefono(municipalidad.getTelefono());
                }
                if (municipalidad.getEmail() != null) {
                    existingMunicipalidad.setEmail(municipalidad.getEmail());
                }
                if (municipalidad.getWebsite() != null) {
                    existingMunicipalidad.setWebsite(municipalidad.getWebsite());
                }
                if (municipalidad.getAlcalde() != null) {
                    existingMunicipalidad.setAlcalde(municipalidad.getAlcalde());
                }
                if (municipalidad.getActivo() != null) {
                    existingMunicipalidad.setActivo(municipalidad.getActivo());
                }

                existingMunicipalidad.setUpdatedAt(ZonedDateTime.now());
                
                log.info("Guardando municipalidad actualizada: {}", existingMunicipalidad);
                return municipalidadRepository.save(existingMunicipalidad);
            });
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return municipalidadRepository.deleteById(id);
    }

    @Override
    public Flux<Municipalidad> findByTipo(String tipo) {
        return municipalidadRepository.findByTipo(tipo);
    }

    @Override
    public Flux<Municipalidad> findByDepartamento(String departamento) {
        return municipalidadRepository.findByDepartamento(departamento);
    }

    @Override
    public Flux<Municipalidad> findByProvincia(String provincia) {
        return municipalidadRepository.findByProvincia(provincia);
    }
}