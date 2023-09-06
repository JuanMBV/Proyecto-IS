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
                    + "id_eventos INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "id_plantilla INTEGER,"
                    + "id_status INTEGER,"
                    + "materia TEXT,"
                    + "parte_cuerpo TEXT,"
                    + "descripcion TEXT,"
                    + "comida TEXT,"
                    + "lugar TEXT,"
                    + "fecha_inicial TEXT,"
                    + "fecha_recordatorio TEXT,"
                    + "timer TEXT,"
                    + "FOREIGN KEY(id_plantilla) REFERENCES plantillas(id_plantilla),"
                    + "FOREIGN KEY(id_status) REFERENCES status(id_status)"
                    + ")"
        )

        // Crear la tabla Estatus
        db.execSQL(
            "CREATE TABLE status ("
                    + "id_status INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "status INTEGER default 0,"
                    + "fecha_registro TEXT NOT NULL"
                    + ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Manejar actualizaciones de la base de datos si es necesario
        db.execSQL("DROP TABLE IF EXISTS plantillas")
        db.execSQL("DROP TABLE IF EXISTS eventos")
        db.execSQL("DROP TABLE IF EXISTS status")
        onCreate(db)
    }
}