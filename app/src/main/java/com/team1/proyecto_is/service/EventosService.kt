package com.team1.proyecto_is.service

import android.content.ContentValues
import android.util.Log
import androidx.core.database.getStringOrNull
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.model.Eventos
import com.team1.proyecto_is.model.Plantillas
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventosService(private val dataBase: DataBase) {

    fun InsertEvento(idPlantilla: Int, materia: String?,
                     parteCuerpo: String?, descripcion: String?, comida: String?, lugar: String?,
                     estatus: Int, fechaRegistro: LocalDateTime?, fechaInicial: LocalDateTime?,
                     fechaFinal: LocalDateTime?, timer: String?): Long{
        val db = dataBase.writableDatabase

        val fechaRegistroFormat = fechaRegistro?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val fechaInicialFormat = fechaInicial?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val fechaFinalFormat = fechaFinal?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        val values = ContentValues().apply {
            put("id_plantilla", idPlantilla)
            put("materia", materia)
            put("parte_cuerpo", parteCuerpo)
            put("descripcion", descripcion)
            put("comida", comida)
            put("lugar", lugar)
            put("status", estatus)
            put("fecha_registro", fechaRegistroFormat)
            put("fecha_inicial", fechaInicialFormat)
            put("fecha_final", fechaFinalFormat)
            put("timer", timer)
        }
        var status: Long = 0

        try {
            status = db.insert("eventos", null, values)
            Log.d("InsertEvento", "Se insertó un evento correctamente")
        } catch (e: Exception){
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }

    fun SelectEvent(idEvento: Int): Eventos{
        val db = dataBase.readableDatabase
        val evento = Eventos()
        try {
            db.rawQuery("SELECT * FROM eventos WHERE id_evento = $idEvento", null).use { cursor ->
                cursor.moveToFirst()
                evento.setIdEventos(cursor.getInt(0))
                val plantilla: Plantillas = PlantillaService(dataBase).SelectPlantilla(cursor.getInt(1))
                evento.setPlantilla(plantilla)
                evento.setMateria(cursor.getStringOrNull(2))
                evento.setParteCuerpo(cursor.getStringOrNull(3))
                evento.setDescripcion(cursor.getStringOrNull(4))
                evento.setComida(cursor.getStringOrNull(5))
                evento.setLugar(cursor.getStringOrNull(6))
                evento.setFechaRegistro(LocalDateTime.parse(cursor.getString(7), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                evento.setFechaInicial(LocalDateTime.parse(cursor.getString(8), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                evento.setFechaFinal(LocalDateTime.parse(cursor.getStringOrNull(9), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                evento.setTimer(cursor.getStringOrNull(10))
            }
        }catch (e: Exception){
            Log.d("Error SelectService", e.toString())
        }
        return evento
    }

    fun SelectAllEvents(): List<Eventos>{
        var listEvents: MutableList<Eventos> = mutableListOf()

        val db = dataBase.readableDatabase

        try {
            db.rawQuery("SELECT * FROM eventos", null).use { cursor ->
                while (cursor.moveToNext()) {
                    val events = Eventos()
                    events.setIdEventos(cursor.getInt(0))
                    val plantilla: Plantillas = PlantillaService(dataBase).SelectPlantilla(cursor.getInt(1))
                    events.setPlantilla(plantilla)
                    events.setMateria(cursor.getStringOrNull(2))
                    events.setParteCuerpo(cursor.getStringOrNull(3))
                    events.setDescripcion(cursor.getStringOrNull(4))
                    events.setComida(cursor.getStringOrNull(5))
                    events.setLugar(cursor.getStringOrNull(6))
                    events.setStatus(cursor.getInt(7))

                    // Manejo de las fechas nulas
                    val fechaRegistroString = cursor.getString(8)
                    val fechaInicialString = cursor.getString(9)
                    val fechaFinalString = cursor.getString(10)

                    events.setFechaRegistro(if (!fechaRegistroString.isNullOrEmpty()) {
                        LocalDateTime.parse(fechaRegistroString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    } else {
                        null
                    })

                    events.setFechaInicial(if (!fechaInicialString.isNullOrEmpty()) {
                        LocalDateTime.parse(fechaInicialString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    } else {
                        null
                    })

                    events.setFechaFinal(if (!fechaFinalString.isNullOrEmpty()) {
                        LocalDateTime.parse(fechaFinalString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    } else {
                        null
                    })

                    events.setTimer(cursor.getStringOrNull(10))
                    //println(events.toString())
                    listEvents.add(events)
                }
            }
        }catch (e: Exception){
            Log.d("Error SelectAllEvents", e.toString())
        }
        return listEvents
    }

}
