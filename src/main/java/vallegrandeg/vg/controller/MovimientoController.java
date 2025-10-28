package vallegrandeg.vg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vallegrandeg.vg.dto.MovimientoDTO;
import vallegrandeg.vg.service.MovimientoService;

import java.util.UUID;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @GetMapping
    public Flux<MovimientoDTO> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<MovimientoDTO>> getMovimientoById(@PathVariable UUID id) {
        return movimientoService.getMovimientoById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<MovimientoDTO> createMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        return movimientoService.createMovimiento(movimientoDTO);
    }

    @PutMapping("/{id}/estado")
    public Mono<ResponseEntity<MovimientoDTO>> updateEstadoMovimiento(
            @PathVariable UUID id,
            @RequestParam String nuevoEstado) {
        return movimientoService.updateEstadoMovimiento(id, nuevoEstado)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}