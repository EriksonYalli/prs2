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
public class AreaDTO {
    private UUID id;
    private String codigoArea;
    private String nombre;
    private String descripcion;
    private UUID areaPadreId;
    private String areaPadreNombre;
    private Integer nivelJerarquico;
    private UUID responsableId;
    private String responsableNombre;
    private String ubicacionFisica;
    private String telefono;
    private String email;
    private boolean activo;
}