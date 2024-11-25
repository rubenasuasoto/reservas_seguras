package com.es.sessionsecurity.service

import com.es.sessionsecurity.error.exception.BadRequestException
import com.es.sessionsecurity.model.Session
import com.es.sessionsecurity.repository.SessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDateTime

@Service
class SessionService {

    @Autowired
    private lateinit var sessionRepository: SessionRepository

    fun checkToken(token:String?) : Boolean {

        if (token==null) throw  BadRequestException("Token invalido")

        //1º Vamos a obtener la Session asociada al token
        val s:Session = sessionRepository
            .findByToken(token)
            .orElseThrow{RuntimeException("Token inválido")}

        // Por ultimo, comprobamos que la fecha sea valida

        return s.fechaExp.isAfter(LocalDateTime.now())
    }

}