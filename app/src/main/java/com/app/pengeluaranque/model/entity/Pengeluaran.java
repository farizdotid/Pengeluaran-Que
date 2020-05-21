package com.app.pengeluaranque.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_pengeluaran")
public class Pengeluaran implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "keterangan")
    public String keterangan;

    @ColumnInfo(name = "harga")
    public int harga;

    public Pengeluaran() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.keterangan);
        dest.writeInt(this.harga);
    }

    protected Pengeluaran(Parcel in) {
        this.uid = in.readInt();
        this.keterangan = in.readString();
        this.harga = in.readInt();
    }

    public static final Creator<Pengeluaran> CREATOR = new Creator<Pengeluaran>() {
        @Override
        public Pengeluaran createFromParcel(Parcel source) {
            return new Pengeluaran(source);
        }

        @Override
        public Pengeluaran[] newArray(int size) {
            return new Pengeluaran[size];
        }
    };
}
