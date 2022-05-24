package com.example.lordoftherings

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class LordOfTheRingsApplication

val characterList = Json.decodeFromString<Personajes>(File("character.json").readText())

fun main(args: Array<String>) {

	runApplication<LordOfTheRingsApplication>(*args)

	println("La lista de personajes tiene:" +characterList.docs.size)

}

/*

TODO: getOthers (no good guy.no bad guy)

 */