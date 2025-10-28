package vallegrandeg.vg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
    private UUID id;
    private String numeroMovimiento;
    private UUID bienPatrimonialId;
    private String tipoMovimiento;
    private String subtipoMovimiento;
    
    // Origen y destino
    private UUID responsableOrigenId;
    private String responsableOrigenNombre;
    private UUID responsableDestinoId;
    private String responsableDestinoNombre;
    private UUID areaOrigenId;
    private String areaOrigenNombre;
    private UUID areaDestinoId;
    private String areaDestinoNombre;
    private UUID ubicacionOrigenId;
    private String ubicacionOrigenNombre;
    private UUID ubicacionDestinoId;
    private String ubicacionDestinoNombre;
    
    // Fechas del proceso
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaAprobacion;
    private LocalDateTime fechaEjecucion;
    private LocalDateTime fechaRecepcion;
    
    // Control de flujo
    private String estadoMovimiento;
    private boolean requiereAprobacion;
    private UUID aprobadoPorId;
    private String aprobadoPorNombre;
    
    // Información del movimiento
    private String motivo;
    private String observaciones;
    private String condicionesEspeciales;
    private String numeroDocumentoSustento;
    private String tipoDocumentoSustento;
    
    // Auditoría
    private UUID usuarioSolicitaId;
    private String usuarioSolicitaNombre;
    private UUID usuarioEjecutaId;
    private String usuarioEjecutaNombre;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}