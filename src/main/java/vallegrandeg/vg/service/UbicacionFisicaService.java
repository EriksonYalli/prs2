package vallegrandeg.vg.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vallegrandeg.vg.dto.UbicacionFisicaDTO;

import java.util.Arrays;
import java.util.UUID;

@Service
public class UbicacionFisicaService {

    public Flux<UbicacionFisicaDTO> getAllUbicaciones() {
        return Flux.fromIterable(generarUbicacionesFicticias());
    }

    public Mono<UbicacionFisicaDTO> getUbicacionById(UUID id) {
        return getAllUbicaciones()
                .filter(ubicacion -> ubicacion.getId().equals(id))
                .next();
    }

    private java.util.List<UbicacionFisicaDTO> generarUbicacionesFicticias() {
        return Arrays.asList(
            UbicacionFisicaDTO.builder()
                .id(UUID.randomUUID())
                .codigoUbicacion("OF-101")
                .nombre("Oficina 101")
                .descripcion("Oficina de Sistemas")
                .tipoUbicacion("OFICINA")
                .piso(1)
                .sector("A")
                .referencia("Frente al ascensor")
                .responsableNombre("Juan Pérez")
                .activo(true)
                .build(),

            UbicacionFisicaDTO.builder()
                .id(UUID.randomUUID())
                .codigoUbicacion("OF-205")
                .nombre("Oficina 205")
                .descripcion("Gerencia General")
                .tipoUbicacion("OFICINA")
                .piso(2)
                .sector("B")
                .referencia("Al lado de sala de reuniones")
                .responsableNombre("María García")
                .activo(true)
                .build(),

            UbicacionFisicaDTO.builder()
                .id(UUID.randomUUID())
                .codigoUbicacion("ALM-001")
                .nombre("Almacén Principal")
                .descripcion("Almacén Central de Logística")
                .tipoUbicacion("ALMACEN")
                .piso(1)
                .sector("C")
                .referencia("Zona de carga y descarga")
                .responsableNombre("Carlos Rodríguez")
                .activo(true)
                .build()
        );
    }
}