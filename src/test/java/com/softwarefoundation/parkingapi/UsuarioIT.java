package com.softwarefoundation.parkingapi;

import com.softwarefoundation.parkingapi.entity.dto.UsuarioCreateDto;
import com.softwarefoundation.parkingapi.entity.dto.UsuarioResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UsuarioIT {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void createUsuarioComUsernamePasswordValidosRetornarUsuarioCriadoComStatus201() {
        UsuarioResponseDto responseBody = this.testClient
                .post()
                .uri("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tod@gmail.com", "123456"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isNotNull();
        Assertions.assertThat(responseBody.getUsername()).isEqualTo("tod@gmail.com");
    }

}
