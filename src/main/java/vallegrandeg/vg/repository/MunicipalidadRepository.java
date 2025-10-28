package vallegrandeg.vg.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import vallegrandeg.vg.model.Municipalidad;
import java.util.UUID;

public interface MunicipalidadRepository extends ReactiveCrudRepository<Municipalidad, UUID> {
    Flux<Municipalidad> findByTipo(String tipo);
    Flux<Municipalidad> findByDepartamento(String departamento);
    Flux<Municipalidad> findByProvincia(String provincia);
}