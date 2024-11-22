package com.es.sessionsecurity.controller

import com.es.sessionsecurity.model.Reserva
import com.es.sessionsecurity.service.ReservaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reservas")
class ReservaController {

    @Autowired
    private lateinit var reservaService: ReservaService

    /*
    OBTENER TODAS LAS RESERVAS POR EL NOMBRE DE USUARIO DE UN CLIENTE
     */
    @GetMapping("/{nombre}")
    fun getByNombreUsuario(
        @PathVariable nombreUsuario: String
    ) : ResponseEntity<List<Reserva>?> {

        /*
        COMPROBAR QUE LA PETICIÓN ESTÁ CORRECTAMENTE AUTORIZADA PARA REALIZAR ESTA OPERACIÓN
         */
        // CÓDIGO AQUÍ

        /*
        LLAMAR AL SERVICE PARA REALIZAR LA L.N. Y LA LLAMADA A LA BASE DE DATOS
         */
        // CÓDIGO AQUÍ

        // RESPUESTA
        return ResponseEntity<List<Reserva>?>(null, HttpStatus.OK); // cambiar null por las reservas

    }

    /*
    INSERTAR UNA NUEVA RESERVA
     */
    @PostMapping("/")
    fun insert(
        @RequestBody nuevaReserva: Reserva
    ) : ResponseEntity<Reserva?>{

        /*
        COMPROBAR QUE LA PETICIÓN ESTÁ CORRECTAMENTE AUTORIZADA PARA REALIZAR ESTA OPERACIÓN
         */
        // CÓDIGO AQUÍ

        /*
        LLAMAR AL SERVICE PARA REALIZAR LA L.N. Y LA LLAMADA A LA BASE DE DATOS
         */
        // CÓDIGO AQUÍ

        // RESPUESTA
        return ResponseEntity<Reserva?>(null, HttpStatus.CREATED); // cambiar null por la reserva
    }

}