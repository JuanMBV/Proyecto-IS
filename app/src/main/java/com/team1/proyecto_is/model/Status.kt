package com.team1.proyecto_is.model

class Status(private var id_status: Int,
             private var status: Int,
             private var fecha_registro: String) {

    constructor() : this(
        0, 0,""
    )

    fun getIdStatus(): Int{
        return id_status
    }
    fun getStatus(): Int{
        return status
    }
    fun getFechaRegistro(): String{
        return fecha_registro
    }
    fun setIdStatus(idStatus: Int) {
        this.id_status = idStatus
    }
    fun setStatus(status: Int){
        this.status = status
    }
    fun setFechaRegistro(fechaRegistro: String) {
        this.fecha_registro = fechaRegistro
    }
}