package com.es.sessionsecurity.repository

import com.es.sessionsecurity.model.Session
import com.es.sessionsecurity.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface SessionRepository : JpaRepository<Session, Long> {

    fun findByToken(token: String) : Optional<Session>

}