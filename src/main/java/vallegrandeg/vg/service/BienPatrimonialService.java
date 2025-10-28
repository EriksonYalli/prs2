package vallegrandeg.vg.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vallegrandeg.vg.dto.BienPatrimonialDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

@Service
public class BienPatrimonialService {

    public Flux<BienPatrimonialDTO> getAllBienesPatrimoniales() {
        return Flux.fromIterable(generarBienesFicticios());
    }

    public Mono<BienPatrimonialDTO> getBienPatrimonialById(UUID id) {
        return getAllBienesPatrimoniales()
                .filter(bien -> bien.getId().equals(id))
                .next();
    }

    private java.util.List<BienPatrimonialDTO> generarBienesFicticios() {
        return Arrays.asList(
            BienPatrimonialDTO.builder()
                .id(UUID.randomUUID())
                .codigoPatrimonial("BP-2025-001")
                .descripcion("Laptop HP EliteBook")
                .marca("HP")
                .modelo("EliteBook 840 G8")
                .serie("5CD1234ABC")
                .estadoBien("DISPONIBLE")
                .estadoConservacion("BUENO")
                .valorAdquisicion(new BigDecimal("4500.00"))
                .responsableActualNombre("Juan Pérez")
                .areaActualNombre("Sistemas")
                .ubicacionActualNombre("Oficina 101")
                .build(),

            BienPatrimonialDTO.builder()
                .id(UUID.randomUUID())
                .codigoPatrimonial("BP-2025-002")
                .descripcion("Escritorio Ejecutivo")
                .marca("Platinum")
                .modelo("EJ-2023")
                .estadoBien("EN_USO")
                .estadoConservacion("BUENO")
                .valorAdquisicion(new BigDecimal("1200.00"))
                .responsableActualNombre("María García")
                .areaActualNombre("Gerencia")
                .ubicacionActualNombre("Oficina 205")
                .build(),

            BienPatrimonialDTO.builder()
                .id(UUID.randomUUID())
                .codigoPatrimonial("BP-2025-003")
                .descripcion("Impresora Multifuncional")
                .marca("Epson")
                .modelo("L5290")
                .serie("EPSL5290123")
                .estadoBien("MANTENIMIENTO")
                .estadoConservacion("REGULAR")
                .valorAdquisicion(new BigDecimal("2800.00"))
                .responsableActualNombre("Carlos Rodríguez")
                .areaActualNombre("Logística")
                .ubicacionActualNombre("Almacén Principal")
                .build()
        );
    }
}