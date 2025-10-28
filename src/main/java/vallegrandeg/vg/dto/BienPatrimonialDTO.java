package vallegrandeg.vg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BienPatrimonialDTO {
    private UUID id;
    private String codigoPatrimonial;
    private String codigoInterno;
    private String descripcion;
    private String marca;
    private String modelo;
    private String serie;
    private String placaPatrimonial;
    private String color;
    private String estadoBien;
    private String estadoConservacion;
    private BigDecimal valorAdquisicion;
    private UUID responsableActualId;
    private String responsableActualNombre;
    private UUID areaActualId;
    private String areaActualNombre;
    private UUID ubicacionActualId;
    private String ubicacionActualNombre;
}