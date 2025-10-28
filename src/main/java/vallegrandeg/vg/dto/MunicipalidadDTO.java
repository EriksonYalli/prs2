package vallegrandeg.vg.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MunicipalidadDTO {
    private UUID id;
    private String nombre;
    private String ruc;
    private String ubigeo;
    private String tipo;
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccion;
    private String telefono;
    private String email;
    private String website;
    private String alcalde;
    private Boolean activo;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}