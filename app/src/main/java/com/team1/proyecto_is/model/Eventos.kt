package com.team1.proyecto_is.model

class Eventos(private var id_eventos: Int,
              private var id_plantilla: Int,
              private var id_status: Int,
              private var materia: String?,
              private var parte_cuerpo: String?,
              private var descripcion: String?,
              private var comida: String?,
              private var lugar: String?,
              private var fecha_inicial: String?,
              private var fecha_recordatorio: String?,
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
        ""
    )

    fun getIdEventos(): Int{
        return id_eventos
    }

    fun getIdPlantilla(): Int{
        return id_plantilla
    }

    fun getIdStatus(): Int {
        return id_status
    }

    fun getMateria(): String? {
        return materia
    }

    fun getParteCuerpo(): String? {
        return parte_cuerpo
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
        return fecha_inicial
    }

    fun getFechaRecordatorio(): String? {
        return fecha_recordatorio
    }

    fun getTimer(): String? {
        return timer
    }
    fun setIdEventos(idEventos: Int) {
        this.id_eventos = idEventos
    }
    fun setIdPlantilla(idPlantilla: Int) {
        this.id_plantilla = idPlantilla
    }
    fun setIdStatus(idStatus: Int) {
        this.id_status = idStatus
    }
    fun setMateria(materia: String?) {
        this.materia = materia
    }
    fun setParteCuerpo(parteCuerpo: String?) {
        this.parte_cuerpo = parteCuerpo
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
        this.fecha_inicial = fechaInicial
    }
    fun setFechaRecordatorio(fechaRecordatorio: String?) {
        this.fecha_recordatorio = fechaRecordatorio
    }
    fun setTimer(timer: String?) {
        this.timer = timer
    }
}