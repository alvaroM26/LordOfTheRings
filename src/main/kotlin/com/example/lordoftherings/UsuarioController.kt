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

                while (user.listaEquipo.size != 6){

                    val personajesAleatorios = characterList.docs.random()

                    if (personajesAleatorios.dueño == null){
                        user.listaEquipo.add(personajesAleatorios.id)
                        personajesAleatorios.dueño = user.token
                        usuarioRepository.save(user)

                        characterList.docs.forEach {
                            if (it.id == personajesAleatorios.id){
                                it.dueño = user.token
                            }
                        }

                    }

                }

                return user.listaEquipo

            }

        }

        return "Token no encontrado"
    }

    @GetMapping("liberarPersonaje/{id}/{token}")
    fun liberarPersona(@PathVariable id : String, @PathVariable token: String): Any {

        usuarioRepository.findAll().forEach { user ->

            if (user.token == token) {

                user.listaEquipo.forEach {list->

                    if (list == id){
                        user.listaEquipo.remove(list)
                        usuarioRepository.save(user)

                        characterList.docs.forEach {

                            if (it.id == list)
                                it.dueño = null
                        }

                        return "Personaje liberado"
                    }

                }

                return "El personaje no pertenece a este equipo"

            }

        }

        return "Token invalido"

    }

    @GetMapping("adentanseEnMazmorra/{token}/{nivel}")
    fun adentanseEnMazmorra(@PathVariable token: String, @PathVariable nivel : String) : Any{

        usuarioRepository.findAll().forEach {user->
            if (user.token == token){

            }
        }

        return "Usuario no encontrado"

    }


}