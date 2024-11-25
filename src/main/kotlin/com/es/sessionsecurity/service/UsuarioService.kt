package com.es.sessionsecurity.service

import com.es.sessionsecurity.error.exception.BadRequestException
import com.es.sessionsecurity.error.exception.ConflictException
import com.es.sessionsecurity.error.exception.NotFoundException
import com.es.sessionsecurity.model.Session
import com.es.sessionsecurity.model.Usuario
import com.es.sessionsecurity.repository.SessionRepository
import com.es.sessionsecurity.repository.UsuarioRepository
import com.es.sessionsecurity.util.CipherUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UsuarioService {

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository
    @Autowired
    private lateinit var sessionRepository: SessionRepository

    private val publicKey = "clave_publica_que_no_cambia"
    val cifrado= CipherUtils()
    fun login(userLogin: Usuario) : String {

        // COMPROBACIÓN DE LOS CAMPOS DEL OBJETO USERLOGIN
        if(userLogin.password.isBlank() || userLogin.nombre.isBlank()) {
            throw BadRequestException("Los campos nombre y password son obligatorios")
        }

        // COMPROBAR CREDENCIALES
        // 1 Obtener el usuario de la base de datos
        var userBD: Usuario = usuarioRepository
            .findByNombre(userLogin.nombre)
            .orElseThrow{NotFoundException("El usuario proporcionado no existe en BDD")}

        // 2 Compruebo nombre y pass
        if(userBD.password == userLogin.password) {
            // 3 GENERAR EL TOKEN
            var token: String = ""
            token = cifrado.encrypt(userBD.nombre,publicKey)

            // 4 CREAR UNA SESSION
            val s: Session = Session(
                null,
                token,
                userBD,
                LocalDateTime.now().plusMinutes(3)
            )

            // 5 INSERTAMOS EN BDD
            sessionRepository.save(s)

            return token
        } else {
            // SI LA CONTRASEÑA NO COINCIDE, LANZAMOS EXCEPCIÓN
            throw NotFoundException("Las credenciales son incorrectas")
        }
    }
    fun altaUsuario(nuevoUsuario: Usuario): Usuario {
        // Validar campos del nuevo usuario
        if (nuevoUsuario.nombre.isBlank() || nuevoUsuario.password.isBlank()) {
            throw BadRequestException("Los campos nombre y password son obligatorios")
        }

        // Verificar si el usuario ya existe
        if (usuarioRepository.findByNombre(nuevoUsuario.nombre).isPresent) {
            throw ConflictException("El usuario ya existe en la base de datos")
        }

        // Cifrar la contraseña antes de guardar
        val encryptedPassword = cifrado.encrypt(nuevoUsuario.password, publicKey)

        // Crear el nuevo usuario con la contraseña cifrada
        val usuarioGuardado = Usuario(
            id = null,
            nombre = nuevoUsuario.nombre,
            password = encryptedPassword
        )

        return usuarioRepository.save(usuarioGuardado)
    }
}


