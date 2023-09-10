package com.team1.proyecto_is.model

class Plantillas(private var idPlantilla: Int,
                 private var nombre: String) {
    constructor(): this(
        0," "
    )
    fun getIdPlantilla(): Int{
        return idPlantilla
    }
    fun getNombre(): String{
        return nombre
    }
    fun setIdPlantilla(idPlantilla: Int){
        this.idPlantilla = idPlantilla
    }
    fun setNombre(nombre: String){
        this.nombre = nombre
    }
    override fun toString(): String {
        return "id_plantilla: $idPlantilla, nombre: $nombre"
    }
}