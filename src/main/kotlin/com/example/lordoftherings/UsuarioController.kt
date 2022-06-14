package com.example.lordoftherings

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

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

                while (user.listaEquipo.size != user.tamano){

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

                user.mazmorraFacil = false
                user.mazmorraMedia = false
                user.mazmorraDificil = false

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

    @GetMapping("adentanseEnMazmorraFacil/{token}")
    fun adentanseEnMazmorraFacil(@PathVariable token: String) : Any{

        usuarioRepository.findAll().forEach {user->

            if (user.token == token) {

                if (!user.mazmorraFacil) {

                    if (user.listaEquipo.size >= 1) {

                        val personajesMuerto = mutableListOf<String>()

                        user.listaEquipo.forEach { perso ->

                            val randomNum = Random.nextInt(0, 100)

                            if (randomNum >= 25) {
                                personajesMuerto.add(perso)
                                println("Este personaje ha muerto: " + perso)
                            }

                        }

                        user.listaEquipo.removeAll(personajesMuerto)

                        user.mazmorraFacil = user.listaEquipo.size >= 1

                        println(user.mazmorraFacil)

                        usuarioRepository.save(user)

                        return "Los personajes vivos son : " + user.listaEquipo + "\n los personajes muertos son " + personajesMuerto

                    }

                    return "Equipo  muerto"

                }

                return "Mazmorra superada"

            }

        }

        return "Usuario no encontrado"

    }

    @GetMapping("adentanseEnMazmorraMedio/{token}")
    fun adentanseEnMazmorraMedio(@PathVariable token: String) : Any{

        usuarioRepository.findAll().forEach { user->

            if (user.token == token){

                if (!user.mazmorraMedia){

                    if (user.listaEquipo.size >= 1 ){

                        val personajeMuerto = mutableListOf<String>()

                        user.listaEquipo.forEach { perso->

                            val randomNum = Random.nextInt(0, 100)

                            if (randomNum in 0..50) {
                                personajeMuerto.add(perso)
                                println("Este personaje ha muerto: " + perso)
                            }

                        }

                        user.listaEquipo.removeAll(personajeMuerto)

                        user.mazmorraMedia = user.listaEquipo.size >= 1

                        usuarioRepository.save(user)

                        return "Los personajes vivos son : " + user.listaEquipo + "\n los personajes muertos son " + personajeMuerto
                    }

                    return "Equipo  muerto"

                }

                return "Mazmorra superada"

            }

        }

        return "Usuario no encontrado"

    }

    @GetMapping("adentanseEnMazmorraDificil/{token}")
    fun adentanseEnMazmorraDificil(@PathVariable token: String) : Any{

        usuarioRepository.findAll().forEach { user->

            if (user.token == token){

                if (!user.mazmorraMedia){

                    if (user.listaEquipo.size >= 1 ){

                        val personajeMuerto = mutableListOf<String>()

                        user.listaEquipo.forEach { perso->

                            val randomNum = Random.nextInt(0, 100)

                            if (randomNum in 0..75) {
                                personajeMuerto.add(perso)
                                println("Este personaje ha muerto: " + perso)
                            }

                        }

                        user.listaEquipo.removeAll(personajeMuerto)

                        user.mazmorraDificil = user.listaEquipo.size >= 1

                        if (user.mazmorraFacil && user.mazmorraMedia && user.mazmorraDificil){
                            user.tamano ++
                        }

                        usuarioRepository.save(user)

                        return "Los personajes vivos son : " + user.listaEquipo + "\n los personajes muertos son " + personajeMuerto
                    }

                    return "Equipo  muerto"

                }

                return "Mazmorra superada"

            }

        }

        return "Usuario no encontrado"

    }


}