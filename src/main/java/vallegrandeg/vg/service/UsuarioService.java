package vallegrandeg.vg.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vallegrandeg.vg.dto.UsuarioDTO;

import java.util.Arrays;
import java.util.UUID;

@Service
public class UsuarioService {

    public Flux<UsuarioDTO> getAllUsuarios() {
        return Flux.fromIterable(generarUsuariosFicticios());
    }

    public Mono<UsuarioDTO> getUsuarioById(UUID id) {
        return getAllUsuarios()
                .filter(usuario -> usuario.getId().equals(id))
                .next();
    }

    private java.util.List<UsuarioDTO> generarUsuariosFicticios() {
        return Arrays.asList(
            UsuarioDTO.builder()
                .id(UUID.randomUUID())
                .username("jperez")
                .nombreCompleto("Juan Pérez")
                .areaNombre("Sistemas")
                .cargoNombre("Analista de Sistemas")
                .estado("ACTIVO")
                .email("jperez@municipalidad.gob.pe")
                .telefono("987654321")
                .build(),

            UsuarioDTO.builder()
                .id(UUID.randomUUID())
                .username("mgarcia")
                .nombreCompleto("María García")
                .areaNombre("Gerencia")
                .cargoNombre("Gerente General")
                .estado("ACTIVO")
                .email("mgarcia@municipalidad.gob.pe")
                .telefono("987654322")
                .build(),

            UsuarioDTO.builder()
                .id(UUID.randomUUID())
                .username("crodriguez")
                .nombreCompleto("Carlos Rodríguez")
                .areaNombre("Logística")
                .cargoNombre("Jefe de Logística")
                .estado("ACTIVO")
                .email("crodriguez@municipalidad.gob.pe")
                .telefono("987654323")
                .build()
        );
    }
}