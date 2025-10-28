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
public class UsuarioDTO {
    private UUID id;
    private String username;
    private String nombreCompleto;
    private UUID areaId;
    private String areaNombre;
    private UUID cargoId;
    private String cargoNombre;
    private String estado;
    private String email;
    private String telefono;
}