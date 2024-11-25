package com.es.sessionsecurity.service

import com.es.sessionsecurity.model.Reserva
import com.es.sessionsecurity.repository.SessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReservaService {
    @Autowired
    private lateinit var reservaService: ReservaService
    fun findByUsuario_Nombre(nombre: String) : List<Reserva>{
        return reservaService.findByUsuario_Nombre(nombre)
    }
}