package com.example.lordoftherings

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class LordOfTheRingsApplication

fun main(args: Array<String>) {

	runApplication<LordOfTheRingsApplication>(*args)

	val filePath = "character.json"
	val characterList = Json.decodeFromString<Personajes>(File(filePath).readText())

	println(characterList.docs.size)

	characterList.docs.forEach {
		println(it.name)
	}

}