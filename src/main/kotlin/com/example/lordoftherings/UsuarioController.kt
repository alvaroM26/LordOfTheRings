package com.example.lordoftherings

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsuarioController (private val usuarioRepository: UsuarioRepository) {

    @PostMapping ("registro")
    @Synchronized
    fun registrarUsuario(@RequestBody usuario: Usuario) : Any{

        val userOpcional = usuarioRepository.findById(usuario.nombre)

        return if (userOpcional.isPresent){

            val user = userOpcional.get()

            if (user.pass == usuario.pass){
                user
            }else{
                "Pass invalida"
            }

        }else{
            usuarioRepository.save(usuario)
            usuario
        }

    }

    @GetMapping("crearEquipo/{token}")
    fun asignarEquipo(@PathVariable token : String) : Any{

        usuarioRepository.findAll().forEach { user->

            if (user.token == token) {

                    for (i in 1..6) {

                        val personajesAleatorios = characterList.docs.random()
                        user.listaEquipo.add(personajesAleatorios.name)
                        usuarioRepository.save(user)

                    }

                    return user.listaEquipo

                }

            }

        return "Token no encontrado"
    }
}

