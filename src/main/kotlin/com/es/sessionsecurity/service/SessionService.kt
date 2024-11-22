package com.es.sessionsecurity.service

import com.es.sessionsecurity.repository.SessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SessionService {

    @Autowired
    private lateinit var sessionRepository: SessionRepository

    fun checkToken() : Boolean {
        return true
    }

}