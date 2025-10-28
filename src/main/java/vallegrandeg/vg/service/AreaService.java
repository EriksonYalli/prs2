package vallegrandeg.vg.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vallegrandeg.vg.dto.AreaDTO;

import java.util.Arrays;
import java.util.UUID;

@Service
public class AreaService {

    public Flux<AreaDTO> getAllAreas() {
        return Flux.fromIterable(generarAreasFicticias());
    }

    public Mono<AreaDTO> getAreaById(UUID id) {
        return getAllAreas()
                .filter(area -> area.getId().equals(id))
                .next();
    }

    private java.util.List<AreaDTO> generarAreasFicticias() {
        return Arrays.asList(
            AreaDTO.builder()
                .id(UUID.randomUUID())
                .codigoArea("SIS")
                .nombre("Sistemas")
                .descripcion("Área de Tecnologías de la Información")
                .nivelJerarquico(2)
                .responsableNombre("Juan Pérez")
                .ubicacionFisica("Piso 1")
                .telefono("123-456")
                .email("sistemas@municipalidad.gob.pe")
                .activo(true)
                .build(),

            AreaDTO.builder()
                .id(UUID.randomUUID())
                .codigoArea("GER")
                .nombre("Gerencia")
                .descripcion("Gerencia General")
                .nivelJerarquico(1)
                .responsableNombre("María García")
                .ubicacionFisica("Piso 5")
                .telefono("123-457")
                .email("gerencia@municipalidad.gob.pe")
                .activo(true)
                .build(),

            AreaDTO.builder()
                .id(UUID.randomUUID())
                .codigoArea("LOG")
                .nombre("Logística")
                .descripcion("Área de Logística y Almacén")
                .nivelJerarquico(2)
                .responsableNombre("Carlos Rodríguez")
                .ubicacionFisica("Piso 1")
                .telefono("123-458")
                .email("logistica@municipalidad.gob.pe")
                .activo(true)
                .build()
        );
    }
}