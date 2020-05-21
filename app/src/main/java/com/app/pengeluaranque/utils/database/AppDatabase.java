package com.app.pengeluaranque.utils.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.app.pengeluaranque.model.entity.Pengeluaran;
import com.app.pengeluaranque.utils.database.daos.PengeluaranDao;

@Database(entities = {Pengeluaran.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PengeluaranDao pengeluaranDao();
}
