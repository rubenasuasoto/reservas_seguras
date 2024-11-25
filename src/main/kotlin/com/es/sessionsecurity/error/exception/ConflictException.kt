package com.es.sessionsecurity.error.exception

class ConflictException(message: String) : RuntimeException("Conflict Exception (409). $message") {
}