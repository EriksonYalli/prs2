package vallegrandeg.vg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionFisicaDTO {
    private UUID id;
    private String codigoUbicacion;
    private String nombre;
    private String descripcion;
    private String tipoUbicacion;
    private UUID ubicacionPadreId;
    private String ubicacionPadreNombre;
    private String direccion;
    private Integer piso;
    private String sector;
    private String referencia;
    private UUID responsableId;
    private String responsableNombre;
    private boolean activo;
}