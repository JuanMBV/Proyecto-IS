package com.team1.proyecto_is.service
import android.content.ContentValues
import android.util.Log
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.model.Status

class PlantillaService(private val dataBase: DataBase) {

    fun InsertPlantilla(nombre: String): Long{
        val db = dataBase.writableDatabase

        val values = ContentValues().apply {
            put("nombre", nombre)
        }
        var status: Long = 0

        try {
           status = db.insert("Plantillas", null ,values)
            Log.d("InsertPlantilla", "Se insertó correctamente")
        } catch (e: Exception){
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }

    fun SelectNamePlantilla(): List<String>{
        val db = dataBase.writableDatabase
        val listNombre : MutableList<String> = mutableListOf()

        try {
            db.rawQuery("SELECT nombre FROM plantillas", null).use { cursor ->
                while (cursor.moveToNext()) {
                    listNombre.add(cursor.getString(0))
                }
            }
        } catch (e: Exception) {
            Log.d("Error al consultar", e.toString())
        } finally {
            db.close()
        }
        return listNombre
    }

    fun ExistePlantilla(nombre: String): Boolean {
        val db = dataBase.readableDatabase
        val query = "SELECT COUNT(*) FROM plantillas WHERE nombre = ?"
        val cursor = db.rawQuery(query, arrayOf(nombre))

        cursor.use {
            if (it.moveToFirst()) {
                val count = it.getInt(0)
                return count > 0
            }
        }

        return false
    }
}