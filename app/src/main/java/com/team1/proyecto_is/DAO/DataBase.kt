package com.team1.proyecto_is.DAO

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBase(context: Context?): SQLiteOpenHelper(context, "Ahora_Si.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        // Crear la tabla Plantillas
        db.execSQL(
            "CREATE TABLE plantillas ("
                    + "id_plantilla INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre TEXT NOT NULL"
                    + ")"
        )

        // Crear la tabla Eventos
        db.execSQL(
            "CREATE TABLE eventos ("
                    + "id_evento INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "id_plantilla INTEGER,"
                    + "materia TEXT,"
                    + "parte_cuerpo TEXT,"
                    + "descripcion TEXT,"
                    + "comida TEXT,"
                    + "lugar TEXT,"
                    + "status INTEGER DEFAULT 0,"
                    + "fecha_registro DATETIME,"
                    + "fecha_inicial DATETIME,"
                    + "fecha_final DATETIME,"
                    + "timer TEXT,"
                    + "FOREIGN KEY(id_plantilla) REFERENCES plantillas(id_plantilla)"
                    + ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Manejar actualizaciones de la base de datos si es necesario
        db.execSQL("DROP TABLE IF EXISTS plantillas")
        db.execSQL("DROP TABLE IF EXISTS eventos")
        onCreate(db)
    }
}