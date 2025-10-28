package vallegrandeg.vg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vallegrandeg.vg.dto.MovimientoDTO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovimientoService {
    // Servicios que se utilizarán cuando se implemente la lógica real
    // private final BienPatrimonialService bienPatrimonialService;
    // private final UsuarioService usuarioService;
    // private final AreaService areaService;
    // private final UbicacionFisicaService ubicacionService;

    public Flux<MovimientoDTO> getAllMovimientos() {
        return Flux.fromIterable(generarMovimientosFicticios());
    }

    public Mono<MovimientoDTO> getMovimientoById(UUID id) {
        return getAllMovimientos()
                .filter(movimiento -> movimiento.getId().equals(id))
                .next();
    }

    private java.util.List<MovimientoDTO> generarMovimientosFicticios() {
        return Arrays.asList(
            MovimientoDTO.builder()
                .id(UUID.randomUUID())
                .numeroMovimiento("MOV-2025-001")
                .bienPatrimonialId(UUID.randomUUID())
                .tipoMovimiento("TRANSFERENCIA_AREA")
                .estadoMovimiento("SOLICITADO")
                .responsableOrigenNombre("Juan Pérez")
                .responsableDestinoNombre("María García")
                .areaOrigenNombre("Sistemas")
                .areaDestinoNombre("Gerencia")
                .ubicacionOrigenNombre("Oficina 101")
                .ubicacionDestinoNombre("Oficina 205")
                .fechaSolicitud(LocalDateTime.now())
                .motivo("Reasignación de equipos informáticos")
                .observaciones("Equipo requiere configuración especial")
                .requiereAprobacion(true)
                .numeroDocumentoSustento("MEMO-2025-001")
                .tipoDocumentoSustento("MEMORANDO")
                .usuarioSolicitaNombre("Carlos Rodriguez")
                .createdAt(LocalDateTime.now())
                .build(),

            MovimientoDTO.builder()
                .id(UUID.randomUUID())
                .numeroMovimiento("MOV-2025-002")
                .bienPatrimonialId(UUID.randomUUID())
                .tipoMovimiento("MANTENIMIENTO")
                .estadoMovimiento("EN_PROCESO")
                .responsableOrigenNombre("María García")
                .responsableDestinoNombre("Taller de Mantenimiento")
                .areaOrigenNombre("Gerencia")
                .areaDestinoNombre("Mantenimiento")
                .ubicacionOrigenNombre("Oficina 205")
                .ubicacionDestinoNombre("Taller Principal")
                .fechaSolicitud(LocalDateTime.now().minusDays(5))
                .fechaAprobacion(LocalDateTime.now().minusDays(4))
                .fechaEjecucion(LocalDateTime.now().minusDays(3))
                .motivo("Mantenimiento preventivo programado")
                .observaciones("Limpieza y actualización de software")
                .requiereAprobacion(true)
                .numeroDocumentoSustento("OT-2025-001")
                .tipoDocumentoSustento("ORDEN_TRABAJO")
                .usuarioSolicitaNombre("María García")
                .createdAt(LocalDateTime.now().minusDays(5))
                .build(),

            MovimientoDTO.builder()
                .id(UUID.randomUUID())
                .numeroMovimiento("MOV-2025-003")
                .bienPatrimonialId(UUID.randomUUID())
                .tipoMovimiento("ASIGNACION_INICIAL")
                .estadoMovimiento("COMPLETADO")
                .responsableOrigenNombre("Almacén Central")
                .responsableDestinoNombre("Pedro López")
                .areaOrigenNombre("Logística")
                .areaDestinoNombre("Recursos Humanos")
                .ubicacionOrigenNombre("Almacén Principal")
                .ubicacionDestinoNombre("Oficina 303")
                .fechaSolicitud(LocalDateTime.now().minusDays(10))
                .fechaAprobacion(LocalDateTime.now().minusDays(9))
                .fechaEjecucion(LocalDateTime.now().minusDays(8))
                .fechaRecepcion(LocalDateTime.now().minusDays(8))
                .motivo("Asignación de equipo nuevo")
                .observaciones("Equipo configurado y listo para usar")
                .requiereAprobacion(true)
                .numeroDocumentoSustento("PECOSA-2025-001")
                .tipoDocumentoSustento("PECOSA")
                .usuarioSolicitaNombre("Ana Martinez")
                .usuarioEjecutaNombre("Carlos Rodriguez")
                .createdAt(LocalDateTime.now().minusDays(10))
                .build()
        );
    }

    // Método para crear un nuevo movimiento
    public Mono<MovimientoDTO> createMovimiento(MovimientoDTO movimientoDTO) {
        // Aquí iría la lógica de validación y creación
        // Por ahora solo retornamos el DTO con un nuevo ID
        movimientoDTO.setId(UUID.randomUUID());
        movimientoDTO.setCreatedAt(LocalDateTime.now());
        movimientoDTO.setFechaSolicitud(LocalDateTime.now());
        movimientoDTO.setEstadoMovimiento("SOLICITADO");
        return Mono.just(movimientoDTO);
    }

    // Método para actualizar el estado de un movimiento
    public Mono<MovimientoDTO> updateEstadoMovimiento(UUID id, String nuevoEstado) {
        return getMovimientoById(id)
                .map(movimiento -> {
                    movimiento.setEstadoMovimiento(nuevoEstado);
                    movimiento.setUpdatedAt(LocalDateTime.now());
                    return movimiento;
                });
    }
}