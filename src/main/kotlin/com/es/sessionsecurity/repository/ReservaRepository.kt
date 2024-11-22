package com.es.sessionsecurity.repository

import com.es.sessionsecurity.model.Reserva
import org.springframework.data.jpa.repository.JpaRepository

interface ReservaRepository : JpaRepository<Reserva, Long>{

    fun findByUsuario_Nombre(nombre: String) : List<Reserva>
}