package com.team1.proyecto_is.service

import android.content.ContentValues
import android.util.Log
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.model.Eventos

class EventosService(private val dataBase: DataBase) {

    fun insertEvento(idEventos: Int, idPlantilla: Int, idStatus: Int, materia: String, parteCuerpo: String, descripcion: String, comida: String, lugar: String, fechaInicial: String, fechaRecordatorio: String, timer: String): Long{
        val db = dataBase.writableDatabase

        val values = ContentValues().apply {
            put("id_eventos", idEventos)
            put("id_plantilla", idPlantilla)
            put("id_status", idStatus)
            put("materia", materia)
            put("parte_cuerpo", parteCuerpo)
            put("descripcion", descripcion)
            put("comida", comida)
            put("lugar", lugar)
            put("fecha_inicial", fechaInicial)
            put("fecha_recordatorio", fechaRecordatorio)
            put("timer", timer)
        }
        var status: Long = 0

     try {
         status = db.insert("eventos", null, values)
         Log.d("insertEvento", "Se insertó correctamente")


     } catch (e: Exception){
         Log.d("Error al insertar", e.toString())
     } finally {
         db.close()
     }
        return status
    }


}