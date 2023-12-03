package com.team1.proyecto_is.service

import android.content.ContentValues
import android.util.Log
import androidx.core.content.contentValuesOf
import androidx.core.database.getStringOrNull
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.model.Eventos
import com.team1.proyecto_is.model.Plantillas
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class EventosService(private val dataBase: DataBase) {

    fun InsertEvento(
        idPlantilla: Int, materia: String?,
        parteCuerpo: String?, descripcion: String?, comida: String?, lugar: String?,
        estatus: Int, fechaRegistro: LocalDateTime?, fechaInicial: LocalDateTime?,
        fechaFinal: LocalDateTime?, timer: String?
    ): Long {
        val db = dataBase.writableDatabase

        val fechaRegistroFormat =
            fechaRegistro?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val fechaInicialFormat =
            fechaInicial?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val fechaFinalFormat =
            fechaFinal?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

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
        } catch (e: Exception) {
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }

    fun SelectEvent(idEvento: Int): Eventos {
        val db = dataBase.readableDatabase
        val evento = Eventos()
        try {
            db.rawQuery("SELECT * FROM eventos WHERE id_evento = $idEvento", null).use { cursor ->
                cursor.moveToFirst()
                evento.setIdEvento(cursor.getInt(0))
                val plantilla: Plantillas =
                    PlantillaService(dataBase).SelectPlantilla(cursor.getInt(1))
                evento.setPlantilla(plantilla)
                evento.setMateria(cursor.getStringOrNull(2))
                evento.setParteCuerpo(cursor.getStringOrNull(3))
                evento.setDescripcion(cursor.getStringOrNull(4))
                evento.setComida(cursor.getStringOrNull(5))
                evento.setLugar(cursor.getStringOrNull(6))
                evento.setFechaRegistro(
                    LocalDateTime.parse(
                        cursor.getString(7),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    )
                )
                evento.setFechaInicial(
                    LocalDateTime.parse(
                        cursor.getString(8),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    )
                )
                evento.setFechaFinal(
                    LocalDateTime.parse(
                        cursor.getStringOrNull(9),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    )
                )
                evento.setTimer(cursor.getStringOrNull(10))
            }
        } catch (e: Exception) {
            Log.d("Error SelectService", e.toString())
        }
        return evento
    }

    fun SelectAllEvents(): List<Eventos> {
        val listEvents: MutableList<Eventos> = mutableListOf()

        val db = dataBase.readableDatabase

        try {
            db.rawQuery("SELECT * FROM eventos WHERE status = 0 ORDER BY fecha_final DESC", null)
                .use { cursor ->
                    while (cursor.moveToNext()) {
                        val events = Eventos()
                        events.setIdEvento(cursor.getInt(0))
                        val plantilla: Plantillas =
                            PlantillaService(dataBase).SelectPlantilla(cursor.getInt(1))
                        events.setPlantilla(plantilla)
                        events.setMateria(cursor.getStringOrNull(2))
                        events.setParteCuerpo(cursor.getStringOrNull(3))
                        events.setDescripcion(cursor.getStringOrNull(4))
                        events.setComida(cursor.getStringOrNull(5))
                        events.setLugar(cursor.getStringOrNull(6))
                        events.setStatus(cursor.getInt(7))

                        val fechaRegistroString = cursor.getString(8)
                        val fechaInicialString = cursor.getString(9)
                        val fechaFinalString = cursor.getString(10)

                        events.setFechaRegistro(
                            if (!fechaRegistroString.isNullOrEmpty()) {
                                LocalDateTime.parse(
                                    fechaRegistroString,
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                )
                            } else {
                                null
                            }
                        )

                        events.setFechaInicial(
                            if (!fechaInicialString.isNullOrEmpty()) {
                                LocalDateTime.parse(
                                    fechaInicialString,
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                )
                            } else {
                                null
                            }
                        )

                        events.setFechaFinal(
                            if (!fechaFinalString.isNullOrEmpty()) {
                                LocalDateTime.parse(
                                    fechaFinalString,
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                )
                            } else {
                                null
                            }
                        )

                        events.setTimer(cursor.getStringOrNull(10))
                        //println(events.toString())
                        listEvents.add(events)
                    }
                }
        } catch (e: Exception) {
            Log.d("Error SelectAllEvents", e.toString())
        }
        return listEvents
    }

    fun getNearestEvent(): List<Eventos> {
        val eventsList = SelectAllEvents()
        return eventsList
            .filter { it.getFechaInicial() != null }
            .sortedBy { it.getFechaInicial() }
            .toList()
    }

    fun SelectCompleteEvents(): List<Eventos> {
        val listEvents: MutableList<Eventos> = mutableListOf()

        val db = dataBase.readableDatabase

        try {
            db.rawQuery("SELECT * FROM eventos WHERE status = 1", null).use { cursor ->
                while (cursor.moveToNext()) {
                    val events = Eventos()
                    events.setIdEvento(cursor.getInt(0))
                    val plantilla: Plantillas =
                        PlantillaService(dataBase).SelectPlantilla(cursor.getInt(1))
                    events.setPlantilla(plantilla)
                    events.setMateria(cursor.getStringOrNull(2))
                    events.setParteCuerpo(cursor.getStringOrNull(3))
                    events.setDescripcion(cursor.getStringOrNull(4))
                    events.setComida(cursor.getStringOrNull(5))
                    events.setLugar(cursor.getStringOrNull(6))
                    events.setStatus(cursor.getInt(7))

                    val fechaRegistroString = cursor.getString(8)
                    val fechaInicialString = cursor.getString(9)
                    val fechaFinalString = cursor.getString(10)

                    events.setFechaRegistro(
                        if (!fechaRegistroString.isNullOrEmpty()) {
                            LocalDateTime.parse(
                                fechaRegistroString,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            )
                        } else {
                            null
                        }
                    )

                    events.setFechaInicial(
                        if (!fechaInicialString.isNullOrEmpty()) {
                            LocalDateTime.parse(
                                fechaInicialString,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            )
                        } else {
                            null
                        }
                    )

                    events.setFechaFinal(
                        if (!fechaFinalString.isNullOrEmpty()) {
                            LocalDateTime.parse(
                                fechaFinalString,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            )
                        } else {
                            null
                        }
                    )

                    events.setTimer(cursor.getStringOrNull(10))
                    //println(events.toString())
                    listEvents.add(events)
                }
            }
        } catch (e: Exception) {
            Log.d("Error SelectCompleteEvents", e.toString())
        }
        return listEvents
    }

    fun SelectPerPlantilla(idPlantilla: Int): List<Eventos> {
        val listEvents: MutableList<Eventos> = mutableListOf()

        val db = dataBase.readableDatabase

        try {
            db.rawQuery(
                "SELECT * FROM eventos WHERE id_plantilla = $idPlantilla AND status = 0 ORDER BY fecha_registro",
                null
            ).use { cursor ->
                while (cursor.moveToNext()) {
                    val events = Eventos()
                    events.setIdEvento(cursor.getInt(0))
                    val plantilla: Plantillas =
                        PlantillaService(dataBase).SelectPlantilla(cursor.getInt(1))
                    events.setPlantilla(plantilla)
                    events.setMateria(cursor.getStringOrNull(2))
                    events.setParteCuerpo(cursor.getStringOrNull(3))
                    events.setDescripcion(cursor.getStringOrNull(4))
                    events.setComida(cursor.getStringOrNull(5))
                    events.setLugar(cursor.getStringOrNull(6))
                    events.setStatus(cursor.getInt(7))

                    val fechaRegistroString = cursor.getString(8)
                    val fechaInicialString = cursor.getString(9)
                    val fechaFinalString = cursor.getString(10)

                    events.setFechaRegistro(
                        if (!fechaRegistroString.isNullOrEmpty()) {
                            LocalDateTime.parse(
                                fechaRegistroString,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            )
                        } else {
                            null
                        }
                    )

                    events.setFechaInicial(
                        if (!fechaInicialString.isNullOrEmpty()) {
                            LocalDateTime.parse(
                                fechaInicialString,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            )
                        } else {
                            null
                        }
                    )

                    events.setFechaFinal(
                        if (!fechaFinalString.isNullOrEmpty()) {
                            LocalDateTime.parse(
                                fechaFinalString,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            )
                        } else {
                            null
                        }
                    )

                    events.setTimer(cursor.getStringOrNull(10))
                    //println(events.toString())
                    listEvents.add(events)
                }
            }
        } catch (e: Exception) {
            Log.d("Error SelectPerPlantilla", e.toString())
        }
        return listEvents
    }

    fun DeleteEvent(idEvent: Int): Int {
        val db = dataBase.writableDatabase

        var rowsDeleted = 0

        try {
            rowsDeleted = db.delete("eventos", "id=?", arrayOf(idEvent.toString()))
            Log.d("DeleteEvent", "Se elimino correctamente")
        } catch (e: Exception) {
            Log.d("DeleteEvent", e.toString())
        } finally {
            db.close()
        }
        return rowsDeleted
    }

    fun CompleteEvent(idEvent: Int): Int {
        val db = dataBase.writableDatabase

        val values = ContentValues().apply {
            put("status", 1)
        }

        var rowsAffected = 0

        try {
            rowsAffected =
                db.update("eventos", values, "id_evento = ?", arrayOf(idEvent.toString()))
            Log.d("CompleteEvent", "Se completo el evento!")
        } catch (e: Exception) {
            Log.d("Error CompleteEvent", e.toString())
        } finally {
            db.close()
        }
        return rowsAffected
    }

    fun InsertEstudiar(materia: String?, horaInicio: LocalDateTime?, horaFin: LocalDateTime?): Long {
        val db = dataBase.writableDatabase

        val horaInicioFormat = horaInicio?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val horaFinFormat = horaFin?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val now = LocalDateTime.of(LocalDate.now(), LocalTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        val values = ContentValues().apply {
            put("id_plantilla", 1)
            put("materia", materia)
            put("fecha_registro", now)
            put("fecha_inicial", horaInicioFormat)
            put("fecha_final", horaFinFormat)
        }
        var status: Long = 0

        try {
            status = db.insert("eventos", null, values)
            Log.d("InsertEstudiar", "Se insertó información de estudio correctamente")
        } catch (e: Exception) {
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }

    fun InsertEjercicio(parteCuerpo: String?, horaInicio: LocalDateTime?, horaFin: LocalDateTime?): Long {
        val db = dataBase.writableDatabase

        val horaInicioFormat = horaInicio?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val horaFinFormat = horaFin?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val now = LocalDateTime.of(LocalDate.now(), LocalTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        val values = ContentValues().apply {
            put("id_plantilla", 2)
            put("parte_cuerpo", parteCuerpo)
            put("fecha_registro", now)
            put("fecha_inicial", horaInicioFormat)
            put("fecha_final", horaFinFormat)
        }
        var status: Long = 0

        try {
            status = db.insert("eventos", null, values)
            Log.d("InsertEjercicio", "Se insertó el evento de ejercicio correctamente")
        } catch (e: Exception) {
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }

    fun InsertHobbie(descripcion: String?, horaInicio: LocalDateTime?, lugar: String?): Long {
        val db = dataBase.writableDatabase

        val horaInicioFormat = horaInicio?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val now = LocalDateTime.of(LocalDate.now(), LocalTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        val values = contentValuesOf().apply {
            put("id_plantilla", 3)
            put("descripcion", descripcion)
            put("fecha_registro", now)
            put("fecha_inicial", horaInicioFormat)
            put("lugar", lugar)
        }
        var status: Long = 0

        try {
            status = db.insert("eventos", null, values)
            Log.d("InsertHobbie", "Se insertó el evento de hobbie correctamente")
        } catch (e: Exception) {
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }

    fun InsertComer(comida: String?, horaInicio: LocalDateTime?): Long {
        val db = dataBase.writableDatabase

        val horaInicioFormat = horaInicio?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val now = LocalDateTime.of(LocalDate.now(), LocalTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        val values = ContentValues().apply {
            put("id_plantilla", 4)
            put("comida", comida)
            put("fecha_registro", now)
            put("fecha_inicial", horaInicioFormat)
        }
        var status: Long = 0

        try {
            status = db.insert("eventos", null, values)
            Log.d("InsertComer", "Se insertó el evento de comer correctamente")
        } catch (e: Exception) {
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }


    fun InsertTarea(materia: String?, descripcion: String?, horaFin: LocalDateTime?): Long {
        val db = dataBase.writableDatabase

        val horaFinFormat = horaFin?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val now = LocalDateTime.of(LocalDate.now(), LocalTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        val values = ContentValues().apply {
            put("id_plantilla", 5)
            put("materia", materia)
            put("descripcion", descripcion)
            put("fecha_registro", now)
            put("fecha_final", horaFinFormat)
        }
        var status: Long = 0

        try {
            status = db.insert("eventos", null, values)
            Log.d("InsertTarea", "Se insertó el evento de Tarea correctamente")
        } catch (e: Exception) {
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }

    fun InsertBreak(horaInicio: LocalDateTime?, horaFin: LocalDateTime?): Long {
        val db = dataBase.writableDatabase

        val horaInicioFormat = horaInicio?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val horaFinFormat = horaFin?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val now = LocalDateTime.of(LocalDate.now(), LocalTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        val values = ContentValues().apply {
            put("id_plantilla", 6)
            put("fecha_registro", now)
            put("fecha_inicial", horaInicioFormat)
            put("fecha_final", horaFinFormat)
        }
        var status: Long = 0

        try {
            status = db.insert("eventos", null, values)
            Log.d("InsertBreak", "Se insertó el evento de Break correctamente")
        } catch (e: Exception) {
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }

    fun InsertEventos(descripcion: String?, lugar: String?, horaInicio: LocalDateTime?): Long {
        val db = dataBase.writableDatabase

        val horaInicioFormat = horaInicio?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val now = LocalDateTime.of(LocalDate.now(), LocalTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        val values = ContentValues().apply {
            put("id_plantilla", 7)
            put("descripcion", descripcion)
            put("lugar", lugar)
            put("fecha_registro", now)
            put("fecha_inicial", horaInicioFormat)
        }
        var status: Long = 0

        try {
            status = db.insert("eventos", null, values)
            Log.d("InsertEventos", "Se insertó el evento de Eventos correctamente")
        } catch (e: Exception) {
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }

    fun InsertExamen(materia: String?, horaInicio: LocalDateTime?): Long {
        val db = dataBase.writableDatabase

        val horaInicioFormat = horaInicio?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val now = LocalDateTime.of(LocalDate.now(), LocalTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        val values = ContentValues().apply {
            put("id_plantilla", 8)
            put("materia", materia)
            put("fecha_registro", now)
            put("fecha_inicial", horaInicioFormat)
        }
        var status: Long = 0

        try {
            status = db.insert("eventos", null, values)
            Log.d("InsertExamen", "Se insertó el evento de examen correctamente")
        } catch (e: Exception) {
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }
}
