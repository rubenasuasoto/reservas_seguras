package com.es.sessionsecurity.repository

import com.es.sessionsecurity.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UsuarioRepository : JpaRepository<Usuario, Long> {

    // DERIVED QUERIES en JPA
    fun findByNombre(nombre: String) : Optional<Usuario>

}