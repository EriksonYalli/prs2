package vallegrandeg.vg.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vallegrandeg.vg.model.Municipalidad;
import java.util.UUID;

public interface MunicipalidadService {
    Flux<Municipalidad> findAll();
    
    Mono<Municipalidad> findById(UUID id);
    
    Mono<Municipalidad> create(Municipalidad municipalidad);
    
    Mono<Municipalidad> update(UUID id, Municipalidad municipalidad);
    
    Mono<Void> delete(UUID id);
    
    Flux<Municipalidad> findByTipo(String tipo);
    
    Flux<Municipalidad> findByDepartamento(String departamento);
    
    Flux<Municipalidad> findByProvincia(String provincia);
}