package com.team1.proyecto_is.service
import android.content.ContentValues
import android.util.Log
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.model.Plantillas

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

    fun SelectPlantilla(idPlantilla: Int): Plantillas{
        val db = dataBase.readableDatabase
        var plantilla = Plantillas()
        db.rawQuery("SELECT * FROM plantillas WHERE id_plantilla = $idPlantilla", null).use { cursor ->
            while(cursor.moveToNext()){
                cursor.moveToFirst()
                plantilla.setIdPlantilla(cursor.getInt(0))
                plantilla.setNombre(cursor.getString(1))
            }
        }
        return plantilla
    }

    fun SelectNamePlantilla(): List<String>{
        val db = dataBase.readableDatabase
        val listNombre : MutableList<String> = mutableListOf()

        db.rawQuery("SELECT nombre FROM plantillas", null).use { cursor ->
            while (cursor.moveToNext()) {
                listNombre.add(cursor.getString(0))
            }
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