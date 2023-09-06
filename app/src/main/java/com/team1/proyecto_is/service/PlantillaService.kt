package com.team1.proyecto_is.service
import android.content.ContentValues
import android.util.Log
import com.team1.proyecto_is.DAO.DataBase
import com.team1.proyecto_is.model.Status

class PlantillaService(private val dataBase: DataBase) {

    fun insertPlantilla(idPlantilla: Int, nombre: String): Long{
        val db = dataBase.writableDatabase

        val values = ContentValues().apply {
            put("id_plantilla", idPlantilla)
            put("nombre", nombre)
        }
        var status: Long = 0

        try {
           status = db.insert("Plantillas", null ,values)
            Log.d("inserPLantilla", "Se insertó correctamente")


        } catch (e: Exception){
            Log.d("Error al insertar", e.toString())
        } finally {
            db.close()
        }
        return status
    }

    fun SelectPlantilla(id: Int): List<String>{
        val db = dataBase.writableDatabase
        val listNombre : MutableList<String> = mutableListOf()

        db.rawQuery("Select nombre from plantillas", null).use{cursor ->
            while (cursor.moveToNext()){
               listNombre.add(cursor.getString(0))
            }

        }
        return listNombre
    }
}