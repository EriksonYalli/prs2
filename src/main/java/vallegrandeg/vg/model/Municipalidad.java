package vallegrandeg.vg.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("municipalidades")
public class Municipalidad {
    @Id
    @Column("id")
    private UUID id;

    @Column("nombre")
    @NotNull(message = "El nombre es requerido")
    private String nombre;

    @Column("ruc")
    @NotNull(message = "El RUC es requerido")
    @Size(min = 11, max = 11, message = "El RUC debe tener 11 dígitos")
    private String ruc;

    @Column("ubigeo")
    @NotNull(message = "El ubigeo es requerido")
    @Size(min = 6, max = 6, message = "El ubigeo debe tener 6 dígitos")
    private String ubigeo;

    @Column("tipo")
    @NotNull(message = "El tipo de municipalidad es requerido")
    private String tipo = "DISTRITAL";

    @Column("departamento")
    @NotNull(message = "El departamento es requerido")
    private String departamento;

    @Column("provincia")
    @NotNull(message = "La provincia es requerida")
    private String provincia;

    @Column("distrito")
    @NotNull(message = "El distrito es requerido")
    private String distrito;

    @Column("direccion")
    private String direccion;

    @Column("telefono")
    @Size(max = 50, message = "El teléfono no puede exceder los 50 caracteres")
    private String telefono;

    @Column("email")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @Column("website")
    private String website;

    @Column("alcalde")
    private String alcalde;

    @Column("activo")
    private Boolean activo = true;

    @CreatedDate
    @Column("created_at")
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private ZonedDateTime updatedAt;

    // Getters y Setters
    // ...
}