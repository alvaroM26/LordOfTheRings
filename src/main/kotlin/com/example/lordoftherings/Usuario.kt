package com.example.lordoftherings

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Usuario(@Id var nombre : String, var pass : String) {

    var token = nombre+pass

    @ElementCollection
    var listaEquipo = mutableListOf<String>()

    var tamano : Int = 6

    var mazmorraFacil : Boolean = false
    var mazmorraMedia : Boolean = false
    var mazmorraDificil : Boolean = false

}