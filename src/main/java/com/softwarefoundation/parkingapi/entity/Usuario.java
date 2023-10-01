package com.softwarefoundation.parkingapi.entity;

import com.softwarefoundation.parkingapi.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB01_USUARIO")
public class Usuario extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USERNAME", length = 100, nullable = false, unique = true)
    private String username;
    @Column(name = "PASSWORD", length = 100, nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 25)
    private Role role;
    @Column(name = "DT_CRIACAO")
    private LocalDateTime dataCriacao;
    @Column(name = "DT_MODIFICACAO")
    private LocalDateTime dataModificacao;
    @Column(name = "CRIADO_POR", length = 100)
    private String criadoPor;
    @Column(name = "MODIFICADO_POR", length = 100)
    private String modificadoPor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
