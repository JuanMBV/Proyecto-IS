package com.team1.proyecto_is.model

class Status(private var idStatus: Int,
             private var status: Int,
             private var fechaRegistro: String) {

    constructor() : this(
        0, 0,""
    )

    fun getIdStatus(): Int{
        return idStatus
    }
    fun getStatus(): Int{
        return status
    }
    fun getFechaRegistro(): String{
        return fechaRegistro
    }
    fun setIdStatus(idStatus: Int) {
        this.idStatus = idStatus
    }
    fun setStatus(status: Int){
        this.status = status
    }
    fun setFechaRegistro(fechaRegistro: String) {
        this.fechaRegistro = fechaRegistro
    }
}