package com.team1.proyecto_is.model

class Eventos(private var idEventos: Int,
              private var idPlantilla: Int,
              private var idStatus: Int,
              private var materia: String?,
              private var parteCuerpo: String?,
              private var descripcion: String?,
              private var comida: String?,
              private var lugar: String?,
              private var fechaInicial: String?,
              private var fechaRecordatorio: String?,
              private var timer: String?) {

    constructor(): this(
        0,
        0,
         0,
         "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    fun getIdEventos(): Int {
        return idEventos
    }

    fun getIdPlantilla(): Int {
        return idPlantilla
    }

    fun getIdStatus(): Int {
        return idStatus
    }

    fun getMateria(): String? {
        return materia
    }

    fun getParteCuerpo(): String? {
        return parteCuerpo
    }

    fun getDescripcion(): String? {
        return descripcion
    }

    fun getComida(): String? {
        return comida
    }

    fun getLugar(): String? {
        return lugar
    }

    fun getFechaInicial(): String? {
        return fechaInicial
    }

    fun getFechaRecordatorio(): String? {
        return fechaRecordatorio
    }

    fun getTimer(): String? {
        return timer
    }
    fun setIdEventos(idEventos: Int) {
        this.idEventos = idEventos
    }
    fun setIdPlantilla(idPlantilla: Int) {
        this.idPlantilla = idPlantilla
    }
    fun setIdStatus(idStatus: Int) {
        this.idStatus = idStatus
    }
    fun setMateria(materia: String?) {
        this.materia = materia
    }
    fun setParteCuerpo(parteCuerpo: String?) {
        this.parteCuerpo = parteCuerpo
    }
    fun setDescripcion(descripcion: String?) {
        this.descripcion = descripcion
    }
    fun setComida(comida: String?) {
        this.comida = comida
    }
    fun setLugar(lugar: String?) {
        this.lugar = lugar
    }
    fun setFechaInicial(fechaInicial: String?) {
        this.fechaInicial = fechaInicial
    }
    fun setFechaRecordatorio(fechaRecordatorio: String?) {
        this.fechaRecordatorio = fechaRecordatorio
    }
    fun setTimer(timer: String?) {
        this.timer = timer
    }
}