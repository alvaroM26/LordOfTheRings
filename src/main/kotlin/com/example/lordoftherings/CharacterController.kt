package com.example.lordoftherings

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CharacterController {

    @GetMapping("getHumans")
    fun requestGetHumans() : List<Doc>{

        val listaFiltrada = characterList.docs.filter {

            var encontrado = false

            if (it.race == "Human"){
                encontrado = true
            }
            encontrado
        }
        return listaFiltrada
    }

    @GetMapping("getElfs")
    fun requestGetElfs() : List<Doc>{

        val listaFiltrada = characterList.docs.filter {

            var encontrado = false

            if (it.race == "Elf"){
                encontrado = true
            }
            encontrado
        }
        return listaFiltrada
    }

    @GetMapping("getOrcs")
    fun requestGetOrcs() : List<Doc>{

        val listaFiltrada = characterList.docs.filter {

            var encontrado = false

            if (it.race == "Orc" || it.race == "Orcs"){
                encontrado = true
            }
            encontrado
        }
        return listaFiltrada
    }

    @GetMapping("getDragons")
    fun requestGetDragons() : List<Doc>{

        val listaFiltrada = characterList.docs.filter {

            var encontrado = false

            if (it.race == "Dragon" || it.race == "Dragons"){
                encontrado = true
            }
            encontrado
        }
        return listaFiltrada
    }

    @GetMapping("getGoodGuys")
    fun requestGetGoodGuys() : List<Doc>{

        val listaFiltrada = characterList.docs.filter {

            var encontrado = false

            if (it.race == "Elf" || it.race == "Human"){
                encontrado = true
            }
            encontrado
        }
        return listaFiltrada
    }

    @GetMapping("getBadGuys")
    fun requestGetBadGuys() : List<Doc>{

        val listaFiltrada = characterList.docs.filter {

            var encontrado = false

            if (it.race == "Orc" || it.race == "Dragon" || it.race == "Orcs" || it.race == "Dragons"){
                encontrado = true
            }
            encontrado
        }
        return listaFiltrada
    }

    @GetMapping("getOthers")
    fun requestGetOthers() : List<Doc>{

        val listaFiltrada = characterList.docs.filter {

            var encontrado = false

            if (it.race != "Orc" && it.race != "Dragon" && it.race != "Elf" && it.race != "Human" && it.race != "Orcs" && it.race !="Dragons"){
                encontrado = true
            }
            encontrado
        }
        return listaFiltrada
    }

}