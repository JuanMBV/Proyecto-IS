package com.team1.proyecto_is.model

import java.time.LocalDateTime

class Eventos(private var idEventos: Int,
              private var plantilla: Plantillas?,
              private var materia: String?,
              private var parteCuerpo: String?,
              private var descripcion: String?,
              private var comida: String?,
              private var lugar: String?,
              private var status: Int,
              private var fechaRegistro: LocalDateTime?,
              private var fechaInicial: LocalDateTime?,
              private var fechaFinal: LocalDateTime?,
              private var timer: String?) {

    constructor(): this(
        0,
        null,
         "",
        "",
        "",
        "",
        "",
        0,
        LocalDateTime.MIN,
        LocalDateTime.MIN,
        LocalDateTime.MIN,
        "",
    )

    fun getIdEventos(): Int {
        return idEventos
    }

    fun getPlantilla(): Plantillas? {
        return plantilla
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

    fun getStatus(): Int{
        return status
    }

    fun getFechaRegistro(): LocalDateTime?{
        return fechaRegistro
    }

    fun getFechaInicial(): LocalDateTime? {
        return fechaInicial
    }

    fun getFechaFinal(): LocalDateTime? {
        return fechaFinal
    }

    fun getTimer(): String? {
        return timer
    }
    fun setIdEventos(idEventos: Int) {
        this.idEventos = idEventos
    }
    fun setPlantilla(plantilla: Plantillas) {
        this.plantilla = plantilla
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

    fun setStatus(status: Int){
        this.status = status
    }

    fun setFechaRegistro(fechaRegistro: LocalDateTime?){
        this.fechaRegistro = fechaRegistro
    }

    fun setFechaInicial(fechaInicial: LocalDateTime?) {
        this.fechaInicial = fechaInicial
    }

    fun setFechaFinal(fechaFinal: LocalDateTime?) {
        this.fechaFinal = fechaFinal
    }

    fun setTimer(timer: String?) {
        this.timer = timer
    }

    override fun toString(): String {
        return "Eventos(idEventos=$idEventos, plantilla=$plantilla, materia=$materia, parteCuerpo=$parteCuerpo, descripcion=$descripcion, comida=$comida, lugar=$lugar, status=$status, fechaRegistro=$fechaRegistro, fechaInicial=$fechaInicial, fechaFinal=$fechaFinal, timer=$timer)"
    }
}