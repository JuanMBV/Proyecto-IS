package com.team1.proyecto_is.model

class Plantillas(private var id_plantilla: Int, private var nombre: String) {
    constructor(): this(
        0," "
    )

    fun getIdPlantilla(): Int{
        return id_plantilla
    }
    fun getNombre(): String{
        return nombre
    }
    fun setIdPlantilla(idPlantilla: Int){
        this.id_plantilla = idPlantilla
    }
    fun setNombre(nombre: String){
        this.nombre = nombre
    }

}