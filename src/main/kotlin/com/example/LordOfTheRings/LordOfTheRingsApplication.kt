package com.example.LordOfTheRings

import com.google.gson.Gson
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LordOfTheRingsApplication

var gson =  Gson()
var listaCharacter = ListaCharacter()

fun main(args: Array<String>) {

	runApplication<LordOfTheRingsApplication>(*args)

	listaCharacter = if(ListaCharacter.fileExist()){
		ListaCharacter.cargarListaPokemonDeFichero()
	}else{
		ObtenerPokemonRequest.get()
	}
	listaCharacter.guardarEnFichero()

	println(listaCharacter)

}
