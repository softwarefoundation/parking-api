package com.softwarefoundation.parkingapi.repository;

import com.softwarefoundation.parkingapi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}