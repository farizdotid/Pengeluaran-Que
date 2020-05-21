package com.app.pengeluaranque.utils.database.daos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.app.pengeluaranque.model.entity.Pengeluaran;

import java.util.List;

@Dao
public interface PengeluaranDao {
    @Query("SELECT * FROM tbl_pengeluaran")
    LiveData<List<Pengeluaran>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(Pengeluaran... pengeluarans);

    @Query("DELETE FROM tbl_pengeluaran")
    void deleteAllData();

    @Query("DELETE FROM tbl_pengeluaran WHERE uid= :uid")
    void deleteSingleData(int uid);

    @Query("SELECT SUM(harga) FROM tbl_pengeluaran")
    LiveData<Integer> getTotalPrice();

    @Query("UPDATE tbl_pengeluaran SET keterangan = :keterangan, harga = :harga WHERE uid = :uid")
    void updateData(String keterangan, int harga, int uid);
}
